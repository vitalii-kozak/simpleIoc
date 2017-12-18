package ioc;

import proxy.BenchmarkProxyHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class SimpleIoc {

    private Config config;
    private Map <String, Object> container  = new HashMap<>();

    public SimpleIoc(Config config) {
        //container = new HashMap<>();
        this.config = config;
        checkUniqueBeanNames();
    }

    private void checkUniqueBeanNames() {
        Set<String> beanNames = new HashSet<>(config.beanNames());
        if (beanNames.size() < config.beanNames().size()) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> beanDefenitions() {
        return config.beanNames();
    }

    public Object getBean(String beanName) {

        if (container.containsKey(beanName)) {
            return container.get(beanName);
        }
        BeanDefenition beanDefinition = config.beanDefinition(beanName);
        try {
            Class beanClass = beanDefinition.getBeanClass();
            Object instanceOfBean = createBean(beanName);
            checkNullBean(instanceOfBean);
            Object newBean = createBenchmarkProxy(instanceOfBean);
            container.put(beanName, newBean);
            //call init
            callInitMethod(newBean);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return container.get(beanName);
    }

    private Object createBenchmarkProxy(Object bean) {
        if (isAnnotatedMethodPresentInBean(bean, Benchmark.class)) {
            return wrapBeanWithBenchmarkProxy(bean);
        }
        return bean;
    }


    private boolean isAnnotatedMethodPresentInBean(Object bean, Class clazz) {
        for (Method method: bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(clazz)) {
                return true;
            }
        }
        return false; // there is no clazz annotation present in this bean
    }


    private Object wrapBeanWithBenchmarkProxy(Object bean) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class[] interfaces = bean.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, new BenchmarkProxyHandler(bean));
    }

//    private Object createBenchmarkProxy(Object bean) {
//        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
//                bean.getClass().getInterfaces(),
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println(method.getName() + " is called");
//                        return method.invoke(bean, args);
//                    }
//                });
//
//    }

    private void callInitMethod(Object bean) throws Exception {
        try {
            Method method = bean.getClass().getDeclaredMethod("init");
            method.invoke(bean);
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        }
    }

    private Method gitInitMethod(Object bean) {
        try {
            return bean.getClass().getMethod("init");
        } catch (NoSuchMethodException ex){
            return null;
        }
    }


    private Object createBean(String beanName) throws Exception{
            Class<?> classOfBean = getBeanClass(beanName);
            if (classOfBean.getDeclaredConstructors()[0].getParameterCount() == 0) {
                Object bean = classOfBean.newInstance();
                return bean;
            }

            Constructor<?> constructor = classOfBean.getDeclaredConstructors()[0];
            return createBeanWithConstructor(constructor);
    }

     private Object createBeanWithConstructor(Constructor<?> constructor) throws Exception {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parametersForConstructor = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Object parameterObject = createParameterForConstructor(parameterTypes[i]);
            parametersForConstructor[i] = parameterObject;
        }

        return constructor.newInstance(parametersForConstructor);
    }

    private Object createParameterForConstructor(Class<?> parameterType) {
        String parameterClassName = parameterType.getSimpleName();
        parameterClassName = Character.toLowerCase(parameterClassName.charAt(0)) + parameterClassName.substring(1);
        return getBean(parameterClassName);
    }

    private Class<?> getBeanClass(String beanName) {
        BeanDefenition beanDefinition = config.beanDefinition(beanName);
        return beanDefinition.getBeanClass();
    }

    private void checkNullBean(Object instanceOfBean) {
        if (instanceOfBean == null) {
            throw new IllegalArgumentException();
        }
    }
}

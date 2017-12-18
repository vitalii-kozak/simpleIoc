package ioc;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleIocTest {

    private Config config = new Config() {
        @Override
        public List<String> beanNames() {
            return Collections.emptyList();
        }

        @Override
        public BeanDefenition beanDefinition(String beanName) {
            return null;
        }
    };

    @Test
    public void createIoc() {
      new SimpleIocTest();
    }

    @Test
    public void createIoCContainer() {
        new SimpleIoc(config);
    }

    @Test
    public void beanDefShouldBeEmpty() {
        SimpleIoc simpleIoc = new SimpleIoc(config);
        List<String> beanDefenitions = simpleIoc.beanDefenitions();
        assertEquals(Collections.emptyList(), beanDefenitions);
    }

    @Test
    public void beanDefWithOneBeanInConfig() {
        String beanName = "bean1";

        Config config = new Config(){

            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName);
            }

            @Override
            public BeanDefenition beanDefinition(String beanName) {
                return new BeanDefenition() {
                    @Override
                    public String getBeanName() {
                        return beanName;
                    }

                    @Override
                    public Class<?> getBeanClass() {
                        return TestBean.class;
                    }
                };
            }
        };
        SimpleIoc simpleIoc = new SimpleIoc(config);
        List<String> beanDefenitions = simpleIoc.beanDefenitions();


        assertEquals(Arrays.asList(beanName), beanDefenitions);
    }

    @Test
    public void beanDefWithSeveralBeanInConfig() {
        String beanName1 = "bean1";
        String beanName2 = "bean2";

        Config config = new Config(){

            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName1, beanName2);
            }

            @Override
            public BeanDefenition beanDefinition(String beanName) {
                return null;
            }
        };
        SimpleIoc simpleIoc = new SimpleIoc(config);
        List<String> beanDefenitions = simpleIoc.beanDefenitions();


        assertEquals(Arrays.asList(beanName1, beanName2), beanDefenitions);
    }


    @Test(expected = IllegalArgumentException.class)
    public void beanNameInConfigShouldBeUniq() {
        String beanName1 = "bean1";
        String beanName2 = "bean1";

        Config config = new Config(){

            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName1, beanName2);
            }

            @Override
            public BeanDefenition beanDefinition(String beanName) {
                return new BeanDefenition() {
                    @Override
                    public String getBeanName() {
                        return beanName;
                    }

                    @Override
                    public Class<?> getBeanClass() {
                        return TestBean.class;
                    }
                };
            }
        };
        SimpleIoc simpleIoc = new SimpleIoc(config);

    }

    @Test
    public void getBeanWithOneBeanInConfig() {

        String beanName = "beanName";
        Class<?> beanClass = TestBean.class;

        Config config = new Config(){

            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName);
            }

            @Override
            public BeanDefenition beanDefinition(String beanName) {
                return new BeanDefenition() {
                    @Override
                    public String getBeanName() {
                        return beanName;
                    }

                    @Override
                    public Class<?> getBeanClass() {
                        return beanClass;
                    }
                };
            }
        };
        SimpleIoc simpleIoc = new SimpleIoc(config);
        TestBean testBean = (TestBean) simpleIoc.getBean(beanName);


        assertNotNull(testBean);
    }

    @Test
    public void getSameBeanSeveralTimes() {
        String beanName1 = "bean1";

        Config config = new Config(){

            @Override
            public List<String> beanNames() {
                return Arrays.asList(beanName1);
            }

            @Override
            public BeanDefenition beanDefinition(String beanName) {
                return new BeanDefenition() {
                    @Override
                    public String getBeanName() {
                        return beanName;
                    }

                    @Override
                    public Class<?> getBeanClass() {
                        return TestBean.class;
                    }
                };
            }
        };

        SimpleIoc simpleIoc = new SimpleIoc(config);
        TestBean testBean1 = (TestBean) simpleIoc.getBean("bean1");
        TestBean testBean2 = (TestBean) simpleIoc.getBean("bean1");

        assertSame(testBean1, testBean2);
    }

    @Test
    public void getBeanWithDependencies() {
        String testBeanName = "testBean";
        Class<?> testBeanClass = TestBean.class;

        String dependentBeanName = "testBeanWithDependency";
        Class<?> dependentBeanClass = TestBeanWithDependency.class;

        Config config = new Config() {
            @Override
            public List<String> beanNames() {
                return Arrays.asList(testBeanName, dependentBeanName);
            }

            @Override
            public BeanDefenition beanDefinition(String beanName) {

                Map<String, BeanDefenition> beanDefinitions = new HashMap<>();
                beanDefinitions.put(testBeanName,
                        new BeanDefenition() {
                            @Override
                            public String getBeanName() {
                                return testBeanName;
                            }

                            @Override
                            public Class<?> getBeanClass() {
                                return testBeanClass;
                            }
                        }
                );

                beanDefinitions.put(dependentBeanName,
                        new BeanDefenition() {
                            @Override
                            public String getBeanName() {
                                return dependentBeanName;
                            }

                            @Override
                            public Class<?> getBeanClass() {
                                return dependentBeanClass;
                            }
                        }
                );

                return beanDefinitions.get(beanName);
            }
        };

        SimpleIoc simpleIoc = new SimpleIoc(config);
        TestBeanWithDependency testBeanWithDependency = (TestBeanWithDependency) simpleIoc.getBean(dependentBeanName);
        TestBean testBean = testBeanWithDependency.testBean;

        assertNotNull(testBean);
    }


    static class TestBean{};
    static class TestBeanWithDependency {

        final TestBean testBean;

        TestBeanWithDependency(TestBean testBean) {
            this.testBean = testBean;
        }
    };
}
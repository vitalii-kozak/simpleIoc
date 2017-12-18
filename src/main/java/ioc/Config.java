package ioc;

import java.util.List;

public interface Config {
    List<String> beanNames();
    BeanDefenition beanDefinition(String beanName);
}

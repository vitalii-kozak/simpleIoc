package ioc;

import app.repositiry.RepoBean;
import app.repositiry.RepoBeanInterface;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SimpleIocRunner {
    public static void main(String[] args) throws Exception{
        Map<String, Class<?>> beanDescription =
                new HashMap<String, Class<?>>() {{
                    put("repoBean", RepoBean.class);
                    //put("repoBean", RepoBean.class);
        }};
        Config config = new JavaConfig(beanDescription);

        //"repoBean"
        //
        SimpleIoc ioC = new SimpleIoc(config);
        RepoBeanInterface repoBean = (RepoBeanInterface) ioC.getBean("repoBean");
        repoBean.calculate();
    }
}

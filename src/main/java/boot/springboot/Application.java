package boot.springboot;

import boot.springboot.communication.Communication;
import boot.springboot.config.RESTConfig;
import boot.springboot.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RESTConfig.class);
//        Communication communication = context.getBean("communication", Communication.class);
//        List<User> list = communication.getAllUsers();
//        System.out.println(list);
    }

}

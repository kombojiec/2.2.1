package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 5)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Mazda", 3)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Mercedes", 600)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Lada", 0)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Lada", 0));

      context.close();
   }
}

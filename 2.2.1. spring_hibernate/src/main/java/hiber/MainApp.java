package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);



      UserService userService = context.getBean(UserService.class);



      List<Car> cars = userService.listCars();
      for(Car car : cars) {
         System.out.println("cars_id = " + car.getId());
         System.out.println("model = " + car.getModel());
         System.out.println("series = " + car.getSeries());
         System.out.println();
      }



      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car( "BMW", 1985)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("OPEL",2020)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("VESTA",2023)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("MERSEDES",1999)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW",   1985));
      context.close();
   }
}

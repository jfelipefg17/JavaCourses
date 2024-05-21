package com.springBootReactive.app;

import com.springBootReactive.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class FirstReactiveProjectApplication implements CommandLineRunner {


  //with this we also print in the log, not just at the terminal
  private static final Logger log = LoggerFactory.getLogger(FirstReactiveProjectApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(FirstReactiveProjectApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {

    iterableExample();
    iterableExampleFlatMap();
  }

  public void iterableExampleFlatMap() throws Exception {

    List<String> nameList = new ArrayList<>();
    nameList.add("Andres df");
    nameList.add("Pedro Fulano");
    nameList.add("Diego lkjhj");
    nameList.add("Juan ad");
    nameList.add("Bruce Lee");
    nameList.add("Bruce Willis");

    // this is a publisher that means an observer
    Flux.fromIterable(nameList)
            .map(name -> new User(name.split(" ")[0].toUpperCase(), name.split(" ")[1].toUpperCase()))
            .flatMap(user -> {
              if (user.getName().equalsIgnoreCase("bruce")) {
                return Mono.just(user);
              } else{
                return Mono.empty();
              }
            })
            .map(user -> {
              String name = user.getName().toLowerCase();
              user.setName(name);
              String lastName = user.getLastName().toLowerCase();
              user.setLastName(lastName);
              return user;
            })
          .subscribe(u -> log.info(u.toString()));
  }



  public void iterableExample() throws Exception {

    List<String> nameList = new ArrayList<>();
    nameList.add("Andres df");
    nameList.add("Pedro Fulano");
    nameList.add("Diego lkjhj");
    nameList.add("Juan ad");
    nameList.add("Bruce Lee");
    nameList.add("Bruce Willis");

    // this is a publisher that means an observer
    Flux<String> names =  Flux.fromIterable(nameList) /*Flux.just("Andres df", "Pedro Fulano", "Diego lkjhj", "Juan ad", "Bruce Lee", "Bruce Willis")*/;
    Flux<User> users = names.map(name -> new User(name.split(" ")[0].toUpperCase(), name.split(" ")[1].toUpperCase()))
            .filter(user -> user.getName().toLowerCase().equals("bruce"))
            .doOnNext(user -> {
              if (user == null) {
                throw new RuntimeException("The name cant be empty");
              }
              System.out.println(user.getName().concat(" ").concat(user.getLastName()));
            })
            // map to do something and change the flow the type of the data or the change to an entity
            .map(user -> {
              String name = user.getName().toLowerCase();
              user.setName(name);
              String lastName = user.getLastName().toLowerCase();
              user.setLastName(lastName);
              return user;
            });

    // without this subscription nothing will print
    users.subscribe(e -> log.info(e.toString()),
            error -> log.error(error.getMessage()),
            //if everything is good and finish the subscription then do something, this is called onComplete
            new Runnable() {
              @Override
              public void run() {
                log.info("We finish great");
              }
            });
  }
}

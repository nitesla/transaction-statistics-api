package com.seerbit.transactionstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionStatsApplication {

  // implements CommandLineRunner{

//   final ConcurrentHashMap<String, Transaction> dataStore = null;

//   @Autowired
//   private ApplicationContext appContext;

  //   @Override
  //   public void run(String... args) throws Exception {

  // 	  String[] beans = appContext.getBeanDefinitionNames();
  // 	  Arrays.sort(beans);
  // 	  for (String bean : beans) {
  // 		  System.out.println(bean);
  // 	  }

  //   }

  public static void main(String[] args) {
    SpringApplication.run(TransactionStatsApplication.class, args);
  }
}

package portfolio.portfoliobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import portfolio.portfoliobackend.model.User;

import java.util.HashMap;

@SpringBootApplication
public class PortfolioBackendApplication {
	public static HashMap<String, User> users ;
	public static void main(String[] args) {
		users= new HashMap<>();
		users.put("madhu-lakkoju",new User("madhu.ml193@gmail.com","madhu-lakkoju"));
		users.put("sravani-lakkoju",new User("lakkojusravani@gmail.com","sravani-lakkoju"));
		users.put("abhilash-kothuru",new User("kothuruabhilashreddy@gmail.com","abhilash-kothuru"));
		users.put("karthik-chalamalasetti",new User("kch25111999@gmail.com","karthik-chalamalasetti"));
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}

}

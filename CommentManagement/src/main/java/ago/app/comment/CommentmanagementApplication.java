package ago.app.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CommentmanagementApplication {

	public static void main(String[] args) {
		System.out.println("Run start");
		SpringApplication.run(CommentmanagementApplication.class, args);
		System.out.println("Running success");
	}
	@RequestMapping("/a")
	public String alive()
	{
		return "I am alive";
	}
}

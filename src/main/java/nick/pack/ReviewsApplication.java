package nick.pack;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewsApplication {
	private final static Logger logger = Logger.getLogger(ReviewsApplication.class);

	public static void main(String[] args) {
		logger.debug("run server program\nHello World!!!");
		SpringApplication.run(ReviewsApplication.class, args);
	}
}

package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args); //SpringApplication.run 의 매개변수로 클래스 이름 적어주고 실행하면 어플리케이션 띄워짐.
	}

}

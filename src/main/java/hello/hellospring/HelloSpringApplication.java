package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"hello.hellospring", "hello.scantest"}) //애플리케이션의 main()메서드가 구현되어있는 패키지 외부의 컴포넌트를 스캔하기 위해 스캔할 패키지 범위를 설정해준다.
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args); //SpringApplication.run 의 매개변수로 클래스 이름 적어주고 실행하면 어플리케이션 띄워짐.
	}

}

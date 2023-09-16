package sh.hellospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // springboot 는 자체 내장 톰캣 가지고 있다.
public class HelloSpringbootApplication {
	// java 특: main method로부터 모든 게 시작됨
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringbootApplication.class, args);
	}
}

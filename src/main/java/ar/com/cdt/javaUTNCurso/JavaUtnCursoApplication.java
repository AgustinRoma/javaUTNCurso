package ar.com.cdt.javaUTNCurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JavaUtnCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaUtnCursoApplication.class, args);
	}

}

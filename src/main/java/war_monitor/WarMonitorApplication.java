package war_monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 이 어노테이션 하나로 ComponentScan + 자동설정 다 됨
// main() 실행하면 Tomcat 켜지고 모든 빈 등록됨
public class WarMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarMonitorApplication.class, args);
	}
}

package ioteko.eurekodiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekoDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekoDiscoveryServerApplication.class, args);
	}

}

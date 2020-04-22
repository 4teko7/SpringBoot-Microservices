package ioteko.springbootconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

	@Autowired
	private DBSettings dbSettings;
	
	
	@GetMapping("/helloWord")
	public String helloWord() {return dbSettings.getConnection() + dbSettings.getHost() + dbSettings.getPort();}
}

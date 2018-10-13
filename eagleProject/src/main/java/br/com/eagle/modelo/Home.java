package br.com.eagle.modelo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@RequestMapping("/")
	public String home() {
		//TENTATIVA DE USAR PAGE HTML
		return "Eagle - Spring boot e RestAssured";
	}

}
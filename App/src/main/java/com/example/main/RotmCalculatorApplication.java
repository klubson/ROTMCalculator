package com.example.main;

import com.example.login.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class RotmCalculatorApplication {
	public static void main(String[] args) throws URISyntaxException, IOException {
		new RotmCalculatorApplication();
	}

	public RotmCalculatorApplication() throws URISyntaxException, IOException {
		new Login().setVisible(true);
	}

}

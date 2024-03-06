package com.example.main;

import com.example.login.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class RotmCalculatorApplication extends JFrame {

	public static void main(String[] args) throws URISyntaxException, IOException {
		new RotmCalculatorApplication();
	}

	public RotmCalculatorApplication() throws URISyntaxException, IOException {
		Login loginPanel = new Login();
		add(loginPanel);
		setSize(new Dimension(600, 500));
		setTitle("Redaktor of The Month Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

}

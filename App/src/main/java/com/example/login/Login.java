package com.example.login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Login extends JPanel {

    JPanel imgPanel;
    JPanel form;
    JLabel usernameLabel;
    JTextField usernameTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton showPasswordButton;
    JButton loginButton;
    JButton forgotPasswordButton;

    Boolean passwordShown = false;

    public Login() throws URISyntaxException, IOException {
        initUI();
        initActionListeners();
    }

    private void initUI() throws URISyntaxException, IOException {

        imgPanel = new JPanel();
        URL res = getClass().getClassLoader().getResource("loginPanelChelseaPolandImage.jpg");
        File file = Paths.get(res.toURI()).toFile();

        Image image = ImageIO.read(file).getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imgPanel.add(imageLabel);

        form = new JPanel();

        usernameLabel = new JLabel("Login: ");
        usernameTextField = new JTextField(20);
        passwordLabel = new JLabel("Hasło: ");
        passwordField = new JPasswordField(20);
        showPasswordButton = new JButton();
        loginButton = new JButton("Zaloguj");
        forgotPasswordButton = new JButton("Zapomniałem hasła");

        form.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        form.add(usernameLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        form.add(usernameTextField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        form.add(passwordLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        form.add(passwordField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        showPasswordButton.setSize(10, 10);

        res = getClass().getClassLoader().getResource("showPassword.jpg");
        file = Paths.get(res.toURI()).toFile();
        image = ImageIO.read(file).getScaledInstance(showPasswordButton.getWidth(), showPasswordButton.getHeight(), Image.SCALE_SMOOTH);
        showPasswordButton.setIcon(new ImageIcon(image));

        form.add(showPasswordButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        form.add(loginButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        form.add(forgotPasswordButton, gridBagConstraints);

        add(imgPanel);
        add(form);

    }

    private void initActionListeners(){
        showPasswordButton.addActionListener(e -> {
            String imagePath;
            if(passwordShown){
                passwordField.setEchoChar('*');
                imagePath = "showPassword.jpg";
            } else {
                passwordField.setEchoChar((char) 0);
                imagePath = "hidePassword.jpg";
            }
            passwordShown = !passwordShown;
            URL res = getClass().getClassLoader().getResource(imagePath);
            File file;
            try {
                file = Paths.get(res.toURI()).toFile();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            Image image;
            try {
                image = ImageIO.read(file).getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showPasswordButton.setIcon(new ImageIcon(image));
        });
        loginButton.addActionListener(e -> {

        });
        forgotPasswordButton.addActionListener(e -> {

        });


    }



}

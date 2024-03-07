package com.example.login;

import com.example.mainmenu.MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Login extends JFrame {
    JPanel container;
    JPanel imgPanel;
    JPanel form;
    JLabel usernameLabel;
    JTextField usernameTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton showPasswordButton;
    JButton loginButton;
    JButton forgotPasswordButton;
    JButton registerButton;

    Boolean passwordShown = false;

    public Login() throws URISyntaxException, IOException {
        initUI();
        initActionListeners();
    }

    private void initUI() throws URISyntaxException, IOException {

        this.setSize(new Dimension(600, 500));
        this.setTitle("Redaktor of The Month Calculator");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

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
        registerButton = new JButton("Zarejestruj się");

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
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
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
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        form.add(loginButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        form.add(forgotPasswordButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        form.add(registerButton, gridBagConstraints);

        container.add(imgPanel);
        container.add(form);

        add(container);

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
        AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    dispose();
                    new MainMenu().setVisible(true);

                } catch (URISyntaxException | IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        };
        loginButton.addActionListener(buttonPressed);
        loginButton.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter_pressed");
        loginButton.getActionMap().put("Enter_pressed", buttonPressed);
        forgotPasswordButton.addActionListener(e -> {

        });
        registerButton.addActionListener(e -> {

        });

    }



}

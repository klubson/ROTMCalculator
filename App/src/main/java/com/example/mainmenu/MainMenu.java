package com.example.mainmenu;

import com.example.login.Login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class MainMenu extends JFrame {
    JPanel container;
    JPanel imgPanel;
    JButton logoutButton;
    JPanel form;

    public MainMenu() throws URISyntaxException, IOException {
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

        form = new JPanel();
        logoutButton = new JButton("Wyloguj się");

        imgPanel = new JPanel();
        URL res = getClass().getClassLoader().getResource("loginPanelChelseaPolandImage.jpg");
        File file = Paths.get(res.toURI()).toFile();

        Image image = ImageIO.read(file).getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imgPanel.add(imageLabel);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        form.add(logoutButton, gridBagConstraints);

        container.add(imgPanel);
        container.add(form);

        add(container);

    }

    private void initActionListeners(){
        logoutButton.addActionListener(e -> {
            dispose();
            try {
                new Login().setVisible(true);
            } catch (URISyntaxException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}

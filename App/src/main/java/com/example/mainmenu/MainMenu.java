package com.example.mainmenu;

import com.example.login.Login;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenu extends JFrame {
    JPanel container;
    JPanel sidePanel;
    SideMenuPanel sideMenuPanel;
    JButton sideMenuButton;
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
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));

        sidePanel = new JPanel();
        sidePanel.setBackground(Color.RED);
        form = new JPanel();

        sideMenuPanel = new SideMenuPanel(this, container, sidePanel);

        logoutButton = new JButton("Wyloguj się");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        form.add(logoutButton, gridBagConstraints);

        container.add(sidePanel);
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

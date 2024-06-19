package com.example.mainmenu;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

@Getter
public class SideMenuPanel {

    private GroupLayout gl;
    private int x = 0;
    private final int a = 0;
    private Integer iconSize = 30;
    private int minWidth;
    private int maxWidth;
    private JPanel side;
    private JPanel buttonPanel;
    private JPanel main;
    private JLabel sideMenuOpenCloseButtonLabel;
    private JLabel sideMenuHomeButtonLabel;
    private JLabel sideMenuHomeLabel;
    private JLabel sideMenuMyProfileButtonLabel;
    private JLabel sideMenuMyProfileLabel;
    private boolean isEnabled;
    private int speed;
    private int responsiveMinWidth;
    private final JFrame frame;
    private boolean isOpen = false;
    public void setSpeed(int speed) {
        if (speed == 0) {
            speed = maxWidth;
        }
        this.speed = speed;
    }


    public SideMenuPanel(JFrame frame, JPanel main, JPanel side) throws URISyntaxException, IOException {

        frame.addComponentListener( new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent arg0) {
                x = 0;
            }
        });

        this.frame = frame;
        this.main = main;
        this.side = side;
        initUI();
        initListeners();
    }

    private void initUI() throws URISyntaxException, IOException {
        minWidth = 0;
        maxWidth = 100;
        responsiveMinWidth = 600;
        isEnabled = true;
        setSpeed(4);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        sideMenuOpenCloseButtonLabel = new JLabel();

        URL res = getClass().getClassLoader().getResource("sideMenuOpenCloseIcon.png");
        assert res != null;
        File file = Paths.get(res.toURI()).toFile();

        Image image = ImageIO.read(file).getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        sideMenuOpenCloseButtonLabel = new JLabel(new ImageIcon(image));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        buttonPanel.add(sideMenuOpenCloseButtonLabel, gridBagConstraints);

        sideMenuHomeButtonLabel = new JLabel();
        res = getClass().getClassLoader().getResource("sideMenuHomeIcon.png");
        assert res != null;
        file = Paths.get(res.toURI()).toFile();

        image = ImageIO.read(file).getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        sideMenuHomeButtonLabel = new JLabel(new ImageIcon(image));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        buttonPanel.add(sideMenuHomeButtonLabel, gridBagConstraints);

        sideMenuHomeLabel = new JLabel("Strona główna");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 30, 0, 30);
        buttonPanel.add(sideMenuHomeLabel, gridBagConstraints);

        sideMenuMyProfileButtonLabel = new JLabel();
        res = getClass().getClassLoader().getResource("sideMenuMyProfileIcon.png");
        assert res != null;
        file = Paths.get(res.toURI()).toFile();

        image = ImageIO.read(file).getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        sideMenuMyProfileButtonLabel = new JLabel(new ImageIcon(image));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        buttonPanel.add(sideMenuMyProfileButtonLabel, gridBagConstraints);

        sideMenuMyProfileLabel = new JLabel("Mój profil");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 30, 0, 30);
        buttonPanel.add(sideMenuMyProfileLabel, gridBagConstraints);

        side.add(buttonPanel);

    }

    private void initListeners() {
        sideMenuOpenCloseButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSideMenu();
            }
        });
    }

    public void onSideMenu() {
        int b = maxWidth % speed;
        if (x == maxWidth) {

            Thread th = new Thread(() -> {
                for (int i = maxWidth; i >= 0; i -= speed) {
                    try {
                        if (b == i) {
                            i = 0;
                        }
                        TimeUnit.NANOSECONDS.sleep(1);
                        side.setSize(new Dimension(minWidth + i, main.getHeight()));

                        if (side != null) {
                            for (Component child : side.getComponents()) {
                                child.setSize(new Dimension(maxWidth + minWidth, child.getHeight()));
                            }
                        }
                        if (frame.getWidth() >= responsiveMinWidth) {
                            if (isEnabled) {
                                main.setLocation(minWidth + i, main.getY());
                            }
                        }

                    } catch (NullPointerException e) {

                        System.out.println("Unknown Side or Main panel \n setSide() and setMain() ");
                        return;

                    } catch (InterruptedException ex) {
                        Logger.getLogger(SideMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            th.start();

            x = 0;
        } else if (x == 0) {
            Thread th = new Thread(() -> {
                for (int i = 0; i <= x; i += speed) {
                    try {

                        TimeUnit.NANOSECONDS.sleep(1);
                        if (maxWidth - b == i) {
                            i += b;
                        }

                        side.setSize(new Dimension(minWidth + i, main.getHeight()));

                        if (side != null) {
                            for (Component child : side.getComponents()) {
                                child.setSize(new Dimension(maxWidth + minWidth, child.getHeight()));
                            }
                        }
                        if (frame.getWidth() >= responsiveMinWidth) {
                            if (isEnabled) {
                                main.setLocation(minWidth + i, main.getY());
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("ERROR: Unknown value for setSide() or setMain()");

                        return;

                    } catch (InterruptedException ex) {
                        Logger.getLogger(SideMenuPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            th.start();
            x = maxWidth;
        }

    }
}

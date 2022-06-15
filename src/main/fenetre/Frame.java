package main.fenetre;

import com.sun.deploy.appcontext.AppContext;
import main.GamePanel;
import main.Main;

import javax.swing.*;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;

    public Frame(String title) {

        setTitle(title);
        setSize(150, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        JButton button = new JButton("Ouvrir le jeu");

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                Main.main(null);
                setVisible(false);
            }
        });

        add(button);

    }



}

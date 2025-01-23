package bank.management.System;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1, button2, button3;
    private JPanel backgroundPanel;

    private final Color[] colors = {
        new Color(173, 216, 230, 150),  
        new Color(255, 182, 193, 150),  
        new Color(255, 228, 225, 150),  
        new Color(224, 255, 255, 150),  
        new Color(173, 216, 230, 100), 
        new Color(144, 238, 144, 150),  
        new Color(255, 255, 224, 150),  
        new Color(255, 200, 124, 150),   
        new Color(255, 240, 245, 150),  
        new Color(240, 248, 255, 150),  
        new Color(224, 255, 255, 150),  
        new Color(250, 250, 210, 150),  
        new Color(245, 222, 179, 150)   
    };

    private int colorIndex = 0;  
    private float blendFactor = 0f;  

    private Color blendColors(Color color1, Color color2, float factor) {
        int red = (int) (color1.getRed() * (1 - factor) + color2.getRed() * factor);
        int green = (int) (color1.getGreen() * (1 - factor) + color2.getGreen() * factor);
        int blue = (int) (color1.getBlue() * (1 - factor) + color2.getBlue() * factor);
        int alpha = (int) (color1.getAlpha() * (1 - factor) + color2.getAlpha() * factor);
        return new Color(red, green, blue, alpha);
    }

    login() {
        super("ATM Interface");

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Check if g is an instance of Graphics2D before casting
                if (g instanceof Graphics2D) {
                    Graphics2D g2d = (Graphics2D) g;
                    int width = getWidth();
                    int height = getHeight();

                    // Calculate blended color
                    Color startColor = blendColors(colors[colorIndex], colors[(colorIndex + 1) % colors.length], blendFactor);
                    Color endColor = blendColors(colors[(colorIndex + 1) % colors.length], colors[(colorIndex + 2) % colors.length], blendFactor);

                    GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, width, height);
                }
            }
        };

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        // Timer to smoothly transition between colors
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blendFactor += 0.01f;  // Increase blend factor for smooth transition
                if (blendFactor >= 1f) {
                    blendFactor = 0f;  // Reset blend factor
                    colorIndex = (colorIndex + 1) % colors.length;  // Move to the next color
                }
                backgroundPanel.repaint();
            }
        });
        timer.start();


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 100, 100);
        add(image);

        // UI Component Initialization
        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 38));
        label1.setBounds(230, 125, 450, 40);
        add(label1);

        label2 = new JLabel("Card No : ");
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setBounds(150, 190, 375, 30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(325, 190, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField2);

        label3 = new JLabel("Pin : ");
        label3.setForeground(Color.BLACK);
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setBounds(150, 250, 375, 30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325, 250, 230, 30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        button1 = new JButton("Sign In");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.BLACK);
        // button1.setBackground(Color.pink);
        button1.setBounds(325, 300, 100, 30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("Clear");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.BLACK);
        // button2.setBackground(Color.pink);
        button2.setBounds(455, 300, 100, 30);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("Sign Up");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.BLACK);
        // button3.setBackground(Color.pink);
        button3.setBounds(325, 350, 230, 30);
        button3.addActionListener(this);
        add(button3);

        setLayout(null);
        setUndecorated(true);
        setSize(850, 480);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                coon c = new coon();
                String cardno = textField2.getText();
                String pin = passwordField3.getText();
                String q = "select * from login where card_no = '" + cardno + "' and pin = '" + pin + "'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()) {
                    setVisible(false);
                    new main(pin);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect card number or pin");
                }
            } else if (e.getSource() == button2) {
                textField2.setText("");
                passwordField3.setText("");
            } else if (e.getSource() == button3) {
                new Signup();
                setVisible(false);
            }
        } catch (Exception E) {
            // Handle exception
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new login();
    }
}

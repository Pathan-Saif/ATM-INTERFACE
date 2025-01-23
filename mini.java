package bank.management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener{

    String pin;
    JButton button;

    private final Color[] colors = {
        new Color(173, 216, 230, 150),  // Light sky blue
        new Color(255, 182, 193, 150),  // Light pink
        new Color(255, 228, 225, 150),  // Light rose
        new Color(224, 255, 255, 150),  // Light water
        new Color(173, 216, 230, 100),  // Light transparent water
        new Color(144, 238, 144, 150),  // Light green
        new Color(255, 255, 224, 150),  // Light yellow
        new Color(255, 200, 124, 150),   // Light orange
        new Color(255, 240, 245, 150),  // Lavender blush
        new Color(240, 248, 255, 150),  // Alice blue
        new Color(224, 255, 255, 150),  // Light cyan
        new Color(250, 250, 210, 150),  // Light goldenrod yellow
        new Color(245, 222, 179, 150)   // Wheat (light tan)
};

private int colorIndex = 0;  // To track the current base color
private float blendFactor = 0f;  // Blending between two colors for smooth transition

private Color blendColors(Color color1, Color color2, float factor) {
    int red = (int) (color1.getRed() * (1 - factor) + color2.getRed() * factor);
    int green = (int) (color1.getGreen() * (1 - factor) + color2.getGreen() * factor);
    int blue = (int) (color1.getBlue() * (1 - factor) + color2.getBlue() * factor);
    int alpha = (int) (color1.getAlpha() * (1 - factor) + color2.getAlpha() * factor);
    return new Color(red, green, blue, alpha);
}

    mini(String pin) {

        this.pin = pin;

        JPanel backgroundPanel;
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
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

        setSize(400, 600);
        setLocation(20, 20);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20, 140, 400, 200);
        add(label1);

        JLabel label2 = new JLabel("卩卂丅卄卂几");
        label2.setFont(new Font("System", Font.BOLD, 15));
        label2.setBounds(150, 20, 200, 20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20, 80, 300, 20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20, 400, 300, 20);
        add(label4);

        try {
            coon c = new coon();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin = '" + pin + "'");
            while (resultSet.next()) {
                label3.setText("Card Number : " + resultSet.getString("card_no").substring(0, 4) + "XXXXXXXX"
                        + resultSet.getString("card_no").substring(12));
            }

        } catch (Exception e) {
            e.getStackTrace();
        }

        try {
            int balance = 0;
            coon c = new coon();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '" + pin + "'");
            while (resultSet.next()) {
                label1.setText(label1.getText() + "<html>" + resultSet.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("amount") + "<br><br><br><html>");

                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
            label4.setText("Your Total Balance Is Rs " + balance);

        } catch (Exception e) {
            e.getStackTrace();
        }

        button = new JButton("Back");
        button.setBounds(20, 500, 100, 25);
        button.setBackground(Color.black);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        add(button);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main(pin);
    }

    public static void main(String[] args) {
        new mini("");
    }
}

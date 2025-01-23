package bank.management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener{

    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5,c6;
    JButton c,s;

    String formno;

    private JPanel backgroundPanel;

    // Define soft colors to transition between
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

    Signup3(String formno){

        this.formno = formno;

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

        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 5, 100, 100);
        add(image);

        JLabel l1 = new JLabel("Page 3");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(320, 40, 400, 40);
        add(l1);

        JLabel l2 = new JLabel("Account Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(280, 70, 400, 40);
        add(l2);

        JLabel l3 = new JLabel("Account Type :");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100, 140, 200, 30);
        add(l3);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        // r1.setBackground(new Color(215, 252, 252));
        r1.setBounds(100, 180, 150, 30);
        add(r1);

        r2 = new JRadioButton("Cuurent Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        // r2.setBackground(new Color(215, 252, 252));
        r2.setBounds(350, 180, 200, 30);
        add(r2);

        r3 = new JRadioButton("Fixed Deposit Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        // r3.setBackground(new Color(215, 252, 252));
        r3.setBounds(100, 220, 200, 30);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        // r4.setBackground(new Color(215, 252, 252));
        r4.setBounds(350, 220, 300, 30);
        add(r4);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);
        buttonGroup.add(r4);

        JLabel l4 = new JLabel("Card No : ");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 300, 200, 30);
        add(l4);

        JLabel l5 = new JLabel("(Your 16-digit card no)");
        l5.setFont(new Font("Raleway", Font.BOLD, 12));
        l5.setBounds(100, 330, 200, 20);
        add(l5);

        JLabel l6 = new JLabel("XXXX-XXXX-XXXX-4841");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(330, 300, 250, 30);
        add(l6);

        JLabel l7 = new JLabel("(It Would appear on atm card/cheque Book and Statement)");
        l7.setFont(new Font("Raleway", Font.BOLD, 12));
        l7.setBounds(330, 330, 500, 20);
        add(l7);

        JLabel l8 = new JLabel("Pin No : ");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 370, 200, 30);
        add(l8);

        JLabel l9 = new JLabel("(Your 4-digit Pin no)");
        l9.setFont(new Font("Raleway", Font.BOLD, 12));
        l9.setBounds(100, 400, 200, 20);
        add(l9);

        JLabel l10 = new JLabel("XXXX");
        l10.setFont(new Font("Raleway", Font.BOLD, 18));
        l10.setBounds(330, 370, 200, 30);
        add(l10);

        JLabel l11 = new JLabel("Services Required : ");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100, 450, 200, 30);
        add(l11);

        c1 = new JCheckBox("ATM CARD");
        // c1.setBackground(new Color(215,252, 252));
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 500, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        // c2.setBackground(new Color(215,252, 252));
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 500, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        // c3.setBackground(new Color(215,252, 252));
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 550, 200, 30);
        add(c3);

        c4 = new JCheckBox("Email Alerts");
        // c4.setBackground(new Color(215,252, 252));
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 550, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        // c5.setBackground(new Color(215,252, 252));
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 600, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        // c6.setBackground(new Color(215,252, 252));
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 600, 200, 30);
        add(c6);

        JCheckBox c7 = new JCheckBox("I here by decleares that the above entered details correct to the best of my knowledge.", true);
        // c7.setBackground(new Color(215,252, 252));
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 670, 600, 20);
        add(c7);

        JLabel l12 = new JLabel("Form No :");
        l12.setFont(new Font("Raleway", Font.BOLD, 14));
        l12.setBounds(700,10,100,30);
        add(l12);

        JLabel l13 = new JLabel(formno);
        l13.setFont(new Font("Raleway", Font.BOLD, 14));
        l13.setBounds(760,10,60,30);
        add(l13);

        s = new JButton("Submit");
        s.setFont(new Font("Raleway", Font.BOLD, 14));
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setBounds(250, 720, 100, 30);
        s.addActionListener(this);
        add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Raleway", Font.BOLD, 14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(420, 720, 100, 30);
        c.addActionListener(this);
        add(c);

        // getContentPane().setBackground(new Color(215, 252, 252));
        setSize(850, 800);
        setLayout(null);
        setLocation(400,20);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String atype = null;
        if (r1.isSelected()) {
            atype = "Saving Account";
        }
        else if(r2.isSelected()){
            atype = "Cuurent Account";
        }
        else if(r3.isSelected()){
            atype = "Fixed Deposit Account";
        }
        else if(r4.isSelected()){
            atype = "Recurring Deposit Account";
        }

        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 1409963000000000L;
        String cardno = "" + Math.abs(first7);

        long first3 = (ran.nextLong() % 9000L) + 1000L;
        String pin = "" + Math.abs(first3);

        String fac = "";
        if (c1.isSelected()) {
            fac += "ATM CARD";
        }
        else if (c2.isSelected()) {
            fac += "Internet Banking";
        }
        else if (c3.isSelected()) {
            fac += "Mobile Banking";
        }
        else if (c2.isSelected()) {
            fac += "Email Alerts";
        }
        else if (c2.isSelected()) {
            fac += "Cheque Book";
        }
        else if (c2.isSelected()) {
            fac += "E-Statement";
        }

        try {
            if (e.getSource()==s) {
                if (atype.equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                }
                else{

                    coon c1 = new coon();
                    String q1 = "insert into signupthree values('"+formno+"', '"+atype+"', '"+cardno+"', '"+pin+"', '"+fac+"')";
                    String q2 = "insert into login values('"+formno+"', '"+cardno+"', '"+pin+"')";
                    c1.statement.executeUpdate(q1);
                    c1.statement.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Card Number : " +cardno+ "\n Pin : " +pin);
                    new Deposit(pin);
                    setVisible(false);

                }
            }
            else if (e.getSource() == c) {
                System.exit(0);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        new Signup3("");
    }
}

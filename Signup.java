package bank.management.System;

import javax.swing.*;
import java.awt.*;
import java.util.Random;



import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener{

    JRadioButton r1,r2,m1,m2,m3;
    JButton next;
    JTextField textFieldClear;
    JPasswordField passwordclear;

    JTextField textName, textfName, textEmail, textAdd, textCity, textPin, textState;
    JDateChooser dateChooser;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    java.lang.String first = " " + Math.abs(first4);

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

    Signup(){

        super("APPLICATION FORM");

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
        backgroundPanel.setDoubleBuffered(true);

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blendFactor += 0.01f;  
                if (blendFactor >= 1f) {
                    blendFactor = 0f;  
                    colorIndex = (colorIndex + 1) % colors.length;  
                }
                backgroundPanel.repaint();
            }
        });
        timer.start();

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25, 10, 100, 100);
        add(image);

        JLabel label1 = new JLabel("APPLICATION FORM NO : " + first);
        label1.setBounds(160, 20, 600, 40);
        label1.setFont(new Font("Raleway", Font.BOLD, 38));
        add(label1);

        JLabel label2 = new JLabel("Page 1");
        label2.setBounds(330, 70, 600, 30);
        label2.setFont(new Font("Raleway", Font.BOLD, 22));
        add(label2);

        JLabel label3 = new JLabel("Personal Details");
        label3.setBounds(290, 100, 600, 30);
        label3.setFont(new Font("Raleway", Font.BOLD, 22));
        add(label3);

        JLabel labelName = new JLabel("Name : ");
        labelName.setBounds(100, 190, 100, 30);
        labelName.setFont(new Font("Raleway", Font.BOLD, 22));
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD, 14));
        textName.setBounds(300, 190, 400, 30);
        add(textName);

        JLabel labelfName = new JLabel("Father's Name : ");
        labelfName.setBounds(100, 240, 200, 30);
        labelfName.setFont(new Font("Raleway", Font.BOLD, 22));
        add(labelfName);

        textfName = new JTextField();
        textfName.setFont(new Font("Raleway", Font.BOLD, 14));
        textfName.setBounds(300, 240, 400, 30);
        add(textfName);

        JLabel DOB = new JLabel("Date Of Birth : ");
        DOB.setBounds(100, 290, 200, 30);
        DOB.setFont(new Font("Raleway", Font.BOLD, 22));
        add(DOB);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105,105,105));
        dateChooser.setBounds(300, 290, 400, 30);
        add(dateChooser);

        JLabel labelG = new JLabel("Gender : ");
        labelG.setBounds(100, 340, 200, 30);
        labelG.setFont(new Font("Raleway", Font.BOLD, 22));
        add(labelG);

        r1 = new JRadioButton("Male");
        // r1.setBackground(new Color(222, 225, 228));
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBounds(300, 340, 80, 30);
        add(r1);

        r2 = new JRadioButton("Female");
        // r2.setBackground(new Color(222, 225, 228));
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBounds(400, 340, 80, 30);
        add(r2);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel labelEmail = new JLabel("Email Add : ");
        labelEmail.setFont(new Font("Raleway", Font.BOLD, 20));
        labelEmail.setBounds(100, 390, 200, 30);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway", Font.BOLD, 14));
        textEmail.setBounds(300, 390, 400, 30);
        add(textEmail);

        JLabel labelMs = new JLabel("Marital Status : ");
        labelMs.setFont(new Font("Raleway", Font.BOLD, 20));
        labelMs.setBounds(100, 440, 200, 30);
        add(labelMs);

        m1 = new JRadioButton("Married");
        // m1.setBackground(new Color(222, 225, 228));
        m1.setFont(new Font("Raleway", Font.BOLD, 14));
        m1.setBounds(300, 440, 80, 30);
        add(m1);

        m2 = new JRadioButton("UnMarried");
        // m2.setBackground(new Color(222, 225, 228));
        m2.setFont(new Font("Raleway", Font.BOLD, 14));
        m2.setBounds(400, 440, 100, 30);
        add(m2);

        m3 = new JRadioButton("Other");
        // m3.setBackground(new Color(222, 225, 228));
        m3.setFont(new Font("Raleway", Font.BOLD, 14));
        m3.setBounds(540, 440, 100, 30);
        add(m3);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(m1);
        buttonGroup2.add(m2);
        buttonGroup2.add(m3);

        JLabel labelAdd = new JLabel("Address : ");
        labelAdd.setFont(new Font("Raleway", Font.BOLD, 20));
        labelAdd.setBounds(100, 490, 200, 30);
        add(labelAdd);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway", Font.BOLD, 14));
        textAdd.setBounds(300, 490, 400, 30);
        add(textAdd);

        JLabel labelCity = new JLabel("City : ");
        labelCity.setFont(new Font("Raleway", Font.BOLD, 20));
        labelCity.setBounds(100, 540, 200, 30);
        add(labelCity);

        textCity = new JTextField();
        textCity.setFont(new Font("Raleway", Font.BOLD, 14));
        textCity.setBounds(300, 540, 400, 30);
        add(textCity);

        JLabel labelPin = new JLabel("PinCode : ");
        labelPin.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPin.setBounds(100, 590, 200, 30);
        add(labelPin);

        textPin = new JTextField();
        textPin.setFont(new Font("Raleway", Font.BOLD, 14));
        textPin.setBounds(300, 590, 400, 30);
        add(textPin);

        JLabel labelState = new JLabel("State : ");
        labelState.setFont(new Font("Raleway", Font.BOLD, 20));
        labelState.setBounds(100, 640, 200, 30);
        add(labelState);

        textState = new JTextField();
        textState.setFont(new Font("Raleway", Font.BOLD, 14));
        textState.setBounds(300, 640, 400, 30);
        add(textState);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14)); 
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.setBounds(620, 700, 80, 30);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(850, 800);
        setLocation(360, 40);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        String formno = first;
        String name = textName.getText();
        String fname = textfName.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (r1.isSelected()) {
            gender = "Male";
        }
        else if (r2.isSelected()) {
            gender = "Female";
        }
        String email = textEmail.getText();
        String marital = null;
        if (m1.isSelected()) {
            marital = "Married";
        }
        else if (m2.isSelected()) {
            marital = "UnMarried";
        }
        else if (m3.isSelected()) {
            marital = "Other";
        }
        String address = textAdd.getText();
        String city = textCity.getText();
        String pin = textPin.getText();
        String state = textState.getText();

        try {
            if (!pin.matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null, "Pincode must be a 6-digit number.");
                return;
            }
            if (textName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }
            else{
                coon coon1 = new coon();
                String q = "insert into signup values('"+formno+"','"+name+"','"+fname+"','"+dob+"','"+gender+"','"+email+"','"+marital+"','"+address+"','"+city+"','"+pin+"','"+state+"')";
                coon1.statement.executeUpdate(q);
                new Signup2(formno);
                setVisible(false);
            }
            
        } catch (Exception E) {
            E.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Signup();
    }
}

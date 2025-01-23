package bank.management.System;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener{

    JComboBox comboBox,comboBox2,comboBox3,comboBox4,comboBox5;
    JTextField textPan,AadharNo;
    JRadioButton r1,r2,r3,r4;
    JButton next;
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

    Signup2(String formno){

        super("APPLICATION FORM");

        // JPanel backgroundPanel = new JPanel() {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         Graphics2D g2d = (Graphics2D) g;
        //         int width = getWidth();
        //         int height = getHeight();
    
        //         // Create a gradient paint from light blue to dark blue
        //         Color lightBlue = new Color(173, 216, 230, 150);  // Light blue with transparency
        //         Color darkBlue = new Color(0, 128, 255, 150);     // Darker blue with transparency
        //         GradientPaint gradient = new GradientPaint(0, 0, lightBlue, width, height, darkBlue);
    
        //         g2d.setPaint(gradient);
        //         g2d.fillRect(0, 0, width, height);
        //     }
        // };

        // setContentPane(backgroundPanel);
        // backgroundPanel.setLayout(null);

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

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 5, 100, 100);
        add(image);

        this.formno = formno;

        JLabel l1 = new JLabel("Page 2");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(350,30,600,40);
        add(l1);

        JLabel l2 = new JLabel("Additional Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(300,60,600,40);
        add(l2);

        JLabel l3 = new JLabel("Religion : ");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100,130,100,30);
        add(l3);

        String religion[] = {"Hindu", "Islam", "Christian", "Sikh", "Other"};
        comboBox = new JComboBox(religion);
        // comboBox.setBackground(new Color(252, 208, 76));
        comboBox.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox.setBounds(350, 130, 320, 30);
        add(comboBox);

        JLabel l4 = new JLabel("Category : ");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100,180,100,30);
        add(l4);

        String category[] = {"General", "OBC", "SC", "ST", "Other"};
        comboBox2 = new JComboBox(category);
        // comboBox2.setBackground(new Color(252, 208, 76));
        comboBox2.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox2.setBounds(350, 180, 320, 30);
        add(comboBox2);
        
        JLabel l5 = new JLabel("Income : ");
        l5.setFont(new Font("Raleway", Font.BOLD, 18));
        l5.setBounds(100,230,100,30);
        add(l5);

        String income[] = {"Null", "< 1,50,000", "< 2,50,000", "5,00,000", "10,00,000", "Above"};
        comboBox3 = new JComboBox(income);
        // comboBox3.setBackground(new Color(252, 208, 76));
        comboBox3.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox3.setBounds(350, 230, 320, 30);
        add(comboBox3);

        JLabel l6 = new JLabel("Education :");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(100,280,110,30);
        add(l6);

        String education[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        comboBox4 = new JComboBox(education);
        // comboBox4.setBackground(new Color(252, 208, 76));
        comboBox4.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox4.setBounds(350, 280, 320, 30);
        add(comboBox4);

        JLabel l7 = new JLabel("Occupation :");
        l7.setFont(new Font("Raleway", Font.BOLD, 18));
        l7.setBounds(100,330,150,30);
        add(l7);

        String occupation[] = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        comboBox5 = new JComboBox(occupation);
        // comboBox5.setBackground(new Color(252, 208, 76));
        comboBox5.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox5.setBounds(350, 330, 320, 30);
        add(comboBox5);

        JLabel l8 = new JLabel("PAN No :");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100,380,150,30);
        add(l8);

        textPan = new JTextField();
        textPan.setFont(new Font("Raleway", Font.BOLD, 14));
        textPan.setBounds(350, 380, 320, 30);
        add(textPan);

        JLabel l9 = new JLabel("Aadhar No :");
        l9.setFont(new Font("Raleway", Font.BOLD, 18));
        l9.setBounds(100,430,150,30);
        add(l9);

        AadharNo = new JTextField();
        AadharNo.setFont(new Font("Raleway", Font.BOLD, 14));
        AadharNo.setBounds(350, 430, 320, 30);
        add(AadharNo);

        JLabel l10 = new JLabel("Senior Citizen :");
        l10.setFont(new Font("Raleway", Font.BOLD, 18));
        l10.setBounds(100,480,150,30);
        add(l10);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        // r1.setBackground(new Color(252, 208, 76));
        r1.setBounds(350,480,100,30);
        add(r1);

        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        // r2.setBackground(new Color(252, 208, 76));
        r2.setBounds(450,480,100,30);
        add(r2);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(r1);
        buttonGroup1.add(r2);

        JLabel l11 = new JLabel("Existing Acc :");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100,530,150,30);
        add(l11);

        r3 = new JRadioButton("Yes");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        // r3.setBackground(new Color(252, 208, 76));
        r3.setBounds(350,530,100,30);
        add(r3);

        r4 = new JRadioButton("No");
        r4.setFont(new Font("Raleway", Font.BOLD, 14));
        // r4.setBackground(new Color(252, 208, 76));
        r4.setBounds(450,530,100,30);
        add(r4);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(r3);
        buttonGroup2.add(r4);

        JLabel l12 = new JLabel("Form No :");
        l12.setFont(new Font("Raleway", Font.BOLD, 14));
        l12.setBounds(700,10,100,30);
        add(l12);

        JLabel l13 = new JLabel(formno);
        l13.setFont(new Font("Raleway", Font.BOLD, 14));
        l13.setBounds(760,10,60,30);
        add(l13);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.setBounds(570, 640, 100, 30);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(850, 750);
        setLocation(450,80);
        // getContentPane().setBackground(new Color(252,208, 76));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String rel = (String) comboBox.getSelectedItem();
        String cate = (String) comboBox2.getSelectedItem();
        String inc = (String) comboBox3.getSelectedItem();
        String edu = (String) comboBox4.getSelectedItem();
        String occ = (String) comboBox5.getSelectedItem();

        String pan = textPan.getText();
        String aadhar = AadharNo.getText();

        String scitizen = " ";
        if ((r1.isSelected())) {
            scitizen = "Yes";
        }
        else if(r2.isSelected()){
            scitizen = "No";
        }

        String eaccount = " ";
        if ((r1.isSelected())) {
            eaccount = "Yes";
        }
        else if(r2.isSelected()){
            eaccount = "No";
        }

        try {
            if (!aadhar.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "AadharNo must be a 12-digit number.");
                return;
            }
            if (!pan.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "PAN No must be a 10-digit number.");
                return;
            }
            if (textPan.getText().equals("") || AadharNo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }
            else{
                coon c1 = new coon();
                String q = "insert into signuptwo values('"+formno+"', '"+rel+"', '"+cate+"', '"+inc+"', '"+edu+"', '"+occ+"', '"+pan+"', '"+aadhar+"', '"+scitizen+"', '"+eaccount+"')";
                c1.statement.executeUpdate(q);
                new Signup3(formno);
                setVisible(false);

            }
            
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Signup2("");
    }
}

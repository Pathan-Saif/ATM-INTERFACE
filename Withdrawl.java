package bank.management.System;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;



public class Withdrawl extends JFrame implements ActionListener{

    String pin;
    TextField textField;
    JButton b1,b2;
    // C:\Users\HP\Desktop\BANK\icon

    Withdrawl(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/new.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel l1 = new JLabel("MAXIMUM WITHDRAWL RS. 10,000");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(550, 180, 400, 35);
        l3.add(l1);

        JLabel l2 = new JLabel("Please Enter Your Amount");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(600, 250, 700, 35);
        l3.add(l2);

        textField = new TextField();
        textField.setBounds(550, 290, 280, 35);
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("WITHDRAWL");
        b1.setBounds(710, 380, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(710, 440, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                
                String amount = textField.getText();
                Date date = new Date();
                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Enter Amount you Want To Withdraw");
                }
                else{
                    coon c = new coon();
                    ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
                    int balance = 0;
                    while(resultSet.next()){
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        }
                        else{
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs." +amount+ " debited successfully");
                    setVisible(false);
                    new main(pin);
                }
            }
            
            catch (Exception E) {
                E.getStackTrace();
            }
        }
        else if(e.getSource() == b2){
            setVisible(false);
            new main(pin);
        }
    }
    public static void main(String[] args) {
        new Withdrawl("");
    }
}

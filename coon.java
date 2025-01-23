package bank.management.System;

import java.sql.*;

// import com.mysql.cj.xdevapi.Statement;

public class coon {
    Connection connection;
    Statement statement;
    public coon(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem", "root", "P@thanwife380" );
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

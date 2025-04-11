package sample.studentportal2;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public  Connection getConnection(){

        String dbName = "university_portal";
        String dbUser = "root";
        String dbPassword = "05122004";
        String url = "jdbc:mysql://localhost/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,dbUser,dbPassword);

        }catch (Exception e){
            System.out.println("Failed to connect");
            System.out.println(e);
        }
        return databaseLink;
    }





}

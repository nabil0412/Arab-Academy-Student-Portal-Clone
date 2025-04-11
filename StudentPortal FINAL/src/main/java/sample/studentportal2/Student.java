package sample.studentportal2;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class Student {


        private String regNo;
        private String password;
        private String firstName;
        private String lastName;
        private String address;
        private LocalDate DOB;
        private String gender;
        private int termNo;
        private String department;
        private double GPA;


        public Student(String regNo, String password,String firstName,  String lastName, String address, LocalDate DOB, String gender, int termNo, String department) {
            this.regNo = regNo;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.DOB = DOB;
            this.gender = gender;
            this.termNo = termNo;
            this.department = department;
            this.GPA = 0.0;
        }

        public  Student(String regNo,String password){
            this.regNo = regNo;
            this.password = password;
        }

    public String getRegNo() {
        return regNo;
    }

    public Boolean Insert_Student(){

            DatabaseConnection dbc = new DatabaseConnection();
            Connection connect = dbc.getConnection();

            String query = "SELECT COUNT(*) FROM student_log WHERE reg_no = " + regNo;
            try{
                Statement statement = connect.createStatement();
                ResultSet query_output = statement.executeQuery(query);
                System.out.println("Checkpoint 1");

                int no_of_records = 0;
                if(query_output.next()) {
                    no_of_records = query_output.getInt(1);
                }
                System.out.println(no_of_records);
                if(no_of_records != 0){
                    return  false;
                }
                System.out.println("Checkpoint 2");

                String insert_query = "INSERT INTO student (Reg_No, First_Name, Last_Name, Address, Data_Birth, GPA, Gender, Term_No, Dep_Name) "
                        + "VALUES ('" + regNo + "', '" + firstName + "', '" + lastName + "', '" + address + "', '"
                        + DOB + "', " + GPA + ", '" + gender + "', " + termNo + ", '" + department + "');";


                PreparedStatement pstmt = connect.prepareStatement(insert_query);
                pstmt.execute();
                System.out.println("Wrote student!");

                String login_query = "INSERT INTO student_log (reg_no, password) VALUES ('" + regNo + "', '" + password + "');";
                pstmt = connect.prepareStatement(login_query);
                pstmt.execute();
                System.out.println("Wrote student log-in!");

                return true;


            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }

        public Boolean Check_Login(){

            DatabaseConnection dbc = new DatabaseConnection();
            Connection connect = dbc.getConnection();

            String query = "SELECT COUNT(*) FROM student_log WHERE reg_no = " + regNo + " AND password = '" + password + "'";
            try {
                Statement statement = connect.createStatement();
                ResultSet query_output = statement.executeQuery(query);
                System.out.println("Checkpoint 1");
                int no_of_records = 0;
                if (query_output.next()) {
                    no_of_records = query_output.getInt(1);
                }
                System.out.println(no_of_records);
                if (no_of_records == 0) {
                    return false;
                }else{
                    return  true;
                }
            }catch(Exception e){
                System.out.println("SQL error");
                return false;
            }

        }






}






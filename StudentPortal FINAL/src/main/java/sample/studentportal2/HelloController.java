package sample.studentportal2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;

import java.util.Date;
import java.util.ResourceBundle;


public class HelloController implements  Initializable {

    // ****************** Buttons switching Scenes ************************
    public void switchToStarting(ActionEvent event) throws  IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("hello-view.fxml");
        fsc.switchView(event);
    }

    public  void switchToRegister(ActionEvent event) throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("register.fxml");
        fsc.switchView(event);
    }
    public void switchToHome(ActionEvent event) throws  IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("home_page.fxml");
        fsc.switchView(event);
    }
    public  void switchToProfile(ActionEvent event) throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("profile.fxml");
        fsc.switchView(event);
    }
    public  void switchToRegistration(ActionEvent event)throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("registration.fxml");
        fsc.switchView(event);
    }
    public void switchToBooks(ActionEvent event) throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("books.fxml");
        fsc.switchView(event);
    }
    public void switchToCourseList(ActionEvent event) throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("courseList.fxml");
        fsc.switchView(event);
    }
    public void switchToServices(ActionEvent event) throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("requestService.fxml");
        fsc.switchView(event);
    }
    public void switchToBus(ActionEvent event) throws IOException{
        FXML_Scene_Change fsc = new FXML_Scene_Change("bus.fxml");
        fsc.switchView(event);
    }

    // ****************** End of Buttons switching Scenes ************************


    // ******************* Combo Boxes and Initialize **********************
    @FXML
    ComboBox<String> gender_combo;
    @FXML
    ComboBox<String> department_combo;
    @FXML
    ComboBox<String> combo_bus;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        String[] g = {"Male","Female"};
        String[] d = {"Computer Engineering","Electrical Engineering","Mechanical Engineering"};
        String[] b = {"Route A","Route B","Route C"};
        try {
            gender_combo.getItems().addAll(g);
            department_combo.getItems().addAll(d);
        }catch(Exception e){
            System.out.println("Wrong page,ignore");
        }
        try{
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
            bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));

        }catch(Exception e){
            System.out.println("Wrong page,ignore");
        }

        try{
            RegcoursecodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
            courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));

        }catch(Exception e){
            System.out.println("Wrong page,ignore");
        }

        try{
            combo_bus.getItems().addAll(b);
        }catch(Exception e){
            System.out.println("Wrong page,ignore");
        }

    }
    // ******************* End of Combo Boxes and Initialize **********************



    //************Text Boxes and Date ******************
    @FXML
    private TextField text1, text2, text3, text4, text5;
    @FXML
    private TextField regno_login,password_login;
    @FXML
    private DatePicker date1;

    @FXML
    private TextField registerNoField;
    @FXML
    private TextField dobField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField gpaField;
    @FXML
    private TextField termNumberField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField departmentField;

    @FXML
    private TextField courseCodeField;

    //*********************Text Boxes and Date*****************************


    //************Currently Logged In**************
    public static String logged_in_Reg_No;

    public void setLogged_in_Reg_No(String logged_in_Reg_No) {
        HelloController.logged_in_Reg_No = logged_in_Reg_No;
    }

    public String getLogged_in_Reg_No() {
        return logged_in_Reg_No;
    }
    //************Currently Logged In End**************

    //**************Showing Alerts********************
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //**************End of Showing Alerts********************


    //*****Register Submit**********
    public void Register_Student(){
        if (text1.getText().isEmpty() || text2.getText().isEmpty() || text3.getText().isEmpty() ||
                text4.getText().isEmpty() || text5.getText().isEmpty()) {
            showAlert("Empty Fields", "Please fill in all the text fields.");
            return;
        }

        if (date1.getValue() == null) {
            showAlert("Empty Date", "Please select a date.");
            return;
        }

        if (gender_combo.getValue() == null || department_combo.getValue() == null) {
            showAlert("Empty ComboBoxes", "Please select a gender and a department.");
            return;
        }

        String regNo = text1.getText();
        String password = text2.getText();
        String firstName = text3.getText();
        String lastName = text4.getText();
        String address = text5.getText();
        LocalDate dob = date1.getValue();
        String gender = gender_combo.getValue();
        String department = department_combo.getValue();

        Student student = new Student(regNo, password, firstName, lastName, address, dob, gender, 1,department);
        Boolean added = student.Insert_Student();
        if(!added){
            showAlert("Registration Number Duplicate","This registration number already exists");
        }else{
            showAlert("Registration Successful","Student was registered successfully");
        }
    }
    //*****End of Register Submit**********




    //**********Student Log-in*******************
    Student logged_in_student;
    public  void Check_Login(ActionEvent event){
        if (regno_login.getText().isEmpty() || password_login.getText().isEmpty()) {
            showAlert("Empty Fields", "Please fill in all the text fields.");
            return;
        }
        String regNo = regno_login.getText();
        System.out.println(regNo);
        String password = password_login.getText();
        setLogged_in_Reg_No(regNo);
        logged_in_student = new Student(regNo,password);
        System.out.println(this.logged_in_Reg_No);
        if(!logged_in_student.Check_Login()) {
            showAlert("Wrong Credentials", "Wrong registration number or password");
            return;
        }

        try{
            FXML_Scene_Change fsc = new FXML_Scene_Change("home_page.fxml");
            fsc.switchView(event);
        } catch (Exception e) {
            System.out.println("Loading failed");
        }

    }
    //**********End of Student Log-in*******************


    //***************Profile Viewer **************************************
    public void display_info(){
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();
        String regNo = getLogged_in_Reg_No();
        System.out.println(regNo);
        String query = "SELECT * FROM student WHERE reg_no = " + regNo;
        try {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Checkpoint 1");
            if (resultSet.next()) {
                registerNoField.setText(String.valueOf(resultSet.getString("Reg_No")));
                addressField.setText(resultSet.getString("Address"));
                firstNameField.setText(resultSet.getString("First_Name"));
                lastNameField.setText(resultSet.getString("Last_Name"));
                gpaField.setText(String.valueOf(resultSet.getBigDecimal("GPA")));
                termNumberField.setText(String.valueOf(resultSet.getInt("Term_No")));
                genderField.setText(resultSet.getString("Gender"));
                departmentField.setText(resultSet.getString("Dep_Name"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //***************End of Profile Viewer **************************************



    //****************Course Enrollment****************************
    public Boolean check_field() {

        if (courseCodeField.getText().isEmpty()) {
            showAlert("Empty field", "You must enter a course code");
            return false;
        }

        String courseCode = courseCodeField.getText();
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();
        String query = "SELECT COUNT(*) FROM course WHERE course_code = " + courseCode;
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
                showAlert("Invalid Course", "You must enter a valid course code");
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public void Enroll(){
        if(!check_field()){return;}
        String courseCode = courseCodeField.getText();
        String regNo = getLogged_in_Reg_No();
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();

        String query = "SELECT COUNT(*) FROM studenttakescourse WHERE course_code = " + courseCode;
        String insertQuery = "INSERT INTO studenttakescourse (REG_NO, course_code) VALUES (" + regNo + ", " + courseCode + ")";
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertQuery);
            pstmt.execute();
            System.out.println("Wrote student!");
            showAlert("Enrollment complete","Registered course successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void Unenroll(){
        if(!check_field()){return;}
        String courseCode = courseCodeField.getText();
        String regNo = getLogged_in_Reg_No();
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();


        String query = "SELECT COUNT(*) FROM studenttakescourse WHERE course_code = " + courseCode + " AND reg_no = " + regNo;
        String deleteQuery = "DELETE FROM studenttakescourse WHERE reg_no = '" + regNo + "' AND course_code = '" + courseCode + "'";
        try{
            Statement statement = connect.createStatement();
            ResultSet query_output = statement.executeQuery(query);
            int no_of_records = 0;
            if (query_output.next()) {
                no_of_records = query_output.getInt(1);
            }
            System.out.println(no_of_records);
            if (no_of_records == 0) {
                showAlert("Invalid Course", "You must be registered to unenroll from a course ");
                return;
            }
            PreparedStatement pstmt = connect.prepareStatement(deleteQuery);
            pstmt.execute();
            System.out.println("Wrote student!");
            showAlert("Unenrollment complete","Unenrolled from course successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //****************End of Course Enrollment****************************

    //**Books Table********//
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, Integer> isbnColumn;
    @FXML
    private TableColumn<Book, Integer> courseCodeColumn;
    @FXML
    private TableColumn<Book, String> bookNameColumn;

    // ObservableList to hold the data
    private ObservableList<Book> bookList;



    public void loadDataFromDatabase() {
        // Initialize the ObservableList
        bookList = FXCollections.observableArrayList();

        String query = "SELECT * FROM book";

        DatabaseConnection db = new DatabaseConnection();

        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                int isbn = resultSet.getInt("ISBN");
                int courseCode = resultSet.getInt("Course_Code");
                String bookName = resultSet.getString("Book_Name");
                System.out.println("Retrieved: ISBN=" + isbn + ", CourseCode=" + courseCode + ", BookName=" + bookName);

                bookList.add(new Book(isbn, courseCode, bookName));
            }
            booksTable.setItems(bookList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Book {
        private final int isbn;
        private final int courseCode;
        private final String bookName;

        public Book(int isbn, int courseCode, String bookName) {
            this.isbn = isbn;
            this.courseCode = courseCode;
            this.bookName = bookName;
        }

        public int getIsbn() {
            return isbn;
        }

        public int getCourseCode() {
            return courseCode;
        }

        public String getBookName() {
            return bookName;
        }
    }
    // ******* End of Books Table ***************************

    // ***** Courses Table***************
    public static class Registered_Course {
        private int courseCode;
        private String courseName;
        private String teacherName;

        public Registered_Course(int courseCode, String courseName, String teacherName) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.teacherName = teacherName;
        }

        public int getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getTeacherName() {
            return teacherName;
        }
    }

    @FXML
    private TableView<Registered_Course> registeredCoursesTable;
    @FXML
    private TableColumn<Registered_Course, String> RegcoursecodeColumn;
    @FXML
    private TableColumn<Registered_Course, String> courseNameColumn;
    @FXML
    private TableColumn<Registered_Course, String> teacherColumn;

    private ObservableList<Registered_Course> courseTeacherList;

    @FXML
    private void loadCourses(ActionEvent event) {
        courseTeacherList = FXCollections.observableArrayList();

        String query = "SELECT teaching_staff.Tutor_Name, course.Course_Code, course.Course_Name " +
                "FROM teaching_staff_teaches_course " +
                "JOIN teaching_staff ON teaching_staff_teaches_course.staff_id = teaching_staff.staff_id " +
                "JOIN course ON teaching_staff_teaches_course.Course_Code = course.Course_Code " +
                "JOIN studenttakescourse ON studenttakescourse.Course_Code = course.Course_Code " +
                "WHERE studenttakescourse.REG_NO = " + getLogged_in_Reg_No();

        DatabaseConnection db = new DatabaseConnection();
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int courseCode = resultSet.getInt("Course_Code");
                String courseName = resultSet.getString("Course_Name");
                String teacherName = resultSet.getString("Tutor_Name");


                courseTeacherList.add(new Registered_Course(courseCode, courseName, teacherName));
            }


            registeredCoursesTable.setItems(courseTeacherList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ***** End of Courses Table***************

    public int get_request_no(){
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();
        String query = "SELECT COUNT(*) FROM student_requests_service";
        try {
            Statement statement = connect.createStatement();
            ResultSet query_output = statement.executeQuery(query);
            System.out.println("Checkpoint 1");

            int no_of_records = 0;
            if (query_output.next()) {
                no_of_records = query_output.getInt(1);
            }
            return no_of_records + 217;
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    //************************ Bus*****************************
    public void reserve_bus(){
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();
        int reqno = get_request_no();
        String regno = getLogged_in_Reg_No();
        String path = combo_bus.getValue();
        String insertQuery = "INSERT INTO student_requests_service (Reg_No, Request_No) VALUES ('" + regno + "', '" + reqno + "')";
        String insertBusQuery = "INSERT INTO bus_subscription (Request_No, Line_Path) VALUES ('" + reqno + "', '" + path + "')";
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertQuery);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertBusQuery);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        showAlert("Confirmed Reservation","Bus reservation confirmed");

    }

    //*******************************End of bus***************************

    //*********************Clinic************************
    @FXML
    public RadioButton ambulance;

    @FXML
    private TextField sickness_text;
    public  void request_clinic(){

        DatabaseConnection dbc = new DatabaseConnection();
        Connection connect = dbc.getConnection();
        int reqno = get_request_no();
        String sickness = sickness_text.getText();

        int ambulance_required =  0;
        if(ambulance.isSelected()){
            ambulance_required = 1;
            System.out.println(ambulance_required);
        }


        String regno = getLogged_in_Reg_No();
        String insertQuery = "INSERT INTO student_requests_service (Reg_No, Request_No) VALUES ('" + regno + "', '" + reqno + "')";
        String insertClinicQuery = "INSERT INTO clinic_reservations (Request_No, Sickness_Type, Ambulance_Required) " +
                "VALUES ('" + reqno + "', '" + sickness + "', '" + ambulance_required + "')";
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertQuery);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("No execute");
        }
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertClinicQuery);
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        showAlert("Confirmed Reservation","Clinic reservation confirmed");

    }


}





module sample.studentportal2 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens sample.studentportal2 to javafx.fxml;
    exports sample.studentportal2;
}
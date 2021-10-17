/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;
import java.sql.SQLException;
import java.util.ArrayList;
import utilities.DatabaseDriver;
import utilities.DatabaseHandler;


/**
 *
 * @author Yoga
 */
public class StudentJDBC {
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/Student";
        String user = "app";
        String passwd = "app";
        /*String driver = "com.mysql.cj.jdbc.Driver";
        //String url="jdbc:mysql://localhost:3306/employee?serverTimezone=UTC";
        String user = "root";
        String passwd = "root";*/
        DatabaseDriver dbDriver = new DatabaseDriver(driver, url, user, passwd);
        DatabaseHandler dbHandler = new DatabaseHandler(dbDriver);
        Student std1 = new Student(62050137, "ABC", 3.80);
        Student std2 = new Student(62050138, "BBB", 2.90);
        StudentTable.insertStudent(dbHandler, std1);
        StudentTable.insertStudent(dbHandler, std2);
       
        Student std = StudentTable.findStudentById(dbHandler, 62050002);
        std.setName("Third");
        StudentTable.updateStudent(dbHandler, std);
        
        StudentTable.removeStudent(dbHandler, std);
        
        ArrayList<Student> StudentList = StudentTable.findAllStudent(dbHandler);
        if (StudentList != null) {
            printAllStudent(StudentList);
        }
        dbHandler.closeConnection();
    }
    
    public static void printAllStudent(ArrayList<Student> stdList) {
        for(int i = 0; i < stdList.size(); i++) {
            System.out.print(stdList.get(i).getId() + " ");
            System.out.print(stdList.get(i).getName() + " ");
            System.out.println(stdList.get(i).getGPA() + " ");
        }
        
    }
    
}

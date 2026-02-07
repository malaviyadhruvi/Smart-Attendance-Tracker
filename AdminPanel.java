import java.sql.*;
import java.util.Scanner;

public class AdminPanel {

    public static void adminMenu() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Admin Username: ");
            String user = sc.next();

            System.out.print("Password: ");
            String pass = sc.next();

            Connection con = DBConnection.getConnection();

            PreparedStatement loginPs = con.prepareStatement(
                    "SELECT * FROM admin WHERE username=? AND password=?");
            loginPs.setString(1, user);
            loginPs.setString(2, pass);

            ResultSet rs = loginPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid Admin Login");
                return;
            }

            System.out.println("Login Successful");

            System.out.print("Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Course (BCA/MCA/MSCIT): ");
            String course = sc.next();

            System.out.print("Semester: ");
            int sem = sc.nextInt();

            PreparedStatement psStudent = con.prepareStatement(
                    "INSERT INTO students (roll_no, name, course, semester) VALUES (?,?,?,?)");
            psStudent.setInt(1, roll);
            psStudent.setString(2, name);
            psStudent.setString(3, course);
            psStudent.setInt(4, sem);
            psStudent.executeUpdate();

            System.out.print("Total Lectures: ");
            int total = sc.nextInt();

            System.out.print("Attended Lectures: ");
            int attended = sc.nextInt();

            PreparedStatement psAtt = con.prepareStatement(
                    "INSERT INTO attendance (roll_no, total_lectures, attended_lectures) VALUES (?,?,?)");
            psAtt.setInt(1, roll);
            psAtt.setInt(2, total);
            psAtt.setInt(3, attended);
            psAtt.executeUpdate();

            System.out.print("Marks (out of 100): ");
            int marks = sc.nextInt();

            PreparedStatement psMarks = con.prepareStatement(
                    "INSERT INTO marks (roll_no, subject_marks) VALUES (?,?)");
            psMarks.setInt(1, roll);
            psMarks.setInt(2, marks);
            psMarks.executeUpdate();

            System.out.println("Student, Attendance & Marks Saved Successfully");

            con.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Roll Number already exists");
        } catch (Exception e) {
            System.out.println("ADMIN PANEL ERROR");
            e.printStackTrace();
        }
    }
}

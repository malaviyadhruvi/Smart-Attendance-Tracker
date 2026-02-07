import java.sql.*;
import java.util.Scanner;

public class StudentPanel {

    public static void view() {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();

            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement(
                    "SELECT * FROM students WHERE roll_no=?");
            ps1.setInt(1, roll);
            ResultSet s = ps1.executeQuery();

            if (!s.next()) {
                System.out.println("Student Not Found");
                return;
            }

            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT * FROM attendance WHERE roll_no=?");
            ps2.setInt(1, roll);
            ResultSet a = ps2.executeQuery();
            a.next();

            PreparedStatement ps3 = con.prepareStatement(
                    "SELECT * FROM marks WHERE roll_no=?");
            ps3.setInt(1, roll);
            ResultSet m = ps3.executeQuery();
            m.next();

            int total = a.getInt("total_lectures");
            int attended = a.getInt("attended_lectures");
            int percent = (attended * 100) / total;
            int marks = m.getInt("subject_marks");

            String remark = marks >= 75 ? "Good" : marks >= 50 ? "Average" : "Needs Improvement";

            System.out.println("\n----- STUDENT REPORT -----");
            System.out.println("Roll No   : " + s.getInt("roll_no"));
            System.out.println("Name      : " + s.getString("name"));
            System.out.println("Course    : " + s.getString("course"));
            System.out.println("Semester  : " + s.getInt("semester"));
            System.out.println("Attendance: " + percent + "%");

            if (percent < 75)
                System.out.println("Attendance Shortage");

            System.out.println("Marks     : " + marks);
            System.out.println("Remark    : " + remark);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

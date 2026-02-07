import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nSMART ATTENDANCE SYSTEM");
            System.out.println("1. Admin Login");
            System.out.println("2. Student View Report");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();

            if (ch == 1) {
                AdminPanel.adminMenu();
            } else if (ch == 2) {
                StudentPanel.view();
            } else {
                break;
            }
        }
    }
}

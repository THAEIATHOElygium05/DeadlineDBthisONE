import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DeadlineDAO deadlineDAO = new DeadlineDAO();

    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("==== Deadline Management System ====");
            System.out.println("1. Add Deadline");
            System.out.println("2. View All Deadlines");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            System.out.println("===================");
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addDeadline();
                    break;
                case 2:
                    viewAllDeadlines();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 3);
    }

    private static void addDeadline() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Due Date (yyyy.MM.dd): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Status: ");
        String status = scanner.nextLine();

        Deadline deadline = new Deadline(title, description, dueDate, status);
        deadlineDAO.addDeadline(deadline);
        System.out.println("Deadline added successfully!");
    }

    private static void viewAllDeadlines() {
        List<Deadline> deadlines = deadlineDAO.getAllDeadlines();
        System.out.println("All Deadlines:");
        for (Deadline deadline : deadlines) {
            System.out.println("Title: " + deadline.getTitle());
            System.out.println("Description: " + deadline.getDescription());
            System.out.println("Due Date: " + deadline.getDueDate());
            System.out.println("Status: " + deadline.getStatus());
            System.out.println("-----------------------");
        }
    }
}


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        VolunteerSystem system = new VolunteerSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println(
                "==========================================");
        System.out.println(
                "  Smart Event Volunteer Coordination System");
        System.out.println(
                "==========================================\n");

        while (running) {
            displayMenu();

            int choice = -1;
            try {
                choice = Integer.parseInt(
                        scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter "
                        + "a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleRegister(system, scanner);
                    break;
                case 2:
                    handleAssign(system, scanner);
                    break;
                case 3:
                    handlePriorityAssign(system, scanner);
                    break;
                case 4:
                    handleRemove(system, scanner);
                    break;
                case 5:
                    handleSearchById(system, scanner);
                    break;
                case 6:
                    handleSearchByName(system, scanner);
                    break;
                case 7:
                    handleViewByPriority(system, scanner);
                    break;
                case 8:
                    system.undoLastOperation();
                    break;
                case 9:
                    system.displayAllVolunteers();
                    break;
                case 10:
                    system.displayWaitingQueue();
                    break;
                case 11:
                    system.displayOperationHistory();
                    break;
                case 12:
                    running = false;
                    System.out.println("\nThank you for "
                            + "using the Volunteer "
                            + "Coordination System.");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. "
                            + "Please enter 1-12.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println(
                "\n========== MAIN MENU ==========");
        System.out.println("1.  Register Volunteer");
        System.out.println("2.  Assign Next Volunteer (FIFO)");
        System.out.println("3.  Assign by Priority");
        System.out.println("4.  Remove Volunteer");
        System.out.println("5.  Search Volunteer by ID");
        System.out.println("6.  Search Volunteer by Name");
        System.out.println("7.  View Volunteers by Priority");
        System.out.println("8.  Undo Last Operation");
        System.out.println("9.  Display All Volunteers");
        System.out.println("10. Display Waiting Queue");
        System.out.println("11. Display Operation History");
        System.out.println("12. Exit");
        System.out.println(
                "================================");
        System.out.print("Enter your choice: ");
    }

    private static void handleRegister(
            VolunteerSystem system, Scanner scanner) {
        System.out.println(
                "\n--- Register New Volunteer ---");
        System.out.print("Enter volunteer name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter task assigned "
                + "(e.g. Registration Desk, "
                + "Crowd Control, Logistics): ");
        String task = scanner.nextLine().trim();
        System.out.print("Enter priority level "
                + "(LOW / MEDIUM / HIGH): ");
        String priority = scanner.nextLine().trim();
        if (name.isEmpty() || task.isEmpty()
                || priority.isEmpty()) {
            System.out.println(
                    "All fields are required.");
            return;
        }
        system.registerVolunteer(name, task, priority);
    }

    private static void handleAssign(
            VolunteerSystem system, Scanner scanner) {
        System.out.println(
                "\n--- Assign Next Volunteer "
                        + "(FIFO Order) ---");
        System.out.print("Enter task to assign: ");
        String task = scanner.nextLine().trim();
        if (task.isEmpty()) {
            System.out.println(
                    "Task cannot be empty.");
            return;
        }
        system.assignVolunteer(task);
    }

    private static void handlePriorityAssign(
            VolunteerSystem system, Scanner scanner) {
        System.out.println(
                "\n--- Priority-Based Assignment ---");
        System.out.print("Enter task to assign "
                + "by priority: ");
        String task = scanner.nextLine().trim();
        if (task.isEmpty()) {
            System.out.println(
                    "Task cannot be empty.");
            return;
        }
        system.assignByPriority(task);
    }

    private static void handleRemove(
            VolunteerSystem system, Scanner scanner) {
        System.out.println("\n--- Remove Volunteer ---");
        System.out.print(
                "Enter volunteer ID to remove: ");
        try {
            int id = Integer.parseInt(
                    scanner.nextLine().trim());
            system.removeVolunteer(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. "
                    + "Please enter a number.");
        }
    }

    private static void handleSearchById(
            VolunteerSystem system, Scanner scanner) {
        System.out.println("\n--- Search by ID ---");
        System.out.print("Enter volunteer ID: ");
        try {
            int id = Integer.parseInt(
                    scanner.nextLine().trim());
            system.searchVolunteerById(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. "
                    + "Please enter a number.");
        }
    }

    private static void handleSearchByName(
            VolunteerSystem system, Scanner scanner) {
        System.out.println("\n--- Search by Name ---");
        System.out.print("Enter volunteer name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println(
                    "Name cannot be empty.");
            return;
        }
        system.searchVolunteerByName(name);
    }

    private static void handleViewByPriority(
            VolunteerSystem system, Scanner scanner) {
        System.out.println(
                "\n--- View by Priority ---");
        System.out.print("Enter priority level "
                + "(LOW / MEDIUM / HIGH): ");
        String priority = scanner.nextLine().trim();
        if (priority.isEmpty()) {
            System.out.println(
                    "Priority cannot be empty.");
            return;
        }
        system.viewByPriority(priority);
    }
}
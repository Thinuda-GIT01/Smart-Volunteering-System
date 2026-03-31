public class VolunteerSystem {

    private CustomLinkedList volunteerList;
    private CustomQueue waitingQueue;
    private CustomStack operationStack;
    private int nextId;

    public VolunteerSystem() {
        this.volunteerList = new CustomLinkedList();
        this.waitingQueue = new CustomQueue();
        this.operationStack = new CustomStack();
        this.nextId = 1;
    }

    public void registerVolunteer(String name,
                                  String task,
                                  String priority) {
        if (!priority.equalsIgnoreCase("LOW") &&
                !priority.equalsIgnoreCase("MEDIUM") &&
                !priority.equalsIgnoreCase("HIGH")) {
            System.out.println("Invalid priority. "
                    + "Please use LOW, MEDIUM, or HIGH.");
            return;
        }
        Volunteer volunteer = new Volunteer(
                nextId, name, task,
                priority.toUpperCase());
        volunteerList.addVolunteer(volunteer);
        waitingQueue.enqueue(volunteer);
        operationStack.push("REGISTER:" + nextId
                + ":" + name + ":" + task
                + ":" + priority.toUpperCase());
        nextId++;
    }

    public void assignVolunteer(String task) {
        if (waitingQueue.isEmpty()) {
            System.out.println("No volunteers waiting "
                    + "for assignment.");
            return;
        }
        Volunteer volunteer = waitingQueue.dequeue();
        volunteer.setTaskAssigned(task);
        volunteer.setAssigned(true);
        operationStack.push("ASSIGN:"
                + volunteer.getVolunteerId()
                + ":" + volunteer.getVolunteerName()
                + ":" + task);
        System.out.println("Volunteer "
                + volunteer.getVolunteerName()
                + " assigned to: " + task);
    }

    public void removeVolunteer(int volunteerId) {
        Volunteer volunteer = volunteerList
                .searchById(volunteerId);
        if (volunteer == null) {
            System.out.println("Volunteer ID "
                    + volunteerId + " not found.");
            return;
        }
        String undoRecord = "REMOVE:"
                + volunteer.getVolunteerId() + ":"
                + volunteer.getVolunteerName() + ":"
                + volunteer.getTaskAssigned() + ":"
                + volunteer.getPriorityLevel() + ":"
                + volunteer.isAssigned();
        volunteerList.removeVolunteer(volunteerId);
        operationStack.push(undoRecord);
    }

    public void searchVolunteerById(int volunteerId) {
        System.out.println("Searching for ID: "
                + volunteerId);
        Volunteer v = volunteerList
                .searchById(volunteerId);
        if (v != null) {
            System.out.println("Found: " + v);
        }
    }

    public void searchVolunteerByName(String name) {
        System.out.println("Searching for: " + name);
        Volunteer v = volunteerList
                .searchByName(name);
        if (v != null) {
            System.out.println("Found: " + v);
        }
    }

    public void assignByPriority(String task) {
        System.out.println(
                "=== Priority-Based Assignment ===");
        System.out.println("Task: " + task);

        boolean anyAssigned = false;
        String[] priorityOrder = {"HIGH", "MEDIUM", "LOW"};

        for (String priority : priorityOrder) {
            System.out.println("\nChecking "
                    + priority + " priority...");

            Volunteer volunteer =
                    findUnassignedByPriority(priority);

            while (volunteer != null) {
                volunteer.setTaskAssigned(task);
                volunteer.setAssigned(true);
                operationStack.push("ASSIGN:"
                        + volunteer.getVolunteerId()
                        + ":" + volunteer.getVolunteerName()
                        + ":" + task);
                System.out.println("  Assigned: "
                        + volunteer.getVolunteerName()
                        + " [" + priority + "] → " + task);
                anyAssigned = true;
                volunteer =
                        findUnassignedByPriority(priority);
            }
        }

        if (!anyAssigned) {
            System.out.println("No unassigned volunteers "
                    + "found for priority assignment.");
        }
    }

    private Volunteer findUnassignedByPriority(
            String priority) {
        return volunteerList
                .findFirstUnassignedByPriority(priority);
    }

    public void viewByPriority(String priority) {
        if (!priority.equalsIgnoreCase("LOW") &&
                !priority.equalsIgnoreCase("MEDIUM") &&
                !priority.equalsIgnoreCase("HIGH")) {
            System.out.println("Invalid priority level.");
            return;
        }
        System.out.println("=== Volunteers with "
                + priority.toUpperCase()
                + " Priority ===");
        int count = volunteerList
                .displayByPriority(
                        priority.toUpperCase());
        if (count == 0) {
            System.out.println("No volunteers found "
                    + "with " + priority + " priority.");
        }
    }

    public void undoLastOperation() {
        if (operationStack.isEmpty()) {
            System.out.println("Nothing to undo. "
                    + "No operations recorded.");
            return;
        }
        System.out.println("\n=== UNDO OPERATION ===");
        System.out.println("Last operation: "
                + operationStack.peek());

        String lastOperation = operationStack.pop();
        String[] parts = lastOperation.split(":");
        String operationType = parts[0];

        switch (operationType) {
            case "REGISTER":
                undoRegister(parts);
                break;
            case "ASSIGN":
                undoAssign(parts);
                break;
            case "REMOVE":
                undoRemove(parts);
                break;
            default:
                System.out.println("Unknown operation "
                        + "type: " + operationType);
        }
        System.out.println("=== UNDO COMPLETE ===\n");
    }

    private void undoRegister(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            String name = parts[2];
            boolean removed =
                    volunteerList.removeVolunteer(id);
            if (removed) {
                System.out.println("✓ Undo REGISTER: "
                        + "Volunteer '" + name
                        + "' (ID:" + id
                        + ") has been removed.");
            } else {
                System.out.println("✗ Undo REGISTER "
                        + "failed: Volunteer ID "
                        + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error during undo "
                    + "REGISTER: " + e.getMessage());
        }
    }

    private void undoAssign(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            String name = parts[2];
            String task = parts[3];
            Volunteer volunteer =
                    volunteerList.searchById(id);
            if (volunteer != null) {
                volunteer.setAssigned(false);
                volunteer.setTaskAssigned("Unassigned");
                waitingQueue.enqueue(volunteer);
                System.out.println("✓ Undo ASSIGN: "
                        + "Volunteer '" + name
                        + "' unassigned from '"
                        + task
                        + "' and returned to queue.");
            } else {
                System.out.println("✗ Undo ASSIGN "
                        + "failed: Volunteer '"
                        + name + "' not found.");
            }
        } catch (Exception e) {
            System.out.println("Error during undo "
                    + "ASSIGN: " + e.getMessage());
        }
    }

    private void undoRemove(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            String name = parts[2];
            String task = parts[3];
            String priority = parts[4];
            boolean assigned =
                    Boolean.parseBoolean(parts[5]);

            Volunteer existing =
                    volunteerList.searchById(id);
            if (existing != null) {
                System.out.println("✗ Undo REMOVE "
                        + "failed: Volunteer ID "
                        + id + " already exists.");
                return;
            }
            Volunteer restored = new Volunteer(
                    id, name, task, priority);
            restored.setAssigned(assigned);
            volunteerList.addVolunteer(restored);
            System.out.println("✓ Undo REMOVE: "
                    + "Volunteer '" + name
                    + "' (ID:" + id
                    + ") has been restored.");
        } catch (Exception e) {
            System.out.println("Error during undo "
                    + "REMOVE: " + e.getMessage());
        }
    }

    public void undoMultiple(int steps) {
        if (steps <= 0) {
            System.out.println("Please enter a "
                    + "positive number of steps.");
            return;
        }
        System.out.println("Undoing last "
                + steps + " operation(s)...");
        int completed = 0;
        while (completed < steps
                && !operationStack.isEmpty()) {
            undoLastOperation();
            completed++;
        }
        System.out.println("Completed " + completed
                + " undo operation(s).");
        if (completed < steps) {
            System.out.println("Note: Only " + completed
                    + " operations were available.");
        }
    }

    public void displayAllVolunteers() {
        volunteerList.displayVolunteers();
    }

    public void displayWaitingQueue() {
        waitingQueue.displayQueue();
    }

    public void displayOperationHistory() {
        operationStack.displayStack();
    }
}
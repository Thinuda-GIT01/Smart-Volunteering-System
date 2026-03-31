public class CustomLinkedList {

    private Node head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addVolunteer(Volunteer volunteer) {
        Node newNode = new Node(volunteer);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Volunteer registered: "
                + volunteer.getVolunteerName());
    }

    public boolean removeVolunteer(int volunteerId) {
        if (head == null) {
            System.out.println("List is empty.");
            return false;
        }
        if (head.data.getVolunteerId() == volunteerId) {
            head = head.next;
            size--;
            System.out.println("Volunteer removed successfully.");
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getVolunteerId()
                    == volunteerId) {
                current.next = current.next.next;
                size--;
                System.out.println(
                        "Volunteer removed successfully.");
                return true;
            }
            current = current.next;
        }
        System.out.println("Volunteer not found.");
        return false;
    }

    public Volunteer searchById(int volunteerId) {
        Node current = head;
        while (current != null) {
            if (current.data.getVolunteerId()
                    == volunteerId) {
                return current.data;
            }
            current = current.next;
        }
        System.out.println("Volunteer not found.");
        return null;
    }

    public Volunteer searchByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.data.getVolunteerName()
                    .equalsIgnoreCase(name)) {
                return current.data;
            }
            current = current.next;
        }
        System.out.println("Volunteer not found.");
        return null;
    }

    public void displayVolunteers() {
        if (head == null) {
            System.out.println("No volunteers registered.");
            return;
        }
        System.out.println("===== Volunteer List =====");
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". "
                    + current.data.toString());
            current = current.next;
            count++;
        }
        System.out.println("Total volunteers: " + size);
    }

    public Volunteer findFirstUnassignedByPriority(
            String priority) {
        Node current = head;
        while (current != null) {
            Volunteer v = current.data;
            if (v.getPriorityLevel()
                    .equalsIgnoreCase(priority) &&
                    !v.isAssigned()) {
                return v;
            }
            current = current.next;
        }
        return null;
    }

    public int displayByPriority(String priority) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data.getPriorityLevel()
                    .equalsIgnoreCase(priority)) {
                System.out.println("  "
                        + current.data.toString());
                count++;
            }
            current = current.next;
        }
        return count;
    }

    public int getSize() {
        return size;
    }
}
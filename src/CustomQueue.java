public class CustomQueue {

    private Node front;
    private Node back;
    private int size;

    public CustomQueue() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    public void enqueue(Volunteer volunteer) {
        Node newNode = new Node(volunteer);

        if (back == null){
            front = newNode;
            back = newNode;
        }else {
            back.next = newNode;
            back = newNode;
        }
        size++;
        System.out.println(volunteer.getVolunteerName() + " added to waiting queue.");

        }
    public Volunteer dequeue() {
        if (front == null){
            System.out.println("Queue is empty. No volunteers waiting.");
            return null;
        }
        Volunteer volunteer = front.data;
        front = front.next;

        if (front == null){
            back = null;
        }
        size--;
        System.out.println(volunteer.getVolunteerName() + " removed from waiting queue for assignment.");
        return volunteer;
    }

    public Volunteer peek() {
        if (front == null){
            System.out.println("Queue is empty. No volunteers waiting.");
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }
    public int size() {
        return size;
    }

    public void displayQueue() {
        if (front == null){
            System.out.println("No volunteers in queue");
            return;
        }
        System.out.println("****Waiting for queue****");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println("Position " + position + ": " + current.data.toString());
            current = current.next;
            position++;
        }
        System.out.println("Total waiting in queue: " + size);
    }
}

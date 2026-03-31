public class Volunteer {

    private int volunteerId;
    private String volunteerName;
    private String taskAssigned;
    private String priorityLevel;
    private boolean assigned;

    // Constructor
    public Volunteer(int volunteerId, String volunteerName, String taskAssigned, String priorityLevel) {
        this.volunteerId = volunteerId;
        this.volunteerName = volunteerName;
        this.taskAssigned = taskAssigned;
        this.priorityLevel = priorityLevel;
        this.assigned = false;
    }

    // Getters
    public int getVolunteerId() {
        return volunteerId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public String getTaskAssigned() {
        return taskAssigned;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public boolean isAssigned() {
        return assigned;
    }

    // Setters
    public void setTaskAssigned(String taskAssigned) {
        this.taskAssigned = taskAssigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    // toString method
    @Override
    public String toString() {
        return "ID: " + volunteerId +
                " | Name: " + volunteerName +
                " | Task: " + taskAssigned +
                " | Priority: " + priorityLevel +
                " | Assigned: " + assigned;
    }
}
public class CustomStack {
    private class StackNode{
        String operation;
        StackNode next;


        StackNode(String operation){
            this.operation = operation;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public CustomStack(){
        this.top = null;
        this.size = 0;
    }


    public void push(String operation){
        StackNode newNode = new StackNode(operation);

        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Operation Recorded." + operation);
    }

    public String pop(){
        if (top == null){
            System.out.println("Nothing to Undo. Stack is empty");
            return null;

        }

        String operation = top.operation;
        top = top.next;
        size--;

        System.out.println("Undoing Operation" + operation);
        return operation;
    }

    public String peek(){
        if (top == null){
            System.out.println("Stack is empty");
            return null;
        }
        return top.operation;
    }
    public boolean isEmpty(){
        return top == null;
    }
    public int getSize(){
        return size;
    }

    public void displayStack(){
        if (top == null){
            System.out.println("No operations recorded");
            return;
        }

        System.out.println("***** Operation History *****");
        StackNode current = top;
        int count = 1;
        while (current != null){
            System.out.println(count + ". " + current.operation);
            current = current.next;
            count++;

        }
        System.out.println("Total operations recorded: " + size);
    }

}

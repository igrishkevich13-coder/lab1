package lab1;
import java.util.Scanner;

public class CardArrangementNodes {
    static class Node {
        String color;
        Node next;
        Node(String color) {
            this.color = color;
            this.next = null;
        }
    }
    static class Stack {
        Node head;
        Node tail;
        int size;
        Stack() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }
        void pushTop(String color) {
            Node newNode = new Node(color);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }
        void pushBottom(String color) {
            Node newNode = new Node(color);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }
        String popTop() {
            if (head == null) return null;
            String color = head.color;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return color;
        }
        String popBottom() {
            if (head == null) return null;
            if (head == tail) {
                String color = head.color;
                head = null;
                tail = null;
                size--;
                return color;
            }
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            String color = tail.color;
            current.next = null;
            tail = current;
            size--;
            return color;
        }
        boolean isEmpty() {
            return head == null;
        }
        void print() {
            Node current = head;
            int index = 1;
            while (current != null) {
                System.out.println(index + ": " + current.color);
                current = current.next;
                index++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = 0;
        boolean validInput = false;
        
        while (!validInput) {
        	System.out.print("Введите количество карточек (n): ");
        	if (scanner.hasNextInt()) {
        		n = scanner.nextInt();
        		if (n <= 0) {
        			System.out.println("Введите корректное число");
                } else {
                    validInput = true;
                }
        	} else {
        		System.out.println("Введите корректное число");
        		scanner.next();
        	}
        }
        Stack result = findInitialArrangement(n);
        System.out.println("\nИсходное расположение карточек в стопке (сверху вниз):");
        result.print();
        System.out.println("\nПроверка - как карточки лягут на стол:");
        printSimulation(result);
        scanner.close();
    }
    public static Stack findInitialArrangement(int n) {
        String[] desiredOnTable = new String[n];
        for (int i = 0; i < n; i++) {
            desiredOnTable[i] = (i % 2 == 0) ? "Белая" : "Черная";
        }
        Stack stack = new Stack();
        for (int i = n - 1; i >= 0; i--) {
            if (!stack.isEmpty()) {
                String bottomCard = stack.popBottom();
                stack.pushTop(bottomCard);
            }
            stack.pushTop(desiredOnTable[i]);
        }
        return stack;
    }
    public static void printSimulation(Stack initialStack) {
        Stack stack = new Stack();
        Node current = initialStack.head;
        while (current != null) {
            stack.pushBottom(current.color);
            current = current.next;
        }
        StringBuilder result = new StringBuilder();
        boolean putOnTable = true;
        boolean first = true;
        while (!stack.isEmpty()) {
            String card;
            if (putOnTable) {
                card = stack.popTop();
                if (!first) result.append(", ");
                result.append(card);
                first = false;
            } else {
                card = stack.popTop();
                stack.pushBottom(card);
            }
            putOnTable = !putOnTable;
        }
        System.out.println(result.toString());
    }
}
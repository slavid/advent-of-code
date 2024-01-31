import java.util.*;

public class Test {

    public static Stack<String> stack1 = new Stack<String>();
    public static Stack<String> stack2 = new Stack<String>();
    public static Stack<String> stack3 = new Stack<String>();
    public static Stack<String> stack4 = new Stack<String>();
    public static Stack<String> stack5 = new Stack<String>();
    public static Stack<String> stack6 = new Stack<String>();
    public static Stack<String> stack7 = new Stack<String>();
    public static Stack<String> stack8 = new Stack<String>();
    public static Stack<String> stack9 = new Stack<String>();

    public static Stack<String>[] listado = new Stack[10];

    public static void main(String[] args) throws EmptyStackException {
        try {
            stack1.push("C");
            stack1.push("Z");
            stack1.push("N");
            stack1.push("B");
            stack1.push("M");
            stack1.push("W");
            stack1.push("Q");
            stack1.push("V");

            stack2.push("H");
            stack2.push("Z");
            stack2.push("R");
            stack2.push("W");
            stack2.push("C");
            stack2.push("B");

            stack3.push("F");
            stack3.push("Q");
            stack3.push("R");
            stack3.push("J");

            stack4.push("Z");
            stack4.push("S");
            stack4.push("W");
            stack4.push("H");
            stack4.push("F");
            stack4.push("N");
            stack4.push("M");
            stack4.push("T");

            stack5.push("G");
            stack5.push("F");
            stack5.push("W");
            stack5.push("L");
            stack5.push("N");
            stack5.push("Q");
            stack5.push("P");

            stack6.push("L");
            stack6.push("P");
            stack6.push("W");

            stack7.push("V");
            stack7.push("B");
            stack7.push("D");
            stack7.push("R");
            stack7.push("G");
            stack7.push("C");
            stack7.push("Q");
            stack7.push("J");

            stack8.push("Z");
            stack8.push("Q");
            stack8.push("N");
            stack8.push("B");
            stack8.push("W");

            stack9.push("H");
            stack9.push("L");
            stack9.push("F");
            stack9.push("C");
            stack9.push("G");
            stack9.push("T");
            stack9.push("J");

            System.out.println("Estado original: ");
            System.out.println(stack1.toString());
            System.out.println(stack2.toString());
            System.out.println(stack3.toString());
            System.out.println(stack4.toString());
            System.out.println(stack5.toString());
            System.out.println(stack6.toString());
            System.out.println(stack7.toString());
            System.out.println(stack8.toString());
            System.out.println(stack9.toString());

            listado[1] = stack1;
            listado[2] = stack2;
            listado[3] = stack3;
            listado[4] = stack4;
            listado[5] = stack5;
            listado[6] = stack6;
            listado[7] = stack7;
            listado[8] = stack8;
            listado[9] = stack9;
            while (!listado[1].isEmpty()) {
                listado[1].pop();
            }
            System.out.println(stack1.toString());
            listado[1].push("Test");
            System.out.println(stack1.toString());
            String a = listado[1].pop();
            listado[1].pop();
            System.out.println(listado[1]);
            System.out.println(a);

        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

    }
}

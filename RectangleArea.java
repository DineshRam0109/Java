import java.util.*;
public class RectangleArea {
public static void main(String[] args) {
Scanner s = new Scanner(System.in);
System.out.print("Enter the length of the rectangle: ");
double length = s.nextDouble();
System.out.print("Enter the breadth of the rectangle: ");
double breadth = s.nextDouble();
System.out.println("The area of the rectangle is: " + length * breadth);
}
}
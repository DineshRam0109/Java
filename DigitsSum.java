import java.util.*;
class DigitsSum {
public static void main(String[] args) {
Scanner s = new Scanner(System.in);
System.out.println("Enter an integer between 0 and 1000:");
int number = s.nextInt();
int sum = 0;
while (number > 0) {
sum += number % 10;
number/= 10;
}
System.out.println("The sum of the digits is: " + sum);
}
}
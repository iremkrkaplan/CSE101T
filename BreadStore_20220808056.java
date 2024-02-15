import java.util.Scanner;
public class BreadStore_20220808056 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double cost;
        int piece = 0,bread;
        System.out.println("enter cost of breads:");
        cost = input.nextDouble();
        if (cost>0) {
            System.out.println("enter piece of bread :");
            piece = input.nextInt();
            if (piece > 0) {
                System.out.println("welcome we have " + piece + " loaves bread");
                System.out.println("cost of 1 bread is: " + cost);
                System.out.println("How many bread do you want? :");
                bread = input.nextInt();
                if ((piece>=bread)&&(bread > 0)) {
                    System.out.println("your cost is: " + (bread * cost));
                    int a = piece - bread;
                    System.out.println("we have " + a + " loaves of bread now");

                }
                else {
                    System.out.println("error");
                }
            } else {
                System.out.println("error");
            }
        }
        else {
            System.out.println("error");
        }

        }



        }

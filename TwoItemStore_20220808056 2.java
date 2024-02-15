import java.util.Scanner;
public class TwoItemStore_20220808056 {
    public static void main(String[] args) {
        String product1, product2;
        int answer;
        int tot2 = 0,totf1=0,totf2=0;
        int tot1 = 0, amount1=0, amount2=0;
        int piece1 = 0, piece2 = 0;
        Scanner input = new Scanner(System.in);
        //seller entering values
        System.out.println("enter the name of 1st product");
        product1 = input.nextLine().toLowerCase();
        System.out.println("enter the number of " + product1 + " we have ");
        int have1 = input.nextInt();
        System.out.println("enter the cost of 1 " + product1);
        int cost1 = input.nextInt();
        System.out.println("enter the name of 2nd product");
        product2 = input.next().toLowerCase();
        System.out.println("enter the number of " + product2 + " we have ");
        int have2 = input.nextInt();
        System.out.println("enter the cost of 1 " + product2);
        int cost2 = input.nextInt();

        //checking sellers inputs
        if (have2 > 0 && have1 > 0 && cost1 > 0 && cost2 > 0) {
        do {
            //customer entering what he/she want
            System.out.println("welcome to our store what would you like?");
            System.out.println("1-" + product1);
            System.out.println("2-" + product2);
            System.out.println("0- to checkout");
            answer = input.nextInt();


                //checking what customer wants
                if (answer == 1) {
                    System.out.println("how many " + product1 + " you want?");
                    piece1 = input.nextInt();
                    if (have1>=piece1+amount1)
                    amount1+=piece1;
                    //checking: is value correct?
                    if (have1 >= amount1 && piece1>0) {
                        tot1 = piece1 * cost1;
                        System.out.println(piece1 + " piece " + product1 + "'s cost is " + tot1);
                        totf1+=tot1;
                    } else if (piece1<=0) {
                        System.out.println("you entered invalid value please try again");
                    } else
                        System.out.println("we dont have that much "+product1+" we only have "+
                                have1);
                } else if (answer == 2) {
                    System.out.println("how many " + product2 + " you want?");
                    piece2 = input.nextInt();
                    if (have2>=piece2+amount2)
                    amount2+=piece2;
                    //checking: is value correct?
                    if (have2 >= amount2&&piece2>0) {
                        tot2 = piece2 * cost2;
                        System.out.println(piece2 + " piece " + product2 + "'s cost is " + tot2);
                        totf2+=tot2;
                    } else if (piece2<=0){
                        System.out.println("you entered invalid value please try again");
                    }
                    else
                        System.out.println("we dont have that much "+product2+" we only have "+
                                have2);
                } else if (answer == 0) {
                    System.out.println("exiting system have a nice day");
                }

        } while (answer != 0);


        int total = (totf1 + totf2);
        System.out.println("total due: " + total);
        //telling the answer to customer (total due or insufficient stock)
            System.out.println("we now have " + product1 + "=" + (have1 - amount1));

            System.out.println("we now have " + product2 + "=" + (have2 - amount2));

    }
        else System.out.println("error exiting system");
    }
}


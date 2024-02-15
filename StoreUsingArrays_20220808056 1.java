import java.util.Scanner;
//@irem 6 december
public class StoreUsingArrays_20220808056 {
    private static Scanner scanner= new Scanner(System.in);

        public static void main(String[] args) {

            String[] product = {"bread","cola","snickers","Ayran"};
           int[] quantity = {10,15,12,30};
           double[] price = {0.75,2.5,2.25,1};
           System.out.println("***customer service***");

            System.out.print("Welcome to our store, we have the following.");
            storeRun(product,quantity,price);
        }

        public static void storeRun(String[] product,int[] quantity, double[] price ) {
            Scanner input = new Scanner(System.in);
            double totalAmount = 0;
            capitalizeArray(product);
            int[] soldQuantity = new int[product.length];

            while (true) {
                int selected = menu(product, quantity, price);
                if (selected == 0) {
                    break;
                } else if (selected < 0) {
                    System.out.println("Error");
                } else {


                    System.out.println("how many product would you like?");
                    int productPiece = input.nextInt();
                    // soldQuantity[selected-1] += 1;
                    //totalAmount += productPiece*price[selected-1];
                    if (productPiece < 0 || productPiece > quantity[selected - 1]) {
                        System.out.println("error");
                        break;

                    } else {
                        totalAmount += productPiece * price[selected - 1];
                        quantity[selected - 1] -= productPiece;
                    }
                }
            }
            System.out.println("Customer Total");
            for (int i = 0; i < product.length; i++) {
                if (soldQuantity[i] == 0) {
                    continue;
                } else {
                    System.out.printf("%s - %d * %.2f = %.2f\n", product[i], soldQuantity[i], price[i], (soldQuantity[i] * price[i]));
                }
            }
            System.out.println("Total due to : " + totalAmount);


            System.out.println(returnedAmounts(totalAmount));


        }



        public static int menu(String[] product ,int[] quantity , double[] price ) {
            Scanner input = new Scanner(System.in);
            System.out.println("What you would like: ");
            for (int i = 0; i < product.length; i++) {
                System.out.printf("%d - for %s (%.2f) Tl\n", i + 1, product[i], price[i]);
            }
            System.out.println("0 - to checkout ");
            int selected = input.nextInt();
            return selected;
        }

    public static String returnedAmounts ( double amount)
    {
        double[] Tl = {0.01,0.05,0.1,0.25,0.5,1,5,10,20,50,100,200};
        int[] numLira = new int[Tl.length];
        String text = "";
        for (int i = Tl.length - 1; i >=0; i--) {
            if (amount >= Tl[i]) {
                int aa = (int) (amount / Tl[i]);
                amount %= Tl[i];
                numLira[i] += aa;
            }
        }
        int k=0;
        while(k<12){

            if(numLira[k]>0){
                text+=numLira[k]+"-"+Tl[k]+"\n";
                k++;
            }
            else{
                k++;
            }
        }

        return text;
    }

            public static String capitalizeString (String text)
            {
                String newLet = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
                return newLet;
            }
            public static void capitalizeArray (String[]item)
            {
                for (int i = 0; i < item.length; i++) {
                    item[i] = capitalizeString(item[i]);
                }
            }


        }





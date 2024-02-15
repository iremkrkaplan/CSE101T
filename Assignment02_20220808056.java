import java.util.Scanner;

public class Assignment02_20220808056 {
    public static void main(String[] args) {
        String[] category={"QUIZ","homework","MidTerm exam","Final exam"};
        int[] quantity= {4,3,1,1};
        int[] weight={10,20,30,40};
        calculateGrade(category,quantity,weight);

    }

    public static int menu(Scanner input,String[] list){
        String val;
        int int_val;
        for(int i =0;i<list.length;i++){
            System.out.println(i+"- "+ list[i]);
        }
        System.out.println("Q - to quit");
        val=input.next();
        input.nextLine();
        if(val.equals("Q") || val.equals("q")){
            return -1;
        }
        else{
            int_val =Integer.parseInt(val);
            if(int_val>=list.length || int_val<0){
                System.out.println("Invalid choice");
            }
        }
        return int_val;
    }
    public static void calculateGrade(String[] category,int[] quantity,int[] weight){

        if(!(category.length==quantity.length && category.length== weight.length)){
            System.out.println("Sizes do not match");
            return;
        }
        int total_weight=0;
        int gpa=0;
        for(int i =0;i<category.length;i++){
            if(!isQuantityValid(quantity[i])){
                System.out.println("Invalid quantity.");
                return;
            }
            total_weight+=weight[i];
            if(!isWeightValid(weight[i],total_weight)) {
                System.out.println("Invalid weight.");
                return;
            }
        }
        int len=0;
        for(int i =0;i< category.length;i++){
            len+=quantity[i];
        }
        int[] grade_arr=new int[len];
        formatCategoryName(category);
        String[] choice_array={"Enter all grades","Display Grade " +
                "Information","Change a single grade"};
        Scanner inp= new Scanner(System.in);
        while(true){
            System.out.println("Welcome to our university grade system.\nPlease enter " +
                    "a choice below:\n");

            int choice=menu(inp,choice_array);
            if(choice==-1) {
                System.out.println("Terminating program");
                break;
            }
            if(choice==0){
                int category_quantity;
                int idx=0;
                for(int i =0;i<category.length;i++){
                    category_quantity=quantity[i];
                    int count=1;
                    while(category_quantity>0){

                        System.out.println("Please enter grade for "+category[i]+" "+(count++));
                        int grade=inp.nextInt();
                        inp.nextLine();
                        if(!(grade<=100&&grade>=0)){
                            System.out.println("Bad input");
                            i=category.length;
                            break;
                        }
                        grade_arr[idx]=grade;
                        idx++;
                        category_quantity--;
                    }

                }
            }
            if(choice==1){
                int category_quantity;
                double [] avg_arr= new double[category.length];
                int index=0;
                for(int i =0;i<category.length;i++){ // to calculate average of grades per subject
                    double sum=0;
                    category_quantity=quantity[i];
                    while(category_quantity>0){
                        sum+=grade_arr[index];
                        index++;
                        category_quantity--;
                    }
                    avg_arr[i]=sum/(quantity[i]);
                }
                System.out.println("Category information:");
                for(int i=0;i< avg_arr.length;i++){//category
                    System.out.println(category[i]+" - "+avg_arr[i]);
                }
                double overall_grade=0;
                for(int i =0;i< avg_arr.length;i++){
                    overall_grade+=(avg_arr[i]*(weight[i]/100.0));
                }
                System.out.println("Overall grade:"+overall_grade);
             //   System.out.println("Grade letter:"+gradeLetter(overall_grade));
                System.out.println("GPA Points:"+gpaPoints(overall_grade));
                System.out.println("Status:"+status(overall_grade));

            }
            else if(choice==2){
                //change a single grade
                int selection=menu(inp,category);
                int index=-1;
                //for loop to adjust the index
                for(int i =0;i<selection;i++){
                    index+=quantity[i];
                }
                System.out.println("Which "+category[selection]+" would you like to change "+("("+1+"-"+(quantity[selection])+")"));
                int val=inp.nextInt();
                if(val>quantity[selection] || val<=0) {
                    System.out.println("Invalid input");
                }else {
                    index += val;
                    System.out.println("Enter the new grade value");
                    int grade=inp.nextInt();
                    if((grade<=100&&(grade>=0)))
                        grade_arr[index] = grade;
                    else{
                        System.out.println("Bad input");
                    }
                }
            }
            else if(choice!='\n')
                continue;
            else
                System.out.println("Wrong input");
/*
           System.out.println("Displaying everything");
            int index=0;
            for(int i =0;i< category.length;i++){
                System.out.println("Category:"+category[i]);
                int category_length=quantity[i];
                int count=0;
                while(category_length>0){
                    System.out.println(category[i]+(count+1)+" - "+grade_arr[index++]);
                    count++;
                    category_length--;
                }
            }*/
        }
    }
  /*  public static char gradeLetter(double grade) {
        public static String gradeLetter(double grade){
            while(true) {

                if (grade <= 100 && grade >= 88) {
                    return ("AA");
                } else if (grade >= 81 && grade <= 87) {
                    return ("BA");
                } else if (grade >= 74 && grade <= 80) {
                    return ("BB");
                } else if (grade >= 67 && grade <= 73) {
                    return ("BC");
                } else if (grade >= 60 && grade <= 66) {
                    return ("CC");
                } else if (grade >= 53 && grade <= 59) {
                    return ("DC");
                } else if (grade >= 46 && grade <= 52) {
                    return ("DD");
                } else if (grade >= 35 && grade <= 45) {
                    return ("FD");
                } else if (grade >= 0 && grade <= 34) {
                    return ("FF");
                } else {
                    return ("Something went wrong");
                }
            }

        }
    }
*/
    public static double gpaPoints(double grade) {
        if (grade<=100&&grade>=88){
            return 4.0;
        }else if (grade>=81&&grade<=87){
            return  3.5;
        }else if (grade>=74&&grade<=80){
            return 3;
        }else if (grade>=67&&grade<=73){
            return  2.5;
        }else if (grade>=60&&grade<=66){
            return  2;
        }else if (grade>=53&&grade<=59){
            return  1.5;
        }else if (grade>=46&&grade<=52){
            return  1.0;
        }else if (grade>=35&&grade<=45){
            return  0.5;
        }else return  0.0;}

    public static String status(double grade) {
        if (grade<=100&&grade>=60) {
            return ("passed");
        }else if (grade>=46&&grade<=59){
            return  ("conditionally passed");
        }else return  ("failed");
    }

    public static String formatCategoryName(String name){
        return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
    }
    public static void  formatCategoryName(String[] name){
        for(int i =0;i<name.length;i++) {
            name[i]= formatCategoryName(name[i]);
        }
    }
    public static boolean isQuantityValid(int quantity){
        return quantity>0;
    }
    public static boolean isWeightValid(int weight,int totalWeight){
        return (weight>=0)&&((totalWeight)<=100);
    }

}

import java.io.*;
public class StoreUsingFiles_20220808056 {
    public static void main(String[] args) throws Exception {



       
    }
    public static void getProductInfo(String[] itemID, String[] itemName, int[] quantity, double[] price, String filename) {
        try {
            // Open the file
            FileInputStream fstream = new FileInputStream(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            // Read the file line by line
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                // Split the line into fields
                String[] fields = line.split(" ");

                // Parse the fields and add the values to the arrays
                itemID[i] = fields[0];
                itemName[i] = fields[1];
                quantity[i] = Integer.parseInt(fields[2]);
                price[i] = Double.parseDouble(fields[3]);
                i++;
            }

            // Close the file
            br.close();
            fstream.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public static void writeProductInfo(int[] itemID, String[] itemName, int[] quantity, double[] price, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < itemID.length; i++) {
                String line = itemID[i] + " " + itemName[i] + " " + quantity[i] + " " + price[i];
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle the exception
        }
    }
    public static int countProducts(String filename){
                int count = 0;
                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    while (reader.readLine() != null) {
                        count++;
                    }
                } catch (IOException e) {
                    // Handle the exception
                }
                return count;

        }

    }



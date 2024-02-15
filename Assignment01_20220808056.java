import java.util.ArrayList;
import java.util.List;
//@20220808056irem & @since20.03.23


public class Assignment01_20220808056 {

    }


    //1st classsssssss
    class Product {
        private long Id;
        private String id;
        private String name;
        private int Quantity;
        private double price;

        //Product constructor
        public Product(String id, String name, int Quantity, double price) {
            this.price = price;
            this.Quantity = Quantity;
            this.name = name;
            this.id = id;
        }


        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            this.Quantity = quantity;
        }


        public int remaining() {
            return Quantity;
        }


        //addtostock
        public int addToInventory(int amount) {

            if (amount >= 0) {
                return Quantity += amount;
            } else return Quantity;
        }

        public double purchase(int amount) {
            if (amount < 0 || amount > Quantity) return 0;
            else {
                Quantity -= amount;
                return amount * price;
            }
        }

        public String toString() {
            return "Product " + name + " has " + Quantity + " remaining";
        }

     /*   public boolean equals(Product Object) {
            return true;
        } */

     /*   @Override
        public boolean equals(Object obj) {
            if (obj instanceof Product) {
                Product other = (Product) obj;
                return this.id.equals(other.id) &&
                        this.name.equals(other.name) &&
                        this.Quantity == other.Quantity &&
                        this.price == other.price;
            }
            return false;
        }
    }*/

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Product)) {
                return false;
            }
            Product other = (Product) o;
            return Math.abs(this.price - other.price) < 0.001;
        }
    }


        //2nd class

    class FoodProduct extends Product {
        //attributes
        private boolean dairy;
        private boolean eggs;
        private boolean peanuts;
        private boolean gluten;
        private int calories;

        public FoodProduct(String id, String name, int quantity, double price, int calories, boolean eggs, boolean dairy, boolean peanuts, boolean gluten) {
            super(id, name, quantity, price);
            this.calories = calories;
            this.eggs = eggs;
            this.peanuts = peanuts;
            this.dairy = dairy;
            this.gluten = gluten;

        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public boolean containsDairy() {
            return dairy;
        }

        public boolean containsEggs() {return eggs;}

        public boolean containsPeanuts() {
            return peanuts;
        }

        public boolean containsGluten() {
            return gluten;
        }

    }


    //3rd class

    class CleaningProduct extends Product {
        private boolean liquid;
        private String whereToUse;
        public CleaningProduct(String id, String name, int quantity, double price, boolean liquid, String whereToUse) {
            super(id, name, quantity, price);
            this.liquid = liquid;
            this.whereToUse = whereToUse;

        }

        public String getWhereToUse() {
            return whereToUse;
        }

        public void setWhereToUse(String whereToUse) {
            this.whereToUse = whereToUse;
        }

        public boolean isLiquid() {
            return liquid;
        }
    }

//4th class


    class Customer {
        protected String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    //5th class

   class ClubCustomer extends Customer {
       private String phone;
       private int points;

        public ClubCustomer(String name, String phone) {
            super(name);
            this.phone = phone;
            points = 0;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPoints() {
            return points;
        }

        public void addPoints(int points) {
            if (points < 0) return;
                this.points += points;
        }

        public String toString() {
            return name + " has " + points + " points";
        }

    }

    //6th class
    class Store {
        private String name;
        private String website;
        private List<Product> productList;


        public Store(String name, String website) {
            this.name = name;
            this.website = website;
            productList = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }


        public int getInventorySize() {
            return productList.size();
        }


        public void addProduct(Product product, int index) {

            if (index < 0 || index >= productList.size()) {
                productList.add(product);
            } else productList.add(index, product);
        }

        public void addProduct(Product product) {
            productList.add(product);
        }

        public Product getProduct(int index) {
            if (index < 0 || index > productList.size()) {
                return null;
            } else return productList.get(index);
        }

        public int getProductIndex(Product p) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).equals(p)) {
                    return i;
                }
            }
            return -1;
        }
    }

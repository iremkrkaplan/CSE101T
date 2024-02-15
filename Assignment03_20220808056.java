import java.util.*;

//@irem20220808056 @since30 may

public class Assignment03_20220808056 {
}

class Product {
    private long Id;
    private String Name;
    private double price;
    private int inventory;
    private int count;

    public Product(long id, String Name, double price, double quantity, int inventory) throws InvalidPriceException, InvalidAmountException {
        setId(id);
        setName(Name);
        setPrice(price);
        setInventory(inventory);

    }
    public Product(int count) {

        this.count = count;
    }

    public Product(long id, String Name, double price) {
        setId(id);
        setName(Name);
        setPrice(price);
    }

    public Product(long id, String name, int quantity, double price) {
        setId(id);
        setName(Name);
        setPrice(price);

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public long getId() {
        return Id;
    }
    public void setId(long Id) {this.Id = Id;}
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPrice() {
        return price;
    }
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) throws InvalidAmountException {
        if (inventory < 0) {
            throw new InvalidAmountException(inventory);
        }
        this.inventory = inventory;
    }

    public void setPrice(double price) throws InvalidPriceException {
        if (price < 0) {
            throw new InvalidPriceException(price);
        }
        this.price = price;
    }
    @Override
    public String toString(){
        return Id +"-" +Name+" @ " + price;
    }
}

class FoodProduct extends Product {
    private boolean dairy;
    private boolean eggs;
    private boolean peanuts;
    private boolean gluten;
    private int calories;

    public FoodProduct(long Id, String Name, int quantity, double price, int calories, boolean eggs, boolean dairy, boolean peanuts, boolean gluten,int inventory) throws InvalidAmountException, InvalidPriceException {
        super(Id, Name, quantity,price,inventory);
        this.calories = calories;
        this.eggs = eggs;
        this.peanuts = peanuts;
        this.dairy = dairy;
        this.gluten = gluten;}
    public FoodProduct(long Id, String Name, int quantity, double price, int calories, boolean eggs, boolean dairy, boolean peanuts) throws InvalidAmountException, InvalidPriceException {
        super(Id, Name, quantity,price);
        this.calories = calories;
        this.eggs = eggs;
        this.peanuts = peanuts;
        this.dairy = dairy;
        this.gluten = gluten;}

    public int getCalories() {
        return calories;
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


    public void setCalories(int calories) throws InvalidAmountException {
        if (calories < 0) {
            throw new InvalidAmountException(calories);
        }
        this.calories = calories;

    }
}
class CleaningProduct extends Product {
    private boolean liquid;
    private String whereToUse;
    public CleaningProduct(long Id, String Name, int quantity, double price, boolean liquid, String whereToUse,int inventory) throws InvalidPriceException, InvalidAmountException {
        super(Id, Name, quantity, price,inventory);
        this.liquid = liquid;
        this.whereToUse = whereToUse;
    }
    public CleaningProduct(long Id, String Name, int quantity, double price, boolean liquid, String whereToUse) throws InvalidPriceException, InvalidAmountException {
        super(Id, Name, quantity, price);
        this.liquid = liquid;
        this.whereToUse = whereToUse;
    }
    public CleaningProduct(long Id, String Name, int quantity, double price, boolean liquid) throws InvalidPriceException, InvalidAmountException {
        super(Id, Name, quantity, price);
        this.liquid = liquid;
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

class Customer {
    protected String Name;
    private ArrayList<Product> cart;
    private double totalDue;
    private String phone;


    public Customer(String Name) {
        this.Name = Name;
        this.cart = new ArrayList<Product>(cart);//PRODUCT,CART
        this.totalDue = 0.0;
    }

    public String getName() {
        return Name;
    }
    public String getPhone() {
        return phone;
    }


    public void addToCart(Store store,Product product, int count) {//throws InvalidAmountException
        if (store == null || product == null) {
            System.out.println("ERROR: Invalid store or product");
            return;
        }
        if (count <= 0) {
            System.out.println("ERROR: Invalid count");
            return;
        }

            /*   cart.add(product);
            totalDue=product.purchase(count);
            System.out.println(product.getName()+" - "+product.getPrice()+" x "+count);
            double totalPrice = product.getPrice() * count;
            receipt += product.getName() + " - " + product.getPrice() + " x " + count + "\n";
            this.totalDue += totalPrice;
            System.out.println(product.getName() + " - " + product.getPrice() + " x " + count);
        } catch (InvalidAmountException e) {
         /*    double totalPrice = product.getPrice() * count;
            this.totalDue += totalPrice;
            this.cart.add(new CartItem(product, count, totalPrice)); */
        ArrayList<Product> cart = cartMap.get(store);

        try {
            if (cart == null) {
                cart = new ArrayList<>();
                cartMap.put(store, cart);
            }
            for (int i = 0; i < count; i++) {
                cart.add(product);
            }
        } catch (InvalidAmountException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (ProductNotFoundException e)  {
             System.out.println("ERROR: " + e.getMessage());
        }
    }


    public String receipt(Store store) {
        ArrayList<Product> cart = cartMap.get(store);
        //if (cart.contains(store))
       // if (!store.hasCart(this)) {
            if (cart == null ||cart.isEmpty()) {
            throw new StoreNotFoundException("Cart not found for store: " + store.getName());
        }

           // throw new StoreNotFoundException("No cart found for the store: " + name);

        StringBuilder sb = new StringBuilder();
        sb.append("Store: ").append(store.getName()).append(" Receipt\n\n");

        double total = 0;
        for (Product product : cart) {
            sb.append(product.getId())
                    .append(" - ")
                    .append(product.getName())
                    .append("@")
                    .append(product.getPrice())
                    .append(" X ")
                    .append(product.getPrice())
                    .append(" ... ")
                    .append(product.getPrice())
                    .append("\n");
            total += product.getPrice();
        }
        sb.append("Total Due - ").append(total);
        return sb.toString();
    }

    private Map<Store, ArrayList<Product>> cartMap;
    public Customer() {
        cartMap = new HashMap<Store,ArrayList<Product>>();
    }

    public double getTotalDue(Store store) {
        if (!cartMap.containsKey(store)) {
            throw new StoreNotFoundException("Cart not found for store: " + store.getName());
        }
        ArrayList<Product> cart = cartMap.get(store);
        double totalDue = 0;
      //  for (CartItem item : cart.getItems()) {
        for (Product product : cart) {
            //totalDue += item.getTotalPrice();
            totalDue += product.getPrice();
        }
        return totalDue;
    }
   /* public int getPoints(Store store) throws StoreNotFoundException {
        int totalPoints = 0;

        for (Customer customer : store.Customers()) {
            try {
                totalPoints += store.getCustomerPoints(customer);
            } catch (CustomerNotFoundException e) {
                // Handle the exception if needed
            }
        }

        return totalPoints;
    }*/
   public int getPoints(Store store) throws StoreNotFoundException {
       int totalPoints = 0;
       Set<Customer> customers = store.getCustomers();

       if (!cartMap.containsKey(store)) {
           throw new StoreNotFoundException("Customer does not have a cart for the passed Store"+ store.getName());
       }
       for (Customer customer : customers) {
           try {
               totalPoints += store.getCustomerPoints(customer);
           } catch (CustomerNotFoundException e) {
               System.out.println(e.getMessage());
               throw new StoreNotFoundException("Customer not found in the store");
           }
       }
       return totalPoints;
   }

    public double pay(Store store,double amount,boolean usePoints) throws InsufficientFundsException , StoreNotFoundException {

        if (!cartMap.containsKey(store)) {
            throw new StoreNotFoundException("Customer does not have a cart for the passed Store"+store.getName());
        }
        if (amount >= totalDue) {
            double change = amount - totalDue;
            System.out.println("Thank you");
            cartMap.get(store).clear();
            totalDue=0;


            if (usePoints) {
                Customer customerToCheck = new Customer(/* customer özellikleri */);
                boolean isInSet = false;
                for (Customer customer : customers) {
                    if (customer.equals(customerToCheck)) {
                        isInSet = true;
                        break;
                    }
                }

            }
            return change;
            }
         else {
            throw new InsufficientFundsException(totalDue,amount);
        }
    }
    Set<Customer> customers = new HashSet<>();
}

class Store {
    private String Name;
    private String website;
    private Set<Customer> customers;
    private Set<Product> products;
    private Map<String, Integer> customerPoints;



    public Store(String Name, String website) {
        this.Name = Name;
        this.website = website;
        this.customers = new HashSet<>();
        this.products = new HashSet<>();
        customerPoints = new HashMap<>();
            customers = new HashSet<>();

    }
    public int getCount() {
        return products.size();
    }
 public Set<Customer> getCustomers() {
        return customers;
    }

    public double purchase(Product product, int amount) throws InvalidAmountException, ProductNotFoundException {
        if (amount <= 0 || amount > product.getCount()) {
            throw new InvalidAmountException(amount);
        }

        Product foundProduct = null;
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                foundProduct = p;
                break;
            }
        }

        if (foundProduct == null) {
            throw new ProductNotFoundException(foundProduct.getName());
        }

        foundProduct.setCount(foundProduct.getCount() - amount);
        return amount * foundProduct.getPrice();
    }



    public int getCustomerPoints(Customer customer) throws CustomerNotFoundException {
        if (!customerPoints.containsKey(customer.getName())) {
            throw new CustomerNotFoundException(customer.getName(), customer);
        }
        return customerPoints.get(customer.getName());
    }



    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getInventorySize() {
        return products.size();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

  /*  public Product getProduct(long id) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found with ID: " + id);
    }

    public Product getProduct(String Name) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getName().equals(Name)) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found with name: " + Name);
    }  */

    public void addCustomer(Customer customer) {
        customerPoints.put(customer.getName(), 0);
        customers.add(customer);

    }
    public int getProductCount(Product product) throws ProductNotFoundException {
        int count = 0;
        for (Product p : products) {
            if (p.equals(product)) {
                count++;
            }
        }

        if (count == 0) {
            throw new ProductNotFoundException("Product not found"+product.getName());
        }

        return count;
    }


    /*  public ClubCustomer getCustomer(String phone) throws CustomerNotFoundException {
        for (ClubCustomer customer : customers) {
            if (customer.getPhone().equals(phone)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer not found with phone number: " + phone,,,,,,,9;
    }
 */


    public void removeProduct(Product product) throws ProductNotFoundException {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            if (currentProduct.equals(product)) {
                iterator.remove();
                return;
            }
        }
        throw new ProductNotFoundException("Product not found: " + product.getName());
    }


    public void removeCustomer(String phone) throws CustomerNotFoundException {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getPhone().equals(phone)) {
                iterator.remove();
                return;
            }
        }
        throw new CustomerNotFoundException("Customer not found with phone number: " + phone);
    }
    public Store() {
        products = new HashSet<>();
    }
    public void addToInventory(Product product, int amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (!products.contains(product)) {
            product.setInventory(amount);
            products.add(product);
        }else{
            for (Product p : products) {
                if (p.equals(product)) {
                    int currentInventory = p.getInventory();
                    p.setInventory(currentInventory + amount);
                    break;
                }}
        }
        }

}
/*class CustomerNotFoundException extends Exception {
    private final String phone;
    public CustomerNotFoundException(String phone) {
        this.phone=phone;
    }
}
 class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
} */











//exceptions
class CustomerNotFoundException extends IllegalArgumentException {
    private final String phone;
    private Customer customer;

    public CustomerNotFoundException(String phone,Customer customer) {
        this.phone = phone;
        this.customer=customer;
    }
//bunu yapabiliyor muyum???????
    public CustomerNotFoundException(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CustomerNotFoundException: Name - " + customer.getName();
    }
}

class InsufficientFundsException extends RuntimeException {
    private final double total;
    private final double payment;

    public InsufficientFundsException(double total, double payment) {
        this.total = total;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "InsufficientFundsException: " + total + " due, but only " + payment + " given";
    }
}

class InvalidAmountException extends RuntimeException {
    private int amount;
    private int quantity;
    public InvalidAmountException(int amount) {
        this.amount = amount;
        this.quantity = 0;
    }

    public InvalidAmountException(int amount, int quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        if (quantity == 0) {
            return "InvalidAmountException: " + amount;
        } else {
            return "InvalidAmountException: " + amount + " was requested, but only " + quantity + " remaining";
        }
    }
}

/*class InvalidPriceException extends RuntimeException {
   private double price;
    public InvalidPriceException(String message) {
        super(message);
    }

   public InvalidPriceException(double price) {
       this.price = price;
   }

   @Override
   public String toString() {
       return "InvalidPriceException: " + price;
   }
}  */
class InvalidPriceException extends RuntimeException {
    private double price;
    public InvalidPriceException(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

   /*  public void setPrice(double price) throws InvalidPriceException {
         if (price < 0) {
             throw new InvalidPriceException();
         }
         this.price = price;
     }*/

    @Override
    public String toString() {
        return "InvalidPriceException: " + getMessage();
    }
}

class StoreNotFoundException extends IllegalArgumentException{
    private String Name;
    public StoreNotFoundException(String Name) {
        this.Name = Name;
    }
    @Override
    public String toString(){
        return "StoreNotFoundException: "+ Name;
    }
}
class ProductNotFoundException extends IllegalArgumentException {
    private Product product;
    private final long ID;
    private final String Name;

    public ProductNotFoundException(long ID,String name,Product product) {
        this.ID = ID;
        this.Name = null;
        this.product=product;
    }

    public ProductNotFoundException(String Name) {
        this.ID = -1L;
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "ProductNotFoundException: ID - " + ID+" name - " + Name;
    }
}




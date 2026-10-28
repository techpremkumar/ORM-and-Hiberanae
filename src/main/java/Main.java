
public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer(
                101,
                "Arun",
                "arun@gmail.com"
        );

        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Email: " + customer.getEmail());
    }
}

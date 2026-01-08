import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1 - Add Book");
            System.out.println("2 - Add Phone");
            System.out.println("3 - View All Products");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Book name: ");
                String name = scanner.nextLine();
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                products.add(new Book(name, price));
            }

            if (choice == 2) {
                System.out.print("Phone name: ");
                String name = scanner.nextLine();
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                products.add(new Phone(name, price));
            }

            if (choice == 3) {
                for (Product p : products) {
                    if (p instanceof Book) {
                        System.out.println("This product is a book");
                    }
                    if (p instanceof Phone) {
                        System.out.println("This product is a phone");
                    }
                    System.out.println(p);
                }
            }

        } while (choice != 0);

        System.out.println("Goodbye!");
    }
}

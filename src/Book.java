public class Book extends Product {

    public Book(String name, double price) {
        super(name, price);
    }

    @Override
    public double getFinalPrice() {
        return price * 0.9;
    }
}

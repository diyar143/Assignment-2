public class Phone extends Product {

    public Phone(String name, double price) {
        super(name, price);
    }

    @Override
    public double getFinalPrice() {
        return price * 0.8;
    }
}

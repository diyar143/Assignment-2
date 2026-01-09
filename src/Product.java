public class Product {

    protected String name;
    protected double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "No name";
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            this.price = 1;
        }
    }

    public double getFinalPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " | Final price: " + getFinalPrice();
    }
}

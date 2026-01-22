package model;
public class MenuItem implements Cookable {
    private String name;
    private double price;
    public MenuItem(String name, double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        this.name = name;
        this.price = price;
    }
    @Override public void cook() { System.out.println("Cooking " + name + "..."); }
    @Override public String getRecipe() { return "Recipe for " + name; }
    @Override public String toString() { return name + " - $" + price; }
}
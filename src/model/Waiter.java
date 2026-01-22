package model;
public class Waiter extends Staff {
    private int tables;
    public Waiter(int id, String name, int tables) {
        super(id, name);
        this.tables = tables;
    }
    @Override public void work() { System.out.println("Waiter " + getName() + " is serving " + tables + " tables"); }
    @Override public String getRole() { return "Waiter"; }
}
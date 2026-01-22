package model;
public class Chef extends Staff {
    private String specialization;
    public Chef(int id, String name, String spec) {
        super(id, name);
        this.specialization = spec;
    }
    @Override public void work() { System.out.println("Chef " + getName() + " is cooking " + specialization); }
    @Override public String getRole() { return "Chef"; }
}
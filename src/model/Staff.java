package model;
public abstract class Staff {
    private int id;
    private String name;

    public Staff(int id, String name) {
        setId(id);
        setName(name);
    }
    public abstract void work();
    public abstract String getRole();
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }
    public void setName(String name) {
        if (name == null || name.length() < 2) throw new IllegalArgumentException("Name too short");
        this.name = name;
    }
    public String getName() { return name; }
    public int getId() { return id; }
    @Override
    public String toString() { return "[" + getRole() + "] ID: " + id + ", Name: " + name; }
}
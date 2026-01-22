package menu;
import model.*;
import exception.*;
import java.sql.*;
import java.util.*;

public class RestaurantMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n1. Add Chef\n2. Add Waiter\n3. View All Staff\n4. View Chefs Only\n5. View Waiters Only\n6. Make All Staff Work\n7. Add Menu Item\n8. View All Menu Items\n9. Cook Item\n0. Exit");
    }

    @Override
    public void run() {
        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) break;
                handleChoice(choice);
            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    private void handleChoice(int choice) throws Exception {
        try (Connection conn = DatabaseConfig.getConnection()) {
            switch (choice) {
                case 1 -> addStaff(conn, "Chef");
                case 2 -> addStaff(conn, "Waiter");
                case 3 -> getStaff(conn, null).forEach(System.out::println);
                case 4 -> getStaff(conn, "Chef").forEach(System.out::println);
                case 5 -> getStaff(conn, "Waiter").forEach(System.out::println);
                case 6 -> getStaff(conn, null).forEach(Staff::work);
                case 7 -> addMenuItem(conn);
                case 8 -> getItems(conn).forEach(System.out::println);
                case 9 -> cook(conn);
                default -> throw new InvalidInputException("Invalid choice");
            }
        }
    }

    private void addStaff(Connection conn, String role) throws SQLException {
        System.out.print("Name: "); String name = scanner.nextLine();
        String sql = "INSERT INTO staff (name, role, specialization, tables_served) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name); ps.setString(2, role);
            if (role.equals("Chef")) {
                System.out.print("Spec: "); ps.setString(3, scanner.nextLine()); ps.setNull(4, Types.INTEGER);
            } else {
                ps.setNull(3, Types.VARCHAR); System.out.print("Tables: "); ps.setInt(4, Integer.parseInt(scanner.nextLine()));
            }
            ps.executeUpdate();
        }
    }

    private List<Staff> getStaff(Connection conn, String role) throws SQLException {
        List<Staff> list = new ArrayList<>();
        String sql = (role == null) ? "SELECT * FROM staff" : "SELECT * FROM staff WHERE role = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (role != null) ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("Chef"))
                    list.add(new Chef(rs.getInt("id"), rs.getString("name"), rs.getString("specialization")));
                else
                    list.add(new Waiter(rs.getInt("id"), rs.getString("name"), rs.getInt("tables_served")));
            }
        }
        return list;
    }

    private void addMenuItem(Connection conn) throws SQLException {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Price: "); double price = Double.parseDouble(scanner.nextLine());
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO menu_items (name, price) VALUES (?, ?)")) {
            ps.setString(1, name); ps.setDouble(2, price); ps.executeUpdate();
        }
    }

    private List<MenuItem> getItems(Connection conn) throws SQLException {
        List<MenuItem> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM menu_items")) {
            while (rs.next()) list.add(new MenuItem(rs.getString("name"), rs.getDouble("price")));
        }
        return list;
    }

    private void cook(Connection conn) throws Exception {
        List<MenuItem> items = getItems(conn);
        for (int i = 0; i < items.size(); i++) System.out.println(i + ". " + items.get(i));
        items.get(Integer.parseInt(scanner.nextLine())).cook();
    }
}
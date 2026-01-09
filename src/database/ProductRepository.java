package database;

import model.Product;
import model.Book;
import model.Phone;

public class ProductRepository {

    public static void saveProduct(Product product) {

        try (Connection conn = DatabaseManager.getConnection()) {

            String sql =
                    "INSERT INTO products (type, name, price) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            if (product instanceof Book) {
                ps.setString(1, "Book");
            } else if (product instanceof Phone) {
                ps.setString(1, "Phone");
            } else {
                ps.setString(1, "Product");
            }

            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());

            ps.executeUpdate();

            System.out.println("Product saved to database");

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void showAllProducts() {

        try (Connection conn = DatabaseManager.getConnection()) {

            ResultSet rs = conn.createStatement()
                    .executeQuery("SELECT * FROM products");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("type") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }

        } catch (Exception e) {
            System.out.println("Database error");
        }
    }
}

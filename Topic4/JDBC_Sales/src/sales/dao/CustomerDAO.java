package sales.dao;

import java.sql.*;
import java.util.ArrayList;
import sales.entities.Customer;

public class CustomerDAO {

    private final Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Lấy tất cả khách hàng
    public ArrayList<Customer> selectAll() throws SQLException {
        if (conn == null) return null;

        String sql = "SELECT * FROM customers";
        ArrayList<Customer> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customer_id"));
                c.setName(rs.getString("customer_name"));
                c.setContact(rs.getString("contact_name"));
                c.setAddress(rs.getString("address"));
                c.setCity(rs.getString("city"));
                c.setPostCode(rs.getString("post_code"));
                c.setCountry(rs.getString("country"));

                list.add(c);
            }
        }

        return list;
    }

    // 2. Thêm khách hàng
    public boolean insert(Customer customer) throws SQLException {
        if (conn == null) return false;

        String sql = "INSERT INTO customers(customer_name, contact_name, address, city, post_code, country) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());

            return ps.executeUpdate() > 0;
        }
    }

    // 3. Cập nhật khách hàng
    public boolean update(int id, Customer customer) throws SQLException {
        if (conn == null) return false;

        String sql = "UPDATE customers SET customer_name=?, contact_name=?, address=?, city=?, post_code=?, country=? WHERE customer_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, id);

            return ps.executeUpdate() > 0;
        }
    }

    // 4. Xóa khách hàng
    public boolean delete(int id) throws SQLException {
        if (conn == null) return false;

        String sql = "DELETE FROM customers WHERE customer_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
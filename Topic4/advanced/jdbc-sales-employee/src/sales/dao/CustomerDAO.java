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
        if (conn == null) {
            return null;
        }

        String select = "select * from customers";
        ArrayList<Customer> customers = new ArrayList<>();

        try (
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(select);
        ) {
            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setContact(rs.getString("contact_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setPostCode(rs.getString("post_code"));
                customer.setCountry(rs.getString("country"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new SQLException("Can not display customers: " + e.getMessage());
        }

        return customers;
    }

    // 2. Thêm khách hàng
    public boolean insert(Customer customer) throws SQLException {
        if (conn == null) {
            return false;
        }

        String insert = "insert into customers(customer_name, contact_name, address, city, post_code, country) values (?, ?, ?, ?, ?, ?)";

        int index = 1;

        try (PreparedStatement ps = conn.prepareStatement(insert)) {

            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getContact());
            ps.setString(index++, customer.getAddress());
            ps.setString(index++, customer.getCity());
            ps.setString(index++, customer.getPostCode());
            ps.setString(index++, customer.getCountry());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Can not insert customer: " + e.getMessage());
        }
    }

    // 3. Cập nhật khách hàng
    public boolean update(int id, Customer customer) throws SQLException {
        if (conn == null) {
            return false;
        }

        String update = "update customers set customer_name = ?, contact_name = ?, address = ?, city = ?, post_code = ?, country = ? where customer_id = ?";

        int index = 1;

        try (PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getContact());
            ps.setString(index++, customer.getAddress());
            ps.setString(index++, customer.getCity());
            ps.setString(index++, customer.getPostCode());
            ps.setString(index++, customer.getCountry());
            ps.setInt(index++, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Can not update customer: " + e.getMessage());
        }
    }

    // 4. Xóa khách hàng
    public boolean delete(int id) throws SQLException {
        if (conn == null) {
            return false;
        }

        String delete = "delete from customers where customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Can not delete customer: " + e.getMessage());
        }
    }
}

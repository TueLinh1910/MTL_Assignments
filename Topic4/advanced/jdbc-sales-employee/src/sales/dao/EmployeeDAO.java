package sales.dao;

import java.sql.*;
import java.util.ArrayList;
import sales.entities.Employee;

public class EmployeeDAO {

    private final Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Lấy tất cả nhân viên
    public ArrayList<Employee> selectAll() throws SQLException {
        if (conn == null) {
            return null;
        }

        String select = "SELECT * FROM employees";
        ArrayList<Employee> employees = new ArrayList<>();

        try (
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Employee emp = new Employee();

                emp.setId(rs.getInt("employee_id"));
                emp.setLastName(rs.getString("last_name"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setBirthdate(rs.getString("birth_date"));
                emp.setSupervisor(rs.getInt("supervisor_id"));

                employees.add(emp);
            }

        } catch (SQLException e) {
            throw new SQLException("Can not get employees: " + e.getMessage());
        }

        return employees;
    }

    // 2. Thêm nhân viên (DÙNG STORED PROCEDURE)
    public boolean insert(Employee employee) throws SQLException {
        if (conn == null) {
            return false;
        }

        String insert = "{call sp_add_employee(?, ?, ?, ?)}";

        int index = 1;

        try (CallableStatement cs = conn.prepareCall(insert)) {

            cs.setString(index++, employee.getLastName());
            cs.setString(index++, employee.getFirstName());
            cs.setString(index++, employee.getBirthdate());
            if (employee.getSupervisor() == null) {
            cs.setNull(index++, Types.INTEGER);
            } else {
            cs.setInt(index++, employee.getSupervisor());
            }

            return cs.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Can not insert employee: " + e.getMessage());
        }
    }

    // 3. Update nhân viên
    public boolean update(int id, Employee employee) throws SQLException {
        if (conn == null) {
            return false;
        }

        String update = "UPDATE employees SET last_name = ?, first_name = ?, birth_date = ?, supervisor_id = ? WHERE employee_id = ?";

        int index = 1;

        try (PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setString(index++, employee.getLastName());
            ps.setString(index++, employee.getFirstName());
            ps.setString(index++, employee.getBirthdate());
            if (employee.getSupervisor() == null) {
            ps.setNull(index++, Types.INTEGER);
            } else {
            ps.setInt(index++, employee.getSupervisor());
            }
            ps.setInt(index++, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Can not update employee: " + e.getMessage());
        }
    }

    // 4. Xóa nhân viên
    public boolean delete(int id) throws SQLException {
        if (conn == null) {
            return false;
        }

        String delete = "DELETE FROM employees WHERE employee_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Can not delete employee: " + e.getMessage());
        }
    }
}
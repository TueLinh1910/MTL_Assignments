package com.example.lab04.service.imp;

import com.example.lab04.dto.OrderRequest;
import com.example.lab04.dto.OrderResponse;
import com.example.lab04.entity.Customer;
import com.example.lab04.entity.Employee;
import com.example.lab04.entity.Order;
import com.example.lab04.exception.ResourceNotFoundException;
import com.example.lab04.repository.CustomerRepository;
import com.example.lab04.repository.EmployeeRepository;
import com.example.lab04.repository.OrderRepository;
import com.example.lab04.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final EmployeeRepository employeeRepo;

    // CREATE
    @Override
    public OrderResponse create(OrderRequest req) {

        Customer c = customerRepo.findById(req.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Employee e = employeeRepo.findById(req.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Order o = new Order();
        o.setCustomer(c);
        o.setEmployee(e);
        o.setOrderDate(req.getOrderDate() != null ? req.getOrderDate() : LocalDateTime.now());

        return map(orderRepo.save(o));
    }

    // UPDATE
    @Override
    public OrderResponse update(Integer id, OrderRequest req) {

        Order o = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Customer c = customerRepo.findById(req.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Employee e = employeeRepo.findById(req.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        o.setCustomer(c);
        o.setEmployee(e);

        if (req.getOrderDate() != null) {
            o.setOrderDate(req.getOrderDate());
        }

        return map(orderRepo.save(o));
    }

    // DELETE
    @Override
    public void delete(Integer id) {
        if (!orderRepo.existsById(id)) {
            throw new ResourceNotFoundException("Order not found");
        }
        orderRepo.deleteById(id);
    }

    // GET BY ID
    @Override
    public OrderResponse getById(Integer id) {
        Order o = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return map(o);
    }

    // GET ALL
    @Override
    public List<OrderResponse> getAll() {
        return orderRepo.findAll().stream()
                .map(this::map)
                .toList();
    }

    // GET BY EMPLOYEE
    @Override
    public List<OrderResponse> getByEmployee(Integer employeeId) {
        return orderRepo.findOrdersByEmployeeWithDetails(employeeId)
                .stream()
                .map(this::map)
                .toList();
    }


    // MAP ENTITY → DTO
    private OrderResponse map(Order o) {

        String empName = (o.getEmployee() != null)
                ? o.getEmployee().getFirstName() + " " + o.getEmployee().getLastName()
                : null;

        return new OrderResponse(
                o.getOrderId(),
                o.getOrderDate(),
                o.getCustomer() != null ? o.getCustomer().getCustomerId() : null,
                o.getCustomer() != null ? o.getCustomer().getCustomerName() : null,
                o.getEmployee() != null ? o.getEmployee().getEmployeeId() : null,
                empName
        );
    }
}

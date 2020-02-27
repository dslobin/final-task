package by.epam.autoshow.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long orderId;
    private AutoShowService service;
    private Customer customer;
    private LocalDateTime serviceTime;
    private BigDecimal price;
    private OrderStatus status;

    public Order(Long orderId, AutoShowService service, Customer customer,
                 LocalDateTime serviceTime, BigDecimal price, OrderStatus status) {
        this.orderId = orderId;
        this.service = service;
        this.customer = customer;
        this.serviceTime = serviceTime;
        this.price = price;
        this.status = status;
    }

    public Order() {
        service = new AutoShowService();
        customer = new Customer();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public AutoShowService getService() {
        return service;
    }

    public void setService(AutoShowService service) {
        this.service = service;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalDateTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(service, order.service) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(serviceTime, order.serviceTime) &&
                Objects.equals(price, order.price) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, service, customer, serviceTime, price, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", service=").append(service);
        sb.append(", customer=").append(customer);
        sb.append(", serviceTime=").append(serviceTime);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
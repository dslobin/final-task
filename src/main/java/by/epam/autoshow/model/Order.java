package by.epam.autoshow.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long orderId;
    private Long serviceId;
    private Long customerId;
    private LocalDateTime serviceTime;
    private BigDecimal price;
    private OrderStatus status;

    public Order(Long id, Long serviceId, Long customerId, LocalDateTime serviceTime,
                 BigDecimal price, OrderStatus status) {
        this.orderId = id;
        this.serviceId = serviceId;
        this.customerId = customerId;
        this.serviceTime = serviceTime;
        this.price = price;
        this.status = status;
    }

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalDateTime orderDate) {
        this.serviceTime = orderDate;
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
        return Objects.equals(serviceId, order.serviceId) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(serviceTime, order.serviceTime) &&
                Objects.equals(price, order.price) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, customerId, serviceTime, price, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", customerId=").append(customerId);
        sb.append(", serviceTime=").append(serviceTime);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

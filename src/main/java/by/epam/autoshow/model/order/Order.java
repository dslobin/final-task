package by.epam.autoshow.model.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private Long orderId;
    private Long serviceId;
    private Long customerId;
    private LocalDate orderDate;
    private BigDecimal overallPrice;
    private OrderStatus status;

    public Order(Long id, Long serviceId, Long customerId, LocalDate orderDate,
                 BigDecimal overallPrice, OrderStatus status) {
        this.orderId = id;
        this.serviceId = serviceId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.overallPrice = overallPrice;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(BigDecimal overallPrice) {
        this.overallPrice = overallPrice;
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
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(overallPrice, order.overallPrice) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, customerId, orderDate, overallPrice, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", customerId=").append(customerId);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", overallPrice=").append(overallPrice);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

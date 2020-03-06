package by.epam.autoshow.model;

import java.math.BigDecimal;
import java.util.Objects;

public class AutoShowService {
    private Long serviceId;
    private String title;
    private BigDecimal cost;
    private String description;

    public AutoShowService() {
    }

    public AutoShowService(Long id, String title, BigDecimal cost, String description) {
        this.serviceId = id;
        this.title = title;
        this.cost = cost;
        this.description = description;
    }

    public AutoShowService(String title, BigDecimal cost, String description) {
        this.title = title;
        this.cost = cost;
        this.description = description;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoShowService that = (AutoShowService) o;
        return Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, title, cost, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AutoShowService{");
        sb.append("serviceId=").append(serviceId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

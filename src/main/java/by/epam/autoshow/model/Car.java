package by.epam.autoshow.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private Long carId;
    private String model;
    private int mileage;
    private FuelType fuelType;
    private BodyType bodyType;
    private int volume;
    private String transmission; // коробка передач
    private String driveUnit; // привод
    private Color color;
    private int issueYear;
    private BigDecimal price;
    private SaleStatus status;
    private String description;
    private String imageUrl;

    public Car(Long id, String model, int mileage, FuelType fuelType, BodyType bodyType, int volume,
               String transmission, String driveUnit, Color color, int issueYear,
               BigDecimal price, SaleStatus status, String description, String imageUrl) {
        this.carId = id;
        this.model = model;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.volume = volume;
        this.transmission = transmission;
        this.driveUnit = driveUnit;
        this.color = color;
        this.issueYear = issueYear;
        this.price = price;
        this.status = status;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Car() {
        super();
        color = new Color();
        bodyType = new BodyType();
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return mileage == car.mileage &&
                volume == car.volume &&
                issueYear == car.issueYear &&
                Objects.equals(car.price, price) &&
                Objects.equals(model, car.model) &&
                fuelType == car.fuelType &&
                Objects.equals(bodyType, car.bodyType) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(driveUnit, car.driveUnit) &&
                Objects.equals(color, car.color) &&
                status == car.status &&
                Objects.equals(description, car.description) &&
                Objects.equals(imageUrl, car.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, mileage, fuelType, bodyType, volume, transmission,
                driveUnit, color, issueYear, price, status, description, imageUrl);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("carId=").append(carId);
        sb.append(", model='").append(model).append('\'');
        sb.append(", mileage=").append(mileage);
        sb.append(", fuelType=").append(fuelType);
        sb.append(", bodyType=").append(bodyType);
        sb.append(", volume=").append(volume);
        sb.append(", transmission='").append(transmission).append('\'');
        sb.append(", driveUnit='").append(driveUnit).append('\'');
        sb.append(", color=").append(color);
        sb.append(", issueYear=").append(issueYear);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", description='").append(description).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
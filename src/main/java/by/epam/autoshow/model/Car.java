package by.epam.autoshow.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private final Long carId;
    private final String model;
    private final int mileage;
    private final FuelType fuelType;
    private final BodyType bodyType;
    private final int volume;
    private final String transmission;
    private final String driveUnit;
    private final Color color;
    private final int issueYear;
    private final BigDecimal price;
    private final SaleStatus status;
    private final String description;
    private final String imageUrl;

    public static class Builder {
        /**
         * Required parameters
         */
        private final String model;
        private final int mileage;
        private final FuelType fuelType;
        private final BodyType bodyType;
        private final int volume;
        private final String transmission;
        private final String driveUnit;
        private final int issueYear;
        private final BigDecimal price;
        private final SaleStatus status;
        /**
         * Optional parameters - initialized to default values
         */
        private Long carId = 0L;
        private String description = null;
        private String imageUrl = null;
        private Color color = new Color();

        public Builder(String model, int mileage, FuelType fuelType, BodyType bodyType,
                       int volume, String transmission, String driveUnit, int issueYear,
                       BigDecimal price, SaleStatus status) {
            this.model = model;
            this.mileage = mileage;
            this.fuelType = fuelType;
            this.bodyType = bodyType;
            this.volume = volume;
            this.transmission = transmission;
            this.driveUnit = driveUnit;
            this.issueYear = issueYear;
            this.price = price;
            this.status = status;
        }

        public Builder setCarId(Long carId) {
            this.carId = carId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    private Car(Builder builder) {
        this.carId = builder.carId;
        this.model = builder.model;
        this.mileage = builder.mileage;
        this.fuelType = builder.fuelType;
        this.bodyType = builder.bodyType;
        this.volume = builder.volume;
        this.transmission = builder.transmission;
        this.driveUnit = builder.driveUnit;
        this.color = builder.color;
        this.issueYear = builder.issueYear;
        this.price = builder.price;
        this.status = builder.status;
        this.description = builder.description;
        this.imageUrl = builder.imageUrl;
    }

    public Long getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public int getVolume() {
        return volume;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public Color getColor() {
        return color;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
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
                Objects.equals(carId, car.carId) &&
                Objects.equals(model, car.model) &&
                fuelType == car.fuelType &&
                bodyType == car.bodyType &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(driveUnit, car.driveUnit) &&
                Objects.equals(color, car.color) &&
                Objects.equals(price, car.price) &&
                status == car.status &&
                Objects.equals(description, car.description) &&
                Objects.equals(imageUrl, car.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, model, mileage, fuelType, bodyType, volume, transmission, driveUnit,
                color, issueYear, price, status, description, imageUrl);
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
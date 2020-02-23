package by.epam.autoshow.model;

public enum SaleStatus {
    IN_STOCK("In stock"),
    SOLD("Sold");

    private String value;

    SaleStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
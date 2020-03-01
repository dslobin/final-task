package by.epam.autoshow.model;

public enum BodyType {
    CABRIOLET("cabriolet"), COUPE("coupe"), LIMOUSINE("limousine"), MINIBUS("minibus"),
    MINIVAN("minivan"), THREE_DOOR_SUV("3 door suv"), FIVE_DOOR_SUV("5 door suv"),
    PICKUP("pickup"), SEDAN("sedan"), STATION_WAGGON("station waggon"),
    THREE_DOOR_HATCHBACK("3 door hatchback"), FIVE_DOOR_HATCHBACK("5 door hatchback"),
    PASSENGER_VAN("passenger van"), LIFTBACK("liftback"), OTHER("");

    private String type;

    BodyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
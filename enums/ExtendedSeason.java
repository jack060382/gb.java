package enums;

public enum ExtendedSeason {
    WINTER(1, "Winter"),
    SPRING(2, "Spring"),
    SUMMER(3, "Summer"),
    AUTUMN(4, "Autumn");

    private final int order;
    private final String name;

    private ExtendedSeason(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }
/*
    public String getName() {
        return name;
    }

 */
}

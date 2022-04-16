package hotelsearchengine.models;

public class Services {
    private boolean hasCasino;
    private boolean hasGym;
    private boolean hasPool;
    private boolean hasRestaurant;
    private boolean hasSpa;
    private boolean hasWifi;
    private boolean hasParking;
    private boolean hasTv;
    private boolean hasAirConditioning;
    private boolean hasBar;
    private boolean hasLaundry;
    private boolean hasPets;
    private boolean hasWheelchairAccess;
    private boolean hasSmoking;
    private boolean hasWheelchair;
    private boolean hasElevator;
    private boolean hasAirportShuttle;
    public Services() {
        //TODO: skoða builder patterns fyrir java
    }

    public boolean isHasCasino() {
        return hasCasino;
    }

    public boolean isHasGym() {
        return hasGym;
    }
}


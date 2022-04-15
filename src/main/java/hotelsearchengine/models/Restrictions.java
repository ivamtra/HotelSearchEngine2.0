package hotelsearchengine.models;

import java.util.Date;
public class Restrictions {
    private int minPrice = 0;
    private int maxPrice = 0;
    private double minStars = 0;
    private double maxStars = 0;
    private String name = "";
    private String location = "";
    private Services services = new Services();
    private int hotelId = -1;
    private Date startDate = new Date();
    private Date endDate = new Date();

    public Restrictions() {
        //TODO: skoða builder patterns fyrir java
    }
}

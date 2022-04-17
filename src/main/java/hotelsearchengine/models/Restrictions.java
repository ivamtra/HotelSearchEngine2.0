package hotelsearchengine.models;

import java.util.ArrayList;
import java.util.Date;
public class Restrictions {
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minStars;
    private Integer maxStars;
    private String name;
    private String location;
    private ArrayList<Service> services;
    private Date startDate;
    private Date endDate = new Date();

    public Restrictions(Integer maxPrice, Integer minPrice, Integer maxStars, Integer minStars, String hotelName, String hotelLocation, String availableFrom, String availableTo, ArrayList<Service> services) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.maxStars = maxStars;
        this.minStars = minStars;
        this.name = hotelName;
        this.location = hotelLocation;
        this.services = services;
        this.startDate = new Date(); // TODO
        this.endDate = new Date(); // TODO
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinStars() {
        return minStars;
    }

    public void setMinStars(Integer minStars) {
        this.minStars = minStars;
    }

    public Integer getMaxStars() {
        return maxStars;
    }

    public void setMaxStars(Integer maxStars) {
        this.maxStars = maxStars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

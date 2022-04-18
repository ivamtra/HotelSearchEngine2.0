package hotelsearchengine.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Restrictions {
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minStars;
    private Integer maxStars;
    private String name;
    private String location;
    private ArrayList<Service> services;
    private Date startDate;
    private Date endDate;
    private Double avgRating;
    private Integer minSize;
    private Integer maxSize;


    public Restrictions(Integer minPrice, Integer maxPrice, Integer minStars, Integer maxStars, String name, String location, ArrayList<Service> services, Date startDate, Date endDate, Double avgRating, Integer minSize, Integer maxSize) {

        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minStars = minStars;
        this.maxStars = maxStars;
        this.name = name;
        this.location = location;
        this.services = services;
        this.startDate = startDate;
        this.endDate = endDate;
        this.avgRating = avgRating;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
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

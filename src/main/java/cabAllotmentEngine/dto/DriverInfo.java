package cabAllotmentEngine.dto;

import cabAllotmentEngine.enums.RatingType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DriverInfo extends PersonInfo {
    private Cab cab;
    private Map<Integer, RatingType> customerRatingMap;


    public DriverInfo(String name, Integer id, Cab cab){
        this.setName(name);
        this.setId(id);
        cab.setDriver(this);
        this.cab = cab;
    }

    public RatingType getCustomerRating(Integer customerId) {
        if(customerRatingMap == null)
            return null;

        if(customerRatingMap.get(customerId) == null)
            return null;

        return customerRatingMap.get(customerId);
    }

    public void giveCustomerRating(Integer customerId, RatingType ratingCustomerGaveToDriver, RatingType ratingDriverGaveToCustomer) {
        if(customerRatingMap == null)
            customerRatingMap = new HashMap<>();

        customerRatingMap.put(customerId, ratingDriverGaveToCustomer);

        Integer noOfCustomerRating = this.getTotalNumberRating();
        if(noOfCustomerRating == null)
            noOfCustomerRating = 1;

        Double averageDriverRating = this.getAverageRating();
        if(averageDriverRating == null){
            averageDriverRating = (double) ratingCustomerGaveToDriver.getRating();
        }else {
            Double totalRating = averageDriverRating * noOfCustomerRating;
            totalRating += (double) ratingCustomerGaveToDriver.getRating();
            noOfCustomerRating++;
            averageDriverRating = totalRating / noOfCustomerRating;
        }

        this.setTotalNumberRating(noOfCustomerRating);
        this.setAverageRating(averageDriverRating);
    }
}

package cabAllotmentEngine.dto;

import cabAllotmentEngine.enums.RatingType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class CustomerInfo extends PersonInfo {
    private Map<Integer, RatingType> driverRatingMap;
    private Map<Integer, DriverInfo> driverMap;

    public CustomerInfo(String name, Integer id){
        this.setName(name);
        this.setId(id);
    }

    public RatingType getDriverRating(Integer driverId){
        if(driverRatingMap == null)
            return null;

        if(driverRatingMap.get(driverId) == null)
            return null;

        return driverRatingMap.get(driverId);
    }

    public DriverInfo getDriver(Integer driverId){
        if(driverMap == null)
            return null;

        if(driverMap.get(driverId) == null)
            return null;

        return driverMap.get(driverId);
    }

    public void giveDriverRating(DriverInfo driverInfo, RatingType ratingGotFromDriver, RatingType ratingGiverToDriver) {
        if(driverRatingMap == null)
            driverRatingMap = new HashMap<>();

        driverRatingMap.put(driverInfo.getId(), ratingGiverToDriver);

        if(driverMap == null)
            driverMap = new HashMap<>();

        driverMap.put(driverInfo.getId(), driverInfo);
        Integer noOfDriverRating = this.getTotalNumberRating();
        if(noOfDriverRating == null)
            noOfDriverRating = 1;

        Double averageCustomerRating = this.getAverageRating();
        if(averageCustomerRating == null){
            averageCustomerRating = (double) ratingGotFromDriver.getRating();
        }else {
            Double totalRating = averageCustomerRating * noOfDriverRating;
            totalRating += (double) ratingGotFromDriver.getRating();
            noOfDriverRating++;
            averageCustomerRating = totalRating / noOfDriverRating;
        }

        this.setTotalNumberRating(noOfDriverRating);
        this.setAverageRating(averageCustomerRating);
    }
}

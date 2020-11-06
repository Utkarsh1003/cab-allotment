package cabAllotmentEngine;

import cabAllotmentEngine.dto.*;
import cabAllotmentEngine.enums.RatingType;
import cabAllotmentEngine.enums.RuleType;
import cabAllotmentEngine.services.DriverService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Cab cab1 = new Cab(1, new LocationInfo(1.0, 0.0));
        Cab cab2 = new Cab(2, new LocationInfo(1.0, 0.0));
        Cab cab3 = new Cab(3, new LocationInfo(3.0, 0.0));
        Cab cab4 = new Cab(4, new LocationInfo(4.0, 0.0));

        DriverInfo driver1 = new DriverInfo("Driver 1", 1, cab1);
        DriverInfo driver2 = new DriverInfo("Driver 2", 2, cab2);
        DriverInfo driver3 = new DriverInfo("Driver 3", 3, cab3);
        DriverInfo driver4 = new DriverInfo("Driver 4", 4, cab4);

        CustomerInfo customer1 = new CustomerInfo("Customer 1", 1);
        CustomerInfo customer2 = new CustomerInfo("Customer 2", 2);
        CustomerInfo customer3 = new CustomerInfo("Customer 3", 3);


        driver1.giveCustomerRating(1, RatingType.VERY_BAD, RatingType.EXCELLENT);
        driver1.giveCustomerRating(2, RatingType.EXCELLENT, RatingType.GOOD);
        driver1.giveCustomerRating(3, RatingType.VERY_BAD, RatingType.BAD);

        driver2.giveCustomerRating(1, RatingType.VERY_BAD, RatingType.EXCELLENT);
        driver2.giveCustomerRating(2, RatingType.EXCELLENT, RatingType.EXCELLENT);
        driver2.giveCustomerRating(3, RatingType.GOOD, RatingType.EXCELLENT);

        driver3.giveCustomerRating(1, RatingType.AVERAGE, RatingType.BAD);
        driver3.giveCustomerRating(2, RatingType.GOOD, RatingType.EXCELLENT);
        driver3.giveCustomerRating(3, RatingType.AVERAGE, RatingType.AVERAGE);


        customer1.giveDriverRating(driver1, RatingType.EXCELLENT, RatingType.VERY_BAD);
        customer1.giveDriverRating(driver2, RatingType.EXCELLENT, RatingType.BAD);
        customer1.giveDriverRating(driver3, RatingType.BAD, RatingType.VERY_BAD);

        customer2.giveDriverRating(driver1, RatingType.GOOD, RatingType.GOOD);
        customer2.giveDriverRating(driver2, RatingType.EXCELLENT, RatingType.EXCELLENT);
        customer2.giveDriverRating(driver3, RatingType.EXCELLENT, RatingType.AVERAGE);

        customer3.giveDriverRating(driver1, RatingType.GOOD, RatingType.VERY_BAD);
        customer3.giveDriverRating(driver2, RatingType.EXCELLENT, RatingType.GOOD);
        customer3.giveDriverRating(driver3, RatingType.AVERAGE, RatingType.AVERAGE);



        CabPool cabPool = new CabPool(1, "Area 1");
        List<Cab> cabs = new ArrayList<>();
        cabPool.setCabs(cabs);
        cabs.add(cab1);
        cabs.add(cab2);
        cabs.add(cab3);
        cabs.add(cab4);

        LocationInfo customerLocation = new LocationInfo(-1.0, 0.0);


        DriverService driverService = DriverService.getInstance();
        List<Cab> eligibleCabs = driverService.getEligibleCabs(cabPool, customer1, RuleType.RULE1);

        driverService.printAverageRatingSortedCabs(eligibleCabs);
        driverService.printCabsAccordingToDistance(eligibleCabs, customerLocation);
    }
}

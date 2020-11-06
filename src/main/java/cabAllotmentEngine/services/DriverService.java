package cabAllotmentEngine.services;

import cabAllotmentEngine.dto.*;
import cabAllotmentEngine.enums.RatingType;
import cabAllotmentEngine.enums.RuleType;

import java.util.*;
import java.util.stream.Collectors;

public class DriverService {

    private static DriverService instance;

    public static DriverService getInstance() {
        if(instance == null)
            instance = new DriverService();

        return instance;
    }

    public List<Cab> getEligibleCabs(CabPool cabPool, CustomerInfo customerInfo, RuleType ruleType){
        List<Cab> eligibleCabs = new ArrayList<>();
        if(cabPool == null || cabPool.getCabs() == null || cabPool.getCabs().isEmpty()){
            System.out.println("No cab available");
            return eligibleCabs;
        }

        if(customerInfo == null){
            System.out.println("No cab available");
            return eligibleCabs;
        }

        RuleFactory ruleFactory = RuleFactory.getInstance();
        Rule rule = ruleFactory.getRule(ruleType);

        RuleVariables ruleVariables = new RuleVariables();
        ruleVariables.setCustomerInfo(customerInfo);

        List<Cab> cabs = cabPool.getCabs();
        eligibleCabs = rule.getEligibleCabs(cabs, ruleVariables);

        if(eligibleCabs.isEmpty()){
            System.out.println("No cab available");
            return eligibleCabs;
        }

        System.out.println(customerInfo.getName() + " has Average rating : " + customerInfo.getAverageRating());

        return eligibleCabs;
    }

    public void printAverageRatingSortedCabs(List<Cab> eligibleCabs) {
        List<Cab> sortedCabs = sortCabAccordingToRating(eligibleCabs);
        System.out.println("Eligible Drivers ");
        System.out.println(sortedCabs);
    }

    public List<Cab> sortCabAccordingToRating(List<Cab> eligibleCabs){
        return eligibleCabs.stream().sorted(new Comparator<Cab>() {
            @Override
            public int compare(Cab cab1, Cab cab2) {
                if(cab1.getDriver().getAverageRating() == null)
                    return 0;

                if(cab2.getDriver().getAverageRating() == null)
                    return 0;

                return cab1.getDriver().getAverageRating().compareTo(cab2.getDriver().getAverageRating());
            }
        }).collect(Collectors.toList());
    }

    public void printCabsAccordingToDistance(List<Cab> eligibleCabs, LocationInfo customerLocation) {
        if(eligibleCabs.isEmpty()){
            System.out.println("No cab available");
            return;
        }

        if(customerLocation == null || customerLocation.getLat() == null || customerLocation.getLng() == null)
            return;

        List<Cab> sortedCabs = sortCabAccordingtoDistance(eligibleCabs, customerLocation);
        System.out.println("Eligible Drivers by distance ");
        System.out.println(sortedCabs);
    }

    public List<Cab> sortCabAccordingtoDistance(List<Cab> eligibleCabs, LocationInfo locationInfo){
        if(eligibleCabs.size() == 1){
            Double distanceCab = calculateEuclideanDistance(eligibleCabs.get(0), locationInfo);
            eligibleCabs.get(0).setDistance(distanceCab);
            return eligibleCabs;
        }

        return eligibleCabs.stream().sorted(new Comparator<Cab>() {
            @Override
            public int compare(Cab cab1, Cab cab2) {
                Double distanceCab1 = calculateEuclideanDistance(cab1, locationInfo);
                cab1.setDistance(distanceCab1);
                Double distanceCab2 = calculateEuclideanDistance(cab2, locationInfo);
                cab2.setDistance(distanceCab2);

                return distanceCab1.compareTo(distanceCab2);
            }
        }).collect(Collectors.toList());
    }

    private Double calculateEuclideanDistance(Cab cab, LocationInfo locationInfo) {
        Double customerLat = locationInfo.getLat();
        Double customerLng = locationInfo.getLng();

        Double driverLat = cab.getCurrentLocation().getLat();
        Double driverLng = cab.getCurrentLocation().getLng();

        return Math.pow(((Math.pow((customerLat - driverLat), 2)) + (Math.pow((customerLng - driverLng), 2))), 0.5);
    }
}

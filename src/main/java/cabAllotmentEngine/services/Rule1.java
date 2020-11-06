package cabAllotmentEngine.services;

import cabAllotmentEngine.dto.Cab;
import cabAllotmentEngine.dto.CustomerInfo;
import cabAllotmentEngine.dto.DriverInfo;
import cabAllotmentEngine.dto.RuleVariables;
import cabAllotmentEngine.enums.RatingType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rule1 implements Rule{
    @Override
    public List<Cab> getEligibleCabs(List<Cab> cabs, RuleVariables ruleVariables) {
        if(cabs == null || ruleVariables == null)
            return new ArrayList<>();

        List<Cab> firstRuleCabs = cabs.stream().filter(cab -> { return rule1(cab, ruleVariables);}).collect(Collectors.toList());
        if(!firstRuleCabs.isEmpty())
            return firstRuleCabs;

        List<Cab> secondRuleCabs = cabs.stream().filter(cab -> { return rule2(cab, ruleVariables);}).collect(Collectors.toList());
        if(!secondRuleCabs.isEmpty())
            return secondRuleCabs;

        return new ArrayList<>();
    }


    private boolean rule1(Cab cab, RuleVariables ruleVariables) {
        CustomerInfo customerInfo = ruleVariables.getCustomerInfo();

        if(cab.getDriver() == null || customerInfo == null)
            return false;

        if(cab.getDriver().getAverageRating() == null || customerInfo.getAverageRating() == null)
            return false;

        return cab.getDriver().getAverageRating() >= customerInfo.getAverageRating();
    }

    private boolean rule2(Cab cab, RuleVariables ruleVariables) {
        CustomerInfo customerInfo = ruleVariables.getCustomerInfo();

        if(cab.getDriver() == null)
            return false;

        if(customerInfo.getDriverRating(cab.getDriver().getId()) == null)
            return false;

        if(RatingType.VERY_BAD.equals(customerInfo.getDriverRating(cab.getDriver().getId())))
            return false;

        DriverInfo driver = customerInfo.getDriver(cab.getDriver().getId());
        if(driver != null && RatingType.VERY_BAD.equals(driver.getCustomerRating(customerInfo.getId())))
            return false;

        return true;
    }
}

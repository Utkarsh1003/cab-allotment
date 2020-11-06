package cabAllotmentEngine.services;

import cabAllotmentEngine.dto.Cab;
import cabAllotmentEngine.dto.RuleVariables;

import java.util.List;

public interface Rule {
    public List<Cab> getEligibleCabs(List<Cab> cabs, RuleVariables ruleVariables);
}

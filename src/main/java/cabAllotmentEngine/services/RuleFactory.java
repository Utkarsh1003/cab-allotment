package cabAllotmentEngine.services;

import cabAllotmentEngine.enums.RuleType;

public class RuleFactory {
    private static RuleFactory instance;

    public static RuleFactory getInstance(){
        if(instance == null)
            instance = new RuleFactory();

        return instance;
    }

    public Rule getRule(RuleType ruleType){
        if(RuleType.RULE1.equals(ruleType))
            return new Rule1();

        return new Rule1();
    }
}

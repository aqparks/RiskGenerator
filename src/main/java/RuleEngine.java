import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RuleEngine {
    private final List<Rule> rules;
    private final Application application;

    protected enum RULE_TYPE {
        INCREASES, MULTIPLIES, REDUCES
    }

    public RuleEngine(final Application application) {
        rules = new ArrayList<>();
        rules.add(new CriticalInfrastructureRule());
        rules.add(new BaseRule());
        rules.add(new TargetedRule());
        rules.add(new WhitelistRule());
        Collections.sort(rules);

        this.application = application;
    }

    public Double calculateRisk() {
        double riskScore = 0.00;
        for (Netflow netflow : application.getNetflows()) {
            for (Rule rule : rules) {
                rule.evaluate(netflow);
            }
            riskScore = riskScore + netflow.getRiskScore();
        }

        BigDecimal bigDecimal = new BigDecimal(riskScore).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    private void addRule(Rule rule) {
        rules.add(rule);
    }
}

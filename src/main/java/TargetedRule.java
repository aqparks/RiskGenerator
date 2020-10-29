import java.util.HashMap;
import java.util.Map;

public class TargetedRule extends Rule {
    private final Map<IPAddress, Map<IPAddress, Double>> observedSourceToDestination;

    public TargetedRule() {
        super(1, 5.00);
        observedSourceToDestination = new HashMap<>();
    }

    @Override
    public void evaluate(final Netflow netflow) {
        if (observedSourceToDestination.containsKey(netflow.getSourceIp())) {
            Map<IPAddress, Double> observedDestinations = observedSourceToDestination.get(netflow.getSourceIp());
            if (observedDestinations.containsKey(netflow.getDestinationIp())) {
                Double previousScore = observedDestinations.get(netflow.getDestinationIp());
                observedDestinations.put(netflow.getDestinationIp(), previousScore * 1.10);
                netflow.setRiskScore(netflow.getRiskScore() - previousScore + (previousScore * 1.10));
            } else {
                observedDestinations.put(netflow.getDestinationIp(), getWeight());
            }
        } else if (netflow.getDestinationIpType().equals(IPAddress.IP_ADDRESS_TYPE.MALWARE)) {
            Map<IPAddress, Double> observedDestinations = new HashMap<>();
            observedDestinations.put(netflow.getDestinationIp(), getWeight());
            observedSourceToDestination.put(netflow.getSourceIp(), observedDestinations);
            netflow.setRiskScore(getWeight());
        }
    }
}

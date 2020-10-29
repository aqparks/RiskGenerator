public class CriticalInfrastructureRule extends Rule {

    public CriticalInfrastructureRule() {
        super(2, 2.00);
    }

    @Override
    public void evaluate(final Netflow netflow) {
        if (
                netflow.getSourceIpType().equals(IPAddress.IP_ADDRESS_TYPE.CRITICAL_INFRASTRUCTURE)
                        && netflow.getDestinationIpType().equals(IPAddress.IP_ADDRESS_TYPE.MALWARE)
        ) {
            netflow.setRiskScore(netflow.getRiskScore() * 2);
        }
    }
}

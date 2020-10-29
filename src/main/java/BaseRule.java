public class BaseRule extends Rule {
    public BaseRule() {
        super(0, 0.00);
    }

    @Override
    public void evaluate(final Netflow netflow) {
        if (netflow.getSourceIpType().equals(IPAddress.IP_ADDRESS_TYPE.NONE)
                && netflow.getDestinationIpType().equals(IPAddress.IP_ADDRESS_TYPE.NONE)) {
            netflow.setRiskScore(netflow.getRiskScore() + getWeight());
        }
    }
}

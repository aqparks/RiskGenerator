public class WhitelistRule extends Rule {

    public WhitelistRule() {
        super(4, 0.00);
    }

    @Override
    public void evaluate(final Netflow netflow) {
        if (netflow.getSourceIpType().equals(IPAddress.IP_ADDRESS_TYPE.WHITELIST)) {
            netflow.setRiskScore(getWeight());
        }
    }
}

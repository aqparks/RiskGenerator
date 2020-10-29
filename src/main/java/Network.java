public class Network {
    private final Integer networkCidr;
    private final IPAddress networkId;
    private final Integer networkMask;
    private final IPAddress lowestIpAddress;
    private final IPAddress highestIpAddress;

    public Network(final String cidr) {
        String[] parts = cidr.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("CIDR was in unexpected format. Bad value: " + cidr);
        }

        networkId = new IPAddress(parts[0]);
        networkCidr = Integer.valueOf(parts[1]);
        networkMask = (-1) << (32 - networkCidr);

        lowestIpAddress = new IPAddress(networkId.getIpAddress() & networkMask);
        highestIpAddress = new IPAddress(lowestIpAddress.getIpAddress() + (~networkMask));
    }

    public Boolean isIpInNetwork(final IPAddress ipAddress) {
        return lowestIpAddress.getIpAddress() <= ipAddress.getIpAddress()
                && ipAddress.getIpAddress() <= highestIpAddress.getIpAddress();
    }
}

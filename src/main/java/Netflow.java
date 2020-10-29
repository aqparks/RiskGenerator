import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class Netflow {

    private final LocalDateTime timestamp;
    private final IPAddress sourceIp;
    private final IPAddress destinationIp;
    private final IPAddress.IP_ADDRESS_TYPE sourceIpType;
    private final IPAddress.IP_ADDRESS_TYPE destinationIpType;
    private Double riskScore;

    public Netflow(final Application application, final String netflow) {
        String[] parts = netflow.split("\\\\t");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Netflow was in unexpected format. Found " + parts.length + " parts in " + netflow);
        }

        timestamp = LocalDateTime.ofEpochSecond(Long.valueOf(parts[0]), 0, ZoneOffset.UTC);
        sourceIp = new IPAddress(parts[1]);
        destinationIp = new IPAddress(parts[2]);
        sourceIpType = application.getIpAddressType(sourceIp);
        destinationIpType = application.getIpAddressType(destinationIp);
        riskScore = 0.00;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public IPAddress getSourceIp() {
        return sourceIp;
    }

    public IPAddress getDestinationIp() {
        return destinationIp;
    }

    public IPAddress.IP_ADDRESS_TYPE getSourceIpType() {
        return sourceIpType;
    }

    public IPAddress.IP_ADDRESS_TYPE getDestinationIpType() {
        return destinationIpType;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(final Double riskScore) {
        this.riskScore = riskScore;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Netflow netflow = (Netflow) o;
        return getTimestamp().equals(netflow.getTimestamp()) &&
                getSourceIp().equals(netflow.getSourceIp()) &&
                getDestinationIp().equals(netflow.getDestinationIp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceIp(), getDestinationIp());
    }
}

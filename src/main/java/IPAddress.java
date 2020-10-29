import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddress implements Comparable<IPAddress> {
    private Integer firstOctet;
    private Integer secondOctet;
    private Integer thirdOctet;
    private Integer fourthOctet;
    private final Integer ipAddress;

    protected enum IP_ADDRESS_TYPE {
        NONE, MALWARE, CRITICAL_INFRASTRUCTURE, WHITELIST
    }

    Pattern ipAddressPattern = Pattern.compile(
            "^(?<firstOctet>\\d{1,3})\\." +
                    "(?<secondOctet>\\d{1,3})\\." +
                    "(?<thirdOctet>\\d{1,3})\\." +
                    "(?<fourthOctet>\\d{1,3})$");

    public IPAddress(final Integer ipAddress) {
        this.ipAddress = ipAddress;
    }

    public IPAddress(final String ipAddress) {
        Matcher matcher = ipAddressPattern.matcher(ipAddress);
        if (!matcher.find()) {
            throw new IllegalArgumentException("A valid IP address is required. Got bad value: " + ipAddress);
        }

        firstOctet = Integer.valueOf(matcher.group("firstOctet"));
        secondOctet = Integer.valueOf(matcher.group("secondOctet"));
        thirdOctet = Integer.valueOf(matcher.group("thirdOctet"));
        fourthOctet = Integer.valueOf(matcher.group("fourthOctet"));

        this.ipAddress = ((firstOctet << 24) & 0xFF000000)
                | ((secondOctet << 16) & 0xFF0000)
                | ((thirdOctet << 8) & 0xFF00)
                | (fourthOctet & 0xFF);
    }

    public Integer getIpAddress() {
        return ipAddress;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPAddress ipAddress1 = (IPAddress) o;
        return getIpAddress().equals(ipAddress1.getIpAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIpAddress());
    }

    @Override
    public int compareTo(final IPAddress o) {
        return Integer.compare(getIpAddress(), o.getIpAddress());
    }
}

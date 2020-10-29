import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private final RuleEngine ruleEngine;
    private final List<Network> malwareCidrs;
    private final List<IPAddress> criticalHosts;
    private final List<IPAddress> whiteListedHosts;
    private final List<Netflow> netflows;

    public Application() {
        malwareCidrs = new ArrayList<>();
        criticalHosts = new ArrayList<>();
        whiteListedHosts = new ArrayList<>();
        netflows = new ArrayList<>();
        ruleEngine = new RuleEngine(this);
    }

    public Double riskScore(final List<String> malwareCidrs, final List<String> criticalHosts,
                            final List<String> whitelistedHosts, final String netflows) {
        addMalwareCidrs(malwareCidrs);
        addCriticalHosts(criticalHosts);
        addWhitelistHosts(whitelistedHosts);
        addNetflowRecords(netflows);

        return ruleEngine.calculateRisk();
    }

    private void addNetflowRecords(final String netflowRecord) {
        Scanner scanner = new Scanner(netflowRecord);
        scanner.useDelimiter("\\\\n");
        while (scanner.hasNext()) {
            netflows.add(new Netflow(this, scanner.next()));
        }
    }

    public List<Netflow> getNetflows() {
        return netflows;
    }


    private void addMalwareCidrs(final List<String> malwareCidrs) {
        for (String each : malwareCidrs) {
            this.malwareCidrs.add(new Network(each));
        }
    }

    protected Boolean isMalware(final IPAddress ipAddress) {
        for (Network malwareNetwork : malwareCidrs) {
            if (malwareNetwork.isIpInNetwork(ipAddress)) {
                return true;
            }
        }
        return false;
    }

    private void addCriticalHosts(final List<String> criticalHosts) {
        for (String each : criticalHosts) {
            this.criticalHosts.add(new IPAddress(each));
        }
    }

    protected Boolean isCriticalHost(final IPAddress ipAddress) {
        for (IPAddress criticalHost : criticalHosts) {
            if (ipAddress.equals(criticalHost)) {
                return true;
            }
        }
        return false;
    }

    private void addWhitelistHosts(final List<String> whiteListedHosts) {
        for (String each : whiteListedHosts) {
            this.whiteListedHosts.add(new IPAddress(each));
        }
    }

    protected Boolean isWhitelisted(final IPAddress ipAddress) {
        for (IPAddress whitelistIp : whiteListedHosts) {
            if (ipAddress.equals(whitelistIp)) {
                return true;
            }
        }
        return false;
    }

    protected IPAddress.IP_ADDRESS_TYPE getIpAddressType(final IPAddress ipAddress) {
        if (isCriticalHost(ipAddress)) {
            return IPAddress.IP_ADDRESS_TYPE.CRITICAL_INFRASTRUCTURE;
        } else if (isMalware(ipAddress)) {
            return IPAddress.IP_ADDRESS_TYPE.MALWARE;
        } else if (isWhitelisted(ipAddress)) {
            return IPAddress.IP_ADDRESS_TYPE.WHITELIST;
        } else {
            return IPAddress.IP_ADDRESS_TYPE.NONE;
        }
    }
}

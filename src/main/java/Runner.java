import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static Application application;

    @Parameter(names = "-malwareCidr", description = "The host")
    private List<String> malwareCidrs = new ArrayList<>();

    @Parameter(names = "-criticalHost", description = "The host")
    private List<String> criticalHosts = new ArrayList<>();

    @Parameter(names = "-whitelistedHost", description = "The host")
    private List<String> whitelistedHosts = new ArrayList<>();

    @Parameter(names = "-netflows", description = "Netflow records")
    private String netflows;

    public static void main(String[] args) {
        application = new Application();
        Runner runner = new Runner();
        JCommander.newBuilder().addObject(runner).build().parse(args);
        runner.run();

    }

    public void run() {
        System.out.println(application.riskScore(malwareCidrs, criticalHosts, whitelistedHosts, netflows));
    }
}

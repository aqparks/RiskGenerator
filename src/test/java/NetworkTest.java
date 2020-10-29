import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NetworkTest {

    Network testNetwork;
    IPAddress testGoodIpAddress;
    IPAddress testBadIpAddress;

    @BeforeEach
    void setup() {
        String testNetworkCidr = "192.168.1.0/24";
        String goodIpAddress = "192.168.1.56";
        String badIpAddress = "10.0.10.144";

        testNetwork = new Network(testNetworkCidr);
        testGoodIpAddress = new IPAddress(goodIpAddress);
        testBadIpAddress = new IPAddress(badIpAddress);
    }

    @Test
    void isIpInNetwork() {
        assertTrue(testNetwork.isIpInNetwork(testGoodIpAddress));
        assertFalse(testNetwork.isIpInNetwork(testBadIpAddress));
    }

    @Test
    void isIpInNetworkTwo() {
        testNetwork = new Network("10.0.0.0/8");
        assertFalse(testNetwork.isIpInNetwork(testGoodIpAddress));
        assertTrue(testNetwork.isIpInNetwork(testBadIpAddress));
    }
}
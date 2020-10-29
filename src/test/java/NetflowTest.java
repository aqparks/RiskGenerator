import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NetflowTest {

    Netflow testNetflow;
    LocalDateTime expectedTimestamp;
    IPAddress expectedSourceIpAddress;
    IPAddress expectedDestinationIpAddress;
    Application application;

    @BeforeEach
    void setup() {
        application = new Application();
        expectedTimestamp = LocalDateTime.ofEpochSecond(333462875, 0, ZoneOffset.UTC);
        expectedSourceIpAddress = new IPAddress("237.138.224.101");
        expectedDestinationIpAddress = new IPAddress("60.67.159.30");
        testNetflow = new Netflow(application, "333462875\\t237.138.224.101\\t60.67.159.30");
    }

    @Test
    void getTimestamp() {
        assertEquals(expectedTimestamp, testNetflow.getTimestamp());
    }

    @Test
    void getSourceIp() {
        assertEquals(expectedSourceIpAddress, testNetflow.getSourceIp());
    }

    @Test
    void getDestinationIp() {
        assertEquals(expectedDestinationIpAddress, testNetflow.getDestinationIp());
    }
}
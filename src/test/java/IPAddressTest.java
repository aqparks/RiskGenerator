import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPAddressTest {

    String testIpAddress = "192.168.1.1";
    String compareIpAddress = "192.168.1.2";

    IPAddress ipAddress;
    IPAddress compareAddress;

    @BeforeEach
    void setup() {
        ipAddress = new IPAddress(testIpAddress);
        compareAddress = new IPAddress(compareIpAddress);
    }

    @Test
    void getIpAddress() {
        assertEquals(-1062731519, ipAddress.getIpAddress());
    }

    @Test
    void testEquals() {
        assertEquals(new IPAddress(testIpAddress), ipAddress);
        assertNotEquals(ipAddress, compareAddress);
    }

    @Test
    void compareTo() {
        assertTrue(ipAddress.getIpAddress() < compareAddress.getIpAddress());
    }
}
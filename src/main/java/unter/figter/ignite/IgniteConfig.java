package unter.figter.ignite;

import java.util.Arrays;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {
   
   @Bean
   public Ignite igniteInstance() {
       IgniteConfiguration cfg = new IgniteConfiguration();

       // Configuration for Ignite discovery SPI.
       TcpDiscoverySpi spi = new TcpDiscoverySpi();

       // Set the IP addresses of the nodes.
       TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
       ipFinder.setAddresses(Arrays.asList("10.101.104.140:47500"));
       spi.setIpFinder(ipFinder);

       // Apply the discovery SPI configuration.
       cfg.setDiscoverySpi(spi);

       // Other configurations...

       // Start the Ignite node.
       return Ignition.start(cfg);
   }
}

package org.apache.ignite.examples.datagrid.affinity.mapper;

import java.util.Arrays;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

public class AffinityKeyMapperExample {
    private static final String CACHE_NAME = "myCache";

    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start(createIgniteConfiguration())) {
            System.out.println();
            System.out.println(">>> Affinity key mapper example started.");

            // Auto-close cache at the end of the example.
            try (IgniteCache<String, Object> cache = ignite.getOrCreateCache(CACHE_NAME)) {
                String companyKey1 = "myCompany1";
                String companyKey2 = "myCompany2";

                Company company1 = new Company(companyKey1);
                Company company2 = new Company(companyKey2);

                cache.put(companyKey1, company1);
                cache.put(companyKey2, company2);

                String personKey1 = "myPerson1";
                String personKey2 = "myPerson2";
                String personKey3 = "myPerson3";

                Person person1 = new Person(personKey1, companyKey1);
                Person person2 = new Person(personKey2, companyKey1);
                Person person3 = new Person(personKey3, companyKey2);

                cache.put(createPersonAffinityKey(person1), person1);
                cache.put(createPersonAffinityKey(person2), person2);
                cache.put(createPersonAffinityKey(person3), person3);
            }
            finally {
                // Distributed cache could be removed from cluster only by #destroyCache() call.
                ignite.destroyCache(CACHE_NAME);
            }
        }
    }

    private static String createPersonAffinityKey(Person person) {
        return CompoundAffinityKeyUtils.createCompoundAffinityKey(person.getCompanyId(), person.getPersonId());
    }

    private static IgniteConfiguration createIgniteConfiguration() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        cfg.setPeerClassLoadingEnabled(true);

        cfg.setCacheConfiguration(createCacheConfiguration());
        cfg.setDiscoverySpi(createDiscoverySpi());

        return cfg;
    }

    private static CacheConfiguration<String, Object> createCacheConfiguration() {
        CacheConfiguration<String, Object> cacheCfg = new CacheConfiguration<>(CACHE_NAME);

        // custom AffinityKeyMapper configuration
        cacheCfg.setAffinityMapper(new CompoundAffinityKeyMapper());

        cacheCfg.setCacheMode(CacheMode.PARTITIONED);
        cacheCfg.setStatisticsEnabled(true);
        cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);

        return cacheCfg;
    }

    private static DiscoverySpi createDiscoverySpi() {
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();

        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47509"));

        discoverySpi.setIpFinder(ipFinder);

        return discoverySpi;
    }
}

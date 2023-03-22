package com.blogsearch.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {
    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {

        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        // path는 DiskStoreConfiguration 클래스의 ENV enum 참조하거나 PhysicalPath로 설정
        configuration.diskStore(new DiskStoreConfiguration().path("java.io.tmpdir"));

        net.sf.ehcache.CacheManager manager = net.sf.ehcache.CacheManager.create(configuration);

        CacheConfiguration getCacheConfig = new CacheConfiguration()
                .maxEntriesLocalHeap(1000)
                .eternal(false)
                .timeToIdleSeconds(0) // 지정한 시간 동안 사용되지 않으면, 캐시에서 제거. 값이 0이라면 조회 관련 만료 시간을 지정하지 않는다.
                .timeToLiveSeconds(30) // 지정한 시간이 지나면 캐시에서 제거된다. 값이 0이라면 만료 시간을 지정하지 않는다.
                .memoryStoreEvictionPolicy("LRU")
                .transactionalMode(CacheConfiguration.TransactionalMode.OFF)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.NONE))
                .name("getCache");
        Cache getAuthenticatedMenuByUriCache = new Cache(getCacheConfig);

        // 캐시 추가
        manager.addCache(getAuthenticatedMenuByUriCache);

        return new EhCacheCacheManager(manager);
    }
}
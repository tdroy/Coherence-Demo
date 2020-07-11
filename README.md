# Coherence-Demo

This is Oracle Coherence Cache demo. Database hold the Pincodes of India cities and state.
Suring startup time all database table loaded into cache and then displayed in portal, for this no need to make database call every time user hit the page. Hence faster response.

This project need coherence.jar in classpath.

There are two coherence configuration files. 
1) example-config.xml : This contains cache name and type (disttributed).
2) tangosol-coherence-override.xml : For cache cluster configuration, like multicast port and reference example-config.xml file.

The com.tangosol.net.CacheFactory is used to get the current cache factory. Using the factory we can fetch the Cache instance.

CacheFactory.ensureCluster();
NamedCache cache = CacheFactory.getCache("state-example");
.....
cache.put("key", stateNames);
.....
cache.get("key");


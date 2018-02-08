package com.ltybd;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created by zhouyongbo on 2017-10-31.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchConfiguration implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);
    private String clusterName;
    private String clusterNodes;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    private TransportClient transportClient;

    private PreBuiltTransportClient preBuiltTransportClient;

    @Override
    public void destroy() throws Exception {
        try {
            logger.info("Closing elasticSearch client");
            if (transportClient != null) {
                transportClient.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return transportClient;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    protected void buildClient() {
//        try {
//            preBuiltTransportClient = new PreBuiltTransportClient(settings());
//            String InetSocket[] = clusterNodes.split(":");
//            String address = InetSocket[0];
//            Integer port = Integer.valueOf(InetSocket[1]);
//            transportClient = preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));
//        } catch (UnknownHostException e) {
//            logger.error(e.getMessage());
//        }
        try {
            PreBuiltTransportClient  preBuiltTransportClient = new PreBuiltTransportClient(settings());
            if (!"".equals(clusterNodes)){
                for (String nodes:clusterNodes.split(",")) {
                    String InetSocket [] = nodes.split(":");
                    String  Address = InetSocket[0];
                    Integer  port = Integer.valueOf(InetSocket[1]);
                    preBuiltTransportClient.addTransportAddress(new
                            InetSocketTransportAddress(InetAddress.getByName(Address),port ));
                }
                transportClient = preBuiltTransportClient;
            }
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 初始化默认的client
     */
    private Settings settings() {
		Settings settings = Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
//        Settings settings = Settings.builder().put("cluster.name", "es-cluster").build();
        return settings;
    }
}

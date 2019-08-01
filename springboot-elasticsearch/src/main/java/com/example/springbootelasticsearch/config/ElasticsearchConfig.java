package com.example.springbootelasticsearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/15 19:24
 * @describe
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${spring.data.elasticsearch.username:elastic}")
    private String username;

    @Value("${spring.data.elasticsearch.password:changeme}")
    private String password;

    @Value("${spring.data.elasticsearch.cluster-name:elasticsearch}")
    private String clusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes:192.168.0.108:9300}")
    private String clusterNodes;

    private static final String SPLIT_STR = ":";

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        String[] split = clusterNodes.split(SPLIT_STR);
        TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
                .put("cluster.name", clusterName)
                .put("xpack.security.user", username + SPLIT_STR + password)
                .build())
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(split[0]), Integer.valueOf(split[1])));
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(split[0]), Integer.valueOf(split[1])));
        return client;
    }

}

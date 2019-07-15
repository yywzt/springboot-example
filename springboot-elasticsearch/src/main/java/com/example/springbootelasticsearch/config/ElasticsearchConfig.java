package com.example.springbootelasticsearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
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

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("xpack.security.user", "elastic:changeme")
                .build())
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        return client;
    }
}

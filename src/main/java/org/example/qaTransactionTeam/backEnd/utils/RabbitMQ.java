package org.example.qaTransactionTeam.backEnd.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {

    public static final Logger logger = Logger.getLogger(RabbitMQ.class);
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private AMQP.BasicProperties.Builder properties;
    private String access = Configs1.ACCESS_TO_RABBIT;


    public RabbitMQ(String host) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setUri(access+host);
        openConn();
    }

    public RabbitMQ(String url, String host) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setUri(url+host);
        openConn();
    }

    public RabbitMQ() throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setUri(access);
        openConn();
    }

    private void openConn() throws IOException, TimeoutException {
        connection = factory.newConnection();
        channel = connection.createChannel();
        properties = new AMQP.BasicProperties.Builder();
        logger.info("Открыт коннект к RabbitMQ");
    }

    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }

    public ConnectionFactory getFactory() {
        return factory;
    }

    public AMQP.BasicProperties.Builder getProperties() {
        return properties;
    }

    public void closeConn() throws IOException {
        connection.close();
        logger.info("Закрыт коннект к RabbitMQ");
    }
}

package com.rentgame.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigTopic {
	//NOME EXCHANGE
	@Value("${nome.exchange.rabbitmq}")
	private String nomeDaExchangeTopic;
	//NOME EXCHANGE
	
	//----------------------------------------------
	//ROUTING-KEY'S
	@Value("${nome.routingKey.topic1}")
	private String routingKeyTopic1;
	
	@Value("${nome.routingKey.topic2}")
	private String routingKeyTopic2;
	
	@Value("${nome.routingKey.topic3}")
	private String routingKeyTopic3;
	
	@Value("${nome.routingKey.topic4}")
	private String routingKeyTopic4;
	
	@Value("${nome.routingKey.topic5}")
	private String routingKeyTopic5;
	//ROUTING-KEY'S
	//----------------------------------------------
	
	//NOME-FILAS
	@Value("${nome.queue.fila1}")
	private String queueTesteTopic1;

	@Value("${nome.queue.fila2}")
	private String queueTesteTopic2;

	@Value("${nome.queue.fila3}")
	private String queueTesteTopic3;
	
	@Value("${nome.queue.fila4}")
	private String queueTesteTopic4;
	
	@Value("${nome.queue.fila5}")
	private String queueTesteTopic5;
	//NOME-FILAS
	
	
	
	// CRIAÇÃO DE FILAS
	@Bean(name = "queueTesteTopic1")
    public Queue queueTesteTopic1() {
        return new Queue(queueTesteTopic1, true);
    }

    @Bean(name = "queueTesteTopic2")
    public Queue queueTesteTopic2() {
        return new Queue(queueTesteTopic2, true);
    }

    @Bean(name = "queueTesteTopic3")
    public Queue queueTesteTopic3() {
        return new Queue(queueTesteTopic3, true);
    }
    @Bean(name = "queueTesteTopic4")
    public Queue queueTesteTopic4() {
    	return new Queue(queueTesteTopic4, true);
    }
    @Bean(name = "queueTesteTopic5")
    public Queue queueTesteTopic5() {
    	return new Queue(queueTesteTopic5, true);
    }
	
	//CRIAÇÃO DE FILAS

	// Bean que cria a exchange
	@Bean(name = "exchangeNameTopic")
	public TopicExchange declareExchange() {
		return ExchangeBuilder.topicExchange(nomeDaExchangeTopic)// nome da exchange
				.durable(false)// decide se a exchange é duravel ou não
				.build();
	}

	@Bean//Esse metodo declara as filas e as ralaciona com a sua exchange e a sua routing key
	public Declarables topicExchangeBindings(
			@Qualifier("exchangeNameTopic") TopicExchange topicExchange,
			@Qualifier("queueTesteTopic1") Queue queueTesteTopic1,
			@Qualifier("queueTesteTopic2") Queue queueTesteTopic2,
			@Qualifier("queueTesteTopic3") Queue queueTesteTopic3,
			@Qualifier("queueTesteTopic4") Queue queueTesteTopic4,
			@Qualifier("queueTesteTopic5") Queue queueTesteTopic5
			) {
		
		return new Declarables(
				BindingBuilder.bind(queueTesteTopic1).to(topicExchange).with(routingKeyTopic1),
				BindingBuilder.bind(queueTesteTopic2).to(topicExchange).with(routingKeyTopic2),
				BindingBuilder.bind(queueTesteTopic3).to(topicExchange).with(routingKeyTopic3),
				BindingBuilder.bind(queueTesteTopic4).to(topicExchange).with(routingKeyTopic4),
				BindingBuilder.bind(queueTesteTopic5).to(topicExchange).with(routingKeyTopic5)
				);
	}
	
	@Bean(name = "mapnames")
	public Map<String, String> boxingName(){
		Map<String, String> mapNames = new LinkedHashMap<String, String>();
		mapNames.put("exchange", nomeDaExchangeTopic);
		
		mapNames.put("routingkey1", routingKeyTopic1);
		mapNames.put("routingkey2", routingKeyTopic2);
		mapNames.put("routingkey3", routingKeyTopic3);
		return mapNames;
	}
}

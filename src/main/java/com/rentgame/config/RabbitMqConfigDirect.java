package com.rentgame.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigDirect {
	//NOME EXCHANGE
	@Value("${nome.exchange.rabbitmq}")
	private String nomeDaExchangeDirect;
	//NOME EXCHANGE
	
	//----------------------------------------------
	//ROUTING-KEY'S
	@Value("${nome.routingKey.direct1}")
	private String routingKeyDirect1;
	
	@Value("${nome.routingKey.direct2}")
	private String routingKeyDirect2;
	
	@Value("${nome.routingKey.direct3}")
	private String routingKeyDirect3;
	//ROUTING-KEY'S
	//----------------------------------------------
	
	//NOME-FILAS
	@Value("${nome.queue.fila1}")
	private String queueTesteDirect1;

	@Value("${nome.queue.fila2}")
	private String queueTesteDirect2;

	@Value("${nome.queue.fila3}")
	private String queueTesteDirect3;
	//NOME-FILAS
	
	
	
	// CRIAÇÃO DE FILAS
	@Bean(name = "queueTesteDirect1")
    public Queue queueTesteDirect1() {
        return new Queue(queueTesteDirect1, true);
    }

    @Bean(name = "queueTesteDirect2")
    public Queue queueTesteDirect2() {
        return new Queue(queueTesteDirect2, true);
    }

    @Bean(name = "queueTesteDirect3")
    public Queue queueTesteDirect3() {
        return new Queue(queueTesteDirect3, true);
    }
	
	//CRIAÇÃO DE FILAS

	// Bean que cria a exchange
	@Bean(name = "exchangeNameDirect")
	public DirectExchange declareExchange() {
		return ExchangeBuilder.directExchange(nomeDaExchangeDirect)// nome da exchange
				.durable(false)// decide se a exchange é duravel ou não
				.build();
	}

	@Bean//Esse metodo declara as filas e as ralaciona com a sua exchange e a sua routing key
	public Declarables directExchangeBindings(
			@Qualifier("exchangeNameDirect") DirectExchange directExchange,
			@Qualifier("queueTesteDirect1") Queue queueTesteDirect1,
			@Qualifier("queueTesteDirect2") Queue queueTesteDirect2,
			@Qualifier("queueTesteDirect3") Queue queueTesteDirect3) {
		return new Declarables(
				BindingBuilder.bind(queueTesteDirect1).to(directExchange).with(routingKeyDirect1),
				BindingBuilder.bind(queueTesteDirect2).to(directExchange).with(routingKeyDirect2),
				BindingBuilder.bind(queueTesteDirect3).to(directExchange).with(routingKeyDirect3));
	}
	
	@Bean(name = "mapnames")
	public Map<String, String> boxingName(){
		Map<String, String> mapNames = new LinkedHashMap<String, String>();
		mapNames.put("exchange", nomeDaExchangeDirect);
		
		mapNames.put("routingkey1", routingKeyDirect1);
		mapNames.put("routingkey2", routingKeyDirect2);
		mapNames.put("routingkey3", routingKeyDirect3);
		return mapNames;
	}
}

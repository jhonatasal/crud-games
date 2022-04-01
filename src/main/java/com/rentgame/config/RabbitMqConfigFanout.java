package com.rentgame.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigFanout {
	//NOME EXCHANGE
	@Value("${nome.exchange.rabbitmq}")
	private String nomeDaExchangeFanout;
	//NOME EXCHANGE
	
	//----------------------------------------------
	//ROUTING-KEY'S
	@Value("${nome.routingKey.fanout1}")
	private String routingKeyFanout1;
	
	@Value("${nome.routingKey.fanout2}")
	private String routingKeyFanout2;
	
	@Value("${nome.routingKey.fanout3}")
	private String routingKeyFanout3;
	//ROUTING-KEY'S
	//----------------------------------------------
	
	//NOME-FILAS
	@Value("${nome.queue.fila1}")
	private String queueTesteFanout1;

	@Value("${nome.queue.fila2}")
	private String queueTesteFanout2;

	@Value("${nome.queue.fila3}")
	private String queueTesteFanout3;
	//NOME-FILAS
	
	
	
	// CRIAÇÃO DE FILAS
	@Bean(name = "queueTesteFanout1")
    public Queue queueTesteFanout1() {
        return new Queue(queueTesteFanout1, true);
    }

    @Bean(name = "queueTesteFanout2")
    public Queue queueTesteFanout2() {
        return new Queue(queueTesteFanout2, true);
    }

    @Bean(name = "queueTesteFanout3")
    public Queue queueTesteFanout3() {
        return new Queue(queueTesteFanout3, true);
    }
	
	//CRIAÇÃO DE FILAS

	// Bean que cria a exchange
	@Bean(name = "exchangeNameFanout")
	public FanoutExchange declareExchange() {
		return ExchangeBuilder.fanoutExchange(nomeDaExchangeFanout)// nome da exchange
				.durable(false)// decide se a exchange é duravel ou não
				.build();
	}

	@Bean//Esse metodo declara as filas e as ralaciona com a sua exchange e a sua routing key
	public Declarables fanoutExchangeBindings(
			@Qualifier("exchangeNameFanout") FanoutExchange fanoutExchange,
			@Qualifier("queueTesteFanout1") Queue queueTesteFanout1,
			@Qualifier("queueTesteFanout2") Queue queueTesteFanout2,
			@Qualifier("queueTesteFanout3") Queue queueTesteFanout3) {
		
		return new Declarables(
				BindingBuilder.bind(queueTesteFanout1).to(fanoutExchange),
				BindingBuilder.bind(queueTesteFanout2).to(fanoutExchange),
				BindingBuilder.bind(queueTesteFanout3).to(fanoutExchange)
				);
	}
	
	@Bean(name = "mapnames")
	public Map<String, String> boxingName(){
		Map<String, String> mapNames = new LinkedHashMap<String, String>();
		mapNames.put("exchange", nomeDaExchangeFanout);
		
		mapNames.put("routingkey1", routingKeyFanout1);
		mapNames.put("routingkey2", routingKeyFanout2);
		mapNames.put("routingkey3", routingKeyFanout3);
		return mapNames;
	}
}

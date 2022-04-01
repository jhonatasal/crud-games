package com.rentgame.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigHeaders {
	//NOME EXCHANGE
	@Value("${nome.exchange.rabbitmq}")
	private String nomeDaExchangeHeaders;
	//NOME EXCHANGE
	
	//----------------------------------------------
	
	//NOME-FILAS
	@Value("${nome.queue.fila1}")
	private String queueTesteHeaders1;

	@Value("${nome.queue.fila2}")
	private String queueTesteHeaders2;

	@Value("${nome.queue.fila3}")
	private String queueTesteHeaders3;
	
	@Value("${nome.queue.fila4}")
	private String queueTesteHeaders4;
	//NOME-FILAS
	
	
	
	// CRIAÇÃO DE FILAS
	@Bean(name = "queueTesteHeaders1")
    public Queue queueTesteHeaders1() {
        return new Queue(queueTesteHeaders1, true);
    }

    @Bean(name = "queueTesteHeaders2")
    public Queue queueTesteHeaders2() {
        return new Queue(queueTesteHeaders2, true);
    }

    @Bean(name = "queueTesteHeaders3")
    public Queue queueTesteHeaders3() {
        return new Queue(queueTesteHeaders3, true);
    }
    
    @Bean(name = "queueTesteHeaders4")
    public Queue queueTesteHeaders4() {
    	return new Queue(queueTesteHeaders4, true);
    }
	
	//CRIAÇÃO DE FILAS

	// Bean que cria a exchange
	@Bean(name = "exchangeNameHeaders")
	public HeadersExchange declareExchange() {
		return ExchangeBuilder.headersExchange(nomeDaExchangeHeaders)// nome da exchange
				.durable(false)// decide se a exchange é duravel ou não
				.build();
	}

	@Bean//Esse metodo declara as filas e as ralaciona com a sua exchange e a sua routing key
	public Declarables headersExchangeBindings(
			@Qualifier("exchangeNameHeaders") HeadersExchange headersExchange,
			@Qualifier("queueTesteHeaders1") Queue queueTesteHeaders1,
			@Qualifier("queueTesteHeaders2") Queue queueTesteHeaders2,
			@Qualifier("queueTesteHeaders3") Queue queueTesteHeaders3,
			@Qualifier("queueTesteHeaders4") Queue queueTesteHeaders4			
			) {
		return new Declarables(
				BindingBuilder.bind(queueTesteHeaders1).to(headersExchange).whereAll("Jhonatas", "Alves", "Souza").exist(),
				BindingBuilder.bind(queueTesteHeaders2).to(headersExchange).whereAny("Jhonatas", "Alves", "Souza").exist(),
				BindingBuilder.bind(queueTesteHeaders3).to(headersExchange).whereAll(Map.of("Nome","Jhonatas","Idade",26)).match(),
				BindingBuilder.bind(queueTesteHeaders4).to(headersExchange).whereAny(Map.of("Nome","Jhonatas","Idade",26)).match()
				);
	}
	
	@Bean(name = "mapnames")
	public Map<String, String> boxingName(){
		Map<String, String> mapNames = new LinkedHashMap<String, String>();
		mapNames.put("exchange", nomeDaExchangeHeaders);
		
		return mapNames;
	}
}

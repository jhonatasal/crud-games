package com.rentgame.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
	@Value("${topic.name1}")
	private String nameTopic1;

	@Bean
	public NewTopic topic1() {
		return TopicBuilder
				.name(nameTopic1)//nome do topico
				.partitions(3) //o numero de partições determina a qtd de participantes de um grupo
				.build();
	}
}

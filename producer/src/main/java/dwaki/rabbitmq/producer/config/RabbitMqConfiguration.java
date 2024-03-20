package dwaki.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dwaki.rabbitmq.producer.utils.ProducerConstants;

@Configuration
public class RabbitMqConfiguration {

	private String exchange = ProducerConstants.EXCHANGE_NAME;
	private String queue = ProducerConstants.QUEUE_NAME;
	private String rountingKey = ProducerConstants.ROUTING_KEY;

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Queue queue() {
		return new Queue(queue);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(rountingKey);
	}

}

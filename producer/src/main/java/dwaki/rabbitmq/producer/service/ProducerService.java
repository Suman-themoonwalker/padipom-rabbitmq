package dwaki.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import dwaki.rabbitmq.producer.model.Due;
import dwaki.rabbitmq.producer.utils.ProducerConstants;

@Service
public class ProducerService {

	private static final Logger LOG = LoggerFactory.getLogger(ProducerService.class);

	private String exchange = ProducerConstants.EXCHANGE_NAME;
	private String rountingKey = ProducerConstants.ROUTING_KEY;

	private RabbitTemplate rabbitTemplate;

	public ProducerService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public String sendMessage(Due due) {

		try {

			rabbitTemplate.convertAndSend(exchange, rountingKey, due);
			LOG.info(String.format("This message sent to queue %s", due));
			return "Message Sent";

		} catch (Exception e) {

			LOG.info(String.format("This message failed to send to queue %s", due));
			return "Message Failed to Send";

		}

	}

}

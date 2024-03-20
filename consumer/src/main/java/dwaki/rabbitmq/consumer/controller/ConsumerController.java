package dwaki.rabbitmq.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

import dwaki.rabbitmq.consumer.utils.ConsumerConstants;

@Controller
public class ConsumerController {

	private static final Logger LOG = LoggerFactory.getLogger(ConsumerController.class);

	@RabbitListener(queues = { ConsumerConstants.QUEUE_NAME })
	public void consumeProductDetails(String productDetails) {

		LOG.info(String.format("This is the consumed product details %s", productDetails));

	}

}

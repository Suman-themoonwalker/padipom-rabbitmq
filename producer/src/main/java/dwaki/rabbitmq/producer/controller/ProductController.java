package dwaki.rabbitmq.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dwaki.rabbitmq.producer.model.Due;
import dwaki.rabbitmq.producer.service.ProducerService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	private ProducerService service;

	public ProductController(ProducerService service) {
		this.service = service;
	}

	@PostMapping("/pubNsub/sendDetails")
	public ResponseEntity<String> sendProductDetails(@RequestBody Due due) {

		LOG.info(String.format("Received this message %s", due.toString()));
		service.sendMessage(due);

		return ResponseEntity.ok(String.format("This Message sent to Queue %s", due.toString()));

	}

}

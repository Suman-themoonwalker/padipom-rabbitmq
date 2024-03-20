package dwaki.rabbitmq.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dwaki.rabbitmq.producer.model.Product;
import dwaki.rabbitmq.producer.service.ProducerService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	private ProducerService service;

	public ProductController(ProducerService service) {
		this.service = service;
	}

	@PostMapping("/sendDetails")
	public ResponseEntity<String> sendProductDetails(@RequestBody Product product) {

		LOG.info(String.format("Received this message %s", product.toString()));
		service.sendMessage(product.toString());

		return ResponseEntity.ok(String.format("This Message sent to Queue %s", product.toString()));

	}

}

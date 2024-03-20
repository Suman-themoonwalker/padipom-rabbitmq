package dwaki.rabbitmq.producer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class Product {

	private String productName;
	private Integer numberOfItems;

}

package dwaki.rabbitmq.producer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class Due {

	private String companyName;
	private Double dueAmount;
	private String dueDate;

}

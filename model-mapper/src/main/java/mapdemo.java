import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.gillianbc.model.Address;
import com.gillianbc.model.Customer;
import com.gillianbc.model.Item;
import com.gillianbc.model.Name;
import com.gillianbc.model.Order;
import com.gillianbc.model.OrderDTO;

public class mapdemo {

	public static void main(String[] args) {

		ModelMapper modelMapper = new ModelMapper();

		// Make some data
		// Order uses the Address and Customer classes
		// Customer uses the Name class
		// There is also a collection of Items
		Order sourceOrder = makeData();

		// We can map it to the target type even though the fieldnames
		// don't match exactly

		OrderDTO targetOrderDTO = modelMapper.map(sourceOrder, OrderDTO.class);
		System.out.println(targetOrderDTO);

		// We can also map it back again

		Order targetOrder = modelMapper.map(targetOrderDTO, Order.class);
		System.out.println(targetOrder);
	}

	public static Order makeData() {

		Address billingAddress = new Address();
		billingAddress.setCity("Sheffield");
		billingAddress.setStreet("High St");

		Name name = new Name();
		name.setFirstName("Gillian");
		name.setLastName("Szemeti");

		Customer customer = new Customer();
		customer.setName(name);

		Item item = new Item();
		item.setItemName("Candyfloss");
		item.setPrice(2);

		Item item2 = new Item();
		item2.setItemName("Handkerchief");
		item2.setPrice(3);

		List<Item> bought = new ArrayList<>();
		bought.add(item);
		bought.add(item2);

		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setCustomer(customer);
		order.setItemsList(bought);

		return order;

	}

}

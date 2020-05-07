import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.gillianbc.model.Address;
import com.gillianbc.model.Customer;
import com.gillianbc.model.Item;
import com.gillianbc.model.Name;
import com.gillianbc.model.Order;
import com.gillianbc.model.OrderDTO;

import lombok.Data;


public class mapdemo {

	public static void main(String[] args) {
		System.out.println(makeAndMapData());
	}

	public static OrderDTO makeAndMapData() {
		
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
		
		//  This is the source object we're going to map
		//  It uses the Address and Customer classes
		//  Customer uses the Name class
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setCustomer(customer);
		
		List<Item> bought = new ArrayList<>();
		bought.add(item);
		bought.add(item2);
		
		order.setItemsList(bought);
		
		
		// The model mapper can implicitly work out the mappings from the field names
		ModelMapper modelMapper = new ModelMapper();
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		
		return orderDTO;
		
		
	}

	
	

	
	

	

}

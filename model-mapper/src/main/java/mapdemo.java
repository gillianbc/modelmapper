import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import com.gillianbc.model.Address;
import com.gillianbc.model.Customer;
import com.gillianbc.model.Item;
import com.gillianbc.model.ItemDTO;
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
		System.out.println(sourceOrder);
		//  Order(customer=Customer(name=Name(firstName=Gillian, lastName=Szemeti)), 
		//  billingAddress=Address(street=High St, city=Sheffield), 
		//  itemsList=[Item(itemName=Candyfloss, price=2), Item(itemName=Handkerchief, price=3)])
		

		// We can map it to the target type even though the fieldnames
		// don't match exactly

		OrderDTO targetOrderDTO = modelMapper.map(sourceOrder, OrderDTO.class);
		System.out.println(targetOrderDTO);
		//  OrderDTO(customerFirstName=Gillian, customerLastName=Szemeti, 
		//  billingStreet=High St, billingCity=Sheffield, 
		//  items=[Item(itemName=Candyfloss, price=2), Item(itemName=Handkerchief, price=3)])
		

		// We can also map it back again, but it's not managed to do the address

		Order targetOrder = modelMapper.map(targetOrderDTO, Order.class);
		System.out.println(targetOrder);
		//  Order(customer=Customer(name=Name(firstName=Gillian, lastName=Szemeti)), 
		//  billingAddress=null, 
		//  itemsList=[Item(itemName=Candyfloss, price=2), Item(itemName=Handkerchief, price=3)])
		

		// Now let's define a converter for strings. When we map a string to a string,
		// we'll make it uppercase (this is looking very javascript-like)
		// It hasn't uppercased the child recs - item.itemName - not sure why

		Converter<String, String> bigLetters = new AbstractConverter<String, String>() {
			protected String convert(String source) {
				return source == null ? null : source.toUpperCase();
			}
		};

		modelMapper.addConverter(bigLetters);

		targetOrderDTO = modelMapper.map(sourceOrder, OrderDTO.class);
		System.out.println(targetOrderDTO);
		//  OrderDTO(customerFirstName=GILLIAN, customerLastName=SZEMETI, 
		//  billingStreet=HIGH ST, billingCity=SHEFFIELD, 
		//  items=[Item(itemName=Candyfloss, price=2), Item(itemName=Handkerchief, price=3)])
		
		Converter<List<Item>, List<ItemDTO>> bigItems = new AbstractConverter<List<Item>, List<ItemDTO>> () {
			protected List<ItemDTO> convert(List<Item> source) {
				List<ItemDTO> result = new ArrayList<>();
 				for (Item sourceitem : source) {
 					ItemDTO targetItem = modelMapper.map(sourceitem, ItemDTO.class);
					result.add(targetItem);
					
				}
 				return result;
			}
		};

		modelMapper.addConverter(bigItems);
		

		targetOrderDTO = modelMapper.map(sourceOrder, OrderDTO.class);
		
		System.out.println(targetOrderDTO);
		// Not sure why I have to do this faffing to print the ArrayList, but it has uppercased the strings now
		// i.e. it's applied the list converter and then the string converter
		System.out.println(targetOrderDTO.getItems().get(0).getItemName());
		System.out.println(targetOrderDTO.getItems().get(1).getItemName());
		
		
		

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

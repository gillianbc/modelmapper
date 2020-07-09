import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.ValidationException;

import com.gillianbc.model.Apple;
import com.gillianbc.model.BramleyApple;
import com.gillianbc.model.Delivery;
import com.gillianbc.model.Orange;
import com.gillianbc.model.PieceOfFruit;

public class mapdemo {

	public static void main(String[] args) {

		ModelMapper modelMapper = new ModelMapper();

		//  We have Apples and Oranges
		//  They have subclasses of BramleyApple and Tangerine which have a cost field
		//  We want to map them both to PieceOfFruit and MenuItem
		//  Additionally, we have Delivery items which is where we'll get the shipping number for our Piece of Fruit
		//  I've deliberately used different field names to show explicit mappings

		// First, let's create a BramleyApple
		BramleyApple bramley = new BramleyApple();
		bramley.setSkinType("red and shiny");
		bramley.setSweet(true);
		
		
		
		// Create our blank destination object
		PieceOfFruit pieceOfFruit = new PieceOfFruit();
		
		//  Now we create a type map
		TypeMap<BramleyApple, PieceOfFruit> bramleyToFruit = modelMapper.createTypeMap(BramleyApple.class, PieceOfFruit.class);
		
		//Add a mapping
		bramleyToFruit.addMapping(BramleyApple::getSkinType, PieceOfFruit::setAppearance);
		System.out.println("We have a mapping now " + bramleyToFruit.getMappings());
		
		bramleyToFruit.addMappings(mapper -> {
			  
			mapper.map(src -> src.getTree().getTreeName(), (dest, treename) -> dest.getPlant().setPlantName((String) treename));
			
			});
		
		System.out.println("We have a subclass mapping now " + bramleyToFruit.getMappings());
		
		// Now we create a Delivery
		Delivery delivery = new Delivery();
		delivery.setDeliveryNumber(1234);
		
		TypeMap<Delivery, PieceOfFruit> deliveryToFruit = modelMapper.createTypeMap(Delivery.class, PieceOfFruit.class);
		deliveryToFruit.addMapping(Delivery::getDeliveryNumber, PieceOfFruit::setShippingNumber);
		System.out.println("We have a delivery mapping now " + deliveryToFruit.getMappings());
		
		//Now map
		modelMapper.map(bramley, pieceOfFruit);
		System.out.println(pieceOfFruit);
		
		//Now add in the delivery details
		modelMapper.map(delivery, pieceOfFruit);
		System.out.println(pieceOfFruit);
		
	
		/*//  Explicit Mappings / Expression Mappings
		modelMapper.typeMap(Apple.class, Orange.class).addMappings(mapper -> {
			  
			mapper.map(src -> src.getSkinType(), Orange::setPeelType);
			mapper.map(src -> src.isSweet(), Orange::setRipe);
			
			});
		
		// You can also map from/to child elements
		// There are examples on this page: http://modelmapper.org/user-manual/property-mapping/
		
		// View the typemap we've built:
		TypeMap<Apple, Orange> appleToOrange = modelMapper.getTypeMap(Apple.class, Orange.class);
		System.out.println(appleToOrange.getMappings());

		// Do the mapping
		modelMapper.map(apple, orange);
		
		System.out.println("Apple: " + apple);
		System.out.println(" Orange " + orange);
		
		// Validating Matches.  If any fields in the target have not been populated
		// we'll see an exception here
		try {
			modelMapper.validate();
		} catch (ValidationException e) {
			System.out.println("\n ***** This is a deliberate error: *****\n");
			System.out.println(e.getMessage());
		}
		
		//Create a typemap
		TypeMap<Orange, Apple> orangeToApple = modelMapper.createTypeMap(Orange.class, Apple.class);
		System.out.println("\nNo orangeToApple mappings yet " + orangeToApple.getMappings());
		
		//Add a mapping
		orangeToApple.addMapping(Orange::getSeedCount, Apple::setPipsCount);
		System.out.println("We have a mapping now " + orangeToApple.getMappings());
		
		//Add another mapping - note the different syntax
		orangeToApple.addMappings(mapper -> mapper.map(Orange::getPeelType, Apple::setSkinType));
		System.out.println("We have 2 mappings now " + orangeToApple.getMappings());
		
		// Using the property map will even cater for mismatched data types
		// Here I am mapping farmer = "1" to producerNumber = 1
		orangeToApple.addMappings(mapper -> 
			mapper.map(Orange::getFarmer, Apple::setProducerNumber));
		
		System.out.println("We have 3 mappings now " + orangeToApple.getMappings());
		orange.setFarmer("1");
		
		System.out.println("\nDo the mapping, it will map a string to a number:");
		modelMapper.map(orange, apple);
		System.out.println("Apple: " + apple);
		System.out.println(" Orange " + orange);
		
		//  But of course it won't be able to map "Farmer John" to a number
		orange.setFarmer("Farmer John");
		try {
			modelMapper.map(orange, apple);
		} catch (MappingException e) {
			System.out.println("\nIt errored as I expected - cannot map \"Farmer John\" to a number");
		}*/
		
		
		

	}

}

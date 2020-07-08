import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.ValidationException;

import com.gillianbc.model.Apple;
import com.gillianbc.model.Orange;



public class mapdemo {

	public static void main(String[] args) {

		ModelMapper modelMapper = new ModelMapper();
		
		Apple apple = new Apple();
		apple.setSkinType("red and shiny");
		apple.setSweet(true);
		Orange orange = new Orange();
	
		//  Explicit Mappings / Expression Mappings
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
		}
		
		
		

	}

}

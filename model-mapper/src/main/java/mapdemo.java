import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.gillianbc.model.Apple;
import com.gillianbc.model.Orange;



public class mapdemo {

	public static void main(String[] args) {

		ModelMapper modelMapper = new ModelMapper();
		
		Apple apple = new Apple();
		apple.setSkinType("red and shiny");
		apple.setSweet(true);
		Orange orange = new Orange();
	
		modelMapper.typeMap(Apple.class, Orange.class).addMappings(mapper -> {
			  
			mapper.map(src -> src.getSkinType(), Orange::setPeelType);
			mapper.map(src -> src.isSweet(), Orange::setRipe);
			
			});
		
		// View the typemap we've built:
		TypeMap<Apple, Orange> typeMap = modelMapper.getTypeMap(Apple.class, Orange.class);
		System.out.println(typeMap.getMappings());

		// Do the mapping
		modelMapper.map(apple, orange);
		
		System.out.println("Apple: " + apple + " Orange " + orange);

	}

}

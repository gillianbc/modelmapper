import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.ValidationException;

import com.gillianbc.model.Apple;
import com.gillianbc.model.BramleyApple;
import com.gillianbc.model.Delivery;
import com.gillianbc.model.Orange;
import com.gillianbc.model.PieceOfFruit;
import com.gillianbc.model.Tree;

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
		
		Tree tree = new Tree();
		tree.setTreeName("Old apple tree");
		bramley.setTree(tree);
		
		
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
		
	
		
		
		

	}

}

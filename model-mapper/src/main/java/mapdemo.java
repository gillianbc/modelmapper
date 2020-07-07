import org.modelmapper.ModelMapper;

import com.gillianbc.model.Address;
import com.gillianbc.model.Person;
import com.gillianbc.model.PersonWithAddressVO;

public class mapdemo {

	public static void main(String[] args) {

		ModelMapper modelMapper = new ModelMapper();

		// Make some data
		PersonWithAddressVO sourcePersonWithAddressVO = makeData();
		System.out.println(sourcePersonWithAddressVO);

		//  Map the PersonWithAddressVO to both a Person and an Address
		//  I've not even added any mapping instructions - it can guess what I want
		//  from the field names
		Person person = modelMapper.map(sourcePersonWithAddressVO, Person.class);
		System.out.println(person);

		Address address = modelMapper.map(sourcePersonWithAddressVO, Address.class);
		System.out.println(address);

	}

	public static PersonWithAddressVO makeData() {

		PersonWithAddressVO personWithAddressVO = new PersonWithAddressVO();
		personWithAddressVO.setCity("Sheffield");
		personWithAddressVO.setStreet("High St");
		personWithAddressVO.setFirstName("Gillian");
		personWithAddressVO.setLastName("Szemeti");

		return personWithAddressVO;

	}

}

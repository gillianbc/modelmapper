package com.gillianbc.model;
import java.util.List;

import lombok.Data;

@Data
public class PersonWithAddressVO {
	private String firstName;
	private String lastName;
	private String street;
	private String city;
}
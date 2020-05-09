package com.gillianbc.model;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	private String customerFirstName;
	private String customerLastName;
	private String billingStreet;
	private String billingCity;
	
	private List<ItemDTO> items;
}
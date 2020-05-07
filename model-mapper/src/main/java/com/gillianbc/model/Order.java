package com.gillianbc.model;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	private Customer customer;
	private Address billingAddress;
	List<Item> itemsList;
}
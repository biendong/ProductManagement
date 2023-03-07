package com.retailer.product.dtos;

import lombok.Data;

@Data
public class RequestedProduct {
	private String title;

	private String description;

	private String brand;

	private Integer categoryId;
}

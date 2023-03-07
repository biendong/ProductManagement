package com.retailer.product.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
	private Integer id;

	private String title;

	private String description;

	private String brand;

	private Date modifiedDate;

	private Date createdDate;

	private CategoryDTO category;
}

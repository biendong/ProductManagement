package com.retailer.product.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryDTO {
	private Integer id;

	private String name;

	private Date modifiedDate;

	private Date createdDate;

//	private List<ProductDTO> products;
}

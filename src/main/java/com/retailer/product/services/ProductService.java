package com.retailer.product.services;

import java.util.List;
import java.util.Optional;

import com.retailer.product.dtos.ProductDTO;
import com.retailer.product.dtos.RequestedProduct;

public interface ProductService {
	/**
	 * Get all the categories
	 * 
	 * @return The {@link List} of {@link ProductDTO}
	 */
	List<ProductDTO> getAllProduct();

	/**
	 * Get {@link ProductDTO} by product Id
	 * 
	 * @param productId The product Id
	 * @return The {@link Optional} of {@link ProductDTO}
	 */
	Optional<ProductDTO> getProductById(Integer productId);

	/**
	 * Create a {@link ProductDTO}
	 * 
	 * @param requestedProduct The requested product
	 * @return The {@link Optional} of created {@link ProductDTO}
	 */
	Optional<ProductDTO> createProduct(RequestedProduct requestedProduct);

	/**
	 * Update a {@link ProductDTO}
	 * 
	 * @param productId        The product Id
	 * @param requestedProduct The requested product
	 * @return The {@link Optional} of updated {@link ProductDTO}
	 */
	Optional<ProductDTO> updateProduct(Integer productId, RequestedProduct requestedProduct);

	/**
	 * Delete a {@link ProductDTO} by Id
	 * 
	 * @param productId
	 * @return true if delete successfully otherwise false
	 */
	boolean deleteProduct(Integer productId);
}

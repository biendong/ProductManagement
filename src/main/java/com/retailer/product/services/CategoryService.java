package com.retailer.product.services;

import java.util.List;
import java.util.Optional;

import com.retailer.product.dtos.CategoryDTO;
import com.retailer.product.dtos.RequestedCategory;

/**
 * Category service
 * 
 * @author haitrand
 * @since 05 Mar 2023
 */
public interface CategoryService {
	/**
	 * Get all the categories
	 * 
	 * @return The {@link List} of {@link CategoryDTO}
	 */
	List<CategoryDTO> getAllCategory();

	/**
	 * Get {@link CategoryDTO} by category Id
	 * 
	 * @param categoryId The category Id
	 * @return The {@link Optional} of {@link CategoryDTO}
	 */
	Optional<CategoryDTO> getCategoryById(Integer categoryId);

	/**
	 * Create a {@link CategoryDTO}
	 * 
	 * @param requestedCategory The requested category
	 * @return The {@link Optional} of created {@link CategoryDTO}
	 */
	Optional<CategoryDTO> createCategory(RequestedCategory requestedCategory);

	/**
	 * Update a {@link CategoryDTO}
	 * 
	 * @param categoryId        The category Id
	 * @param requestedCategory The requested category
	 * @return The {@link Optional} of updated {@link CategoryDTO}
	 */
	Optional<CategoryDTO> updateCategory(Integer categoryId, RequestedCategory requestedCategory);

	/**
	 * Delete a {@link CategoryDTO} by Id
	 * 
	 * @param categoryId
	 * @return true if delete successfully otherwise false
	 */
	boolean deleteCategory(Integer categoryId);
}

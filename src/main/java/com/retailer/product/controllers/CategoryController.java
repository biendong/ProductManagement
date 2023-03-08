package com.retailer.product.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.retailer.product.dtos.CategoryDTO;
import com.retailer.product.dtos.RequestedCategory;
import com.retailer.product.services.CategoryService;

/**
 * Category controller
 * 
 * @author haitrand
 * @since 05 Mar 2023
 */
@RestController
@RequestMapping("categories")
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * Get all the categories
	 * 
	 * @return The {@link List} of {@link CategoryDTO}
	 */
	@GetMapping(path = "")
	public List<CategoryDTO> getAllCategory() {
		return categoryService.getAllCategory();
	}

	/**
	 * Get {@link CategoryDTO} by category Id
	 * 
	 * @param categoryId The category Id
	 * @return The {@link CategoryDTO}
	 */
	@GetMapping(path = "/{id}")
	public CategoryDTO getCategory(@PathVariable(name = "id") Integer categoryId) {
		return categoryService.getCategoryById(categoryId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
	}

	/**
	 * Create a {@link CategoryDTO}
	 * 
	 * @param requestedCategory The requested category
	 * @return The created category
	 */
	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryDTO createCategory(@RequestBody RequestedCategory requestedCategory) {
		return categoryService.createCategory(requestedCategory)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fail to create category"));
	}

	/**
	 * Update a {@link CategoryDTO}
	 * 
	 * @param categoryId        The category Id
	 * @param requestedCategory The requested category
	 * @return The updated category
	 */
	@PutMapping(path = "/{id}")
	public CategoryDTO updateCategory(@PathVariable(name = "id") Integer categoryId,
			@RequestBody RequestedCategory requestedCategory) {
		return categoryService.updateCategory(categoryId, requestedCategory)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
	}

	/**
	 * Delete a {@link CategoryDTO} by Id
	 * 
	 * @param categoryId
	 * @return true if delete successfully otherwise false
	 */
	@DeleteMapping(path = "/{id}")
	public boolean deleteCategoryById(@PathVariable(name = "id") Integer categoryId) {
		return categoryService.deleteCategory(categoryId);
	}
}

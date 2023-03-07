package com.retailer.product.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.retailer.product.dtos.CategoryDTO;
import com.retailer.product.dtos.RequestedCategory;
import com.retailer.product.mappers.CategoryMapper;
import com.retailer.product.models.Category;
import com.retailer.product.repositories.CategoryRepositories;
import com.retailer.product.services.CategoryService;

/**
 * The implementation class of {@link CategoryService}
 * 
 * @author haitrand
 * @since 05 Mar 2023
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryMapper categoryMapper;
	private CategoryRepositories categoryRepositories;

	public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepositories categoryRepositories) {
		this.categoryMapper = categoryMapper;
		this.categoryRepositories = categoryRepositories;
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		return categoryMapper.mapModelToDtos(categoryRepositories.findAll());
	}

	@Override
	public Optional<CategoryDTO> getCategoryById(Integer categoryId) {
		Optional<Category> optCategory = categoryRepositories.findById(categoryId);
		if (!optCategory.isPresent()) {
			return Optional.empty();
		}

		return Optional.ofNullable(categoryMapper.mapModelToDto(optCategory.get()));
	}

	@Override
	public Optional<CategoryDTO> createCategory(RequestedCategory requestedCategory) {
		Category category = categoryMapper.mapRequestedToModel(requestedCategory);

		Date currentDate = new Date();
		category.setModifiedDate(currentDate);
		category.setCreatedDate(currentDate);

		category = categoryRepositories.save(category);

		return Optional.ofNullable(categoryMapper.mapModelToDto(category));
	}

	@Override
	public Optional<CategoryDTO> updateCategory(Integer categoryId, RequestedCategory requestedCategory) {
		Optional<Category> optCategory = categoryRepositories.findById(categoryId);
		if (!optCategory.isPresent()) {
			return Optional.empty();
		}

		Category category = optCategory.get();
		categoryMapper.updateModel(category, requestedCategory);

		Date currentDate = new Date();
		category.setModifiedDate(currentDate);

		category = categoryRepositories.save(category);

		return Optional.ofNullable(categoryMapper.mapModelToDto(category));
	}

	@Override
	public boolean deleteCategory(Integer categoryId) {
		Optional<Category> optCategory = categoryRepositories.findById(categoryId);
		if (!optCategory.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		}

		categoryRepositories.delete(optCategory.get());

		return true;
	}
}

package com.retailer.product.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.retailer.product.dtos.CategoryDTO;
import com.retailer.product.dtos.RequestedCategory;
import com.retailer.product.models.Category;

@Mapper(uses = ProductMapper.class)
public interface CategoryMapper {
	List<CategoryDTO> mapModelToDtos(List<Category> categories);

	CategoryDTO mapModelToDto(Category category);

	List<Category> mapDtoToModels(List<CategoryDTO> categoryDtos);

	Category mapDtoToModel(CategoryDTO categoryDto);

	Category mapRequestedToModel(RequestedCategory requestedCategory);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateModel(@MappingTarget Category category, RequestedCategory requestedCategory);
}

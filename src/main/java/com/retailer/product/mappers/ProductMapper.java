package com.retailer.product.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.retailer.product.dtos.ProductDTO;
import com.retailer.product.dtos.RequestedProduct;
import com.retailer.product.models.Product;

@Mapper
public interface ProductMapper {
	List<ProductDTO> mapModelToDtos(List<Product> products);

	ProductDTO mapModelToDto(Product product);

	List<Product> mapDtoToModels(List<ProductDTO> productDtos);

	Product mapDtoToModel(ProductDTO productDto);

	@Mapping(target = "category.id", source = "categoryId")
	Product mapRequestedToModel(RequestedProduct requestedProduct);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "category.id", source = "categoryId")
	void updateModel(@MappingTarget Product product, RequestedProduct requestedProduct);
}

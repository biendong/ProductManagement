package com.retailer.product.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.retailer.product.dtos.ProductDTO;
import com.retailer.product.dtos.RequestedProduct;
import com.retailer.product.mappers.ProductMapper;
import com.retailer.product.models.Category;
import com.retailer.product.models.Product;
import com.retailer.product.repositories.CategoryRepositories;
import com.retailer.product.repositories.ProductRepositories;
import com.retailer.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductMapper productMapper;
	private ProductRepositories productRepositories;
	private CategoryRepositories categoryRepositories;

	public ProductServiceImpl(ProductMapper productMapper, ProductRepositories productRepositories,
			CategoryRepositories categoryRepositories) {
		this.productMapper = productMapper;
		this.productRepositories = productRepositories;
		this.categoryRepositories = categoryRepositories;
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		return productMapper.mapModelToDtos(productRepositories.findAll());
	}

	@Override
	public Optional<ProductDTO> getProductById(Integer productId) {
		Optional<Product> optProduct = productRepositories.findById(productId);
		if (!optProduct.isPresent()) {
			return Optional.empty();
		}

		return Optional.ofNullable(productMapper.mapModelToDto(optProduct.get()));
	}

	private void validateCategory(Integer category) {
		if (Objects.isNull(category)) {
			return;
		}

		Optional<Category> optCategory = categoryRepositories.findById(category);
		if (!optCategory.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		}
	}

	@Override
	public Optional<ProductDTO> createProduct(RequestedProduct requestedProduct) {
		validateCategory(requestedProduct.getCategoryId());

		Product product = productMapper.mapRequestedToModel(requestedProduct);

		Date currentDate = new Date();
		product.setModifiedDate(currentDate);
		product.setCreatedDate(currentDate);

		product = productRepositories.save(product);

		return Optional.ofNullable(productMapper.mapModelToDto(product));
	}

	@Override
	public Optional<ProductDTO> updateProduct(Integer productId, RequestedProduct requestedProduct) {
		Optional<Product> optProduct = productRepositories.findById(productId);
		if (!optProduct.isPresent()) {
			return Optional.empty();
		}

		validateCategory(requestedProduct.getCategoryId());

		Product product = optProduct.get();
		productMapper.updateModel(product, requestedProduct);

		Date currentDate = new Date();
		product.setModifiedDate(currentDate);

		product = productRepositories.save(product);

		return Optional.ofNullable(productMapper.mapModelToDto(product));
	}

	@Override
	public boolean deleteProduct(Integer productId) {
		Optional<Product> optProduct = productRepositories.findById(productId);
		if (!optProduct.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		}

		productRepositories.delete(optProduct.get());

		return true;
	}
}

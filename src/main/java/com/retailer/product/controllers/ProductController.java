package com.retailer.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.retailer.product.dtos.ProductDTO;
import com.retailer.product.dtos.RequestedProduct;
import com.retailer.product.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Get all the categories
	 * 
	 * @return The {@link List} of {@link ProductDTO}
	 */
	@GetMapping(path = "")
	public List<ProductDTO> getAllProduct() {
		return productService.getAllProduct();
	}

	/**
	 * Get {@link ProductDTO} by product Id
	 * 
	 * @param productId The product Id
	 * @return The {@link ProductDTO}
	 */
	@GetMapping(path = "/{id}")
	public ProductDTO getProduct(@PathVariable(name = "id") Integer productId) {
		Optional<ProductDTO> optProduct = productService.getProductById(productId);
		if (!optProduct.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}

		return optProduct.get();
	}

	/**
	 * Create a {@link ProductDTO}
	 * 
	 * @param requestedProduct The requested product
	 * @return The created product
	 */
	@PostMapping(path = "")
	public ProductDTO createProduct(@RequestBody RequestedProduct requestedProduct) {
		Optional<ProductDTO> optProduct = productService.createProduct(requestedProduct);
		if (!optProduct.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fail to create product");
		}

		return optProduct.get();
	}

	/**
	 * Update a {@link ProductDTO}
	 * 
	 * @param productId        The product Id
	 * @param requestedProduct The requested product
	 * @return The updated product
	 */
	@PutMapping(path = "/{id}")
	public ProductDTO updateProduct(@PathVariable(name = "id") Integer productId,
			@RequestBody RequestedProduct requestedProduct) {
		Optional<ProductDTO> optProduct = productService.updateProduct(productId, requestedProduct);
		if (!optProduct.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}

		return optProduct.get();
	}

	/**
	 * Delete a {@link ProductDTO} by Id
	 * 
	 * @param productId
	 * @return true if delete successfully otherwise false
	 */
	@DeleteMapping(path = "/{id}")
	public boolean deleteProductById(@PathVariable(name = "id") Integer productId) {
		return productService.deleteProduct(productId);
	}
}

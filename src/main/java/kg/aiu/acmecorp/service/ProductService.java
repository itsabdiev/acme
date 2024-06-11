package kg.aiu.acmecorp.service;

import kg.aiu.acmecorp.enums.Category;
import kg.aiu.acmecorp.model.request.ProductRequest;
import kg.aiu.acmecorp.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllByCategory(List<Category> category);

    List<ProductResponse> getAll();

    ProductResponse getByName(String name);

    Object getById(Long id);

    void removeProductById(Long id);

    void createProduct(ProductRequest request);

    void modifyProduct(ProductRequest request, Long id);
}

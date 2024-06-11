package kg.aiu.acmecorp.endpoint;


import kg.aiu.acmecorp.enums.Category;
import kg.aiu.acmecorp.exception.CustomException;
import kg.aiu.acmecorp.model.request.ProductRequest;
import kg.aiu.acmecorp.model.response.ProductResponse;
import kg.aiu.acmecorp.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductEndpoint {

    ProductService service;

    public List<ProductResponse> getAll(Optional<List<Category>> categories) throws CustomException {
        if (categories.isPresent()) return service.getAllByCategory(categories.get());
        return service.getAll();
    }

    public ProductResponse getByName(String name) {
        return service.getByName(name);
    }

    public Object getProductById(Long id) {
        return service.getById(id);
    }

    public void removeProductById(Long id) {
        service.removeProductById(id);
    }

    public void createProduct(ProductRequest request) {
        service.createProduct(request);
    }

    public void modifyProduct(ProductRequest request, Long id) {
        service.modifyProduct(request,id);
    }
}

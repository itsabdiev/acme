package kg.aiu.acmecorp.service.impl;

import kg.aiu.acmecorp.entity.Product;
import kg.aiu.acmecorp.enums.Category;
import kg.aiu.acmecorp.exception.CustomException;
import kg.aiu.acmecorp.mapper.ProductMapper;
import kg.aiu.acmecorp.model.request.ProductRequest;
import kg.aiu.acmecorp.model.response.ProductResponse;
import kg.aiu.acmecorp.repo.ProductRepo;
import kg.aiu.acmecorp.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepo repo;
    ProductMapper mapper;

    @Override
    public List<ProductResponse> getAllByCategory(List<Category> categories) {
        List<String> categoriesStringList = categories.stream().map(Enum::name).toList();
        Optional<List<Product>> allByCategory = repo.findAllByCategory(categoriesStringList);
        if (allByCategory.isEmpty()) throw new CustomException("Products with these attributes have not been found");
        return allByCategory.get().stream().map(mapper::toModel).toList();
    }

    @Override
    public List<ProductResponse> getAll() {
        Optional<List<Product>> all = repo.findAllWithoutAttributes();
        if (all.isEmpty()) throw new CustomException("List is empty");
        return all.get().stream().map(mapper::toModel).toList();
    }

    @Override
    public ProductResponse getByName(String name) {
        Optional<Product> byName = repo.findByName(name);
        if (byName.isEmpty()) throw new CustomException("Product with this name does not exist");
        return mapper.toModel(byName.get());
    }

    @Override
    public ProductResponse getById(Long id) {
        return mapper.toModel(getByIdOrElseThrow(id));
    }

    private Product getByIdOrElseThrow(Long id) {
        Optional<Product> byId = repo.findById(id);
        if (byId.isEmpty()) throw new CustomException("Product with this id does not exist");
        return byId.get();
    }

    @Override
    public void removeProductById(Long id) {
        getByIdOrElseThrow(id);
        repo.removeById(id);
    }

    @Override
    public void createProduct(ProductRequest request) {
        if (repo.findByName(request.name()).isPresent()) throw new CustomException("Product with this name already exist");
        repo.save(mapper.toEntity(request));
    }

    @Override
    public void modifyProduct(ProductRequest request, Long id) {
        Product existingProduct = getByIdOrElseThrow(id);
        if (repo.findByName(request.name()).isPresent()) throw new CustomException("Product with this name already exist");
        Product entity = mapper.toEntity(request);
        entity.setId(existingProduct.getId());
        repo.save(entity);
    }
}

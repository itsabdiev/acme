package kg.aiu.acmecorp.mapper;

import kg.aiu.acmecorp.entity.Product;
import kg.aiu.acmecorp.model.request.ProductRequest;
import kg.aiu.acmecorp.model.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductResponse toModel(Product entity);
    Product toEntity(ProductRequest model);
}

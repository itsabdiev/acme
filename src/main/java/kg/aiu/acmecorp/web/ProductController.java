package kg.aiu.acmecorp.web;


import kg.aiu.acmecorp.endpoint.ProductEndpoint;
import kg.aiu.acmecorp.entity.Product;
import kg.aiu.acmecorp.enums.Category;
import kg.aiu.acmecorp.model.request.ProductRequest;
import kg.aiu.acmecorp.model.response.ProductResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class ProductController {

    ProductEndpoint endpoint;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false,name = "categories") Optional<List<Category>> categories
    ) {
        try {
            return ResponseEntity.ok(endpoint.getAll(categories));
        } catch (Exception e) {
            log.error("getAll() : ",e);
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> getProductByName(
            @PathVariable(name = "name") String name
    ) {
        try {
            return ResponseEntity.ok(endpoint.getByName(name));
        } catch (Exception e) {
            log.error("getProductByName() : ",e);
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable(name = "id") Long id
    ) {
        try {
            return ResponseEntity.ok(endpoint.getProductById(id));
        } catch (Exception e) {
            log.error("getProductById() : ",e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProductById(
            @PathVariable(name = "id") Long id ) {
        try {
            endpoint.removeProductById(id);
            return ResponseEntity.accepted().body("Product has been removed successfully");
        } catch (Exception e) {
            log.error("removeProductById() : ",e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestBody ProductRequest request
            ) {
        try {
            endpoint.createProduct(request);
            return ResponseEntity.accepted().body("Product has been saved successfully");
        } catch (Exception e) {
            log.error("createProduct() : ",e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyProduct(
            @RequestBody ProductRequest request,
            @PathVariable Long id
    ) {
        try {
            endpoint.modifyProduct(request,id);
            return ResponseEntity.accepted().body("Product has been modified successfully");
        } catch (Exception e) {
            log.error("modifyProduct() : ",e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}

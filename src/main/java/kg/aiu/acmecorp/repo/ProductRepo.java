package kg.aiu.acmecorp.repo;

import kg.aiu.acmecorp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM products WHERE deleted is null and category in (:categories)",nativeQuery = true)
    Optional<List<Product>> findAllByCategory(@Param(value = "categories") List<String> categories);

    @Query(value = "SELECT * FROM products WHERE deleted is null",nativeQuery = true)
    Optional<List<Product>> findAllWithoutAttributes();

    @Query(value = "SELECT * FROM products WHERE deleted IS NULL AND name = :name LIMIT 1",nativeQuery = true)
    Optional<Product> findByName(@Param(value = "name") String name);

    @Query(value = "SELECT * FROM products WHERE deleted IS NULL AND id = :id LIMIT 1",nativeQuery = true)
    Optional<Product> findById(@Param(value = "id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE products set deleted = NOW() where id = :id",nativeQuery = true)
    void removeById(@Param(value = "id") Long id);
}

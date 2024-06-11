package kg.aiu.acmecorp.model.request;

import kg.aiu.acmecorp.enums.Category;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        @NonNull String name,
        @NonNull BigDecimal price,
        @NonNull Category category,
        @NonNull String description
) {
}


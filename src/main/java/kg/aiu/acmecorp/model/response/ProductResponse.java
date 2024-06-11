package kg.aiu.acmecorp.model.response;

import kg.aiu.acmecorp.enums.Category;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        @NonNull Long id,
        @NonNull String name,
        @NonNull BigDecimal price,
        @NonNull Category category,
        @NonNull String description
) {
}

package dto;

import java.math.BigDecimal;

public record ProductIdDto(Long id,
                           String nombre,
                           Long stock,
                           BigDecimal precio) {
}

package domain;

import java.math.BigDecimal;

public record ProductoDto(String nombre,
                          Long stock,
                          BigDecimal precio) {
}

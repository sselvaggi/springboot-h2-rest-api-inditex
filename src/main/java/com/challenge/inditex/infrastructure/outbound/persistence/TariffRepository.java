package com.challenge.inditex.infrastructure.outbound.persistence;

import com.challenge.inditex.domain.Tariff;
import com.challenge.inditex.domain.TariffSearchDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TariffRepository {

    private final JdbcTemplate template;

    public TariffRepository(JdbcTemplate template) {
        this.template = template;
    }

    private static final RowMapper<Tariff> TARIFF_ROW_MAPPER = (rs, rowNum) -> new Tariff(
            rs.getLong("product_id"),
            rs.getLong("brand_id"),
            rs.getLong("price_list"),
            rs.getTimestamp("start_date").toLocalDateTime(),
            rs.getTimestamp("end_date").toLocalDateTime(),
            rs.getBigDecimal("price"),
            rs.getString("curr"),
            rs.getInt("priority")
    );

    public Optional<Tariff> search(TariffSearchDTO search) {
        String sql = """
                SELECT brand_id, start_date, end_date, price_list, product_id, priority, price, curr
                FROM prices
                WHERE product_id = ?
                  AND brand_id = ?
                  AND ? BETWEEN start_date AND end_date
                ORDER BY priority DESC
                LIMIT 1
                """;

        return template.query(
                sql,
                TARIFF_ROW_MAPPER,
                search.productId(),
                search.brandId(),
                search.datetime()
        ).stream().findFirst();
    }
}
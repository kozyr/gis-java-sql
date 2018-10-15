package com.kozyrenko.demo.gisjavasql.db;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.spatial.dialect.postgis.PostgisPG95Dialect;

import java.sql.Types;

public class RidePostgisDialect extends PostgisPG95Dialect {

    public RidePostgisDialect() {
        super();
        registerHibernateType(Types.OTHER, JsonNodeBinaryType.class.getName());
    }
}

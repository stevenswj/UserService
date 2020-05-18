package com.userservice.config.db.sqlite;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.springframework.stereotype.Component;

@Component
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false; // As specify in NHibernate dialect
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type)
            throws MappingException {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }
}
package com.moonsolid.sql;

public interface TransactionCallback {

  Object doInTransaction() throws Exception;

}

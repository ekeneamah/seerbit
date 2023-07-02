package com.seerbit.middleware.erp.middleware.model;

import java.util.List;

public interface POSTransactionRepository {
    POSTransaction save(POSTransaction user);
    POSTransaction findById(String id, String posid);
    List<POSTransaction> findAll();
    void delete(String id);
}


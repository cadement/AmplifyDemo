package com.sharecare.sample.model.impl;

import com.sharecare.sample.model.DBRecord;

public class DBRecordAggregate implements DBRecord {

    private final String id;

    public DBRecordAggregate(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}

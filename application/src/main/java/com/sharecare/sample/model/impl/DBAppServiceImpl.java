package com.sharecare.sample.model.impl;

import com.sharecare.sample.model.DBAppService;
import com.sharecare.sample.model.DBRecord;
import org.springframework.stereotype.Service;

@Service
public class DBAppServiceImpl implements DBAppService {

    @Override
    public DBRecord showRecord(String recordId) {
        return new DBRecordAggregate(recordId);
    }
}

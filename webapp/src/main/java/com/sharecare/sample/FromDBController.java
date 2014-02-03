package com.sharecare.sample;

import com.sharecare.sample.model.DBAppService;
import com.sharecare.sample.model.DBRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/from-db")
public class FromDBController {

    private final DBAppService dbAppService;

    @Autowired
    public FromDBController(DBAppService dbAppService) {
        this.dbAppService = dbAppService;
    }

    @RequestMapping(value = "/{record-id}")
    public ModelAndView showRecord(@PathVariable("record-id") String recordId) {
        DBRecord dbRecord = dbAppService.showRecord(recordId);

        ModelAndView recordPage = new ModelAndView("/recordPage.html");
        recordPage.addObject("dbRecord", dbRecord);

        return recordPage;
    }
}

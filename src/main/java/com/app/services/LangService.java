package com.app.services;

import com.app.dao.LangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LangService {
    @Autowired private LangDao langDao;

    public Map<String, String> getTranslations(long langId, String page) {
        return langDao.getTranslations(langId, page);
    }

}

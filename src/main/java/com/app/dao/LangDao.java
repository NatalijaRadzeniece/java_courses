package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LangDao {

    @Autowired private JdbcTemplate jdbcTemplate;

    public Map<String, String> getTranslations(long langId, String page) {
        return jdbcTemplate.query("SELECT key, text FROM public.translations WHERE lang_id = ? AND page = ? " +
                "OR lang_id = ? AND page = 'all'", (ResultSet rs) -> {
            Map<String, String> result = new HashMap<>();

            while (rs.next()) {
                result.put(rs.getString("key"), rs.getString("text"));
            }

            return result;
        }, langId, page, langId);
    }

}

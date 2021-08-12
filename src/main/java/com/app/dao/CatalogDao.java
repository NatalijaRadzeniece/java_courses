package com.app.dao;

import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.Subcategory;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {
    @Autowired private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategory(rs);
        return jdbcTemplate.query("SELECT * FROM public.categories", rowMapper);
    }

    private Category mapCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();

        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));

        return category;
    }

    public void storeCategory(Category category) {
        jdbcTemplate.update("INSERT INTO public.categories (name) VALUES (?)", category.getName());
    }

    public void storeSubcategory(Subcategory subcategory) {
        jdbcTemplate.update("INSERT INTO public.subcategories (name, category_id) VALUES (?, ?)",
                subcategory.getName(), subcategory.getCategory().getId());
    }

    private Subcategory mapSubcategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        Subcategory subcategory = new Subcategory();
        category.setId(resultSet.getLong("c_id"));
        category.setName(resultSet.getString("c_name"));

        subcategory.setId(resultSet.getLong("s_id"));
        subcategory.setCategory(category);
        subcategory.setName(resultSet.getString("s_name"));

        return subcategory;
    }

    public List<Subcategory> getSubcategories() {
        RowMapper<Subcategory> rowMapper = (rs, rowNumber) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id, s.name AS s_name, c.id AS c_id, c.name AS c_name " +
                "FROM public.subcategories s " +
                "INNER JOIN public.categories c ON c.id = s.category_id ", rowMapper);
    }

    public List<Subcategory> getSubcategories(long categoryId) {
        RowMapper<Subcategory> rowMapper = (rs, rowNumber) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id, s.name AS s_name, c.id AS c_id, c.name AS c_name " +
                "FROM public.subcategories s " +
                "INNER JOIN public.categories c ON c.id = s.category_id WHERE s.category_id = ?", rowMapper, categoryId);
    }

    public List<CatalogItem> getItems() {
        RowMapper<CatalogItem> rowMapper = (rs, rowNumber) -> mapItem(rs);
        return jdbcTemplate.query("SELECT it.id AS item_id, it.name AS item_name, it.description, it.price, " +
                "s.id AS sub_id, s.name AS sub_name, cat.id AS category_id, cat.name AS category_name " +
                "FROM public.catalog it " +
                "INNER JOIN public.subcategories s ON it.subcategory_id = s.id " +
                "INNER JOIN public.categories cat ON s.category_id = cat.id", rowMapper);
    }

    public List<CatalogItem> getItemsById(long id) {
        RowMapper<CatalogItem> rowMapper = (rs, rowNumber) -> mapItem(rs);
        return jdbcTemplate.query("SELECT it.id AS item_id, it.name AS item_name, it.description, it.price, " +
                "s.id AS sub_id, s.name AS sub_name, cat.id AS category_id, cat.name AS category_name " +
                "FROM public.catalog it " +
                "INNER JOIN public.subcategories s ON it.subcategory_id = s.id " +
                "INNER JOIN public.categories cat ON s.category_id = cat.id " +
                "WHERE it.subcategory_id = ?", rowMapper, id);
    }

    private CatalogItem mapItem(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("category_id"));
        category.setName(rs.getString("category_name"));

        Subcategory subcategory = new Subcategory();
        subcategory.setCategory(category);
        subcategory.setId(rs.getLong("sub_id"));
        subcategory.setName(rs.getString("sub_name"));

        CatalogItem item = new CatalogItem();
        item.setId(rs.getLong("item_id"));
        item.setSubcategory(subcategory);
        item.setName(rs.getString("item_name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getBigDecimal("price"));
//        item.setBrandId(rs.getLong("brand_id"));
//        item.setImage(rs.getString("image"));

        return item;
    }

    public void storeCatalogItem(CatalogItem catalogItem) {
        jdbcTemplate.update("INSERT INTO public.catalog (subcategory_id, name, description, price) VALUES (?, ?, ?, ?)",
               catalogItem.getSubcategory().getId(), catalogItem.getName(), catalogItem.getDescription(), catalogItem.getPrice());
    }
}

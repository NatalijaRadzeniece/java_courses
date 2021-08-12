package com.app.dao;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    @Autowired private JdbcTemplate jdbcTemplate;

    // 1st method: request data from DB
    public List<User> getUsers() {
        RowMapper<User>  rowMapper = (rs, rowNumber) -> mapUser(rs);
       return jdbcTemplate.query("SELECT * FROM public.user", rowMapper);
    }
    // 2nd: map response from DB
    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("user_email"));
        user.setPhoneNumber(resultSet.getString("phone_number"));

        return user;
    }

    public void storeUser(User user) {
        jdbcTemplate.update("INSERT INTO public.user (first_name, last_name, user_email, phone_number, password) VALUES (?, ?, ?, ?, '12345')",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}

package com.ait.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate1;

    @Autowired
    private JdbcTemplate jdbcTemplate2;

    public boolean signUp(Map<String, Object> user) {

        String sql = """
                select count(*) from users where email=:email
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.get("email"));
        Integer count = jdbcTemplate1.queryForObject(sql, params, Integer.class);

        if (count != null && count > 0) {
            return false;
        } else {
            String sql2 = """
                    INSERT INTO users(name,email,password,is_active,role) VALUES(:name,:email,:password,1,'USER')
                    """;
            MapSqlParameterSource params2 = new MapSqlParameterSource();
            params2.addValue("name", user.get("name"));
            params2.addValue("email", user.get("email"));
            params2.addValue("password", user.get("password"));
            jdbcTemplate1.update(sql2, params2);
            return true;
        }
    }

    public boolean login(Map<String, Object> user) {
        String sql = """
                select count(*) from users
                where email=:email and password=:password and is_active=1
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.get("email"));
        params.addValue("password", user.get("password"));
        Integer count = jdbcTemplate1.queryForObject(sql, params, Integer.class);

        return count != null && count > 0;
    }

    public List<Map<String, Object>> getAllUsers() {
        String sql = """
                SELECT user_id, name, email, is_active, created_date
                FROM users
                WHERE UPPER(role) = 'USER'
                ORDER BY created_date DESC, user_id DESC
                """;
        return jdbcTemplate2.queryForList(sql);
    }

}

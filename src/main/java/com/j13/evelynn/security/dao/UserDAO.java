package com.j13.evelynn.security.dao;

import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-9
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDAO")
public class UserDAO {

    @Autowired
    JdbcTemplate j;


    public boolean hasName(String name) {
        String sql = "select count(1) from user where name = ?";
        return j.queryForObject(sql, new Object[]{name}, Integer.class) == 0 ? false : true;
    }

    public List<Authority> getAuthorityByName(String name) {
        String sql = "select a.id,a.name from user u , authority a, user_authority ua " +
                " where u.name=? and u.id=ua.user_id and ua.authority_id = a.id";
        return j.query(sql, new Object[]{name}, new BeanPropertyRowMapper<Authority>(Authority.class));
    }

    public List<User> list() {
        String sql = "select u.id as id,u.name as name,a.id as authorityId,a.name as authorityName " +
                " from user u , user_authority ua ,authority a where " +
                " u.id = ua.user_id and ua.authority_id = a.id";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<User>(User.class));
    }


    public void update(User user) {
        String sql = "update user set name=? where id=?";
        j.update(sql, new Object[]{user.getName(), user.getId()});


        sql = "update user_authority set authority_id=? where user_id=?";
        j.update(sql, new Object[]{user.getAuthorityId(), user.getId()});
    }

    public User getUser(int id) {
        String sql = "select u.id as id,u.name as name,a.id as authorityId,a.name as authorityName " +
                " from user u , user_authority ua ,authority a where u.id=? and " +
                " u.id = ua.user_id and ua.authority_id = a.id";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
    }

    public void delete(int id) {
        String sql = "delete from user where id=?";
        j.update(sql, new Object[]{id});
    }

    public void create(final String name, final int authorityId) {
        final String sql = "insert into user (name) values (?)";
        KeyHolder holder = new GeneratedKeyHolder();
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, name);
                return pstmt;
            }
        }, holder);
        final int id = holder.getKey().intValue();

        final String sql2 = "insert into user_authority (user_id,authority_id) values (?,?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql2);
                pstmt.setInt(1, id);
                pstmt.setInt(2, authorityId);
                return pstmt;
            }
        });
    }

    public int getUserByName(String username) {
        String sql = "select id from user where name=?";
        return j.queryForObject(sql, new Object[]{username}, Integer.class);
    }


    public User login(String username, String password) {
        try {
            String sql = "select name,password from user where name=? and password=?";
            return j.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper<User>(User.class));
        } catch (Exception e) {
            return null;
        }
    }


}

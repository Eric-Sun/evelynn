package com.j13.evelynn.security.dao;

import com.j13.evelynn.security.model.Authority;
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
 * Created by sunbo on 14-11-16.
 */
@Repository("authorityDAO")
public class AuthorityDAO {


    @Autowired
    JdbcTemplate j;


    public List<Authority> list() {
        String sql = "SELECT * FROM authority ";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<Authority>(Authority.class));
    }


    public void insert(final String authorityName, String[] resourceIdList) {
        final String sql = "insert into authority (name) values (?)";
        KeyHolder holder = new GeneratedKeyHolder();
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, authorityName);
                return pstmt;
            }
        }, holder);
        final int id = holder.getKey().intValue();

        for (final String resourceId : resourceIdList) {
            final String sql2 = "insert into authority_resource (authority_id,resource_id) values (?,?)";
            j.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement pstmt = con.prepareStatement(sql2);
                    pstmt.setInt(1, id);
                    pstmt.setInt(2, new Integer(resourceId + ""));
                    return pstmt;
                }
            });
        }


    }

    public void update(final Authority authority, String[] resourceIdList) {
        String sql = "update authority set name=? where id=?";
        j.update(sql, new Object[]{authority.getName(), authority.getId()});


        sql = "delete from authority_resource where authority_id=?";
        j.update(sql, new Object[]{authority.getId()});


        for (final String resourceId : resourceIdList) {
            final String sql2 = "insert into authority_resource (authority_id,resource_id) values (?,?)";
            j.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement pstmt = con.prepareStatement(sql2);
                    pstmt.setInt(1, authority.getId());
                    pstmt.setInt(2, new Integer(resourceId + ""));
                    return pstmt;
                }
            });
        }
    }


    public void delete(int id) {
        String sql = "delete from authority where id=?";
        j.update(sql, new Object[]{id});
    }

    public Authority get(int id) {
        String sql = "SELECT * FROM authority where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Authority>(Authority.class));
    }

}

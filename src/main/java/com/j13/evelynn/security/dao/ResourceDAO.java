package com.j13.evelynn.security.dao;

import com.j13.evelynn.security.model.Authority;
import com.j13.evelynn.security.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-9
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
@Repository("resourceDAO")
public class ResourceDAO {

    @Autowired
    JdbcTemplate j;

    public List<Authority> getAuthorityListByResource(String name) {
        String sql = "select a.id,a.name from authority a, authority_resource ar, resource r " +
                "where a.id=ar.authority_id and r.id = ar.resource_id and r.name=?";
        return j.query(sql, new Object[]{name}, new BeanPropertyRowMapper<Authority>(Authority.class));
    }

    public List<Resource> getResourceList() {
        String sql = "select * from resource ";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<Resource>(Resource.class));
    }


    public void insert(final String name) {
        final String sql = "insert into resource (name) values (?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, name);
                return pstmt;
            }
        });
    }


    public Resource get(int id) {
        String sql = "select * from resource where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Resource>(Resource.class));
    }

    public void update(int id, String name) {
        String sql = "update resource set name=? where id=?";
        j.update(sql, new Object[]{name, id});
    }

    public void delete(int id) {
        String sql = "delete from resource where id=?";
        j.update(sql, new Object[]{id});
    }

    public List<Integer> getResourceListByAuthorityId(int id) {
        String sql = "select resource_id from authority_resource where authority_id=?";
        return j.queryForList(sql, new Object[]{id}, Integer.class);
    }
}

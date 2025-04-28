package com.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class MD5Dao {
 
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void queryMD5(String md5) {
        String sql = "SELECT id FROM md5_hash_5 WHERE md5=? union all SELECT id FROM md5_hash_4 WHERE md5=? union all SELECT id FROM md5_hash_3 WHERE md5=? union all SELECT id FROM md5_hash_2 WHERE md5=? union all SELECT id FROM md5_hash_1 WHERE md5=?";
        List<MyObject> data = jdbcTemplate.query(sql, new Object[] {md5,md5,md5,md5,md5  }, new RowMapper<MyObject>() {
            @Override
            public MyObject mapRow(ResultSet rs, int rowNum) throws SQLException {
                MyObject obj = new MyObject();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setValue(rs.getDouble("value"));
                return obj;
            }
        });
        return data;
    }
    
}

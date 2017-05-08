package com.phantom.module.email.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/25.
 */
@Repository
public class EmailDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有未发送的邮件
     * @return
     */
    public List<Map<String,Object>> queryEmails()throws Exception{
        String sql = "select * from t_email where status=0";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 修改邮件状态
     * @param id
     * @return
     */
    public int updateEmailStatus(String id,String comment,EmailEnum emailEnum){
        String sql = "update t_email set status = ?,comment=? where id =?";
        //lambda expression
        int result = jdbcTemplate.update(sql, (PreparedStatement preparedStatement) -> {
            preparedStatement.setObject(1,emailEnum.value());
            preparedStatement.setObject(2,comment);
            preparedStatement.setObject(3,id);
        });

        //not lambda expresssion
        /*int result = jdbcTemplate.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1,emailEnum.value());
                preparedStatement.setObject(2,comment);
                preparedStatement.setObject(3,id);
            }
        });*/
        return result;
    }
}



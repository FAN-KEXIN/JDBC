package cn.fkx.dao.user;

import cn.fkx.bean.Users;
import cn.fkx.uilt.BaseDao;
import cn.fkx.uilt.PageUtil;
import cn.fkx.uilt.ResultSetUtil;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public int add(Users users) {
        String sql="INSERT INTO news_users(uname,upwd,utype,email) VALUES(?,?,?,?)";
        Object[] params={users.getUserName(),users.getPassword(),users.getUserType(),users.getEmail()};
        return executeUpdate(sql,params);
    }

    @Override
    public int deleteByCondition(Serializable id) {
        return 0;
    }

    @Override
    public int update(Users users) {
        return 0;
    }

    @Override
    public Users findByCondition(Serializable id) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public int findRownum() {
        return 0;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        return null;
    }

    /**
     * 验证用户名是否存在
     */
    @Override
    public String validateName(String userName) {
        String sql="SELECT upwd FROM news_users WHERE uname= ?";
        rs=executeQuery(sql,userName);
        //获取密码
        String password=null;
        try {
            if (rs.next()){
                try {
                    password=rs.getString("upwd");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 登录
     */
    @Override
    public Users login(String userName, String passwordInDB) {
        String sql="SELECT uid AS user_id,uname AS userName,upwd AS password,utype AS userType,email FROM news_users WHERE uname=? AND upwd=?";
        Object [] params={userName,passwordInDB};
        rs=executeQuery(sql,params);
        Users users= ResultSetUtil.eachOne(rs,Users.class);
        return users;
    }
}

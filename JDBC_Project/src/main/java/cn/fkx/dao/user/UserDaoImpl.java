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
        String sql="DELETE FROM news_users WHERE uid= ?";
        int num= executeUpdate(sql,id);
        return num;
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

    //查询总记录数
    @Override
    public int findRownum() {
        String sql="SELECT COUNT(uid) AS COUNT FROM news_users";
        rs=executeQuery(sql);
        int count=0;
        try {
            if (rs.next()){
                count=rs.getInt("COUNT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        String sql="SELECT uid as user_id,uname as userName,utype as userType ,email as email ,upwd as password  FROM  news_users LIMIT ?,?";
        Object [] parms={(util.getPageIndex()-1)*util.getPageSize(),util.getPageSize()};
        rs=executeQuery(sql,parms);
        List<Users> list = ResultSetUtil.eachList(rs, Users.class);
        System.out.println(list.size()+"-=-");
        return list;
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

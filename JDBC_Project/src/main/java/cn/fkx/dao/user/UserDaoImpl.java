package cn.fkx.dao.user;

import cn.fkx.bean.Users;
import cn.fkx.uilt.BaseDao;
import cn.fkx.uilt.PageUtil;

import java.io.Serializable;
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
}

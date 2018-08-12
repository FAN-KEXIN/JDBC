package cn.fkx.servit.user;

import cn.fkx.bean.Users;
import cn.fkx.dao.user.UserDao;
import cn.fkx.dao.user.UserDaoImpl;
import cn.fkx.uilt.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public int add(Users users) {
        return userDao.add(users);
    }

    @Override
    public int deleteByCondition(Serializable id) {
        return userDao.deleteByCondition(id);
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
        return userDao.findRownum();
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        return userDao.findAllByPage(util);
    }

    @Override
    public String validateName(String userName) {
        return userDao.validateName(userName);
    }

    @Override
    public Users login(String userName, String passwordInDB) {
        return userDao.login(userName,passwordInDB);
    }
}

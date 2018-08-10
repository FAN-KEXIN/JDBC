package cn.fkx.dao.user;

import cn.fkx.bean.Users;
import cn.fkx.dao.IBaseDao;

public interface UserDao extends IBaseDao<Users>{
    String validateName(String userName);

    Users login(String userName, String passwordInDB);
}

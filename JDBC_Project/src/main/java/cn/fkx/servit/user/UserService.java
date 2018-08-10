package cn.fkx.servit.user;

import cn.fkx.bean.Users;
import cn.fkx.servit.IBaseService;

public interface UserService extends IBaseService<Users>{

    String validateName(String userName);

    Users login(String userName, String passwordInDB);
}

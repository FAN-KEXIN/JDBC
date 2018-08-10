package cn.fkx.controller;

import cn.fkx.bean.Users;
import cn.fkx.servit.ServiceFactory;
import cn.fkx.servit.user.UserService;
import cn.fkx.uilt.Md5Encrypt;
import cn.fkx.uilt.ResultUtil;
import org.springframework.validation.annotation.Validated;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/Loginfo")
public class UserServlet extends BaseServlet {
    //不实例化service层对象 工厂实例化
    private UserService userService;
    private ResultUtil util=new ResultUtil();
    //当用户访问时先执行init
    @Override
    public void init() throws ServletException{
        userService=(UserService) ServiceFactory.getServiceImpl("userService");
    }
    @Override
    public Class getServletClass() {
        System.out.println("=====02:UserServlet===》getServletClass");
        return UserServlet.class;
    }
    //注册
    public String register(HttpServletRequest req, HttpServletResponse resp){
        //获取用户参数
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        Users users=new Users();
        users.setUserName(userName);
        try {
            users.setPassword(Md5Encrypt.getEncryptedPwd(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        users.setUserType(0);//设置用户类型
        int num=userService.add(users);
        if (num>0){
            return "Main";
        }else {
            return "register";
        }
    }
    //登录
    public String login(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("====>UserServlet===>login");
        //获取用户登录的用户名和密码
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        //得从数据库中获取一个用户名  如果没有用户名不需要再执行后续代码
       String passwordInDB=userService.validateName(userName);//验证用户名是否存在
        if (passwordInDB!=null){//说明用户存在 并且找到密码
            try {
                if (Md5Encrypt.validPassword(password,passwordInDB)){
                    Users users=  userService.login(userName,passwordInDB);
                    req.getSession().setAttribute(userName,passwordInDB);
                    return "Main";
                }else {
                    System.out.println("密码错误！");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("用户名不存在！");
        }
        return "login";
    }

    /**
     * ajax验证用户名是否重复
     */
    public ResultUtil validateName(HttpServletRequest req, HttpServletResponse resp){
        //获取用户名
        String userName=req.getParameter("username");

        //调用service层的代码
        String passwordInDB=userService.validateName(userName);

        if (passwordInDB==null){
            util.resultSuccess();
        }else {
            util.resultFail("改昵称已被占用！");
        }
        return util;
    }
}
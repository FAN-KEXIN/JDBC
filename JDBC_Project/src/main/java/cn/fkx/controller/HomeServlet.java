package cn.fkx.controller;

import cn.fkx.bean.Users;
import cn.fkx.servit.ServiceFactory;
import cn.fkx.servit.user.UserService;
import cn.fkx.uilt.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    private UserService userService;
    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }
    @Override
    public void init()throws ServletException{
        userService=(UserService) ServiceFactory.getServiceImpl("userService");
    }
    /**
     * 分页的方法
     */
    public PageUtil findAllByPage(HttpServletRequest req, HttpServletResponse resp){
        //获取当前页面 pageIndex
        int pageIndex=Integer.parseInt(req.getParameter("pageIndex"));
        if (pageIndex==0){
            pageIndex=1;
        }
        //创建PageUtil对象
        PageUtil pageUtil=new PageUtil();
        //把获取的对象封装到PageUtil
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotalCount(userService.findRownum());
        //调用service代码 获取数据
        List<Users> list=userService.findAllByPage(pageUtil);
        pageUtil.setList(list);
        return pageUtil;
    }
    public String deleteUser(HttpServletRequest req, HttpServletResponse resp){
        String id=req.getParameter("id");
        int num=userService.deleteByCondition(id);
        if (num>0){
            return "Main";
        }
        return "login";
    }
}

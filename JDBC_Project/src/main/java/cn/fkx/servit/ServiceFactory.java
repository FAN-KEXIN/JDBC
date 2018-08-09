package cn.fkx.servit;

import cn.fkx.servit.user.UserServiceImpl;

public class ServiceFactory {
    //创建本类的静态对象
    private static ServiceFactory factory;
    //私有化构造
    private ServiceFactory(){}
    static {
        if (factory==null){
            synchronized (ServiceFactory.class){
                if (factory==null){
                    factory=new ServiceFactory();
                }
            }
        }
    }
    public static IBaseService getServiceImpl(String serviceName){
        IBaseService service=null;
        switch (serviceName){
            case "userService":
                service=new UserServiceImpl();
                default:
                    break;
        }
        return service;
    }
}

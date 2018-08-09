package cn.fkx.uilt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    //创建需要单例类的静态变量
    private static ConfigManager configManager;
    //创建Properties，解析Properties文件
    private static Properties properties;
    //静态代码块 不会实例化对象 就能使用
    static {
        String path = "jdbc.properties";
        properties = new Properties();//实例化对象
        InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(path);
        //加载自己的properties文件
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //创建对位访问的接口
    public static synchronized ConfigManager getInstance () {
        return configManager;
    }

    /**
     * properties对象已经有值了！properties已经进入了内存！
     * 我们就可以通过key获取value
     */
    public static String getValue(String key){
        return properties.getProperty(key);
    }

}

package com.lhb.base.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 小旺配置对象
 * <p/>
 * <p/>
 * 配置对象的所有配置项包含在两个文件中：
 * <ul>
 * <li>缺省配置：config.default.properties</li>
 * <li>用户配置: config.user.properties</li>
 * </ui>
 * <p/>
 * <p/>
 * 注意：如果同一个项目在用户配置和缺省配置中都包含，则<strong>用户配置项会会覆盖缺省配置项</strong>。
 * <p/>
 * <p/>
 * 本对象还提供了一些方便方法，用于从配置项的字符串中转换成 boolean,int, long, string
 *
 * @author $id$
 */
public class Configuration {
    private static final String DEFAULT_CONFIG_PROPERTIES = "default.config.properties";
    private static final String USER_CONFIG_PROPERTIES = "user.config.properties";

    private final Properties userProperties = new Properties();
    private final Properties defaultProperties = new Properties();

    private Configuration() {
        Logger logger = LoggerFactory.getLogger(getClass());
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // 装载全局配置
        try (InputStream gis = classloader.getResourceAsStream(DEFAULT_CONFIG_PROPERTIES)) {
            if (gis != null) {
                defaultProperties.load(gis);
            }
        } catch (IOException e) {
            logger.error("文件读写错误.", e);
        }

        // 装载本地配置
        String userConfigPath = null;
        Context envCtx;

        try {
            Context ctx = new InitialContext();
            envCtx = (Context) ctx.lookup("java:comp/env");

            try {
                userConfigPath = (String) envCtx.lookup("configurationPath");
            } catch (NamingException e) {
                logger.error("没有对应的JNDI属性配置.");
            }
        } catch (NamingException e) {
            logger.info("没有启动网络容器.");
        }

        if (userConfigPath == null) {
            try (InputStream lis = new FileInputStream(USER_CONFIG_PROPERTIES)) {
                userProperties.load(lis);
            } catch (IOException e) {
                logger.error("载入本地配置失败(应当位于当前工作目录{})", System.getProperty("user.dir"));
            }
        } else {
            try (InputStream lis = new FileInputStream(userConfigPath)) {
                userProperties.load(lis);
            } catch (IOException e) {
                logger.error("载入JNDI配置失败.", e);
            }
        }
    }

    /**
     * 单例
     *
     * @return 单例
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    /**
     * 获取属性值
     * <p/>
     * 优先获取 user 配置，如果没有 user 配置则获取 default 配置
     *
     * @param key 键
     * @return 属性值或者null
     */
    public String getProperty(String key) {
        String result = userProperties.getProperty(key);
        if (result == null) {
            result = defaultProperties.getProperty(key);
        }
        return result;
    }

    /**
     * 获取整数配置，没有则返回0
     *
     * @param key 键
     * @return 属性值或者0
     */
    public int getIntValue(String key) {
        return this.getIntValue(key, 0);
    }

    /**
     * 获取整数配置，没有则返回缺省值
     *
     * @param key      键
     * @param defvalue 缺省值
     * @return 属性值或者缺省值
     */
    public int getIntValue(String key, int defvalue) {
        String value = getProperty(key);
        return value == null ? defvalue : Integer.parseInt(value);
    }

    /**
     * 获取字符串配置，没有则返回""
     *
     * @param key 键
     * @return 属性值或者""
     */
    public String getStringValue(String key) {
        return this.getStringValue(key, "");
    }

    /**
     * 获取字符串配置，没有则返回缺省值
     *
     * @param key      键
     * @param defValue 缺省值
     * @return 属性值或者缺省值
     */
    public String getStringValue(String key, String defValue) {
        String value = getProperty(key);
        return value == null ? defValue : value;
    }


    /**
     * 获取boolean值，没有则返回缺省值
     *
     * @param key      键
     * @param defValue 缺省值
     * @return 属性值或者缺省值
     */
    public boolean getBooleanValue(String key, boolean defValue) {
        String value = getProperty(key);
        return value == null ? defValue : Boolean.valueOf(value);
    }

    private static class ConfigurationHolder {

        private static final Configuration INSTANCE = new Configuration();
    }
}

package com.hci.business.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * 工具类 - 缓存管理. <br>
 * 处理缓存的添加、更新级删除.
 * @author Holen
 * @version 1.0.0 2013-1-5 新建
 */
public class CacheManager {


    /** LOG. */
    private final static Log log = LogFactory.getLog(CacheManager.class);

    private final static String DEFAULT_CACHE = "defaultcache";

    /** 缓存Manager实例. */
    private static net.sf.ehcache.CacheManager manager;
    static {
        try {
            manager = net.sf.ehcache.CacheManager.getInstance();
            if (null == manager) {
                manager = net.sf.ehcache.CacheManager.create();
            }
        } catch (Exception e) {
            log.error("Initialize cache manager faild!", e);
        }
    }

    /**
     * 从缓存中获取对象.
     *
     * @param cache_name
     *            缓存名称，如ehcache.xml中 <br>
     *            产品实体配置的name：com.handou.ehcache.Product
     * @param key
     *            一般采用缓存对象的主键（具有唯一性的字符串）
     * @return 缓存对象
     */
    public static Serializable getObjectCache(String cache_name,
                                              Serializable key) {
        Cache cache = getCache(cache_name);
        if (cache != null) {
            try {
                Element elem = cache.get(key);
                if (elem != null && !cache.isExpired(elem))
                    return elem.getValue();
            } catch (Exception e) {

                log.error("Getcache(" + cache_name + ") of " + key  + "failed.", e);
            }
        }

        return null;
    }

    /**
     * 从缓存中获取对象.
     *
     *            缓存名称，如ehcache.xml中 <br>
     *            产品实体配置的name：com.hydom.ehcache.Product
     * @param key
     *            一般采用缓存对象的主键（具有唯一性的字符串）
     * @return 缓存对象
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T getObjectCache(Class<T> clazz, Serializable key) {
        return (T)getObjectCache(clazz.getName(), key);
    }

    /**
     * 把对象放入缓存中
     *
     * @param cache_name
     *            缓存名称，如ehcache.xml中 <br>
     *            产品实体配置的name：com.hydom.ehcache.Product
     * @param key
     *            一般采用缓存对象的主键（具有唯一性的字符串）
     * @param value
     *            需要加入缓存的实体对象，如new <br>
     *            一个的com.hydom.ehcache.Product对象
     */
    public synchronized static void putObjectCache(String cache_name,
                                                   Serializable key, Serializable value) {
        Cache cache = getCache(cache_name);
        if (cache != null) {
            try {
                cache.remove(key);
                Element elem = new Element(key, value);
                cache.put(elem);
            } catch (Exception e) {
                log.error("putcache(" + cache_name + ") of " + key + "failed.", e);
            }
        }
    }

    /**
     * 通过缓存名称获取缓存.
     * @param cacheName - 缓存名称
     * @return cache - 缓存
     */
    public final static Cache getCache(Object cacheName) {
        return manager.getCache(cacheName.toString());
    }

    public static Object get(String cacheName, Object key) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return element.getObjectValue();
            }
        }
        return null;
    }

    public static <T> T get(String cacheName, Object key, Class<T> clazz) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return (T) element.getObjectValue();
            }
        }
        return null;
    }

    public static void put(String cacheName, Object key, Object value) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            cache.put(new Element(key, value));
        }
    }

    /**
     * 从缓存中移出
     */
    public static void remove(String cacheName, Serializable key) {
        Ehcache cache = getCache(cacheName);
        cache.remove(key);
    }

    public static void put(String key, Object value) {
        Ehcache ehcache = manager.getEhcache(DEFAULT_CACHE);
        Element element = new Element(key, value);

        ehcache.put(element);
    }

    public static <T> T get(String key, Class<T> clazz) {
        Ehcache ehcache = manager.getEhcache(DEFAULT_CACHE);
        Element element = ehcache.get(key);
        if (element != null) {
            return (T) element.getObjectValue();
        }

        return null;
    }

    public static void remove(Object key) {
        Ehcache ehcache = manager.getEhcache(DEFAULT_CACHE);
        ehcache.remove(key);
    }

}

package com.hci.api.util;


import com.hci.api.bean.Cache;
import com.hci.business.util.Timestamp;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证码放入缓存
 * @author lzh
 * @create 2017/1/10 13:04
 */
public class CodeCache {

    private static Map<String,Object> cacheMap;
    private CodeCache() {}

    static {
        cacheMap = new HashMap<String, Object>();
    }


    //用于保存缓存
    public static void addCache(String key, Object value) {
        synchronized (cacheMap){
            Cache cache = new Cache();
            cache.setKey(key);
            cache.setValue(value);
            Long s = System.currentTimeMillis()/1000;
            cache.setTimeOut(s.intValue());
            cacheMap.put(key, cache);
        }
    }

    //用于得到缓存
    public static Object getCache(String key) {
        synchronized (cacheMap){
            Long outTime = System.currentTimeMillis()/1000;
            Cache cache = (Cache)cacheMap.get(key);
            Object value = null;
            if (null != cache) {
                Integer outTimes = outTime.intValue();
                Integer time = cache.getTimeOut();
                //超过10分钟 清除缓存
                if (outTimes - time > 3600*10) {
                    removeCache(key);
                    return value;
                }
                return cache.getValue();
            }
            return value;
        }
    }


    //用于清除缓存信息
    public static void clearCache() {
        synchronized (cacheMap){
            cacheMap.clear();
        }
    }

    //用于清除指定的缓存信息
    public static void removeCache(String key) {
        synchronized (cacheMap){
            cacheMap.remove(key);
        }
    }
}

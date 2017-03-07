package com.hci.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016-12-20.
 */
public class VerificationCodeUtil {
    /**
     * 获取验证码
     */
    public static String code(String  username)  {
        try{
            char[] code = new char[] {'0', '1', '2', '3', '4', '5', '6','7', '8', '9'};
            Random random = new Random();
            String sRand = "";
            for (int i = 0; i < 6; i++) {
                String rand = String.valueOf(code[random.nextInt(code.length)]);
                sRand += rand;
            }
            return sRand;
        }catch (Exception e){
            return "";
        }

    }


}

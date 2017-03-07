package com.hci.api.util;

import java.math.BigDecimal;

/**
 *  计算工具类
 * Created by Administrator on 2016/9/26 0026.
 */
public class ArithTools {

    private static int DEAFAULT_SCALE = 2;

    /**
     * 精确计算：加
     * @param first
     * @param second
     * @return
     */
    public static double add(double first, double second){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));
        return
                bigDecimal
                    .add(bigDecimal1)
                    .setScale(DEAFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
    }

    /**
     * 精确计算：加
     * @param first
     * @param second
     * @param scale 保留小数位数
     * @return
     */
    public static double addSpeScale(double first, double second, Integer scale){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));
        return
                bigDecimal
                        .add(bigDecimal1)
                        .setScale(scale, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 精确计算：减
     * @param first
     * @param second
     * @return
     */
    public static double substract(double first, double second){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));
        return
                bigDecimal
                        .subtract(bigDecimal1)
                        .setScale(DEAFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 精确计算：减
     * @param first
     * @param second
     * @param scale 保留小数位数
     * @return
     */
    public static double substractSpeScale(double first, double second, Integer scale){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));
        return
                bigDecimal
                        .subtract(bigDecimal1)
                        .setScale(scale, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 精确计算：乘
     * @param first
     * @param second
     * @return
     */
    public static double muliply(double first, double second){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));

        return
                bigDecimal
                        .multiply(bigDecimal1)
                        .setScale(DEAFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 精确计算：乘
     * @param first
     * @param second
     * @param scale 保留小数位数
     * @return
     */
    public static double muliplySpeScale(double first, double second, Integer scale){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));

        return
                bigDecimal
                        .multiply(bigDecimal1)
                        .setScale(scale, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 精确计算：乘
     * @param first
     * @param second
     * @return
     */
    public static double muliply(double first, double second, Boolean floor){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(first));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(second));
        int precision = floor? BigDecimal.ROUND_FLOOR: BigDecimal.ROUND_HALF_UP;
        return
                bigDecimal
                        .multiply(bigDecimal1)
                        .setScale(DEAFAULT_SCALE, precision)
                        .doubleValue();
    }

    /**
     * 精确计算：除
     * @param dividend 被除数
     * @param Divisor 除数
     * @return
     */
    public static double divide(double dividend, double Divisor){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(dividend));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(Divisor));
        return
                bigDecimal
                        .divide(bigDecimal1, DEAFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 精确计算：除
     * @param dividend
     * @param Divisor
     * @param scale 保留小数位数
     * @return
     */
    public static double divideSpeScale(double dividend, double Divisor, Integer scale){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(dividend));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(Divisor));
        return
                bigDecimal
                        .divide(bigDecimal1, scale, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
    }

    /**
     * 格式化数字
     * @param value
     * @param precision
     * @return
     */
    public static String precision(double value, int precision){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
        return
                bigDecimal
                    .setScale(precision, BigDecimal.ROUND_HALF_UP)
                        .toString();
    }
    public  static Double intiPerMoney(Double money){
        BigDecimal bigDecimal=new BigDecimal(String.valueOf(money));
        BigDecimal bigDecimal1=new BigDecimal(12);
        return bigDecimal.divide(bigDecimal1,DEAFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();


    }

    public static void main(String args[]){
      //  System.out.print(ArithTools.divide(ArithTools.muliplySpeScale(100,ArithTools.divideSpeScale(10, 100, 4), 4), 30));
    System.out.print(muliply(1.0109, 10, true));
    }

}

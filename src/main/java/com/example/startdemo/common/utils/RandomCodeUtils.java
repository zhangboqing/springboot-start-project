package com.example.startdemo.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 随机数工具类(随机生成数字、字母、数字字母组合)
 */
public class RandomCodeUtils {

    /**
     * 生成指定长度的数字随机数
     *
     * @param length 长度
     * @return String
     */
    public static String getRandNumberCode(int length) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    /**
     * 生成指定长度的数字随机数,不能以0开头
     *
     * @param length 长度
     * @return String
     */
    public static String getRandNumber(int length) {
        //第一位随机数
        String temp = "123456789";
        int len = temp.length();
        int p;
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        p = r.nextInt(len);
        sb.append(temp.substring(p, p + 1));

        //除第一位以外其他随机数
        for (int i = 0; i < length - 1; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 生成相应长度的数字字母组合的随机数
     *
     * @param size 长度
     * @return String
     */
    public static String getRandStrCode(int size) {
        String temp = "ABCDEFGHJKLMNPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        int length = temp.length();
        int p;
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            p = r.nextInt(length);
            sb.append(temp.substring(p, p + 1));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的字母随机数
     *
     * @param size 长度
     * @return 字符串
     */
    public static String getRandEnglishCode(int size) {
        String temp = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int length = temp.length();
        int p;
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            p = r.nextInt(length);
            sb.append(temp.substring(p, p + 1));
        }
        return sb.toString();
    }


    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }


    public static void main(String[] args) {



    }

}

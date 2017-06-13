package com.rucdm.oneteacher.oneteacher.utils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/19.
 */
public class shabitianwenshumeimd5 {


    public static String getEncryptedPwd(String info) {
        String pwd = null;
        Random rand = new Random();
        byte[] salt = new byte[SALT_LENGTH.intValue()];
        rand.nextBytes(salt);

        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(salt);
            e.update(info.getBytes("UTF-8"));
            byte[] dig = e.digest();
            byte[] pwdArray = new byte[salt.length + dig.length];
            System.arraycopy(salt, 0, pwdArray, 0, salt.length);
            System.arraycopy(dig, 0, pwdArray, salt.length, dig.length);
            pwd = byteToHexString(pwdArray);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return pwd;
    }


    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < b.length; ++i) {
            String hex = Integer.toHexString(b[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            hexString.append(hex.toUpperCase());
        }

        return hexString.toString();
    }


    private static final Integer SALT_LENGTH = Integer.valueOf(12);
}
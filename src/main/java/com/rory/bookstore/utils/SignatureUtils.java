package com.rory.bookstore.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by RoryGao on 15/6/17.
 */
public class SignatureUtils {

    public static String generateSignature(String userName, String emailAddress) {
        long currentTime = System.currentTimeMillis();
        String originalStr = userName + "-" + emailAddress + "-" + StringUtils.getUUID();
        return currentTime + "&" + Base64.encodeBase64String(DigestUtils.md5(originalStr));
    }
}

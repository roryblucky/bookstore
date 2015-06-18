package com.rory.bookstore.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by RoryGao on 15/6/17.
 */
public class SignatureUtils {

    public static String generateSignature(String userName, String emailAddress) throws UnsupportedEncodingException {
        String key = userName + "&" + emailAddress + "&" + StringUtils.getUUID();
        return DigestUtils.md5Hex(key);
    }

}

package com.example.zqapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {
    /**
     * 生成签名
     * @param hashMap
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String testStr=body+"."+secretKey;
// 5393554e94bf0eb6436f240a4fd71282
        String digestHex = md5.digestHex(testStr);


// 5393554e94bf0eb6436f240a4fd71282
        String md5Hex1 = DigestUtil.md5Hex(testStr);

        return md5Hex1;
    }

}

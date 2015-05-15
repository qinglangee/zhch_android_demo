package com.zhch.andex.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

import com.google.common.base.Strings;

/**
 * 加密工具类
 * @author peter
 *
 */
public class CipherUtil {

	private final String DEFAULT_ENCODING = "UTF-8";

	private String privateKey = "@P13ncryptK3Y!+";
	
	private CipherUtil() {
	}
	
	public CipherUtil(String privateKey){
		this.privateKey = privateKey;
	}


    static class SingletonHolder {
        private static final CipherUtil INSTANCE = new CipherUtil();
    }

    public static CipherUtil getInstance() {
    	return SingletonHolder.INSTANCE;
    }
    

    /**
     * base64加密
     * @param cipherText
     * @return
     */
    public String base64Encode(String cipherText){
    	if(Strings.isNullOrEmpty(cipherText)) return cipherText;
		try {
			byte[] bytes = cipherText.getBytes(DEFAULT_ENCODING);
			return new String(Base64.encode(bytes), DEFAULT_ENCODING);
		} catch (Exception e) {
			return cipherText;
		}
    }

    /**
     * base64解密
     * @param cipherText
     * @return
     */
    public String base64Decode(String cipherText){
    	if(Strings.isNullOrEmpty(cipherText)) return cipherText;
		try {
			byte[] bytes = cipherText.getBytes(DEFAULT_ENCODING);
			return new String(Base64.decode(bytes), DEFAULT_ENCODING);
		} catch (Exception e) {
			return cipherText;
		}
    }

    
    /**
     * AES加密
     * @param cipherText
     * @return
     */
    public String aesEncode(String cipherText){
    	if(Strings.isNullOrEmpty(cipherText)) return cipherText;
		try {
			byte[] bytes = cipherText.getBytes(DEFAULT_ENCODING);
			byte[] enbytes = transform(true, bytes);
			byte[] base64Bytes = Base64.encode(enbytes);
			return new String(base64Bytes, DEFAULT_ENCODING);
		} catch (Exception e) {
			return cipherText;
		}
    }

    /**
     * AES解密
     * @param cipherText
     * @return
     */
    public String aesDecode(String cipherText){
    	if(Strings.isNullOrEmpty(cipherText)) return cipherText;
		try {
			byte[] bytes = cipherText.getBytes(DEFAULT_ENCODING);
			byte[] debytes = Base64.decode(bytes);
			byte[] plainBytes = transform(false, debytes);
			return new String(plainBytes, DEFAULT_ENCODING);
		} catch (Exception e) {
			return cipherText;
		}
    }

    /**
     * 加密与解密
     * @param encrypt
     * @param inputBytes
     * @return
     * @throws Exception
     */
    private byte[] transform(boolean encrypt, byte[] inputBytes) throws Exception {
    	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(privateKey.getBytes(DEFAULT_ENCODING));
        byte[] key = messageDigest.digest();

        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
        cipher.init(encrypt, new KeyParameter(key));

        ByteArrayInputStream input = new ByteArrayInputStream(inputBytes);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int inputLen;
        int outputLen;

        byte[] inputBuffer = new byte[1024];
        byte[] outputBuffer = new byte[cipher.getOutputSize(inputBuffer.length)];

        while ((inputLen = input.read(inputBuffer)) > -1) {
            outputLen = cipher.processBytes(inputBuffer, 0, inputLen, outputBuffer, 0);
            if (outputLen > 0) {
                output.write(outputBuffer, 0, outputLen);
            }
        }

        outputLen = cipher.doFinal(outputBuffer, 0);
        if (outputLen > 0) {
            output.write(outputBuffer, 0, outputLen);
        }

        return output.toByteArray();
    }
    
    public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
package cn.codesheep.sia.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES方式加解密
 * @author venus4A
 */
public class AES4AUtil {

	/**
	 * 由服务端提供给调用者的一个用于数据加密的共享密钥
	 */
	private static String _publicKey = "jKV4GEAwBtcyehND";
	/**
	 * 
	 * @param publicKey  AES密钥
	 */
//	public AES4AUtil() {
//		String publicKey = "jKV4GEAwBtcyehND";
//		_publicKey = publicKey;
//	}
	
	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/CTR/PKCS5Padding";
	public static final String ivParameter = "1234567890abcdef";
	
	/**
	 * 解密
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String decrypt(String data) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(_publicKey.getBytes("ASCII"), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());			
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//		byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
		byte[] encrypted1 = Base64.getDecoder().decode(data);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original, "utf-8");
		return originalString;
	}
	
	/**
	 * 加密
	 * @param data
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encrypt(String data) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
		SecretKeySpec sKeySpec = new SecretKeySpec(_publicKey.getBytes(), KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
		byte[] encrypted = cipher.doFinal(data.getBytes("utf-8"));
//		return new BASE64Encoder().encode(encrypted);
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	public static void main(String[] args) throws Exception {
		//举例
		String str = "111111";
		str = AES4AUtil.encrypt(str);
		System.out.println("加密="+str);
		str = AES4AUtil.decrypt(str);
		System.out.println("解密="+str);
	}
}

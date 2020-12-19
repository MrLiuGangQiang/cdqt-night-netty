package com.cdqt.netty.tool.rsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA工具类
 * 
 * @author LiuGangQiang Create in 2020/01/21
 */
public class RSAUtil {
	/**
	 * 秘钥算法 值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 */
	private static final String KEY_ALGORITHM = "RSA";
	/**
	 * 秘钥模值长度 值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 */
	private static final int KEY_INIT_DEFAULT_SIZE = 1024;
	/**
	 * 最大加密明文长度 值为{@value}（超出就采用分段加密）
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;
	/**
	 * 最大解密明文长度 值为{@value}（超出就采用分段解密）
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 */
	private RSAUtil() {
	}

	/**
	 * 内部实例化工具类
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 */
	private static class SingleRSAUtilHolder {
		/**
		 * {@link RSAUtil} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/01/21
		 */
		private static final RSAUtil INSTANCE = new RSAUtil();
	}

	/**
	 * 获取{@link SingleRSAUtilHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 * @return {@link SingleRSAUtilHolder#INSTANCE} 静态常量实例
	 */
	public static RSAUtil getInstance() {
		return SingleRSAUtilHolder.INSTANCE;
	}

	/**
	 * 转换公钥为字符串
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 * @param publicKey 公钥
	 * @return 字符串公钥
	 */
	public String publicKeyToString(RSAPublicKey publicKey) {
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	/**
	 * 转换私钥为字符串
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 * @param privateKey 私钥
	 * @return 字符串私钥
	 */
	public String privateKeyToString(RSAPrivateKey privateKey) {
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}

	/**
	 * 转换字符串为公钥
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 * @param publicKey 公钥
	 * @return 公钥
	 */
	public PublicKey getPublicKey(String publicKey) {
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
			KeyFactory factory;
			factory = KeyFactory.getInstance(KEY_ALGORITHM);
			return factory.generatePublic(x509EncodedKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换字符串为私钥
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 * @param privateKey 私钥
	 * @return 私钥
	 */
	public static PrivateKey getPrivateKey(String privateKey) {
		try {
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			return factory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化一组秘钥
	 *
	 * @author LiuGangQiang Create in 2020/01/21
	 * @return 秘钥对象
	 */
	public RSAKey init() {
		KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(KEY_INIT_DEFAULT_SIZE);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			return new RSAKey(publicKey, privateKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * RSA加密
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 * @param publicKey 公钥
	 * @param plainText 原文
	 * @return 密文
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public String RSAEncode(PublicKey publicKey, String plainText) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] b = plainText.getBytes("UTF-8");
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return Base64.getEncoder().encodeToString(decryptedData);
	}

	/**
	 * RSA加密
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 * @param publicKey 公钥
	 * @param plainText 原文
	 * @return 密文
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public String RSAEncode(String publicKey, String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] b = plainText.getBytes("UTF-8");
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return Base64.getEncoder().encodeToString(decryptedData);
	}

	/**
	 * RSA解密
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 * @param privateKey  私钥
	 * @param encodedText 密文
	 * @return 原文
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IOException
	 */
	public static String RSADecode(PrivateKey privateKey, String encodedText) throws IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		byte[] b = Base64.getDecoder().decode(encodedText);
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * RSA解密
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 * @param privateKey  私钥
	 * @param encodedText 密文
	 * @return 原文
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IOException
	 */
	public String RSADecode(String privateKey, String encodedText) throws IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		byte[] b = Base64.getDecoder().decode(encodedText);
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "UTF-8");
	}

	/*
	 * public String getRSAPrivateKeyAsNetFormat(String privateKey) { try {
	 * StringBuffer buff = new StringBuffer(1024); PKCS8EncodedKeySpec pvkKeySpec =
	 * new PKCS8EncodedKeySpec(getPrivateKey(privateKey).getEncoded()); KeyFactory
	 * keyFactory = KeyFactory.getInstance("RSA"); RSAPrivateCrtKey pvkKey =
	 * (RSAPrivateCrtKey) keyFactory.generatePrivate(pvkKeySpec);
	 * 
	 * buff.append("<RSAKeyValue>"); buff.append( "<Modulus>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getModulus().toByteArray())) +
	 * "</Modulus>"); buff.append("<Exponent>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getPublicExponent().toByteArray())) +
	 * "</Exponent>"); buff.append("<P>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getPrimeP().toByteArray())) + "</P>");
	 * buff.append("<Q>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getPrimeQ().toByteArray())) + "</Q>");
	 * buff.append("<DP>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getPrimeExponentP().toByteArray())) +
	 * "</DP>"); buff.append("<DQ>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getPrimeExponentQ().toByteArray())) +
	 * "</DQ>"); buff.append("<InverseQ>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getCrtCoefficient().toByteArray())) +
	 * "</InverseQ>"); buff.append("<D>" +
	 * Base64.encodeBytes(removeMSZero(pvkKey.getPrivateExponent().toByteArray())) +
	 * "</D>"); buff.append("</RSAKeyValue>"); return buff.toString(); } catch
	 * (Exception e) { System.err.println(e); return null; } }
	 * 
	 * public String getRSAPublicKeyAsNetFormat(String publicKey) { try {
	 * StringBuffer buff = new StringBuffer(1024); KeyFactory keyFactory =
	 * KeyFactory.getInstance("RSA"); RSAPublicKey pukKey = (RSAPublicKey)
	 * keyFactory .generatePublic(new
	 * X509EncodedKeySpec(getPublicKey(publicKey).getEncoded()));
	 * buff.append("<RSAKeyValue>"); buff.append( "<Modulus>" +
	 * Base64.encodeBytes(removeMSZero(pukKey.getModulus().toByteArray())) +
	 * "</Modulus>"); buff.append("<Exponent>" +
	 * Base64.encodeBytes(removeMSZero(pukKey.getPublicExponent().toByteArray())) +
	 * "</Exponent>"); buff.append("</RSAKeyValue>"); return buff.toString(); }
	 * catch (Exception e) { System.err.println(e); return null; } }
	 * 
	 * private static byte[] removeMSZero(byte[] data) { byte[] temp; int len =
	 * data.length; if (data[0] == 0) { temp = new byte[data.length - 1];
	 * System.arraycopy(data, 1, temp, 0, len - 1); } else temp = data; return temp;
	 * }
	 */
	/*
	 * public static void main(String[] args) throws InvalidKeyException,
	 * NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
	 * BadPaddingException, IOException { RSAUtil util = RSAUtil.getInstance();
	 * RSAKey key = util.init(); String cipher = util.RSAEncode(key.getPublicKey(),
	 * "床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。床前明月光，疑是地上霜。举头望明月，低头思故乡。"
	 * ); String text = RSAUtil.RSADecode(key.getPrivateKey(), cipher);
	 * System.out.println(cipher); System.out.println(text); }
	 */
}
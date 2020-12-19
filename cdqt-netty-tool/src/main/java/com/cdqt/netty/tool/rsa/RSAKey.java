package com.cdqt.netty.tool.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSA秘钥对对象
 * 
 * @author LiuGangQiang Create in 2019/07/15
 */
public class RSAKey {

	/**
	 * 公钥
	 *
	 * @author LiuGangQiang Create in 2019/07/15
	 */
	private PublicKey publicKey;

	/**
	 * 私钥
	 *
	 * @author LiuGangQiang Create in 2019/07/15
	 */
	private PrivateKey privateKey;

	/**
	 * 无参构造器
	 *
	 * @author LiuGangQiang Create in 2019/07/15
	 */
	public RSAKey() {
	}

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2019/07/15
	 * @param publicKey  公钥
	 * @param privateKey 私钥
	 */
	public RSAKey(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}

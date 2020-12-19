package com.cdqt.netty.tool.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IO工具类
 *
 * @author LiuGangQiang Create in 2020/03/02
 */
public class IOUtil {
	/**
	 * 把输入流转换为字节数组
	 *
	 * @author LiuGangQiang Create in 2020/01/26
	 * @param input 输入流
	 * @return {@link Byte} 数组
	 * @throws IOException
	 */
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}

package com.cdqt.netty.vess.tools.clazz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.cdqt.netty.base.exception.FistException;

/**
 * 包扫描工具
 *
 * @author LiuGangQiang Create in 2021/01/29
 */
public class ClassScanUtil {

	/**
	 * 获取某个Jar包内Class
	 *
	 * @author LiuGangQiang Create in 2021/01/29
	 * @param path        Jar包绝对路径 包含后缀
	 * @param packageName 包名 为空将扫描所有包
	 * @param isergodic   是否扫描子包
	 * @return {@link List}
	 */
	public static List<String> getClassByJar(String path, String[] packageName, boolean isergodic) {
		List<String> classs = new ArrayList<>();
		JarFile jar;
		try {
			jar = new JarFile(path);
			Enumeration<JarEntry> enumerations = jar.entries();
			/* 判断包名是否为空 */
			if (packageName == null || packageName.length <= 0) {
				/* 为空扫描所有 */
				while (enumerations.hasMoreElements()) {
					JarEntry entry = enumerations.nextElement();
					String entryName = entry.getName();
					if (entryName.endsWith(".class")) {
						classs.add(entryName.replace("/", ".").replace(".class", ""));
					}
				}
			} else {
				/* 包名不为空 先把包名转路径 */
				while (enumerations.hasMoreElements()) {
					JarEntry entry = enumerations.nextElement();
					String entryName = entry.getName();
					for (String pkg : packageName) {
						String packagePath = pkg.replace(".", "/");
						/* 判断是否是class结尾且包名开始的文件 */
						if (entryName.endsWith(".class") && entryName.startsWith(packagePath)) {
							/* 是否遍历子包 */
							if (isergodic) {
								/* 遍历子包直接添加 */
								classs.add(entryName.replace("/", ".").replace(".class", ""));
							} else {
								/* 判断是否属于当前包 */
								if (!entryName.substring(packagePath.length() + 1).contains("/")) {
									classs.add(entryName.replace("/", ".").replace(".class", ""));
								}
							}
						}
					}
				}
			}
			/* 要注意在最后关闭资源 */
			jar.close();
		} catch (IOException e) {
			throw new FistException("framework load jar error {0}", e.getMessage());
		}
		return classs;
	}
}

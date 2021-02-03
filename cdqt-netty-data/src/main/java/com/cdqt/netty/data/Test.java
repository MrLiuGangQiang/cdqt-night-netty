package com.cdqt.netty.data;

import java.util.Date;

import com.cdqt.netty.base.annotation.FistBody;
import com.cdqt.netty.base.annotation.FistFile;
import com.cdqt.netty.base.annotation.FistHeader;
import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.annotation.FistQuery;
import com.cdqt.netty.base.model.FistBaseFile;

public class Test {
	@FistMapping("converterHeader")
	public String converterHeader(@FistHeader("header") String header) {
		return header;
	}

	@FistMapping("converterArray")
	public String converterArray(@FistQuery("array1") String[] array1, @FistBody(value = "array2") String[] array2) {
		StringBuffer sb = new StringBuffer();
		for (String param : array1) {
			sb.append(param).append(">");
		}
		for (String param : array2) {
			sb.append(param).append(">");
		}
		return sb.toString();
	}

	@FistMapping("converterString")
	public String converterString(@FistQuery("param") String param) {
		return param;
	}

	@FistMapping("converterDate")
	public Date converterDate(@FistBody(value = "date") Date date) {
		return date;
	}

	@FistMapping("converterCharacter")
	public Character converterCharacter(@FistQuery("character") Character character) {
		return character;
	}

	@FistMapping("converterInteger")
	public String converterInteger(@FistQuery("num1") Integer num1, @FistQuery("num2") int num2) {
		return "Integer:" + num1 + " int:" + num2;
	}

	@FistMapping("converterDouble")
	public String converterDouble(@FistQuery("num1") Double num1, @FistQuery("num2") double num2) {
		return "Double:" + num1 + " double:" + num2;
	}

	@FistMapping("converterFloat")
	public String converterFloat(@FistQuery("num1") Float num1, @FistQuery("num2") float num2) {
		return "Float:" + num1 + " float:" + num2;
	}

	@FistMapping("converterLong")
	public String converterLong(@FistQuery("num1") Long num1, @FistQuery("num2") long num2) {
		return "Long:" + num1 + " long:" + num2;
	}

	@FistMapping("converterBoolean")
	public String converterBoolean(@FistQuery("param1") Boolean param1, @FistQuery("param2") boolean param2) {
		return "Boolean:" + param1 + " boolean:" + param2;
	}

	@FistMapping("converterByte")
	public String converterByte(@FistQuery("param1") Byte param1, @FistQuery("param2") byte param2) {
		return "Byte:" + param1 + " byte:" + param2;
	}

	@FistMapping("converterShort")
	public String converterShort(@FistQuery("param1") Short param1, @FistQuery("param2") short param2) {
		return "Short:" + param1 + " short:" + param2;
	}

	@FistMapping("converterBody")
	public User converterBody(@FistBody User user) {
		return user;
	}

	@FistMapping("converterEntity")
	public User converterEntity(User user) {
		return user;
	}

	@FistMapping("converterFile")
	public String converterFile(@FistBody(value = "param") String param, @FistFile("file") FistBaseFile file) {
		return "parameter:" + param + " file:" + file.toString();
	}

	@FistMapping("converterFiles")
	public String converterFiles(@FistBody(value = "param") String param, @FistFile("file") FistBaseFile[] files) {
		StringBuffer sb = new StringBuffer("parameter:");
		sb.append(param);
		for (FistBaseFile file : files) {
			sb.append(file.toString()).append(">>>");
		}
		return sb.toString();
	}

	@FistMapping("converterEmpty")
	public String converterEntity(String param) {
		return param;
	}
}

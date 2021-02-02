package com.cdqt.netty.data;

import java.util.Date;

import com.cdqt.netty.base.annotation.FistBody;
import com.cdqt.netty.base.annotation.FistFile;
import com.cdqt.netty.base.annotation.FistHeader;
import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.annotation.FistQuery;
import com.cdqt.netty.base.model.FistBaseFile;

/**
 * ConverterTest
 *
 * @author LiuGangQiang Create in 2021/02/02
 */
public class ConverterTest {
	@FistMapping("converterHeader")
	public String converterHeader(@FistHeader("param") String param) {
		return param;
	}

	@FistMapping("converterArray")
	public String converterArray(@FistQuery("param1") String[] param1, @FistQuery(value = "param2", isBody = true) String[] param2) {
		StringBuffer sb=new StringBuffer();
		for (String param : param1) {
			sb.append(param).append(">");
		}
		for (String param : param2) {
			sb.append(param).append(">");
		}
		return sb.toString();
	}

	@FistMapping("converterString")
	public String converterString(@FistQuery("param") String param) {
		return param;
	}

	@FistMapping("converterDate")
	public Date converterDate(@FistQuery(value = "param", isBody = true) Date param) {
		return param;
	}

	@FistMapping("converterCharacter")
	public Character converterCharacter(@FistQuery("param") Character param) {
		return param;
	}

	@FistMapping("converterInteger")
	public String converterInteger(@FistQuery("param1") Integer param1, @FistQuery("param2") int param2) {
		return "Integer:" + param1 + " int:" + param2;
	}

	@FistMapping("converterDouble")
	public String converterDouble(@FistQuery("param1") Double param1, @FistQuery("param2") double param2) {
		return "Double:" + param1 + " double:" + param2;
	}

	@FistMapping("converterFloat")
	public String converterFloat(@FistQuery("param1") Float param1, @FistQuery("param2") float param2) {
		return "Float:" + param1 + " float:" + param2;
	}

	@FistMapping("converterLong")
	public String converterLong(@FistQuery("param1") Long param1, @FistQuery("param2") long param2) {
		return "Long:" + param1 + " long:" + param2;
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

	@FistMapping("converterEntityError")
	public User2 converterEntityError(User2 user) {
		return user;
	}

	@FistMapping("converterFile")
	public String converterFile(@FistQuery(value = "param", isBody = true) String param, @FistFile("file") FistBaseFile file) {
		return param + ">>" + file.toString();
	}

	@FistMapping("converterFiles")
	public String converterFiles(@FistQuery(value = "param", isBody = true) String param, @FistFile("file") FistBaseFile[] file) {
		StringBuffer sb = new StringBuffer();
		for (FistBaseFile fistBaseFile : file) {
			sb.append(fistBaseFile.toString()).append("<>");
		}
		return param + "<>" + sb.toString();
	}
}

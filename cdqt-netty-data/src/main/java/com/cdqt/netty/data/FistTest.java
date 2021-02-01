package com.cdqt.netty.data;

import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.annotation.FistQuery;

/**
 * 测试类
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
@FistMapping({ "fist" })
public class FistTest {
	@FistMapping({ "test" })
	public String test(
			@FistQuery("arg1") String arg1, 
			@FistQuery("arg2") Integer arg2, 
			@FistQuery("arg3") int arg3, 
			@FistQuery("arg4") Double arg4, 
			@FistQuery("arg5") double arg5, 
			@FistQuery("arg6") Float arg6,
			@FistQuery("arg7") float arg7, 
			@FistQuery("arg8") Long arg8, 
			@FistQuery("arg9") long arg9, 
			@FistQuery("arg10") Character arg10, 
			@FistQuery("arg11") Byte arg11, 
			@FistQuery("arg12") byte arg12, 
			@FistQuery("arg13") Boolean arg13,
			@FistQuery("arg14") boolean arg14) {
		StringBuffer sb=new StringBuffer();
		sb.append("String:"+arg1).append("||");
		sb.append("Integer:"+arg2).append("||");
		sb.append("int:"+arg3).append("||");
		sb.append("Double:"+arg4).append("||");
		sb.append("double:"+arg5).append("||");
		sb.append("Float:"+arg6).append("||");
		sb.append("float:"+arg7).append("||");
		sb.append("Long:"+arg8).append("||");
		sb.append("long:"+arg9).append("||");
		sb.append("Character:"+arg10).append("||");
		sb.append("Byte:"+arg11).append("||");
		sb.append("byte:"+arg12).append("||");
		sb.append("Boolean:"+arg13).append("||");
		sb.append("boolean:"+arg14).append("||");
		return sb.toString();
	}

}

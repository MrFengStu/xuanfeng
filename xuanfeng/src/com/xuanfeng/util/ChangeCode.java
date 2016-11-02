package com.xuanfeng.util;

import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeCode {

	/**
	 * 修改编码格式
	 * @param obj
	 * @return
	 */
	public static String changeCode(String obj){
		String temp =obj;
		try {
			temp = new String(temp.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	/**
	 * 改变String to Date
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   ParsePosition pos = new ParsePosition(0);
		   Date strtodate = formatter.parse(strDate, pos);
		   return strtodate;
		}
}

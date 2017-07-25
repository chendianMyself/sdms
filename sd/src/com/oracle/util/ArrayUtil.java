package com.oracle.util;

public class ArrayUtil {

	
	public static int[] string2intArray(String ids){
		//如果没有传入id序列,返回长度为0的字符串
		if(ids==""||ids==null)return new int[0];
		//使用逗号将字符串拆分成数组
		String[] tempIds=ids.split(",");
		//创建int类型数组,其长度与string数组长度相等
		int[] result=new int[tempIds.length];
		for(int i=0;i<tempIds.length;i++){
			result[i]=Integer.parseInt(tempIds[i]);
		}
		return result;
	}
	
	/*public static void main(String[] args) {
		String str="1,";
		
		int[] result=string2intArray(str);
		if(result.length==0)return;
		
		
		
		String sql="update dept set flag = 1 where id in (";
		for(int i=0;i<result.length;i++){
			sql+="?,";
		}
		sql=sql.substring(0,sql.length()-1);//删除最后一个逗号
		sql+=")";
		
		System.out.println(sql);
	}*/
}

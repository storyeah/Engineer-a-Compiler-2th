package com.alogrithm.engineer_a_compiler;

import org.junit.Test;
import java.util.*;

public class chapter8_optimization{
	private HashMap<String, Integer> lvnExpr =new HashMap<String, Integer>();
	private HashMap<Integer, String> lvnNode =new HashMap<Integer, String>();

	private void LVN_8_4(ArrayList<String> al){
		for(String str:al){
			String[] temp = str.split("=");
			String dstVar = temp[0];
			String expr = temp[1];
			if(!lvnExpr.containsKey(expr)){
				Integer lvn = lvnExpr.size()+1;
				lvnExpr.put(expr, lvn);
				lvnExpr.put(dstVar, lvn);
				lvnNode.put(lvn, dstVar);
			}else{
				lvnExpr.put(dstVar, lvnExpr.get(expr));
				if(lvnExpr.get(lvnNode.get(lvnExpr.get(expr))) == lvnExpr.get(expr)){
					expr = lvnNode.get(lvnExpr.get(expr));
				}
				str = dstVar+"="+expr;
			}
			System.out.println(str);
		}
	}

	@Test
	public void LVN_8_4_Test(){
		ArrayList<String> al = new ArrayList<String>();
		//simplified example. see x+y as x single params .
		al.add("a=x+y");
		al.add("b=x+y");
		al.add("a=17");
		al.add("c=x+y");
		al.add("d=17");
		al.add("d=17");
		al.forEach((k)->System.out.println(k));
		System.out.println("------------------------");
		LVN_8_4(al);
	}
// expected output:
//			a=x+y
//			b=x+y
//			a=17
//			c=x+y
//			d=17
//			c=17
//			------------------------
//			a=x+y
//			b=a
//			a=17
//			c=x+y
//			d=a
//			c=a
}

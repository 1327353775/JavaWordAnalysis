package com.jacob;

import java.util.HashSet;
import java.util.Set;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobPattern {
	private Dispatch selection;
	private ActiveXComponent wordApp;
	public static void main(String args[]) {
		Set<String> s = new HashSet<String>();
		s.add("5");
		s.add("8");
		s.add("54");
		System.out.println(s);
		JacobPattern j = new JacobPattern();
		j.bt("123", s, "1238456789548");
	}
	 public boolean find(String toFindText) { 
         if (toFindText == null || toFindText.equals("")) 
                 return false; 
         // 从selection所在位置开始查询 
         Dispatch find = wordApp.call(selection, "Find").toDispatch(); 
         // 设置要查找的内容 
         Dispatch.put(find, "Text", toFindText); 
         // 向前查找 
         Dispatch.put(find, "Forward", "True"); 
         // 设置格式 
         Dispatch.put(find, "Format", "True"); 
         // 大小写匹配 
         Dispatch.put(find, "MatchCase", "True"); 
         // 全字匹配 
         
         //Dispatch.put(find, "MatchWholeWord", "True"); 
         // 查找并选中 
         return Dispatch.call(find, "Execute").getBoolean(); 
	 } 
	public boolean replaceText(String toFindText, String newText) { 
        if (!find(toFindText)) 
                return false; 
        Dispatch font = Dispatch.get(selection, "Font").toDispatch(); // 字型格式化需要的对象
        Dispatch.put(font, "Color", "1,0,0,0");
        Dispatch.put(selection, "Text", newText); 
        return true; 
	} 
	public void replaceAllText(String toFindText, String newText) { 
        while (find(toFindText)) { 
	        	Dispatch font = Dispatch.get(selection, "Font").toDispatch(); // 字型格式化需要的对象
	            Dispatch.put(font, "Color", "1,0,0,0");
                Dispatch.put(selection, "Text", newText); 
                Dispatch.call(selection, "MoveRight"); 
        } 
	}
	public void bt(String name,Set<String> s,String content) {
		wordApp = new ActiveXComponent("Word.Application"); // 启动word
		Dispatch.put(wordApp, "Visible", new Variant(true));// //设置word可见
		Dispatch docs = wordApp.getProperty("Documents").toDispatch();
		Dispatch document = Dispatch.call(docs, "Add").toDispatch();// create new document
		String userName = wordApp.getPropertyAsString("Username");// 显示用户信息
		System.out.println("用户名:" + userName);
		// 文档对齐，字体设置////////////////////////
		selection = Dispatch.get(wordApp, "Selection").toDispatch();
		Dispatch align = Dispatch.get(selection, "ParagraphFormat").toDispatch(); // 行列格式化需要的对象
		Dispatch font = Dispatch.get(selection, "Font").toDispatch(); // 字型格式化需要的对象
		// 标题处理////////////////////////
		Dispatch.put(align, "Alignment", "1"); // 1:置中 2:靠右 3:靠左
		Dispatch.put(font, "Bold", "1"); // 字型租体
		Dispatch.put(font, "Color", 0); // 字型颜色红色

		Dispatch.call(selection, "TypeText", name); // 写入标题内容

		Dispatch.call(selection, "TypeParagraph"); // 空一行段落
		Dispatch.put(align, "Alignment", "3"); // 1:置中 2:靠右 3:靠左
		Dispatch.put(selection, "Text", "        ");
		Dispatch.call(selection, "MoveDown"); // 光标标往下一行
		// Word文档内容查找及替换////////////////////////
		Dispatch.call(selection, "TypeParagraph"); // 空一行段落
		Dispatch.put(align, "Alignment", "3"); // 1:置中 2:靠右 3:靠左
		Dispatch.put(font, "Color", 0);
		Dispatch.put(selection, "Text", content);		
		//Dispatch.call(selection, "HomeKey", new Variant(6));// 移到开头
		Dispatch find = Dispatch.call(selection, "Find").toDispatch();// 获得Find组件
		for (String string : s) {
			replaceAllText(string, string);
			Dispatch.call(selection, "HomeKey", new Variant(6));
		}
		Dispatch.call(document, "SaveAs", "D:/wordOperate.doc");
	}

}
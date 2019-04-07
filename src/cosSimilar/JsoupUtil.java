package cosSimilar;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {

	
	 public static String URLLoader(String url) {  
	        //1、从URL加载文档，使用Jsoup.connect()方法从URL加载HTML  
	        Document d1;  
	        String content = "";
	        try {  
	            d1 = Jsoup.connect(url).get();  
	           
	            Elements provinces=d1.select(".lemma-summary > div");  
	            
	            for (Element ele : provinces) {  
	           
	            	content = content+ele.text();
	            	//System.out.println(ele.text());
	            	
	            }  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return content;
	         
	    }  
}
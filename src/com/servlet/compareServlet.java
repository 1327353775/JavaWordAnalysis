package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;
import com.bean.UserBean;
import com.dao.CompareSelectDao;
import com.hankcs.hanlp.HanLP;
import com.jacob.JacobPattern;

import cosSimilar.CosineSimilarAlgorithm;
import cosSimilar.JsoupUtil;
import cosSimilar.SimpleSummariserAlgorithm;

/**
 * Servlet implementation class compareServlet
 */
@WebServlet("/compareServlet")
public class compareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public compareServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		String name = request.getParameter("find");
		CompareSelectDao cs = new CompareSelectDao();
		UserBean user = cs.SelectContent(name);
		String content2 = JsoupUtil.URLLoader("https://baike.baidu.com/item/"+name);
		double result = CosineSimilarAlgorithm.cosSimilarityByString(user.getContent(),content2);
		List<String> keywordList = HanLP.extractKeyword(user.getContent(), 4);
		List<String> sentenceList = HanLP.extractSummary(user.getContent(), 4);
		
		Map<String, Integer> ss = SimpleSummariserAlgorithm.segStr(user.getContent());
    	Map<String, Integer> c = SimpleSummariserAlgorithm.getMostFrequentWords(20, ss);
    	Set<String> key = c.keySet();
    	JacobPattern j = new JacobPattern();
    	j.bt(user.getName(), key, user.getContent());
    	System.out.println(key);
    	for (String string : key) {
    		user.setContent(user.getContent().replaceAll(string, "<font color='red'>"+string+"</font>"));
		}	
    	
		user.setSentence(sentenceList.toString());
		user.setCategory(String.valueOf(result));
		user.setWord(keywordList.toString());
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("./index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}

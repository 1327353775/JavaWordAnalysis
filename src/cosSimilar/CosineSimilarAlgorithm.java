package cosSimilar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * <p>Title:</p>
 * <p>Description: 余弦获取文章相似性
 * </p>
 * @createDate：2013-8-26
 * @author xq
 * @version 1.0
 */
public class CosineSimilarAlgorithm {

	/**
	 * 
	* @Title: cosSimilarityByFile
	* @Description: 获取两个文件相似性
	* @param @param firstFile
	* @param @param secondFile
	* @param @return    
	* @return Double   
	* @throws
	 */
	public static Double cosSimilarityByFile(String firstFile,String secondFile){
		try{
			Map<String, Map<String, Integer>> firstTfMap=TfIdfAlgorithm.wordSegCount(firstFile);
			Map<String, Map<String, Integer>> secondTfMap=TfIdfAlgorithm.wordSegCount(secondFile);
			if(firstTfMap==null || firstTfMap.size()==0){
				throw new IllegalArgumentException("firstFile not found or firstFile is empty! ");
			}
			if(secondTfMap==null || secondTfMap.size()==0){
				throw new IllegalArgumentException("secondFile not found or secondFile is empty! ");
			}
			Map<String,Integer> firstWords=firstTfMap.get(firstFile);
			Map<String,Integer> secondWords=secondTfMap.get(secondFile);
			if(firstWords.size()<secondWords.size()){
				Map<String, Integer> temp=firstWords;
				firstWords=secondWords;
				secondWords=temp;
			}
			return calculateCos((LinkedHashMap<String, Integer>)firstWords, (LinkedHashMap<String, Integer>)secondWords);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}
	
	/**
	 * 
	* @Title: cosSimilarityByString
	* @Description: 得到两个字符串的相似性
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	public static Double cosSimilarityByString(String first,String second){
		try{
			Map<String, Integer> firstTfMap=TfIdfAlgorithm.segStr(first);
			Set<String> set = firstTfMap.keySet();
			String res = "";
			for(String i:set) {
				res = res+i;
			}
			//System.out.println(res);
			System.out.println("------------------------");
			
			Map<String, Integer> secondTfMap=TfIdfAlgorithm.segStr(second);
//			
//			for(int i=0;i<firstTfMap.size();i++) {
//				System.out.print(secondTfMap.toString());
//			}
			System.out.println("------------------------");
			if(firstTfMap.size()<secondTfMap.size()){
				Map<String, Integer> temp=firstTfMap;
				firstTfMap=secondTfMap;
				secondTfMap=temp;
				
			}
			
			return calculateCos((LinkedHashMap<String, Integer>)firstTfMap, (LinkedHashMap<String, Integer>)secondTfMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}

	/**
	 * 
	* @Title: calculateCos
	* @Description: 计算余弦相似性
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	private static Double calculateCos(LinkedHashMap<String, Integer> first,LinkedHashMap<String, Integer> second){
		
		List<Map.Entry<String, Integer>> firstList = new ArrayList<Map.Entry<String, Integer>>(first.entrySet());
		List<Map.Entry<String, Integer>> secondList = new ArrayList<Map.Entry<String, Integer>>(second.entrySet());
		//计算相似度  
        double vectorFirstModulo = 0.00;//向量1的模  
        double vectorSecondModulo = 0.00;//向量2的模  
        double vectorProduct = 0.00; //向量积  
        int secondSize=second.size();
		for(int i=0;i<firstList.size();i++){
			if(i<secondSize){
				vectorSecondModulo+=secondList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
				vectorProduct+=firstList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
			}
			vectorFirstModulo+=firstList.get(i).getValue().doubleValue()*firstList.get(i).getValue().doubleValue();
		}
	   return vectorProduct/(Math.sqrt(vectorFirstModulo)*Math.sqrt(vectorSecondModulo));
	}
	
	public static void main(String[] args){
		Double result=cosSimilarityByString("三网融合又可被称为“数位汇流”，是将电信网、计算机互联网和有线电视网三者互联互通，融合发展，从而为用户提供语音、数据和广播电视等服务， 伴随着通信行业加快发展，传统的三网融合已逐渐成为当前互联网发展的趋势。"
				,"三网融合是指电信网、广播电视网、互联网在向宽带通信网、数字电视网、下一代互联网演进过程中，三大网络通过技术改造，其技术功能趋于一致，业务范围趋于相同，网络互联互通、资源共享，能为用户提供语音、数据和广播电视等多种服务。三合并不意味着三大网络的物理合一，而主要是指高层业务应用的融合。三网融合应用广泛，遍及智能交通、环境保护、政府工作、公共安全、平安家居等多个领域。以后的手机可以看电视、上网，电视可以打电话、上网，电脑也可以打电话、看电视。三者之间相互交叉，形成你中有我、我中有你的格局。");
		System.out.println(result);
	}
}

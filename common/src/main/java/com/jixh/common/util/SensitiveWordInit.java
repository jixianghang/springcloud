package com.jixh.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description: ???????????????HashMap????DFA????
 * @Project?test
 * @Author : chenming
 * @Date ? 2014?4?20? ??2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {
	private String ENCODING = "GBK";    //????
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;

	public SensitiveWordInit(){
		super();
	}

	/**
	 * @author chenming
	 * @date 2014?4?20? ??2:28:32
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(){
		try {
			//??????
			Set<String> keyWordSet = readSensitiveWordFile();
			//????????HashMap?
			addSensitiveWordToHashMap(keyWordSet);
			//spring??application???application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * ?????????????HashSet??????DFA?????<br>
	 * ? = {
	 *      isEnd = 0
	 *      ? = {<br>
	 *        isEnd = 1
	 *           ? = {isEnd = 0
	 *                ? = {isEnd = 1}
	 *                }
	 *           ?  = {
	 *               isEnd = 0
	 *             ? = {
	 *               isEnd = 1
	 *              }
	 *            }
	 *           }
	 *      }
	 *  ? = {
	 *      isEnd = 0
	 *      ? = {
	 *       isEnd = 0
	 *       ? = {
	 *              isEnd = 0
	 *              ? = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *       }
	 *      }
	 * @author chenming
	 * @date 2014?4?20? ??3:04:20
	 * @param keyWordSet  ????
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //???????????????
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//??keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //???
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //???char?
				Object wordMap = nowMap.get(keyChar);       //??

				if(wordMap != null){        //?????key?????
					nowMap = (Map) wordMap;
				}
				else{     //??????????map????isEnd???0??????????
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //??????
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //????
				}
			}
		}
	}

	/**
	 * ?????????????????set???
	 * @author chenming
	 * @date 2014?4?20? ??2:31:18
	 * @return
	 * @version 1.0
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;

		File file = new File("D:\\SensitiveWord.txt");    //????
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),ENCODING);
		try {
			if(file.isFile() && file.exists()){      //???????
				set = new HashSet<String>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){    //?????????????set?
					set.add(txt);
				}
			}
			else{         //?????????
				throw new Exception("?????????");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			read.close();     //?????
		}
		return set;
	}
}
package ru.test.parser;

import ru.test.utils.UtilsRegex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер для вывода результата в GUI
 */
public class ParserResult {

	public Map<String, Object> parseMapResult(String result) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("query", getRegexResult(UtilsRegex.queryRegex, result));
		map.put("queryStat", Integer.parseInt(getRegexResult(UtilsRegex.sizeQueryRegex, result)));
		map.put("userCount", Integer.parseInt(getRegexResult(UtilsRegex.sizeUserRegex, result)));
		map.put("countClick", Integer.parseInt(getRegexResult(UtilsRegex.sizeClickRegex, result)));
		map.put("averageDocumentClick", Float.parseFloat(getRegexResult(UtilsRegex.avrDocRegex, result)));
		map.put("noClickQuery", Integer.parseInt(getRegexResult(UtilsRegex.sizeNoQueryRegex, result)));

		return map;
	}

	private String getRegexResult(String regexExp, String text) {
		Matcher mat = Pattern.compile(regexExp).matcher(text);
		if (mat.find()) {
			return mat.group(1);
		}
		return "";
	}

}

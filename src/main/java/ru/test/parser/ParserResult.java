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

	public Map<String, String> parseMapResult(String result, String key) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("query", key);
		map.put("queryStat", getRegexResult(UtilsRegex.sizeQueryRegex, result));
		map.put("userCount", getRegexResult(UtilsRegex.sizeUserRegex, result));
		map.put("countClick", getRegexResult(UtilsRegex.sizeClickRegex, result));
		map.put("averageDocumentClick", getRegexResult(UtilsRegex.avrDocRegex, result));
		map.put("noClickQuery", getRegexResult(UtilsRegex.sizeNoQueryRegex, result));

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

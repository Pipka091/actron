package ru.test.parser;

import ru.test.model.ClickLog;
import ru.test.model.DataLog;
import ru.test.model.QueryLog;
import ru.test.model.SerpListLog;
import ru.test.utils.UtilsRegex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер логов, из строки в обьект DataLog.
 */
public class ParserLogs {

	public final String ALL = "all";

	public DataLog parseLogLine(String line, String key) {
		DataLog dataLog = new DataLog();

		if (!key.equals(ALL)) {
			if (getRegexResult("\\{(" + key + ")\\}", line).isEmpty()) {
				return null;
			}
		}

		dataLog.idUser = Long.parseLong(getRegexResult(UtilsRegex.idUserRegex, line));
		dataLog.dateBeginSession = getRegexResult(UtilsRegex.dateBeginSession, line);
		dataLog.dateEndSession = getRegexResult(UtilsRegex.dateEndSession, line);

		List<String> listBlock = getRegexListResult(UtilsRegex.blockQuery, line);

		for (String block : listBlock) {

			QueryLog queryLog = new QueryLog();
			queryLog.dateQuery = getRegexResult(UtilsRegex.queryDate, block);
			queryLog.textQuery = getRegexResult(UtilsRegex.queryText, block);

			setSerpList(queryLog, block);
			dataLog.listQuery.add(queryLog);
		}

		return dataLog;
	}

	private void setSerpList(QueryLog queryLog, String block) {
		SerpListLog serpListLog = new SerpListLog();

		serpListLog.idExtradite = Long.parseLong(getRegexResult(UtilsRegex.serpListIdExtradite, block));
		List<String> serpListIdDoc = getRegexListResult(UtilsRegex.serpListIdDoc, block);
		List<Long> serpListIdDocSecond = new ArrayList<Long>();

		for (String idDoc : serpListIdDoc) {
			serpListIdDocSecond.add(Long.parseLong(idDoc));
		}

		serpListLog.documentIdList = serpListIdDocSecond;
		queryLog.serpList = serpListLog;

		setClicks(queryLog, block);
	}

	private void setClicks(QueryLog dataLog, String block) {

		List<ClickLog> listClick = new ArrayList<ClickLog>();
		String id = getRegexResult(UtilsRegex.clickIdExtradite, block);
		if (!id.isEmpty()) {
			long idExtradite = Long.parseLong(id);

			List<String> idDocList = getRegexListResult(UtilsRegex.clickIdDoc, block);

			for (String idDoc : idDocList) {
				ClickLog clickLog = new ClickLog();
				clickLog.idExtradite = idExtradite;
				clickLog.documentId = Long.parseLong(idDoc);
				listClick.add(clickLog);
			}
			dataLog.listClick = listClick;
		}
	}

	private String getRegexResult(String regexExp, String text) {
		Matcher mat = Pattern.compile(regexExp).matcher(text);
		if (mat.find()) {
			return mat.group(1);
		}
		return "";
	}


	private List<String> getRegexListResult(String regexExp, String text) {
		List<String> listStr = new ArrayList<String>();
		Matcher mat = Pattern.compile(regexExp).matcher(text);
		while (mat.find()) {
			listStr.add(mat.group(1));
		}
		return listStr;
	}

}

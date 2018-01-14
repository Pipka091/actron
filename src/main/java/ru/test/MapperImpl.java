package ru.test;

import ru.test.api.Mapper;
import ru.test.api.Store;
import ru.test.model.ClickLog;
import ru.test.model.DataLog;
import ru.test.model.QueryLog;
import ru.test.parser.ParserLogs;

/*
 */
public class MapperImpl implements Mapper {

	public ParserLogs parserLogs = new ParserLogs();

	@Override
	public void map(String key, String value, Store store) {
		DataLog data = parserLogs.parseLogLine(value, key);

		for (QueryLog query : data.listQuery) {

			String keyQuery = query.textQuery;
			String result = keyQuery + "=" + data.idUser + "\t";
			for(ClickLog clickLog : query.listClick){
				result += clickLog.documentId + ";";
			}
			store.collect(keyQuery, result);
		}
	}

}

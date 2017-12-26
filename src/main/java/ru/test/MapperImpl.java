package ru.test;

import ru.test.api.Mapper;
import ru.test.api.Store;
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
		if (data != null)
			setMapKey(data, store);
			store.collect(key, data);
	}

	private void setMapKey(DataLog data, Store store) {
		for(QueryLog queryLog : data.listQuery){
			if (store.getMapKey().get(queryLog.textQuery) == null){
				store.getMapKey().put(queryLog.textQuery, queryLog.textQuery);
			}
		}
	}
}

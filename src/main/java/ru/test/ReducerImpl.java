package ru.test;

import ru.test.api.Reducer;
import ru.test.api.Store;
import ru.test.model.ClickLog;
import ru.test.model.DataLog;
import ru.test.model.QueryLog;

import java.util.*;

public class ReducerImpl implements Reducer {

	@Override
	public void reduce(String key, Iterator<DataLog> value, Store store) {

		int sizeQuery = 0;
		int sizeUser = 0;
		int sizeClick = 0;
		float avrDoc = 0;
		int sizeNoQuery = 0;
		long summDoc = 0;
		Map<Long, String> mapUser = new HashMap<Long, String>();

		while (value.hasNext()) {
			DataLog dataLog = value.next();

			if(dataLog == null)
				continue;

			for (QueryLog queryLog : dataLog.listQuery) {

				if (queryLog.textQuery.toLowerCase().equals(key)) {

					if (mapUser.get(dataLog.idUser) == null) {
						mapUser.put(dataLog.idUser, dataLog.idUser.toString());
					}

					sizeClick += queryLog.listClick.size();
					sizeQuery++;

					summDoc += getSummDoc(queryLog.listClick);

					if (queryLog.listClick.size() == 0) {
						sizeNoQuery++;
					}
				}
			}
		}
		sizeUser = mapUser.size();
		if(summDoc > 0)
			avrDoc = summDoc / sizeClick;


		store.setResult(key, "sizeQuery={" + sizeQuery + "}\tsizeUser={" + sizeUser + "}\tsizeClick={" + sizeClick + "}\tavrDoc={" + avrDoc + "}\tsizeNoQuery={" + sizeNoQuery + "}");
	}


	public long getSummDoc(List<ClickLog> list){
		long summ = 0;
		for(ClickLog clickLog : list){
			summ =+ clickLog.documentId;
		}
		return summ;
	}

}

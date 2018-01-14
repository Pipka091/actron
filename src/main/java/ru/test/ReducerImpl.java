package ru.test;

import ru.test.api.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReducerImpl implements Reducer {

	@Override
	public void reduce(String key, Iterator<String> value, StoreSave store) throws IOException {
		int sizeQuery = 0;
		int sizeUser = 0;
		int sizeClick = 0;
		float avrDoc = 0;
		int sizeNoQuery = 0;
		long summDoc = 0;
		Map<String, String> mapUser = new HashMap<String, String>();

		while (value.hasNext()) {
			String dataLog = value.next();

			if (dataLog.isEmpty()) {
				continue;
			}

			String[] data = dataLog.split("=");
			String[] dataStat = data[1].split("\t");
			String[] dataClick;
			try {
				dataClick = dataStat[1].split(";");
			} catch (Exception e) {
				dataClick = new String[0];
			}

			if (mapUser.get(dataStat[0]) == null) {
				mapUser.put(dataStat[0], dataStat[0]);
			}
			sizeQuery++;
			sizeClick += dataClick.length;
			summDoc += getSummDoc(dataClick);

			if (dataClick.length == 0) {
				sizeNoQuery++;
			}

		}

		if (summDoc > 0) {
			avrDoc = summDoc / sizeClick;
		}

		sizeUser = mapUser.size();
		store.saveData("queryText={" + key + "}\tsizeQuery={" + sizeQuery + "}\tsizeUser={" + sizeUser + "}\tsizeClick={" + sizeClick + "}\tavrDoc={" + avrDoc + "}\tsizeNoQuery={" + sizeNoQuery + "}");
	}

	private long getSummDoc(String[] dataClick) {
		Long summ = 0L;
		for (String id : dataClick) {
			summ += Long.parseLong(id);
		}
		return summ;
	}

}

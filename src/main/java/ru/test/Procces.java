package ru.test;

import ru.test.model.DataLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Procces {

	private final String filePatch = "static/sessions.txt";
	private MapperImpl mapper = new MapperImpl();
	private ReducerImpl reduce = new ReducerImpl();

	public void startProcces(StoreImpl store, String key) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(filePatch).getFile());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

		try {
			String line = "";
			while ((line = br.readLine()) != null) {
				mapper.map(key, line, store);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

		if (!key.toLowerCase().equals("all")) {
			List<DataLog> dataLogs = store.store.get(key);
			if (dataLogs.size() > 0) {
				reduce.reduce(key, dataLogs.listIterator(), store);
				store.store = new HashMap<String, List<DataLog>>();
			}
		} else {
			Map<String, String> mapKey = store.getMapKey();
			for (Map.Entry<String, String> entry : mapKey.entrySet()) {
				List<DataLog> dataLogs = store.store.get("all");
				if (dataLogs.size() > 0) {
					reduce.reduce(entry.getKey(), dataLogs.listIterator(), store);
				}
			}
		}
	}

}

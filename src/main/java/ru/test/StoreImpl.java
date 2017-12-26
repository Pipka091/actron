package ru.test;

import ru.test.api.Store;
import ru.test.model.DataLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Хранилище
 */
public class StoreImpl implements Store {

	public HashMap<String, String> mapKey = new HashMap<String, String>();
	public HashMap<String, List<DataLog>> store = new HashMap<String, List<DataLog>>();
	public HashMap<String, String> storeResult = new HashMap<String, String>();

	@Override
	public void collect(String key, DataLog value) {
		if (store.get(key) == null) {
			List<DataLog> list = new ArrayList<DataLog>();
			list.add(value);
			store.put(key, list);
		} else {
			store.get(key).add(value);
		}
	}

	@Override
	public void setResult(String key, String result) {
		storeResult.put(key, result);
	}

	@Override
	public String getResult(String key) {
		return storeResult.get(key);
	}

	@Override
	public Map<String, String> getResult() {
		return storeResult;
	}

	@Override
	public Map<String, String> getMapKey() {
		return mapKey;
	}

	@Override
	public void cleanStore() {
		store = new HashMap<String, List<DataLog>>();
	}

}

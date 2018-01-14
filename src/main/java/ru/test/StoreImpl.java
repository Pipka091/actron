package ru.test;

import org.springframework.stereotype.Component;
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

	public HashMap<String, List<String>> storeLine = new HashMap<String, List<String>>();
;

	@Override
	public void collect(String key, String value) {
		if (storeLine.get(key) == null) {
			List<String> list = new ArrayList<String>();
			list.add(value);
			storeLine.put(key, list);
		} else {
			storeLine.get(key).add(value);
		}
	}
}

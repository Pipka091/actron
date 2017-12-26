package ru.test.api;


import ru.test.model.DataLog;

import java.util.Iterator;

public interface Reducer {
	public void reduce(String key, Iterator<DataLog> value, Store store);
}

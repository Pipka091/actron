package ru.test.api;

import ru.test.model.DataLog;

import java.util.Map;

public interface Store {
	public void collect(String key, String value);
}



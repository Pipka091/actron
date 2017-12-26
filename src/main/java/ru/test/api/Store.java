package ru.test.api;

import ru.test.model.DataLog;

import java.util.Map;

public interface Store {
	public void collect(String key, DataLog value);

	public void setResult(String key, String result);

	public String getResult(String key);
	public Map<String, String> getResult();
	public Map<String, String> getMapKey();
	public void cleanStore();
}

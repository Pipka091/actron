package ru.test.api;


import ru.test.StoreSave;

import java.io.IOException;
import java.util.Iterator;

public interface Reducer {
	public void reduce(String key, Iterator<String> value, StoreSave store) throws IOException;
}

package ru.test;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Component
public class Process {

	private final String filePatch = "static/sessions.txt";
	private MapperImpl mapper = new MapperImpl();
	private ReducerImpl reduce = new ReducerImpl();
	private StoreSave storeSave = new StoreSave();

	@PostConstruct
	public void init() {
		try {
			startProcces(new StoreImpl(), "all");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

		for (Map.Entry<String, List<String>> entry : store.storeLine.entrySet()) {
			reduce.reduce(entry.getKey(), entry.getValue().iterator(), storeSave);
		}
	}

}

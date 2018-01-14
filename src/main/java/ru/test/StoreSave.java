package ru.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gusev on 14.01.2018.
 */
public class StoreSave {

	private final String FILEPATCH = "static/result.txt";

	public void saveData(String line) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILEPATCH).getFile());
		FileWriter fileWriter = new FileWriter(file, true);
		try {
			fileWriter.append(line + "\n");
		} catch (Exception e) {
		} finally {
			fileWriter.close();
		}
	}

	public List<String> readResult() throws FileNotFoundException {
		ArrayList<String> listResult = new ArrayList<String>();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILEPATCH).getFile());

		Scanner scanner = new Scanner(file);

		while (scanner.hasNext()) {
			listResult.add(scanner.nextLine());
		}
		scanner.close();
		return listResult;
	}
}

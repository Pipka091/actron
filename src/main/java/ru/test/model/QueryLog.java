package ru.test.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Данные по запросу.
 */
public class QueryLog {
	public String textQuery;
	public String dateQuery;
	public List<ClickLog> listClick = new ArrayList<ClickLog>();
	public SerpListLog serpList;
}

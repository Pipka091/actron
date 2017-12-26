package ru.test.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Обьект хранит данные с одной строчки сессии логов.
 */
public class DataLog {

	public Long idUser;
	public String dateBeginSession;
	public String dateEndSession;
	public List<QueryLog> listQuery = new ArrayList<QueryLog>();


}

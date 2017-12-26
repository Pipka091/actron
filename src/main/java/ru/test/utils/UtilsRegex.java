package ru.test.utils;

public class UtilsRegex {

	public static final String idUserRegex = "(\\d+)";
	public static final String dateBeginSession = "(\\d+{4}\\-\\d+\\-\\d+)\\tQUERY";
	public static final String dateEndSession = "(\\d+{4}\\-\\d+\\-\\d+)$";
	public static final String blockQuery = "(UERY\\s*\\d+{4}\\-\\d+\\-\\d+.*?)(?:\tQ|\\d{4}\\-)";
	public static final String queryDate = "UERY\\s*(\\d+{4}\\-\\d+\\-\\d+)";
	public static final String queryText = "UERY\\s*\\d+{4}\\-\\d+\\-\\d+\\s*\\{(.*?)\\}";
	public static final String serpListIdExtradite = "SERPLIST\\s*(\\d+)";
	public static final String serpListIdDoc = "\\((.*?)\\)";
	public static final String clickIdDoc = "CLICK\\s*\\d+\\s*(\\d+)";
	public static final String clickIdExtradite = "CLICK\\s*(\\d+)";
	public static final String sizeQueryRegex = "sizeQuery=\\{(.*?)\\}";
	public static final String sizeUserRegex = "sizeUser=\\{(.*?)\\}";
	public static final String sizeClickRegex = "sizeClick=\\{(.*?)\\}";
	public static final String avrDocRegex = "avrDoc=\\{(.*?)\\}";
	public static final String sizeNoQueryRegex = "sizeNoQuery=\\{(.*?)\\}";

}

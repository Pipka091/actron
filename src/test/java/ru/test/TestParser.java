package ru.test;

import org.junit.Test;
import ru.test.model.DataLog;
import ru.test.parser.ParserLogs;

import static org.junit.Assert.assertEquals;

/**
 * Created by gusev on 26.12.2017.
 */

public class TestParser {

	public ParserLogs parserLogs = new ParserLogs();
	public String line = "0\t2016-07-20\tQUERY 2016-07-20 {факторинг}\tSERPLIST 630 (5994)(6942)(6982)(776)(2574)(3731)(6950)(3219)(7520)(8606)\tCLICK 630 6950\tCLICK 630 8606\tQUERY 2016-07-20 {бенефициар}\tSERPLIST 631 (7942)(2996)(4422)(3234)(7880)(2302)(984)(5611)(5064)(8596)\tCLICK 631 7942\tCLICK 631 5064\tQUERY 2016-07-20 {бухгалтерская отчетность}\tSERPLIST 632 (3887)(4092)(5462)(5183)(6607)(6888)(2821)(4644)(8358)(116)\tCLICK 632 3887\tCLICK 632 116\tQUERY 2016-07-20 {понижение зарплаты}\tSERPLIST 633 (905)(2028)(5494)(6085)(1456)(7765)(3483)(8047)(3743)(7736)\tCLICK 633 905\tCLICK 633 6085\tCLICK 633 1456\tCLICK 633 3483\tCLICK 633 3743\tCLICK 633 7736\tQUERY 2016-07-20 {*ндс*}\tSERPLIST 634 (7119)(2434)(1089)(3999)(6672)(75)(6873)(4350)(5448)(613)\tCLICK 634 3999\tCLICK 634 6672\tCLICK 634 75\tCLICK 634 6873\tQUERY 2016-07-20 {доказательство}\tSERPLIST 635 (4446)(6388)(6600)(3816)(669)(3151)(5739)(6273)(6444)(9877)\tCLICK 635 3816\tCLICK 635 6444\tCLICK 635 9877\tQUERY 2016-07-20 {договор поставки по гк рф}\tSERPLIST 636 (620)(3684)(4816)(5599)(947)(6483)(3924)(5675)(4367)(5831)\tCLICK 636 3684\tCLICK 636 4367\tCLICK 636 5831\t2016-07-20\n";

	@Test
	public void testParser(){
		DataLog dataLog = parserLogs.parseLogLine(line, "факторинг");
		assertEquals(new Long(0), dataLog.idUser);
		assertEquals(7, dataLog.listQuery.size());
		assertEquals("2016-07-20", dataLog.dateBeginSession);
		assertEquals("2016-07-20", dataLog.dateEndSession);
		assertEquals("факторинг", dataLog.listQuery.get(0).textQuery);
		assertEquals(2, dataLog.listQuery.get(0).listClick.size());
		assertEquals(6950, dataLog.listQuery.get(0).listClick.get(0).documentId);
		assertEquals(10, dataLog.listQuery.get(0).serpList.documentIdList.size());
		assertEquals(630, dataLog.listQuery.get(0).serpList.idExtradite);
	}

}

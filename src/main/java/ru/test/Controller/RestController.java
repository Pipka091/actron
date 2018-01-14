package ru.test.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.test.StoreSave;
import ru.test.parser.ParserResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Рест контроллер
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

	private StoreSave store = new StoreSave();
	private ParserResult parserResult = new ParserResult();

	@RequestMapping("/getStatAll")
	public List<Map<String, Object>> getStatAll(@RequestParam String query) throws InterruptedException {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		try {
			List<String> result = store.readResult();
			for (String data : result) {
				listResult.add(parserResult.parseMapResult(data));
			}
		} catch (Exception e) {
		}

		return listResult;
	}

}

package ru.test.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.test.Procces;
import ru.test.StoreImpl;
import ru.test.parser.ParserResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Рест контроллер
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

	private StoreImpl store = new StoreImpl();
	private Procces procces = new Procces();
	private ParserResult parserResult = new ParserResult();

	@RequestMapping("/getStat")
	public List<Map<String, String>> getStat(@RequestParam String query) throws InterruptedException {

		try {
			procces.startProcces(store, query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = store.getResult(query);
		Map<String, String> mapResult = parserResult.parseMapResult(result, query);

		store.cleanStore();

		List<Map<String, String>> listResult = new ArrayList<>();
		listResult.add(mapResult);
		return listResult;
	}

	@RequestMapping("/getStatAll")
	public List<Map<String, String>> getStatAll(@RequestParam String query) throws InterruptedException {

		try {
			procces.startProcces(store, query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, String>> listResult = new ArrayList<>();
		Map<String, String> resultMap = store.getResult();
		for(Map.Entry<String, String> entry : resultMap.entrySet()){
			listResult.add(parserResult.parseMapResult(entry.getValue(), entry.getKey()));
		}
		store.cleanStore();
		return listResult;
	}

}

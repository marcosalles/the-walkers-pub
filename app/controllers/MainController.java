package controllers;

import infra.Module;

import java.util.Arrays;
import java.util.List;

import play.mvc.Result;
import views.html.shared.*;

public class MainController extends BaseController {

	public static Result home() {
		Module highlighted = new Module(routes.DeckController.content(DeckController.HIGHLIGHTED), DeckController.HIGHLIGHTED, 5);
		Module latest = new Module(routes.DeckController.content(DeckController.LATEST), DeckController.LATEST, 10);
		List<Module> list = Arrays.asList(latest, highlighted);
		return wrapOk(modular.render(list));
	}

	public static Result random() {
		Module random = new Module(routes.DeckController.content(DeckController.RANDOM), DeckController.RANDOM, 5);
		List<Module> list = Arrays.asList(random);
		return wrapOk(modular.render(list));
	}

	public static Result todo() {
		return TODO;
	}

}

package controllers;

import infra.Module;

import java.util.Arrays;
import java.util.List;

import play.mvc.Result;
import views.html.shared.*;

public class MainController extends BaseController {

	public static Result home() {
		Module highlighted = new Module(routes.DeckController.decks("highlighted"), "highlighted");
		Module latest = new Module(routes.DeckController.decks("latest"), "latest");
		Module random = new Module(routes.DeckController.decks("random"), "random");
		List<Module> list = Arrays.asList(latest, random, highlighted);
		return wrapOk(modular.render(list));
	}

	public static Result authenticated() {
		return TODO;
	}

}

package controllers;

import java.util.List;

import models.magic.Card;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import views.html.*;

import com.avaje.ebean.Ebean;

public class CardController extends BaseController {

	public static Result buscaForm() {
		return ok(buscaCard.render());
	}

	public static Result busca() {
		DynamicForm form = Form.form().bindFromRequest();
		String value = form.field("suggestText").value();
		List<Card> list = Ebean.createQuery(Card.class)
				.where()
				.ilike("suggestText", "%"+value+"%")
				.findList();
		return ok(Json.toJson(list));
	}
}

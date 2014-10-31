package controllers;

import java.util.List;

import models.magic.Card;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import scala.collection.mutable.StringBuilder;
import views.html.card.*;

import com.avaje.ebean.Ebean;

public class CardController extends BaseController {

	public static Result searchForm() {
		Form<Card> form = Form.form(Card.class);
		return wrapOk(searchForm.render(form));
	}

	public static Result search() {
		DynamicForm form = Form.form().bindFromRequest();
		String value = form.field("suggestText").value();
		List<Card> list = Ebean.createQuery(Card.class)
				.where()
				.ilike("suggestText", "%"+value+"%")
				.findList();
		return wrapOk(cards.render(list));
	}
}

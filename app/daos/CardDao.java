package daos;

import java.util.List;

import models.magic.Card;
import play.data.Form;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;

public class CardDao {

	public static Finder<Long, Card> find = new Finder<Long, Card>(Long.class, Card.class);

	public static List<Card> cardsByName(String name) {
		return find.where()
				.ilike("suggestText", "%"+name+"%")
				.findList();
	}

	public static Card cardByMultiverseId(String id) {
		return find.where()
				.eq("multiverseId", id)
				.findUnique();
	}

	public static List<Card> cardsByFilledFields(Form<Card> form) {
		Card card = form.get();
		String name = card.getSuggestText().trim();
		String text = card.getText().trim();
		String type = card.getType().trim();
		String colors = form.field("colors[]").value();
		System.out.println("colors: '"+colors+"'");

		Expression nullExpression = Expr.eq("id", -1);
		Expression exprName = nullExpression;
		Expression exprText = nullExpression;
		Expression exprType = nullExpression;
		Expression exprColor = nullExpression;

		if (name != null && name.length() > 0) {
			exprName = Expr.ilike("suggestText", "%"+name+"%");
		}
		if (text != null && text.length() > 0) {
			exprText = Expr.ilike("text", "%"+text+"%");
		}
		if (type != null && type.length() > 0) {
			exprType = Expr.ilike("text", "%"+type+"%");
		}
		return find.where()
				.or(exprName,
			Expr.or(exprText,
			Expr.or(exprType,
					exprColor)))
				.findList();
	}

//	r: 66 terreno
//	r: 67 commum
//	r: 77 mitica
//	r: 82 rara
//	r: 84 especial (time spiral)
//	r: 85 incomum
}

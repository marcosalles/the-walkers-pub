package daos;

import java.util.List;

import models.CollectionCard;
import models.User;

import com.avaje.ebean.Ebean;

public class CollectionDao {
	
	
	/**
	 * @param String multiverseId
	 * Esse método recebe uma carta e procura 
	 * pessoas que tenham a carta e está marcada
	 * como possivel troca
	 * @return List<User>
	 */
	public static  List<User> owners(String card){
		List<User> owners = Ebean.find(User.class)
				.select("*")
				.fetch("collection")
				.where().and(com.avaje.ebean.Expr.eq("collection.magicCard.multiverseId", card),
						com.avaje.ebean.Expr.eq("collection.tradable", "true"))
				.findList();
		return owners;			
	}
	
	/**
	 * @param String userId
	 * Recebe o userId e pega a coleção dele
	 * @return List<CollectionCard>
	 */
	
	public static List<CollectionCard> userCollecion(long userId){
		return UserDao.userById(userId).getCollection();
	}

}
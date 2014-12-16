package daos;

import java.util.ArrayList;
import java.util.List;

import models.CollectionCard;
import models.User;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

public class CollectionDao {
	
	private static Finder<Long,User> find = new Finder<Long,User>(Long.class,User.class);

	public static  List<User> owners(String multiverseId){
		List<SqlRow> list = Ebean.createSqlQuery("select u.id from users as u join collection_card as cc on cc.user_id = u.id join card as c on c.id = cc.card_id where cc.tradable = true and c.multiverse_id = :id")
		.setParameter("id", multiverseId)
		.findList();
		List<Long> ownerIds = new ArrayList<Long>();
		for (SqlRow sqlRow : list) {
			Long id = sqlRow.getLong("id");
			ownerIds.add(id);
		}
		return UserDao.userByIds(ownerIds);	
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

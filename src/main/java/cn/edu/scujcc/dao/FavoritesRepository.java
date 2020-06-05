package cn.edu.scujcc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.model.Favorites;
import cn.edu.scujcc.model.User;

@Repository
public interface FavoritesRepository extends MongoRepository<Favorites, String>{

	public void deleteByUserIdAndBookId(String userId, String bookId);

	public Favorites findByUserId(String userId);

}

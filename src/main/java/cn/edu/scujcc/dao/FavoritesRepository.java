package cn.edu.scujcc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.edu.scujcc.model.Favorites;

public interface FavoritesRepository extends MongoRepository<Favorites, String> {
	
	public Favorites findByUserId(String userId);
	
	
}

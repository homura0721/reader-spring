package cn.edu.scujcc.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.BookRepository;
import cn.edu.scujcc.dao.FavoritesRepository;
import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Favorites;

@Service
public class FavoritesService {
	@Autowired
	private FavoritesRepository fRepo;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FavoritesService.class);

	/**
	 * 查找收藏夹
	 * @param userId
	 * @return
	 */
	public Favorites getFavorites(String userId) {
		return fRepo.findByUserId(userId);
	}
	
	
	
	/**
	 * 添加收藏夹
	 * @param f
	 * @return
	 */
	public Favorites addFavorites(Favorites f) {
		return fRepo.save(f);
	}

	
}

package cn.edu.scujcc.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.edu.scujcc.UserExistException;
import cn.edu.scujcc.api.BookController;
import cn.edu.scujcc.dao.UserRepository;
import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Favorite;
import cn.edu.scujcc.model.User;

@Service
public class UserService {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CacheManager cacheManager;
	
	
	
	
	/**
	 * 用户注册
	 * @param user	
	 * @return	保存后的用户信息（包括数据库id）
	 */
	public User register(User user) throws UserExistException {
		User result= null;
		//判断用户名是否已在数据库中存在
		User saved = userRepo.findFirstByUsername(user.getUsername());
		if (saved == null) {
			result = userRepo.save(user);
		} else {
			//用户已存在
			logger.error("用户"+user.getUsername()+"已存在。");
			throw new UserExistException();
		}
		return result;
	}
	/**
	 * 用户登录
	 * @param u
	 * @param p
	 * @return
	 */
	public User login(String username, String password) {
		User result = null;
		result = userRepo.findOneByUsernameAndPassword(username, password);
		return result;
	}
	
	/**
	 * 检查登录
	 * @param username
	 * @return
	 */
	public String checkIn(String username) {
		String uid = "";		
		Long ts = System.currentTimeMillis();
		username = username + ts;
		uid = DigestUtils.md5DigestAsHex(username.getBytes());		
		Cache cache = cacheManager.getCache(User.CACHE_NAME);
		cache.put(uid, username);
		return uid;
	}
	
	/**
	 * 通过唯一编号查询用户名
	 * @param token
	 * @return
	 */
	public String currentUser(String token) {
		Cache cache = cacheManager.getCache(User.CACHE_NAME);
		return cache.get(token, String.class);
	}

	

	
	/**
	 * 通过username查询
	 * @param username
	 * @return
	 */
	public User getUser(String realUsername) {
		User result = userRepo.findByUsername(realUsername);
		return result;
	}

	/**
	 * 添加收藏夹
	 * @param realUsername
	 * @param favorite
	 * @return
	 */
	public User addFavorite(String realUsername, Favorite favorite) {
		User saved = getUser(realUsername);
		if(saved != null) {
			saved.addFavorite(favorite);
			return userRepo.save(saved);
		}
		return null;
	}
}

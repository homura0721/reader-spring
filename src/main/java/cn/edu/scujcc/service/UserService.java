package cn.edu.scujcc.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import cn.edu.scujcc.UserExistException;
import cn.edu.scujcc.dao.BookRepository;
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
	private BookRepository bookRepo;
	@Autowired
	private BookService bookService;
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
	public User getUser(String username) {
		User result = userRepo.findByUsername(username);
		return result;
	}

	/**
	 * 根据favorite里的bookId，查询book的详细信息
	 * @param u
	 * @return
	 */
	@SuppressWarnings("null")
	public List<Book> getFavorite(User u ) {
		List<Book> favoriteList = new ArrayList<>();
		List<Favorite> f = u.getFavorite();
		//查询book
		Iterator<Favorite> iterator = f.iterator();
        while(iterator.hasNext()){
        	Favorite fa = iterator.next();
        	Book b = bookService.getBook(fa.bookId);
        	if(b != null) {
        		favoriteList.add(b);  
        	}
        }
        return favoriteList;
	}
	
	/**
	 * 添加收藏夹
	 * @param realUsername
	 * @param favorite
	 */
	public User addFavorite(String username, Favorite favorite) {
		User saved = getUser(username);
		if(saved != null) {
			saved.addFavorite(favorite);
			return userRepo.save(saved);
		}
		return saved;
	}

	/**
	 * 根据username和bookId，删除收藏夹
	 * @param username
	 * @param bookId
	 * @return
	 */
	public User deleteFavorite(String username, String bookId) {
		User u = getUser(username);
		List<Favorite> f = u.getFavorite();
		//迭代器删除bookId
		Iterator<Favorite> iterator = f.iterator();
        while(iterator.hasNext()){
        	Favorite fa = iterator.next();
            if(fa.getBookId().equals(bookId)){
                iterator.remove();
            }
        }
        userRepo.save(u);
		return u;
	}
	
}
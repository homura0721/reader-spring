package cn.edu.scujcc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.UserExistException;
import cn.edu.scujcc.dao.UserRepository;
import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Comment;
import cn.edu.scujcc.model.Favorite;
import cn.edu.scujcc.model.Result;
import cn.edu.scujcc.model.User;
import cn.edu.scujcc.service.BookService;
import cn.edu.scujcc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private CacheManager cacheManager;
	
	
	
	/**
	 * 用户注册
	 * @return
	 */
	@PostMapping("/register")
	public Result<User> register(@RequestBody User u) {
		Result<User> result = new Result<>();
		logger.debug("用户注册："+u);
		try {
			User saved = userService.register(u);
			result.setStatus(Result.STATUS_OK);
			result.setMessage("注册成功");
			result.setData(saved);
		} catch (UserExistException e) {
			logger.error("用户已存在，不能注册");			
			result.setStatus(Result.STATUS_ERROR);
			result.setMessage("用户已存在，不能注册");
		}
		return result;
	}
	
	@GetMapping("/login/{username}/{password}")
	public Result<String> login(@PathVariable("username") String username, @PathVariable("password") String password) {
		Result<String> result = new Result();
		User saved = userService.login(username, password);
		if (saved != null) {
			//登录成功
			String uid = userService.checkIn(username);
			result.setStatus(Result.STATUS_OK);
			result.setData(uid);
			result.setMessage("登录成功");
		}else {
			//登录失败
			logger.error("登录失败。");
			result.setStatus(Result.STATUS_ERROR);
			result.setMessage("账号或密码有误");
		}
		return result;
	}
	
	/**
	 * 查询收藏夹
	 * @param username
	 * @return                                             
	 */
	@GetMapping("/favroite/my/get")
	public List<Favorite> getFavorite(@RequestHeader("token") String token) {
		String username = userService.currentUser(token);
		String realUsername = username.substring(0, username.length()-13); //token里存的username多了后13位，减去
		User u = userService.getUser(realUsername);
		List<Favorite> f = u.getFavorite();
		return f;
	}

	/**
	 * 添加收藏夹
	 * @param username
	 * @param favorite
	 * @return
	 */
	@PostMapping("/favorite/my/add")
	public User addFavorite(@RequestHeader("token") String token, @RequestBody Favorite favorite) {
		User result = null;
		String username = userService.currentUser(token);
		String realUsername = username.substring(0,username.length()-13); //token里存的username多了后13位，减去
		result = userService.addFavorite(realUsername, favorite);
		return result;
	}
	
}
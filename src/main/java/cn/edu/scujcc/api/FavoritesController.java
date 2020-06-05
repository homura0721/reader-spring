package cn.edu.scujcc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Favorites;
import cn.edu.scujcc.model.Result;
import cn.edu.scujcc.service.FavoritesService;
import cn.edu.scujcc.service.UserService;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {
	@Autowired
	private FavoritesService favoritesService;
	private static final Logger logger = LoggerFactory.getLogger(FavoritesController.class);
	
	
	/**
	 * 通过userId，查找收藏夹
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public Result<Favorites> getBook(@PathVariable String userId) {
		logger.info("正在查找收藏夹，用户id："+userId);
		Result<Favorites> result = new Result<>();
		Favorites f = favoritesService.getFavorites(userId);
		if (f != null) {
			result = result.ok();
			result.setData(f);
		} else {
			logger.error("找不到收藏。");
			result = result.error();
			result.setMessage("找不到收藏。");
		}
		return result;
	}
	
	
	

	
}

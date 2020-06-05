package cn.edu.scujcc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Favorites;
import cn.edu.scujcc.model.Result;
import cn.edu.scujcc.service.BookService;
import cn.edu.scujcc.service.FavoritesService;

@RestController
@RequestMapping("/f/")
public class FavoritesController {
	@Autowired
	private FavoritesService fService;
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	
	/**
	 * 按照userId查找收藏夹
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public Result<Favorites> getFavorites(@PathVariable String userId) {
		logger.info("正在查看用户：" + userId + "的收藏夹");
		Result<Favorites> result = new Result<>();
		Favorites f = fService.getFavorites(userId);
		if (f != null) {
			result = result.ok();
			result.setData(f);
		} else {
			logger.error("找不到收藏夹。");
			result = result.error();
			result.setMessage("找不到收藏夹。");
		}
		return result;
	}
	
	/**
	 * 添加到收藏夹
	 * @param f
	 * @return
	 */
	@PostMapping
	public Result<Favorites> addFavorites(@RequestBody Favorites f) {
		logger.info("即将加入收藏夹，数据为：" + f);
		Result<Favorites> result = new Result<>();
		Favorites saved= fService.addFavorites(f);
		if (f != null) {
			result = result.ok();
			result.setData(saved);
		} else {
			result.setStatus(Result.STATUS_ERROR);
			result.setMessage("添加收藏夹失败");
		}
		return result;
	}
	
	/**
	 * 根据userId和bookId，删除收藏夹
	 * @param userId
	 * @param bookId
	 * @return
	 */
	@DeleteMapping("/{userId}/{bookId}")
	public Result<Favorites> detleteBook(@PathVariable String userId, @PathVariable String bookId) {
		logger.info("准备删除收藏夹");
		Result<Favorites> result = new Result<>();
		boolean del = fService.deleteFavorites(userId, bookId);
		if (del) {
			result = result.ok();
		} else {
			result.setStatus(Result.STATUS_ERROR);
			result.setMessage("删除失败");
		}
		return result;
	}
}

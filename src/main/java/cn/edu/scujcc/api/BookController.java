package cn.edu.scujcc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Comment;
import cn.edu.scujcc.model.Result;
import cn.edu.scujcc.model.User;
import cn.edu.scujcc.service.BookService;
import cn.edu.scujcc.service.UserService;


@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@GetMapping
	public  Result<List<Book>> getAllBooks() {
		logger.info("正在读取所有书籍信息!!!");
		Result<List<Book>> result = new Result<List<Book>>();
		List<Book> books = bookService.getAllBooks();
		result = result.ok();
		result.setData(books);
		logger.debug("所有书籍的数量是"+books.size());
		return result;
	}
	
	
	/**
	 * 查询指定id书籍
	 * @param id
	 * @return
	 */
	@GetMapping("/{bookId}")
	public Result<Book> getBook(@PathVariable String bookId) {
		logger.info("正在读取书籍：" + bookId);
		Result<Book> result = new Result<>();
		Book b = bookService.getBook(bookId);
		if (b != null) {
			result = result.ok();
			result.setData(b);
		} else {
			logger.error("找不到书籍。");
			result = result.error();
			result.setMessage("找不到书籍。");
		}
		return result;
	}
	
	
	/**
	 * 删除书籍
	 * @return
	 */
	@DeleteMapping("/{bookId}")
	public Result<Book> detleteBook(@PathVariable String bookId) {
		logger.info("即将删除书籍，bookId="+bookId);
		Result<Book> result = new Result<>();
		boolean del = bookService.deleteBook(bookId);
		if (del) {
			result = result.ok();
		} else {
			result.setStatus(Result.STATUS_ERROR);
			result.setMessage("删除失败");
		}
		return result;
	}
	
	
	/**
	 * 新增书籍
	 * @return
	 */
	@PostMapping
	public Result<Book> createBook(@RequestBody Book b) {
		logger.info("即将新增书籍，书籍数据：" + b);
		Result<Book> result = new Result<>();
		Book saved= bookService.createBook(b);
		if (b != null) {
			result = result.ok();
			result.setData(saved);
		} else {
			result.setStatus(Result.STATUS_ERROR);
			result.setMessage("新增失败");
		}
		return result;
	}
	
	
	/**
	 * 修改书籍
	 * @return
	 */
	@PutMapping
	public Result<Book> updateBook(@RequestBody Book b) {
		logger.debug("即将更新书籍：书籍数据：" + b);
		Result<Book> result = new Result<>();
		Book updated = bookService.updateBook(b);
		result = result.ok();
		result.setData(updated);
		return result;
	}
	
	
	/**
	 * 查询title、author、tag1、tag2、tag3
	 * @param s
	 * @return
	 */
	@GetMapping("/s/")
		public List<Book> search(@RequestParam String keyword){
			return bookService.search(keyword);
		}
	
	
	/**
	 * 添加评论
	 * @param token
	 * @param booklId
	 * @param comment
	 * @return
	 */
	@PostMapping("/{bookId}/comment")
	public Book addComment(@RequestHeader("token") String token, @PathVariable String bookId, @RequestBody Comment comment) {
		Book result = null;
		String us = userService.currentUser(token);
		String username = us.substring(0,us.length()-13); //token里存的username多了后13位，减去
		User u = userService.getUser(username);
		String nickname = u.getNickname(); 
		comment.setAuthor(nickname); //评论用nickname保存
		logger.debug(username + "即将评论书籍" + bookId+ "评论对象：" + comment);
		//保存评论
		result = bookService.addComment(bookId, comment);
		return result;
	}
	
}
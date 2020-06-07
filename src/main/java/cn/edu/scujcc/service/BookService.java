package cn.edu.scujcc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.BookRepository;
import cn.edu.scujcc.model.Book;
import cn.edu.scujcc.model.Comment;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BookService.class);
	
	
	public List<Book> getAllBooks() {
		logger.debug("从数据库读取所有书籍信息");
		return bookRepo.findAll();
	}
	
	/**
	 * 查询一本书籍
	 * @param bookId
	 * @return
	 */
	public Book getBook(String bookId) {
		Optional<Book> result = bookRepo.findById(bookId);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	/**
	 * 删除一本书籍
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(String bookId) {
		boolean result = true;
		bookRepo.deleteById(bookId);
		return result;
	}
	
	/**
	 * 新增一本书籍
	 * @param b
	 * @return
	 */
	public Book createBook(Book b) {
		return bookRepo.save(b);
	}
	
	/**
	 * 更新一本书籍
	 * @param b
	 * @return
	 */
	public Book updateBook(Book b) {
		Book saved = getBook(b.getBookId());
		if (saved != null) {
			if(b.getTitle() !=null) {
				saved.setTitle(b.getTitle());
			}
			if(b.getAuthor() !=null) {
				saved.setAuthor(b.getAuthor());
			}
			if(b.getBody() !=null) {
				saved.setBody(b.getBody());
			}
			if(b.getCover() !=null) {
				saved.setCover(b.getCover());
			}
			if(b.getTag1() !=null) {
				saved.setTag1(b.getTag1());
			}
			if(b.getTag2() !=null) {
				saved.setTag2(b.getTag2());
			}
			if(b.getTag3() !=null) {
				saved.setTag3(b.getTag3());
			}
			if(b.getBlurb() !=null) {
				saved.setBlurb(b.getBlurb());
			}
		}
		return bookRepo.save(saved);
	}
	
	
	/**
	 * 用BookController的search()传来的s在title、author、tag1、tag2、tag3里查询
	 * @param search
	 * @return
	 */
	public List<Book> search(String s){
		List<Book> t = bookRepo.findByTitleLike(s);    //用s在 title 里查到的
		t.addAll(bookRepo.findByAuthorLike(s));        //用s在 author 里查到的	 
		t.addAll(bookRepo.findByTag1(s));          //用s在 tag1 里查到的	
		t.addAll(bookRepo.findByTag2(s));          //用s在 tag2 里查到的 
		t.addAll(bookRepo.findByTag3(s));          //用s在 tag3 里查到的
		return t;
	}
	
	   
	/**
	 * 在book中添加评论
	 * @param bookId
	 * @param comment
	 * @return
	 */
	public Book addComment(String bookId, Comment comment) {
		Book saved = getBook(bookId);
		if(saved != null) {
			saved.addComment(comment);
			return bookRepo.save(saved);
		}
		return null;
	}
	
}
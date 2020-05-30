package cn.edu.scujcc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.BookRepository;
import cn.edu.scujcc.model.Book;


@Service
public class BookService {
	@Autowired
	private BookRepository repo;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BookService.class);
	
	
	public List<Book> getAllBooks() {
		logger.debug("从数据库读取所有书籍信息");
		return repo.findAll();
	}
	
	/**
	 * 查询一本书籍
	 * @param bookId
	 * @return
	 */
	public Book getBook(String bookId) {
		Optional<Book> result = repo.findById(bookId);
		
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
		repo.deleteById(bookId);
		return result;
	}
	
	/**
	 * 新增一本书籍
	 * @param b
	 * @return
	 */
	public Book createBook(Book b) {
		return repo.save(b);
	}
	
	/**
	 * 更新一本书籍
	 * @param b
	 * @return
	 */
	public Book updateBook(Book b) {
		Book saved = getBook(b.getId());
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
		return repo.save(saved);
	}
	
	/**
	 * 搜索title、author、tag1、tag2、tag3
	 * @param title
	 * @param author
	 * @param tag1
	 * @param tag2
	 * @param tag3
	 * @return
	 */
	
	
}

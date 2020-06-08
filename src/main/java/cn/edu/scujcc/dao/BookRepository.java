package cn.edu.scujcc.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cn.edu.scujcc.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{	
	//Like查询title、author、tag1、tag2、tag3
	List<Book> findByTitleLike(String title);
	List<Book> findByAuthorLike(String author);
	
	List<Book> findByTag1Like(String tag1);		//
	List<Book> findByTag2Like(String tag2);		//	
	List<Book> findByTag3Like(String tag3);		//
	
	List<Book> findByBookId(String bookId);	
	
	//List<Book> findByTitleAndAuthorLike(String title, String author);
	
	//List<Book> findByTitleAndAuthorLike(String title, String author);
}
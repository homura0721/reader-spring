package cn.edu.scujcc.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cn.edu.scujcc.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{
	
}

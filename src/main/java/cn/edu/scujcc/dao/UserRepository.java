package cn.edu.scujcc.dao;


import org.springframework.data.mongodb.repository.MongoRepository;


import cn.edu.scujcc.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	
	
	public User findFirstByUsername(String username);
	
	
	public User findOneByUsernameAndPassword(String username, String password);

	
	
	


}

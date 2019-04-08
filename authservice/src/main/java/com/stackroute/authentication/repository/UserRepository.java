package com.stackroute.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stackroute.authentication.model.User;


public interface UserRepository extends JpaRepository<User, String> {

//	@Query("Select u from User u where userId = (?1) and password = (?2)")
//	User validate(String userId, String password);

	 User findByUserIdAndPassword(String userId,String password);
}

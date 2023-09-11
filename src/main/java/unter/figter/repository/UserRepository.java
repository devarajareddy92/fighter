package unter.figter.repository;


import org.springframework.data.repository.CrudRepository;

import unter.figter.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Iterable<User> findAll();
}

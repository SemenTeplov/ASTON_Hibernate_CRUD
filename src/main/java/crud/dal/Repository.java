package crud.dal;

import crud.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<User, Integer> {}

package com.hshop.dao.repository;


import com.hshop.dao.model.UserEntity;
import com.hshop.dto.LoginDTO;
import com.hshop.dto.UserDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
  @Query(
      value = " select u.* from user_ u "
          + " where true "
          + " and ( u.name like concat('%',:#{#dto.name},'%') or :#{#dto.name} is null ) "
          + " and ( u.phone like concat('%',:#{#dto.phone},'%') or :#{#dto.phone} is null ) "
          + " and ( u.address like concat('%',:#{#dto.address},'%') or :#{#dto.address} is null ) "
          + " and ( u.email like concat('%',:#{#dto.email},'%') or :#{#dto.email} is null ) ",
      nativeQuery = true
  )
  List<UserEntity> search(UserDTO dto);

  @Query(
      value = " select u.* from user_ u "
          + " where true "
          + " and ( u.name = :#{#dto.username} or :#{#dto.username} is null ) "
          + " and ( u.phone = :#{#dto.phone} or :#{#dto.phone} is null ) "
          + " and ( u.email = :#{#dto.email} or :#{#dto.email} is null ) ",
      nativeQuery = true
  )
  UserEntity getUser(LoginDTO dto);
}

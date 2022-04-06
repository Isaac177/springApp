package kz.narxoz.springapp;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //Task 2
    List<User> findByNameStartsWith(String name, Pageable pageable);

    //Task 1
    List<User> findByEmailEndingWith(String email);

    List<User> findByEmailContainingOrderByNameDesc(String email);

    //Task 3
    List<User> findBySurnameContaining(String surname);

    //Task 7
    List<User> findAllByEmailNotContaining(String email);

    //Task 5
    //@Query(value = "select name from users", nativeQuery = true)
    List<User> findDistinctByNameContaining(String name, Pageable firstPageWithTwoElements);

    //Task 8
    /*@Query(value = "select * from users where name = surname", nativeQuery = true)
    List<User> findAllByNameEqualsSurname(String name, String surname);*/

    //Task 9
    @Query(value = "SELECT * FROM users where email like '%narxoz.kz' or email like '%gmail.com' or email like '%yandex.ru'", nativeQuery = true)
    List<User> findAllByEmailLike(String email);

    //Task 6
    @Query(value = "select * from users order by name desc", nativeQuery = true)
    List<User> findAllByOrderByNameDescSort(String name);

    //Task 4
    @Query(value = "select * from users order by id desc", nativeQuery = true)
    List<User> findAllByOrderByIdDesc(Long id, Pageable firstPageWithTwoElements);

    @Query(value = "select * from users order by id", nativeQuery = true)
    List<User> findAllByIdOrderById(Long id);

    // Native Query
    @Query(value = "select * from users order by name", nativeQuery = true)
    List<User> findAllSorted();

    @Query(value = "select distinct on (name) * from users", nativeQuery = true)
    List<User> findDistinctByName();

    @Query(value = "select * from users where id > :qid", nativeQuery = true)
    List<User> findByGreaterId(Long qid);

}

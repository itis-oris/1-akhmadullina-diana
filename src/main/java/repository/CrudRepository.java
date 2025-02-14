package repository;

import java.util.List;


public interface CrudRepository<T, ID> {

    boolean add(T type);

    List<T> getAll();

    boolean update(T type);

    boolean delete(ID id);

}

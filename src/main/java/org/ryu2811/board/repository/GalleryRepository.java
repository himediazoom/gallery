package org.ryu2811.board.repository;

import org.ryu2811.board.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Integer> {
}
/*
  <S extends T> S save(S entity);
  <S extends T> Iterable<S> saveAll(Iterable<S> entities);
  Optional<T> findById(ID id);
  boolean existsById(ID id);
  Iterable<T> findAll();
  Iterable<T> findAllById(Iterable<ID> ids);
  long count();
  void deleteById(ID id);
  void delete(T entity);
  void deleteAllById(Iterable<? extends ID> ids);
  void deleteAll(Iterable<? extends T> entities);
  void deleteAll();
 */

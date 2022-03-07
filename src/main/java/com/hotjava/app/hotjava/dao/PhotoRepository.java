package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Photo;
import org.springframework.data.repository.CrudRepository;


public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}

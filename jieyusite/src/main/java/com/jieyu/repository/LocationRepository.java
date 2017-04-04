package com.jieyu.repository;

import com.jieyu.model.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by orangeclk on 16-4-22.
 */
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Location findByName(String name);
}
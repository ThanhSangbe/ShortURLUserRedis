package com.pts.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface URLRepository extends CrudRepository<URL, String> {

}

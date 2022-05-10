package com.vole.voletwitterclone.repository;


import com.vole.voletwitterclone.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TweetRepository extends MongoRepository<Tweet, String> {

    Page<Tweet> findByUsername(Pageable pageable, String username);

}

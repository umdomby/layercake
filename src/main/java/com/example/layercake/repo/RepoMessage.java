package com.example.layercake.repo;

import com.example.layercake.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface RepoMessage extends CrudRepository<Message, Long> {

//    List<Message> findByTag(String tag);

}

package com.adminportal.dao;

import com.adminportal.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Long> {
}

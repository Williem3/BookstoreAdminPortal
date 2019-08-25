package com.adminportal.service.serviceimpl;

import com.adminportal.dao.BookDAO;
import com.adminportal.entity.Book;
import com.adminportal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book save(Book book) {
        return bookDAO.save(book);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>)bookDAO.findAll();
    }

    @Override
    public Book findOne(Long id) {
        return bookDAO.findById(id).orElse(null);
    }
}

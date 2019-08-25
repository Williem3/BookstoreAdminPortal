package com.adminportal.controller;

import com.adminportal.entity.Book;
import com.adminportal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String addBook(Model theModel) {
        Book book = new Book();

        theModel.addAttribute("book", book);

        return "addBook";
    }

    @PostMapping("/add")
    public String addBookPOST(@ModelAttribute("book") Book book, HttpServletRequest request) {
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();

        try {
            byte[] bytes = bookImage.getBytes();
            String name = book.getId()+".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+name)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:bookList";
    }

    @GetMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id, Model theModel) {
        Book book = bookService.findOne(id);

        theModel.addAttribute("book", book);

        return "bookInfo";
    }

    @GetMapping("/updateBook")
    public String updateBook(@RequestParam("id") Long id, Model theModel) {
        Book book = bookService.findOne(id);

        theModel.addAttribute("book", book);

        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
        bookService.save(book);

        MultipartFile bookImage = book.getBookImage();

        if (!bookImage.isEmpty()) {
            try {
                byte[] bytes = bookImage.getBytes();
                String name = book.getId()+".png";

                Files.delete(Paths.get("src/main/resources/static/image/book"+name));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book"+name)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/book/bookInfo?id="+book.getId();
    }

    @GetMapping("/bookList")
    public String bookList(Model model) {
        List<Book> bookList = bookService.findAll();

        model.addAttribute("bookList", bookList);

        return "bookList";
    }

}

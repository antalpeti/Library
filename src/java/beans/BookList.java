/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import pojos.Book;

/**
 *
 * @author Peti
 */
@ManagedBean
@RequestScoped
public class BookList {
    
    private List<Book> bookList;

    /**
     * Creates a new instance of BookList
     */
    public BookList() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        bookList = session.createQuery("FROM Book").list();
        session.close();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}

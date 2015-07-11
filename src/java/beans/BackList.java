/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Book;

/**
 *
 * @author Peti
 */
@ManagedBean
@RequestScoped
public class BackList {

    private List<Book> backList;

    /**
     * Creates a new instance of BookList
     */
    public BackList() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        backList = session.createQuery("FROM Book WHERE memberid IS NOT NULL").list();
        session.close();
    }

    public void back(Book book) {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        book.setMember(null);
        System.out.println(book + " member id = " + book.getMember());
        session.update(book);
        session.close();
    }

    public List<Book> getBackList() {
        return backList;
    }

    public void setBackList(List<Book> backList) {
        this.backList = backList;
    }
}

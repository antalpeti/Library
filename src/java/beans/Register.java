/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import pojos.Book;
import pojos.Member;

/**
 *
 * @author Peti
 */
@ManagedBean
@RequestScoped
public class Register {

    private Member member;
    private Book  book;
    private String error = "";
    
    @ManagedProperty(value="#{lend}")
    private Lend lend;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        member = new Member();
        book = new Book();
    }
    
    public String registerNewMember(){
        if (member.getName() != null && !member.getName().isEmpty() && member.getEmail()!= null && !member.getEmail().isEmpty()) {
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(member);
            session.getTransaction().commit();
            session.close();
            lend.fillMember();
            return "memberregister2lend";
        } else {
            error = "Fill the name and email fields!";
            return "";
        }
    }
    
    public String registerNewBook(){
        if (book.getAuthor() != null && !book.getAuthor().isEmpty() && book.getTitle()!= null && !book.getTitle().isEmpty() && 
                book.getPages() != 0) {
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            session.close();
            return "bookregister2index";
        } else {
            error = "Fill the author, title and pages fields!";
            return "";
        }
    }
    
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Lend getLend() {
        return lend;
    }

    public void setLend(Lend lend) {
        this.lend = lend;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

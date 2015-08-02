/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import pojos.Book;
import pojos.Member;

/**
 *
 * @author Peti
 */
@ManagedBean
@SessionScoped
public class Lend {

    private List<Book> lendList;
    private List<Member> memberList;
    private int actMemberId;
    private Member actMember;
    private String actMemberText = "";

    @ManagedProperty(value = "#{bookList}")
    private BookList bookList;

    /**
     * Creates a new instance of Lend
     */
    public Lend() {
        lendList = new ArrayList<Book>();
        fillMember();
    }

    public void fillMember() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        memberList = session.createQuery("FROM Member").list();
        session.close();
    }

    public String put(Book book) {
        lendList.add(book);
        bookList.getBookList().remove(book);
        book.setAvailable(false);

        return "index2lend";
    }

    public void remove(Book book) {
        book.setAvailable(true);
        bookList.getBookList().add(book);
        lendList.remove(book);
    }

    public void selectMember() {
        for (Member member : memberList) {
            if (actMemberId == member.getId()) {
                actMember = member;
                break;
            }
        }
        actMemberText = "Selected member: " + actMember;
    }

    public void lend() {
        if (actMember != null) {
            Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Book book : lendList) {
                book.setMember(actMember);
                session.update(book);
            }
            session.getTransaction().commit();
            session.close();
            lendList.clear();
        } else {
            actMemberText = "Select a member!!!";
        }
    }

    public List<Book> getLendList() {
        return lendList;
    }

    public void setLendList(List<Book> lendList) {
        this.lendList = lendList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public String getActMemberText() {
        return actMemberText;
    }

    public void setActMemberText(String actMemberText) {
        this.actMemberText = actMemberText;
    }

    public int getActMemberId() {
        return actMemberId;
    }

    public void setActMemberId(int actMemberId) {
        this.actMemberId = actMemberId;
    }

    public BookList getBookList() {
        return bookList;
    }

    public void setBookList(BookList bookList) {
        this.bookList = bookList;
    }
}

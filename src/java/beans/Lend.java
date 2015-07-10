/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
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

    /**
     * Creates a new instance of Lend
     */
    public Lend() {
        lendList = new ArrayList<Book>();
        fillMember();
    }

    private void fillMember() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        memberList = session.createQuery("FROM Member").list();
        session.close();
    }

    public String put(Book book) {
        if (!lendList.contains(book)) {
            lendList.add(book);
        }

        return "index2lend";
    }
    
    public void remove(Book book) {
        lendList.remove(book);
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

}
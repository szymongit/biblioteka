package pl.altkom.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.altkom.model.BookData;

@Stateless
public class JpaBookDataDAO implements BookDataDAO {

    @PersistenceContext (unitName = "cw2PU")
    private EntityManager em;

    @Override
    public void saveBookData(BookData bookData) {
        em.persist(bookData);
    }

    @Override
    public List<BookData> getBooksData() {
        return em.createQuery("select p from BookData p", BookData.class).getResultList();
    }

    @Override
    public void deleteBookData(int id) {
        BookData bookData = em.find(BookData.class, id);
        if (bookData != null) {
            em.remove(bookData);
        }
    }

    @Override
    public void updateBookData(BookData bookData) {
        em.merge(bookData);
    }

    @Override
    public BookData getBookData(int id) {
        return em.createQuery("select p from BookData p where p.id=:id", BookData.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<BookData> getBooksData(String byTitle, String byAuthor, String byCategory, 
            String sortBy) {
        String queryString = "select p from BookData p";
        String firstNameConstraint = " lower(p.title) like :t";
        String lastNameConstraint = " and lower(p.category) like :c";
        String authorConstraint = " and lower(p.author) like :a";
        String sortConstraint = " order by p." + sortBy;
        String query = queryString + " where " + firstNameConstraint 
                + lastNameConstraint + authorConstraint + sortConstraint;

        return em.createQuery(query, BookData.class)
                .setParameter("t", byTitle.toLowerCase() + "%")
                .setParameter("c", byCategory.toLowerCase() + "%")
                .setParameter("a", byAuthor.toLowerCase() + "%")
                .getResultList();
    }
}
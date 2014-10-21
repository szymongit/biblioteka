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
        return em.createQuery("select b from BookData b", BookData.class).getResultList();
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
        return em.createQuery("select b from BookData b where b.id=:id", BookData.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<BookData> getBooksData(String byAuthor, String byTitle, String byCategory, 
            String sortBy) {
        String queryString = "select b from BookData b";
        String authorConstraint = " lower(b.author) like :a";
        String titleConstraint = " and lower(b.title) like :t";
        String categoryConstraint = " and lower(b.category) like :c";
        String sortConstraint = " order by b." + sortBy;
        String query = queryString + " where " + authorConstraint 
                + titleConstraint + categoryConstraint + sortConstraint;

        return em.createQuery(query, BookData.class)
                .setParameter("a", "%" + byAuthor.toLowerCase() + "%")
                .setParameter("t", "%" + byTitle.toLowerCase() + "%")
                .setParameter("c", byCategory.toLowerCase() + "%")
                .getResultList();
    }
}
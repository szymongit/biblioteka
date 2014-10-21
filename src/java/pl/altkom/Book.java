package pl.altkom;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.altkom.dao.BookDataDAO;
import pl.altkom.model.BookData;

@ViewScoped
@ManagedBean(name = "bean")
public class Book implements Serializable {

    private BookData bookData = new BookData();

    @EJB
    private BookDataDAO dao;

    private int editedId;
    private String sortBy = "title";
    private String order = "asc";
    private String byTitle = "";
    private String byAuthor = "";
    private String byCategory = "";

    public BookData getBookData() {
        return bookData;
    }

    public void setBookData(BookData bookData) {
        this.bookData = bookData;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getByTitle() {
        return byTitle;
    }

    public void setByTitle(String byTitle) {
        this.byTitle = byTitle;
    }

    public String getByAuthor() {
        return byAuthor;
    }

    public void setByAuthor(String byAuthor) {
        this.byAuthor = byAuthor;
    }

    public String getByCategory() {
        return byCategory;
    }

    public void setByCategory(String byCategory) {
        this.byCategory = byCategory;
    }

    public int getEditedId() {
        return editedId;
    }

    public void setEditedId(int editedId) {
        this.editedId = editedId;
    }

    public String saveBookData() {
        // zapis do bazy
        dao.saveBookData(bookData);
        return "main";
    }

    public String deleteBookData(int id) {
        dao.deleteBookData(id);
        return "manage";
    }

    public void editBookData(BookData bookData) {
        editedId = bookData.getId();
        this.bookData = bookData;
    }

    public String updateBookData() {
        dao.updateBookData(bookData);
        return "manage";
    }

    public List<BookData> getBooksData() {
        return dao.getBooksData(byTitle, byAuthor, byCategory, sortBy + " " + order);
    }
}
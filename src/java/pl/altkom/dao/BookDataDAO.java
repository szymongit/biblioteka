package pl.altkom.dao;

import java.util.List;
import javax.ejb.Local;
import pl.altkom.model.BookData;

@Local
public interface BookDataDAO {

    public void saveBookData(BookData bookData);
    public List<BookData> getBooksData();
    public List<BookData> getBooksData(String byTitle, String byCategory,
            String sortBy);
    public void deleteBookData(int id);
    public void updateBookData(BookData bookData);
    public BookData getBookData(int id);
}
package by.art.gallery;

import by.art.gallery.repository.AuthorDAO;
import by.art.gallery.repository.DAOFactory;
import by.art.gallery.repository.entity.Author;
import by.art.gallery.repository.exception.DAOException;

import java.util.List;

public class App {

    public static void main(String[] args) {

            AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();

            try {
                List<Author> authors = authorDAO.getAuthorForSelect();
                authors.forEach(System.out::println);
            } catch (DAOException e) {
                e.printStackTrace();
            }




    }
}

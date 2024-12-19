package ee.ivkhkdev.nptv23libraryjpa.services;

import ee.ivkhkdev.nptv23libraryjpa.entity.Author;
import ee.ivkhkdev.nptv23libraryjpa.helpers.AuthorHelperImplementation;
import ee.ivkhkdev.nptv23libraryjpa.interfaces.AppHelper;
import ee.ivkhkdev.nptv23libraryjpa.interfaces.AppService;
import ee.ivkhkdev.nptv23libraryjpa.repository.AuthorRepository;
import ee.ivkhkdev.nptv23libraryjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService implements AppService<Author> {

    @Autowired
    private AppHelper<Author> authorAppHelper;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorHelperImplementation authorHelperImplementation;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean add() {
        Optional<Author> optionalAuthor = authorAppHelper.create();
        if (optionalAuthor.isEmpty()) {
            return false;
        }
        Author author = optionalAuthor.get();
        authorRepository.save(author);
        return true;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }

    @Override
    public boolean changeAvailability() {
        Long authorId = authorHelperImplementation.findIdEntityForChangeAvailability(authorRepository.findAll());
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty()) {
            return false;
        }
        Author author = optionalAuthor.get();
        author.setAvailable(false);
        authorRepository.save(author);
        return true;
    }

    @Override
    public boolean print() {
         return authorAppHelper.printList(authorRepository.findAll());
    }


}

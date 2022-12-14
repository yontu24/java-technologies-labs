package ro.uaic.info.documents.submission.documentssubmisson.repo;

import java.util.List;

public interface AbstractRepository<T> {
    T get(String id);
    void save(T t);
    void delete(T t);
    void update(T t);
    List<T> getData(String author);
}

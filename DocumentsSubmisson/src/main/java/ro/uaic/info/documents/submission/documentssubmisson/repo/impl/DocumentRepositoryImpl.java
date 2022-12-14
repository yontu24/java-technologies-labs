package ro.uaic.info.documents.submission.documentssubmisson.repo.impl;

import ro.uaic.info.documents.submission.documentssubmisson.entities.DocumentEntity;
import ro.uaic.info.documents.submission.documentssubmisson.repo.DocumentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class DocumentRepositoryImpl implements DocumentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DocumentEntity get(String id) {
        return entityManager.find(DocumentEntity.class, id);
    }

    @Override
    public void save(DocumentEntity documentEntity) {
        entityManager.persist(documentEntity);
    }

    @Override
    public void delete(DocumentEntity documentEntity) {
        entityManager.remove(documentEntity);
    }

    @Override
    public void update(DocumentEntity documentEntity) {
        entityManager.merge(documentEntity);
    }

    @Override
    public List<DocumentEntity> getData(String author) {
        TypedQuery<DocumentEntity> query = entityManager.createNamedQuery("DocumentEntity.findAll", DocumentEntity.class);
        query.setParameter("author", author);
        return query.getResultList();
    }
}

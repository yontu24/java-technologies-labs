package ro.uaic.info.documents.submission.documentssubmisson.controllers.rest.resources;

import ro.uaic.info.documents.submission.documentssubmisson.entities.DocumentEntity;
import ro.uaic.info.documents.submission.documentssubmisson.models.Document;
import ro.uaic.info.documents.submission.documentssubmisson.services.SubmissionService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/submission")
@RequestScoped
public class SubmissionResource {

    @Inject
    private SubmissionService<Document, DocumentEntity> submissionService;

    @GET
    @Path("/document/{documentId}")
    @Produces(MediaType.TEXT_PLAIN)
    @DenyAll
    public Response showDocument(@PathParam(("documentId")) String documentId) {
        DocumentEntity documentEntity = submissionService.getDocument(documentId);
        return Response.status(Response.Status.OK).entity("Found " + documentEntity).build();
    }

    @POST
    @Path("document")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("user")
    public Response addDocument(Document document) {
        submissionService.addDocument(document);
        return Response.status(Response.Status.CREATED).entity("Document " + document + " has been added.").build();
    }

    @PUT
    @Path("document")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("user")
    public Response updateDocument(Document document) {
        submissionService.replaceDocument(document);
        return Response.status(Response.Status.OK).entity("Document " + document + " has been modified.").build();
    }

    @DELETE
    @Path("document/{documentId}")
    @Produces(MediaType.TEXT_PLAIN)
//    @Transactional
    @RolesAllowed("admin")
    public Response deleteDocument(@PathParam("documentId") String documentId) {
        submissionService.deleteDocument(documentId);
        return Response.status(Response.Status.OK).entity("Document " + documentId + " has been deleted.").build();
    }

    @GET
    @Path("documents/{author}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response getAllDocuments(@PathParam("author") String author) {
        List<DocumentEntity> documentList = submissionService.getDocumentsUploadedByUser(author);
        return Response.status(Response.Status.OK).entity(documentList).build();
    }
}

package webserver667.requests;

import java.net.Socket;
import java.nio.file.Path;

import webserver667.configuration.MimeTypes;
import webserver667.logging.Logger;
import webserver667.responses.IResource;
import webserver667.responses.Resource;
import webserver667.responses.writers.ResponseWriter;
import webserver667.responses.writers.ResponseWriterFactory;


public class RequestHandler implements Runnable {

    private Socket socket;
    private MimeTypes mimeTypes;
    private Path docRootPath;


    public RequestHandler(Socket socket, MimeTypes mimeTypes, Path path) {

        this.socket = socket;
        this.mimeTypes = mimeTypes;
        this.docRootPath = path;

    }

    @Override
    public void run() {
        try {
            RequestReader requestReader = new RequestReader(socket.getInputStream());
            // public Resource(String uri, String queryString, String documentRoot, MimeTypes mimeTypes)
            // mimeTypes from the request first line (GET /index.html HTTP/1.1)
            IResource resource = new Resource(requestReader.getRequest().getURI(), requestReader.getRequest().getQueryString(), this.docRootPath.toString() , this.mimeTypes);
            ResponseWriter responseWriter = ResponseWriterFactory.create(socket.getOutputStream(), resource, requestReader.getRequest());
            responseWriter.write();
            int statusCode = responseWriter.getHttpResponse().getStatusCode().getCode();
            int contentLength = Integer.parseInt(responseWriter.getHttpResponse().getHeader("Content-Length"));
            System.out.println(Logger.getLogString(socket.getInetAddress().toString(), requestReader.getRequest(), statusCode, contentLength));
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



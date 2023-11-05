package webserver667.responses.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import webserver667.requests.HttpMethods;
import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class OkResponseWriter extends ResponseWriter {

    private String scriptBody;


    public OkResponseWriter(OutputStream out, IResource testResource, HttpRequest httpRequest) {
        super(out, testResource);
        this.scriptBody = null;
    }

 

    @Override
    public void write() {
        try {

            HttpResponse httpResponse = null;

            String filePath = super.getResource().getPath().toString();
            Path path = Paths.get(filePath);

            byte[] fileContent = Files.readAllBytes(path);

            httpResponse = new HttpResponse(HttpResponseCode.OK, "HTTP/1.1");
            // get the content type from the mime types
            String contentType = super.getResource().getMimeType();
            httpResponse.addHeader("Content-Type: " + contentType);
            httpResponse.addHeader("Content-Length: " + fileContent.length);
            super.getOutputStream().write(httpResponse.toString().getBytes());
            super.getOutputStream().write(fileContent);

            super.getOutputStream().flush();
            super.setHttpResponse(httpResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package webserver667.responses.writers;

import java.io.IOException;
import java.io.OutputStream;

import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class CreatedResponseWriter extends ResponseWriter {

    public CreatedResponseWriter(OutputStream out, IResource testResource, HttpRequest httpRequest) {
        super(out, testResource);
    }

    @Override
    public void write() {
        try {
            HttpResponse httpResponse = new HttpResponse(HttpResponseCode.CREATED, "HTTP/1.1");
            httpResponse.addHeader("Content-Type: text/html");

            super.getOutputStream().write(httpResponse.toString().getBytes());
            super.getOutputStream().flush();
            super.setHttpResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

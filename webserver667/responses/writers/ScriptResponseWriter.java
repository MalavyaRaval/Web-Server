package webserver667.responses.writers;

import java.io.IOException;
import java.io.OutputStream;
import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class ScriptResponseWriter extends ResponseWriter {

    private String scriptBody;

    public ScriptResponseWriter(OutputStream out, IResource testResource, HttpRequest request) {
        super(out, testResource);
        this.scriptBody = "";
    }

    public ScriptResponseWriter(OutputStream out, IResource testResource, HttpRequest request, String scriptBody) {
       this(out, testResource, request);
        this.scriptBody = scriptBody;

    }

    @Override
    public void write() {

        try {

            HttpResponse httpResponse = null;

            httpResponse = new HttpResponse(HttpResponseCode.OK, "HTTP/1.1");
            // get the content type from the mime types
            String contentType = super.getResource().getMimeType();
            httpResponse.addHeader("Content-Type: " + contentType);
            httpResponse.addHeader("Content-Length: " + scriptBody.length());
            super.getOutputStream().write(httpResponse.toString().getBytes());
            super.getOutputStream().write(scriptBody.getBytes());

            super.getOutputStream().flush();
            super.setHttpResponse(httpResponse);

        } catch (

        IOException e) {

            e.printStackTrace();
        }
    }
}

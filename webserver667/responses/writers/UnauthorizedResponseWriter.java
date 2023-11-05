package webserver667.responses.writers;

import java.io.OutputStream;
import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class UnauthorizedResponseWriter extends ResponseWriter {

    public UnauthorizedResponseWriter(OutputStream out, IResource testResource, HttpRequest request) {
        super(out, testResource);
    }

    @Override
    public void write() {

        try {
            HttpResponse httpResponse = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "HTTP/1.1");
            httpResponse.addHeader("Content-Type: text/html");
            httpResponse.addHeader("Content-Length: 0");
            httpResponse.addHeader("WWW-Authenticate: Basic realm=\"667 Server\"");

            super.getOutputStream().write(httpResponse.toString().getBytes());

            super.getOutputStream().flush();
            super.setHttpResponse(httpResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

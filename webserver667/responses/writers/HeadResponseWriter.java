package webserver667.responses.writers;

import java.io.OutputStream;

import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class HeadResponseWriter extends ResponseWriter {

    public HeadResponseWriter(OutputStream out, IResource testResource, HttpRequest httpRequest) {
        super(out, testResource);
    }

    @Override
    public void write() {

        /*
         * Your server must handle HEAD requests for a static document. The server will
         * attempt to
         * find the requested file and:
         * a. If the file exists, the server must respond to the client with a valid
         * HTTP success
         * response that does not include the file in the body of the response
         */

        try {

            HttpResponse httpResponse = new HttpResponse(HttpResponseCode.OK, "HTTP/1.1");
            httpResponse.addHeader("Content-Type: text/html");
            httpResponse.addHeader("Content-Length: 0");

            super.getOutputStream().write(httpResponse.toString().getBytes());
            super.getOutputStream().flush();
            super.setHttpResponse(httpResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

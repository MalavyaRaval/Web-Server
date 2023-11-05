package webserver667.responses.writers;

import java.io.OutputStream;

import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class NoContentResponseWriter extends ResponseWriter {

        public NoContentResponseWriter(OutputStream outputStream, IResource resource, HttpRequest httpRequest) {
                super(outputStream, resource);
        }

        @Override
        public void write() {

                try {
                        HttpResponse httpResponse = new HttpResponse(HttpResponseCode.NO_CONTENT, "HTTP/1.1");
                        // get the content type from the mime types
                        String contentType = super.getResource().getMimeType();
                        httpResponse.addHeader("Content-Type: " + contentType);
                        httpResponse.addHeader("Content-Length: 0");

                        super.getOutputStream().write(httpResponse.toString().getBytes());

                        super.getOutputStream().flush();
                        super.setHttpResponse(httpResponse);

                } catch (Exception e) {
                        e.printStackTrace();
                }

        }
}

package webserver667.responses.writers;

import java.io.IOException;
import java.io.OutputStream;
import webserver667.requests.HttpRequest;
import webserver667.responses.HttpResponse;
import webserver667.responses.HttpResponseCode;
import webserver667.responses.IResource;

public class InternalServerErrorResponseWriter extends ResponseWriter {

        public InternalServerErrorResponseWriter(OutputStream out, IResource testResource, HttpRequest httpRequest) {
                super(out, testResource);
        }

        @Override
        public void write() {

                try {
                        HttpResponse httpResponse = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR,
                                        "HTTP/1.1");
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

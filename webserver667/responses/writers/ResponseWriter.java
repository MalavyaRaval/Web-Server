package webserver667.responses.writers;

import java.io.OutputStream;
import java.net.http.HttpRequest;

import webserver667.responses.HttpResponse;
import webserver667.responses.IResource;

public abstract class ResponseWriter {

        private OutputStream outputStream;
        private IResource resource;
        private HttpResponse httpResponse;

    
        public ResponseWriter(OutputStream outputStream, IResource resource) {

                this.outputStream = outputStream;
                this.resource = resource;
        }
    
        public abstract void write();

        public OutputStream getOutputStream() {
                return this.outputStream;
        }

        public IResource getResource() {
            return resource;
        }

        public HttpResponse getHttpResponse() {
            return httpResponse;
        }

        public void setHttpResponse(HttpResponse httpResponse) {
            this.httpResponse = httpResponse;
        }
}

package webserver667.responses.authentication;

import webserver667.requests.HttpRequest;
import webserver667.responses.IResource;

public abstract class UserAuthenticator {

       
        private HttpRequest request;
        private IResource resource;

        public UserAuthenticator(HttpRequest request, IResource resource) {
                this.request = request;
                this.resource = resource;
        }

        public HttpRequest getRequest() {
                return this.request;
        }

        public IResource getResource() {
                return this.resource;
        }

        public abstract boolean isAuthenticated();

}

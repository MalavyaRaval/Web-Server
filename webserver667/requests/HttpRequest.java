package webserver667.requests;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private HttpMethods method;
    private String uri;
    private String queryString;
    private String version;
    // <headerKey, headerValue>
    // key --> value
    private Map<String, String> headers;
  
    private byte[] bytes;

    // constructor
    public HttpRequest() {
        // initialize instance variables
        this.method = null;
        this.uri = null;
        this.queryString = null;
        this.version = null;
        this.headers = new HashMap<>();
        // empty byte array
        this.bytes = new byte[0];
        
    }

    

    public void setHttpMethod(HttpMethods method) {
        this.method = method;
    }

    public HttpMethods getHttpMethod() {
        return this.method;
    }

    public void setURI(String uri) {
        // check if there is a query string
        if (uri.contains("?")) {
            // split the uri into the uri and the query string
            String[] uriParts = uri.split("\\?");
            this.uri = uriParts[0];
            this.queryString = uriParts[1];
        } else {
            // no query string
            this.uri = uri;
        }
    }

    public String getURI() {
        return this.uri;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }


    public int getContentLength() {
        // check if the header exists
        if (this.headers.containsKey("Content-Length")) {
            // return the value of the header
            return Integer.parseInt(this.headers.get("Content-Length"));
        } else {
            // return 0
            return 0;
        }
    }

    public boolean hasBody() {
        if(this.bytes.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setBody(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBody() {
        return this.bytes;
    }

    public String getHeader(String requestedKey) {
        // if key doesn't exist, return null
        return this.headers.get(requestedKey);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }



    public void addHeader(String headerLine) {
        // split the header line into the header key and the header value
        // only split on the first colon
        String[] headerParts = headerLine.split(":", 2);
        this.headers.put(headerParts[0], headerParts[1].trim());
    }


    public String toString() {
        String requestString = "";
        requestString += this.method + " " + this.uri + " " + this.version + "\n";
        for (String key : this.headers.keySet()) {
            requestString += key + ": " + this.headers.get(key) + "\n";
        }
        requestString += "\n";
        requestString += new String(this.bytes);
        return requestString;
    }
    
}

package webserver667.responses;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
        
        private HttpResponseCode status;
        private Map<String, String> headers;
        private String version;
        
        
        
        public HttpResponse(HttpResponseCode status, String version) {
            this.status = status;
            this.headers = new HashMap<>();
            this.version = version;
           
        }

        public String getStatusLine() {
            return this.status.toString();
        }
        
        public String getHeader(String header) {
            return this.headers.get(header);
        }
            
        public void addHeader(String headerLine) {
            String[] headerParts = headerLine.split(":");
            this.headers.put(headerParts[0], headerParts[1].trim());
        }
        
        
        public String toString() {
          
            StringBuilder sb = new StringBuilder();
            sb.append(this.version);
            sb.append(" ");
            sb.append(this.status.toString());
            sb.append("\r\n");
            for (String key : this.headers.keySet()) {
                sb.append(key);
                sb.append(": ");
                sb.append(this.headers.get(key));
                sb.append("\r\n");
            }
            sb.append("\r\n");
            return sb.toString();
        }

        public void setStatus(HttpResponseCode newCode) {
            this.status = newCode;
        }   

        public HttpResponseCode getStatusCode() {
            return this.status;
        }

        public void setVersion(String expectedVersion) {
            this.version = expectedVersion;
        }

        public String getVersion() {
            return this.version;
        }
        
}

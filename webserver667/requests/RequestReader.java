package webserver667.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import webserver667.exceptions.BadRequestException;
import webserver667.exceptions.MethodNotAllowedException;

public class RequestReader {

    private HttpRequest request;
    private InputStream input;

    public RequestReader(InputStream input) {
        this.input = input;
    }

    public HttpRequest getRequest() throws BadRequestException, MethodNotAllowedException {
        if (request == null) {
            this.request = parseRequest(this.input);

        }
        return this.request;

    }

    private HttpRequest parseRequest(InputStream input) throws BadRequestException, MethodNotAllowedException {
        HttpRequest httpRequest = new HttpRequest();

        // Read the input stream line by line using a BufferedReader
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // Read the request line (e.g., "GET /index.html HTTP/1.1")
            String requestLine = reader.readLine();
            if (requestLine != null) {
                // Split the request line into its components (method, URI, version)
                String[] requestLineParts = requestLine.split(" ");
                if (requestLineParts.length == 3) {
                    String method = requestLineParts[0];
                    String uri = requestLineParts[1];
                    String version = requestLineParts[2];

                    // Check if the method is in the enum
                    if (!HttpMethods.contains(method)) {
                        throw new MethodNotAllowedException("Method not allowed: " + method);
                    }

                    // Set the parsed values in the HttpRequest object
                    httpRequest.setHttpMethod(HttpMethods.valueOf(method));
                    httpRequest.setURI(uri);
                    httpRequest.setVersion(version);
                } else {
                    throw new BadRequestException("Invalid request line: " + requestLine);
                }
            }

            // Parse headers and read the request body (if applicable)

            // Read headers until the blank line (\r\n\r\n) is encountered
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                httpRequest.addHeader(line);
            }

            // move the reader to the next line

            if (httpRequest.getHeader("Content-Length") != null) {
                int contentLength = Integer.parseInt(httpRequest.getHeader("Content-Length"));
                byte[] body = new byte[contentLength];

                // Read bytes from the input stream and store them in the 'body' array
                for (int i = 0; i < contentLength; i++) {
                    int byteValue = reader.read();
                    if (byteValue == -1) {
                        // Handle the case where the input stream ends prematurely
                        throw new BadRequestException("Request body is shorter than Content-Length");
                    }
                    body[i] = (byte) byteValue;
                }

                // Set the 'body' array in the 'HttpRequest' object
                httpRequest.setBody(body);
            }

        } catch (IOException e) {
            throw new BadRequestException("Error reading request: " + e.getMessage() + " " + e.getStackTrace());
        }

        return httpRequest;
    }

}

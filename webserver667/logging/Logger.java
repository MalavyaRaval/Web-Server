package webserver667.logging;

import webserver667.requests.HttpRequest;

public class Logger {
    
    public static String getLogString(String ip, HttpRequest request, int statusCode, int contentLength) {
        
        String logString = ip + " - - [" + java.time.LocalDateTime.now() + "] \"" + request.getHttpMethod() + " " + request.getURI() + " " + request.getVersion() + "\" " + statusCode + " " + contentLength;
        
        return logString;
    }

    public static void debug(String string) {
    }
}

package webserver667.requests;

// ENUMS

public enum HttpMethods {
    GET, POST, PUT, DELETE, HEAD;

    public static boolean contains(String method) {
        for (HttpMethods httpMethod : values()) {
            if (httpMethod.name().equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}

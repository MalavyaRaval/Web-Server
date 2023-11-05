package webserver667.responses.authentication;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import webserver667.requests.HttpRequest;
import webserver667.responses.IResource;

public class UserPasswordAuthenticator extends UserAuthenticator {

    public UserPasswordAuthenticator(HttpRequest request, IResource resource) {
        super(request, resource);
    }

    public boolean isAuthenticated() {

        String authorizationHeaderValue = super.getRequest().getHeader("Authorization");
    
        String credentials = new String(
                Base64.getDecoder().decode(authorizationHeaderValue.replace("Basic ", "")),
                Charset.forName("UTF-8"));

        String[] tokens = credentials.split(":");

        String username = tokens[0].trim();
        String password = tokens[1].trim();

        // encode them into SHA-1
        byte[] passwordBytes;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            passwordBytes = messageDigest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }

        String encodedPass = Base64.getEncoder().encodeToString(passwordBytes);

        Map<String, String> passwordFileEntries = new HashMap<>();

        try {
            
            Files.readAllLines(Paths.get(super.getResource().getPath().resolveSibling(".passwords").toString()))
                    .forEach(line -> {
                        
                        String[] lineParts = line.replace("{SHA-1}", "").split(":");

                        passwordFileEntries.put(lineParts[0].trim(), lineParts[1].trim());
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
       
        String passwordFilePassword = passwordFileEntries.get(username);

        if (passwordFileEntries != null && passwordFilePassword != null) {

          

            if (passwordFilePassword.equals(encodedPass)) {
                return true;
            }
        }

        return false;
    }

}

package webserver667.responses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import webserver667.configuration.MimeTypes;
import webserver667.requests.HttpRequest;
import webserver667.responses.authentication.UserAuthenticator;
import webserver667.responses.authentication.UserPasswordAuthenticator;

public class Resource implements IResource {

    /*
     * + Resource(uri: String, queryString: String,
     * documentRoot: String): Resource
     * + exists(): boolean
     * + isProtected(): boolean
     * + isScript(): boolean
     * + getPath(): Path
     * + getUserAuthenticator(request: HttpRequest):
     * IUserAuthenticator)
     */
    private Path path;
    private MimeTypes mimeTypes;

    public Resource(String uri, String queryString, String documentRoot, MimeTypes mimeTypes) {
        
        // create a Path object from the uri
        this.path = Path.of(documentRoot, uri);
        this.mimeTypes = mimeTypes;

    }

    @Override
    public boolean exists() {
        // check if the resource exists
        return this.path.toFile().exists();
    }

    @Override
    public Path getPath() {
        
        return this.path;
    }

    @Override
    public boolean isProtected() {
        // if there is a .passwords file in the same directory as the resource
        // then the resource is protected
        return this.path.resolveSibling(".passwords").toFile().exists();
    }

    @Override
    public boolean isScript() {
    
        // if the resource is in a directory called "scripts" then it is a script
        return this.path.getParent().endsWith("scripts");
       
    }

    @Override
    public UserAuthenticator getUserAuthenticator(HttpRequest request) {
       
        return new UserPasswordAuthenticator(request, this);
    }

    @Override
    public String getMimeType() {
        
        return this.mimeTypes.toString();
    }

    @Override
    public long getFileSize() throws IOException {
        
        return this.path.toFile().length();
    }

    @Override
    public byte[] getFileBytes() throws IOException {
        
        return Files.readAllBytes(this.path);
    }

    @Override
    public long lastModified() {
        
        return this.path.toFile().lastModified();
    }
}

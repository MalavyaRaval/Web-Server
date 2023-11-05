package driver;

import webserver667.configuration.MimeTypes;
import webserver667.configuration.ServerConfiguration;

public interface IStartableServer extends AutoCloseable {
  public void start(
      ServerConfiguration configuration,
      MimeTypes mimeTypes);

  public void stop();
}

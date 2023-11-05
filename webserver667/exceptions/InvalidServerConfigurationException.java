package webserver667.exceptions;

import java.util.Set;

import webserver667.configuration.ServerCliFlag;

public class InvalidServerConfigurationException extends ServerConfigurationException {
  public InvalidServerConfigurationException(Set<ServerCliFlag> cliFlagSpecification, Set<String> errors) {
    super(String.format(
        "Please correct the following errors: %s\n\n%s",
        String.join("\n", errors),
        ServerConfigurationException.generateUsageInformation(cliFlagSpecification)));
  }
}
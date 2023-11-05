package webserver667.exceptions;

import java.util.Set;

import webserver667.configuration.ServerCliFlag;

public class ConfigurationHelpRequestedException extends ServerConfigurationException {
  public ConfigurationHelpRequestedException(Set<ServerCliFlag> cliFlagSpecification) {
    super(ServerConfigurationException.generateUsageInformation(cliFlagSpecification));
  }
}

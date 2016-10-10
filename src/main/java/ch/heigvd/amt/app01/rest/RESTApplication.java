package ch.heigvd.amt.app01.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * This is how to configure jackson in an application using wildfly.
 * Source:
 * http://stackoverflow.com/questions/28307646/how-to-configure-jackson-in-wildfly
 */
@ApplicationPath("/api")
@Provider
public class RESTApplication extends Application {

}
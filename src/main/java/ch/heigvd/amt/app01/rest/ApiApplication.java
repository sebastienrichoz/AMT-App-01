package ch.heigvd.amt.app01.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Define the Rest API with the /api prefix.
 *
 * @author Damien Rochat <damien.rochat@heig-vd.ch> & Sébastien Richoz <sebastien.richoz1@heig-vd.ch>
 */
@ApplicationPath("/api")
public class ApiApplication extends Application {

}
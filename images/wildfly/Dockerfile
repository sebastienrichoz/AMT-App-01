FROM jboss/wildfly:10.1.0.Final

MAINTAINER Damien Rochat <damien.rochat@heig-vd.ch>
MAINTAINER Sébastien Richoz <sebastien.richoz1@heig-vd.ch>

ADD ../../dist/helloworld.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


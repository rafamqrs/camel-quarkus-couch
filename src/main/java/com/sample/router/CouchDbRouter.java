package com.sample.router;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CouchDbRouter extends RouteBuilder{

    @ConfigProperty(name = "folder",  defaultValue = "/tmp/data")
    String twitterUserName;

    @Override
    public void configure() throws Exception {

        from("file:/tmp/data?noop=true")
        .routeId("insertFruit")
        .convertBodyTo(String.class)
//        .setHeader("CouchDbMethod", "delete") Set the Header with CouchDdMethod and value (delete / update)
        .to("couchdb:http://localhost:5984/fruits?username=user1&password=user1")
        .log("${body}");

        from("timer:foo?period=10000")
        .routeId("getAllFruits")
        .setHeader("Authorization", constant("Basic dXNlcjE6dXNlcjE="))
        .to("http://localhost:5984/fruits/_all_docs")
        .log("${body}")
        ;
    }
    
}

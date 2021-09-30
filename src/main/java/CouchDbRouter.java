import org.apache.camel.builder.RouteBuilder;

public class CouchDbRouter extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        from("timer://fruits?fixedRate=true&period=60000")
        .routeId("getBooks")
        .process(new BookProcessor())
        .to("couchdb:http://localhost:5984/fruits?username=admin&password=password");

        from("couchdb:http://localhost:5984/fruits?username=admin&password=password")
        .routeId("get")
        .log("${body}");

    }
    
}

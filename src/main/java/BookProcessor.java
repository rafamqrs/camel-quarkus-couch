import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BookProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // TODO Auto-generated method stub
        String json = "{'_id': 'cff26982c5bf97a462e8fdab8aa006968', 'fruit': 'Apple',            'size': 'Large',            'color': 'Red'          }";
        exchange.getIn().setBody(json);
    }

}

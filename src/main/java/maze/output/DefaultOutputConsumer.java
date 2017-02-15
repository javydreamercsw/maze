package maze.output;

import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Javier A. Ortiz Bultron <javier.ortiz.78@gmail.com>
 */
@ServiceProvider(service = OutputConsumer.class)
public class DefaultOutputConsumer implements OutputConsumer {

    @Override
    public void output(String o) {
        System.out.println(o);
    }
}

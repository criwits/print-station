package top.criwits.print.printer;

import javax.print.PrintService;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import java.util.Arrays;

public class PrintHelper {
    public final static void waitEndJobs(final PrintService printService) throws InterruptedException {

        int queue = 1;
        while (queue != 0) {
            final AttributeSet attributes = printService.getAttributes();
            final Attribute a = Arrays.stream(attributes.toArray())//
                    .filter(o -> o.getName().equalsIgnoreCase("queued-job-count"))//
                    .findFirst()//
                    .orElse(null);
            queue = Integer.parseInt(attributes.get(a.getClass()).toString());
            Thread.sleep(5000);
        }
    }
}

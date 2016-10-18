import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samue on 18.10.2016.
 */
public class SpamfilterTest {

    @Test
    public void TestSimpleSpam() {
        String spamContent = "viagra for free";
        Spamfilter f = new Spamfilter();

        f.feed(spamContent,true);
        f.train();
        assertTrue(f.isSpam(spamContent));
    }

}
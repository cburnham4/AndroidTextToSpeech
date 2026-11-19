package letshangllc.texttospeech;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation smoke test.
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    @Test
    public void useAppContext() {
        String packageName = InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getPackageName();
        assertEquals("letshangllc.texttospeech", packageName);
    }
}

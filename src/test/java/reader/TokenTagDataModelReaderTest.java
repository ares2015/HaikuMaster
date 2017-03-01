package reader;

import com.haikuMaster.reader.TokenTagDataModelReader;
import com.haikuMaster.reader.TokenTagDataModelReaderImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 3/1/2017.
 */
public class TokenTagDataModelReaderTest {

    @Test
    public void test() throws IOException {
        TokenTagDataModelReader tokenTagDataModelReader = new TokenTagDataModelReaderImpl();
        Map<String, Set<String>> tokenTagDataModel = tokenTagDataModelReader.read();
        assertTrue(tokenTagDataModel.size() > 0);
        assertTrue(tokenTagDataModel.get("dog").size() > 0);
        System.out.println("TokenTagDataModel size is: " + tokenTagDataModel.size());
    }
}

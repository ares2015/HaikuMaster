package reader;

import com.haikuMaster.reader.Word2VecModelReader;
import com.haikuMaster.reader.Word2VecModelReaderImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 3/1/2017.
 */
public class Word2VecModelReaderTest {

    @Test
    public void test() throws IOException {
        Word2VecModelReader word2VecModelReader = new Word2VecModelReaderImpl();
        Map<String, List<String>> word2VecModel = word2VecModelReader.read();
        assertTrue(word2VecModel.size() > 0);
        assertTrue(word2VecModel.get("dog").size() > 0);
        System.out.println("Word2VecModel size is: " + word2VecModel.size());
    }
}

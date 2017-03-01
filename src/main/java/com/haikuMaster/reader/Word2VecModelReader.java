package com.haikuMaster.reader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 3/1/2017.
 */
public interface Word2VecModelReader {

    Map<String, List<String>> read() throws IOException;

}

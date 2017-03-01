package com.haikuMaster.reader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 3/1/2017.
 */
public interface TokenTagDataModelReader {

    Map<String, Set<String>> read() throws IOException;
}

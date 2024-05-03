package org.ssv.service.util;

import org.ssv.exception.InvalidContentException;
import org.ssv.model.Smell;
import java.util.List;

public interface ContentParser {
    List<Smell> parseContent(String content) throws InvalidContentException;
}

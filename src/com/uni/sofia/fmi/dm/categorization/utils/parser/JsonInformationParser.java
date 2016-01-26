package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Dimitar
 */
public class JsonInformationParser implements InformationParser {

    @Override
    public TokensInformationHolder parse(String url) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonFileEntity jsonFilEntity = mapper.readValue(new File(url), JsonFileEntity.class);
        } catch (IOException ex) {
            Logger.getLogger(JsonInformationParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public TokensInformationHolder parse(File file) {
        return this.parse(file.getAbsolutePath());
    }

}

package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.TokensHolder;
import java.io.File;

/**
 *
 * @author Dimitar
 */
public interface InformationParser {

    TokensHolder parse(String url);
    
    TokensHolder parse(File file);
}
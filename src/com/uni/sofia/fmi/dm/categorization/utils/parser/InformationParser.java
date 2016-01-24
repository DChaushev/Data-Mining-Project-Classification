package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;
import java.io.File;

/**
 *
 * @author Dimitar
 */
public interface InformationParser {

    TokensInformationHolder parse(String url);

    TokensInformationHolder parse(File files);
}

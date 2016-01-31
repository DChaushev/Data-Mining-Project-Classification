package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 *
 * @author Dimitar
 */
public class StanfordReviewsConverter {

    private static final String POSITIVE_REVIEWS_FOLDER = "pos";
    private static final String NEGATIVE_REVIEWS_FOLDER = "neg";

    public static void convertTxtToJson(String baseFolder, String jsonFileName) {
        convertTxtToJson(new File(baseFolder), new File(jsonFileName));
    }

    public static void convertTxtToJson(File baseFolder, File jsonFile) {
        List<ReviewEntity> positiveEntities = readFilesFromPath(new File(baseFolder, POSITIVE_REVIEWS_FOLDER), Categories.POSITIVE);
        List<ReviewEntity> negativeEntities = readFilesFromPath(new File(baseFolder, NEGATIVE_REVIEWS_FOLDER), Categories.NEGATIVE);

        JsonFileEntity jsonFileEntity = new JsonFileEntity();
        jsonFileEntity.setNegativeReviews(negativeEntities.size());
        jsonFileEntity.setPositiveReviews(positiveEntities.size());
        positiveEntities.addAll(negativeEntities);
        jsonFileEntity.setReviews(positiveEntities);

        createJsonFile(jsonFileEntity, jsonFile);
    }

    private static List<ReviewEntity> readFilesFromPath(File folder, Categories type) {
        List<ReviewEntity> entities = new ArrayList<>();
        try {
            Files.walk(folder.toPath()).forEach(file -> {
                if (Files.isRegularFile(file)) {
                    try {
                        String content = new String(Files.readAllBytes(file));
                        entities.add(new ReviewEntity(type, content));
                    } catch (IOException ex) {
                        Logger.getLogger(StanfordReviewsConverter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            );

        } catch (IOException ex) {
            Logger.getLogger(StanfordReviewsConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return entities;
    }

    private static void createJsonFile(Object contents, File outputFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(outputFile, contents);
        } catch (IOException ex) {
            Logger.getLogger(AmazonReviewsConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

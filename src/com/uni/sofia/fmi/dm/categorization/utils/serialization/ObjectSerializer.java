package com.uni.sofia.fmi.dm.categorization.utils.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitar
 */
public class ObjectSerializer {

    private static final File SERIALIZATION_FOLDER = new File("serialized");
    private static final String SER_SUFFIX = ".ser";

    public static String serialize(Object object) {
        if (!SERIALIZATION_FOLDER.exists()) {
            SERIALIZATION_FOLDER.mkdir();
        }

        String fileName = String.valueOf(object.hashCode());
        File fullPath = new File(SERIALIZATION_FOLDER, fileName + SER_SUFFIX);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullPath))) {

            out.writeObject(object);
            return fullPath.toString();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjectSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        // should not reach this
        return null;
    }

    public static Object deserialize(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {

            Object result = in.readObject();
            return result;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObjectSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        // should not reach this
        return null;
    }

}

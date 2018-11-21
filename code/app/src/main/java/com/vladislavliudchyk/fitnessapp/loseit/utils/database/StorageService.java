package com.vladislavliudchyk.fitnessapp.loseit.utils.database;

import android.content.Context;

import com.vladislavliudchyk.fitnessapp.loseit.data.PersonalData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class that provides read/write operations
 * with user data and allows to store them in file
 */
public class StorageService {

    /**
     * This method write data to storage
     * @param context Context of the application
     * @param personalData Value of the personal data
     */
    public static void writeToStorage(Context context, PersonalData personalData) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput("userdata", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(personalData);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method read data from storage
     * @param context Context of the application
     * @return Value of the personal data
     */
    public static PersonalData loadFromStorage(Context context) {

        File file = context.getFileStreamPath("userdata");
        if(file == null || !file.exists()) {
            return new PersonalData();
        }
        FileInputStream fileInputStream;
        PersonalData userData = null;
        try {
            fileInputStream = context.openFileInput("userdata");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userData = (PersonalData) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userData;
    }
}

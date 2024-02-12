package com.allocate.ontime.encryption;

//import com.allocate.ontime.retrofit.EDModel;
import com.google.gson.Gson;

import retrofit2.Response;
//import timber.log.Timber;

/**
 * Created by admin on 02-Feb-18.
 */

public class EDHelper {

    private static final String TAG = EDHelper.class.getSimpleName();

    public static final String KEY = "MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MzA=";


    public static EDModel encrypt(Object object) {
        String normalTextEnc = "";
        try {
            String temp = new Gson().toJson(object);
            temp = temp.replace("QEA\\u003d", "QEA=");
            normalTextEnc = AESHelper.getInstance().encrypt(KEY, temp);
        } catch (Exception e) {
            //LogFiles.appendEncryptionError("Error in encryption\nError : " + e.toString());
        }
        return new EDModel(normalTextEnc);
    }

    public static EDModel encryptString(String value) {
        String normalTextEnc = "";
        try {
            String temp = value.replace("QEA\\u003d", "QEA=");
            normalTextEnc = AESHelper.getInstance().encrypt(KEY, temp);
        } catch (Exception e) {
            //LogFiles.appendEncryptionError("Error in encryption\nError : " + e.toString());
        }
        return new EDModel(normalTextEnc);
    }
    public static <T extends Object> T decryptModel(Object o, Class<T> objClass) {
        String encrypted = "";

        String decrypted = "";
        try {
            encrypted = ((EDModel) ((Response) o).body()).getData();
            decrypted = AESHelper.getInstance().decrypt(KEY, encrypted);
        } catch (Exception e) {
            //LogFiles.appendEncryptionError("Error in decryption\nError : " + e.toString());
        }
        return new Gson().fromJson(decrypted, objClass);
    }

    public static <T extends Object> T decrypt(String encrypted, Class<T> objClass) {
        String decrypted = "";
        try {
            decrypted = AESHelper.getInstance().decrypt(KEY, encrypted);
        } catch (Exception e) {
            //LogFiles.appendEncryptionError("Error in decryption\nError : " + e.toString());
        }
        return new Gson().fromJson(decrypted, objClass);
    }
    public static String decrypt(String encrypted) {
        String decrypted = "";
        try {
            decrypted = AESHelper.getInstance().decrypt(KEY, encrypted);
        } catch (Exception e) {
//            Timber.tag("EDHelperError").e(e);
            //LogFiles.appendEncryptionError("Error in decryption\nError : " + e.toString());
        }
        return decrypted;
    }
}

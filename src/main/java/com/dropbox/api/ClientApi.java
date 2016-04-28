package com.dropbox.api;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.Metadata;

import java.util.List;

/**
 * Service provides dropbox api access
 */
public class ClientApi {

    private static final String ACCESS_TOKEN = "Enter dropbox access token here";

    public static void createFolder(String folderName, String path) throws DbxException {

        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        client.files().createFolder(path + folderName);
    }

    public static void deleteFolder(String folderName, String path) throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        client.files().delete(path + folderName);
    }

    public static void deleteFoldersContaining(String partialFolderName) throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        List<Metadata> entries = client.files().listFolder("").getEntries();
        for (Metadata metadata : entries) {
            String path = metadata.getPathDisplay();
            if (path.contains(partialFolderName)) {
                client.files().delete(metadata.getPathLower());
            }
        }
    }

}

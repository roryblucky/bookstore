package com.rory.bookstore.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by RoryGao on 15/6/14.
 */
public class FileUploadUtils {
    public static List<FileItem> parseRequest(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(request.getServletContext().getRealPath("/upload")));
        factory.setSizeThreshold(50000);
        List<FileItem> fileItems = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                fileItems = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        return fileItems;
    }

    public static String uploadFile(FileItem fileItem, File location) throws IOException {
        File saveFile = new File(location + "/" + StringUtils.getUUID() + "_" + fileItem.getName());

        InputStream input = fileItem.getInputStream();
        FileOutputStream output = new FileOutputStream(saveFile);

        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = input.read(buffer)) != -1) {
            output.write(buffer, 0, length);
        }
        input.close();
        output.flush();
        output.close();

        return saveFile.getName();
    }

    public static void removeFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}

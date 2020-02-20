package by.epam.autoshow.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import by.epam.autoshow.util.manager.PagePathProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = {"/image-upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CarImageUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "D:\\Work\\uploads";
    private static final String FILE_UPLOAD_ERROR_RESULT = "Произошла ошибка, не удалось загрузить файл.";
    private static final String FILE_VALIDATION_SUCCESS_RESULT = "Файл загружен успешно!";
    private static final String PARAM_UPLOAD_RESULT = "uploadResult";
    private static final String PART_PARAMETER_FILENAME = "filename";
    private static final String EMPTY_STRING = "";
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String imageFileName = uploadFile(request);
            logger.debug(imageFileName);
            request.setAttribute(PARAM_UPLOAD_RESULT, FILE_VALIDATION_SUCCESS_RESULT);
            request.getRequestDispatcher(PagePathProperty.FILE_UPLOAD_PAGE_PROPERTY).forward(request, response);
        } catch (FileNotFoundException e) {
            logger.error(e);
            request.setAttribute(PARAM_UPLOAD_RESULT, FILE_UPLOAD_ERROR_RESULT);
            request.getRequestDispatcher(PagePathProperty.FILE_UPLOAD_PAGE_PROPERTY).forward(request, response);
        }
    }

    private String uploadFile(HttpServletRequest request)
            throws IOException, ServletException {
        String uploadFileDir = UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String absoluteFilePath = EMPTY_STRING;
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.equals(EMPTY_STRING)) {
                absoluteFilePath = uploadFileDir + File.separator + fileName;
                part.write(absoluteFilePath);
                return absoluteFilePath;
            }
        }
        return absoluteFilePath;
    }

    private String extractFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.strip().startsWith(PART_PARAMETER_FILENAME)) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return EMPTY_STRING;
    }
}
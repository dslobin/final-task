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
import java.util.UUID;

import by.epam.autoshow.service.CarService;
import by.epam.autoshow.service.ServiceException;
import by.epam.autoshow.service.impl.CarServiceImpl;
import by.epam.autoshow.util.provider.MessagePath;
import by.epam.autoshow.util.provider.MessageProvider;
import by.epam.autoshow.util.provider.PagePathProvider;
import by.epam.autoshow.util.provider.JspPagePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = {"/image-upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CarImageUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "D:\\Work\\uploads";
    private static final String ATTRIBUTE_UPLOAD_RESULT = "uploadResult";
    private static final String PART_PARAMETER_FILENAME = "filename";
    private static final String PARAM_CAR_ID = "carId";
    private static final String EMPTY_STRING = "";
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long carId = Long.parseLong(request.getParameter(PARAM_CAR_ID));
        try {
            String imagePath = uploadFile(request);
            logger.debug("Real path: " + getServletContext().getRealPath("/static/img/"));
            logger.debug("Uploaded image = " + imagePath);
            CarService carService = CarServiceImpl.getInstance();
            carService.updateCarImage(carId, imagePath);
            request.setAttribute(ATTRIBUTE_UPLOAD_RESULT,
                    MessageProvider.getProperty(MessagePath.FILE_UPLOAD_SUCCESS_RESULT));
            request.getRequestDispatcher(PagePathProvider.getProperty(JspPagePath.FILE_UPLOAD_PAGE_PROPERTY))
                    .forward(request, response);
        } catch (FileNotFoundException | ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTRIBUTE_UPLOAD_RESULT,
                    MessageProvider.getProperty(MessagePath.FILE_UPLOAD_ERROR_RESULT));
            request.getRequestDispatcher(PagePathProvider.getProperty(JspPagePath.FILE_UPLOAD_PAGE_PROPERTY))
                    .forward(request, response);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String uploadFileDir = UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String absoluteFilePath = EMPTY_STRING;
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.equals(EMPTY_STRING)) {
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                String randomFileName = UUID.randomUUID() + fileExtension;
                absoluteFilePath = uploadFileDir + randomFileName;
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
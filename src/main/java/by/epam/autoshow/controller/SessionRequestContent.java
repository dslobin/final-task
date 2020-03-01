package by.epam.autoshow.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private boolean invalidateSession = false;
    private static final int FIRST_ARRAY_ELEMENT_INDEX = 0;
    private static final Logger logger = LogManager.getLogger();

    public SessionRequestContent(HttpServletRequest request) {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        initialize(request);
    }

    private void initialize(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Iterator<String> sessionAttributesIterator = httpSession.getAttributeNames().asIterator();
        while (sessionAttributesIterator.hasNext()) {
            String name = sessionAttributesIterator.next();
            Object sessionAttribute = httpSession.getAttribute(name);
            //logger.debug("\nSESSION ATTRIBUTE: name = " + name + " object = " + sessionAttribute);
            sessionAttributes.put(name, sessionAttribute);
        }
        Iterator<String> requestAttributesIterator = request.getAttributeNames().asIterator();
        while (requestAttributesIterator.hasNext()) {
            String name = requestAttributesIterator.next();
            Object requestAttribute = request.getAttribute(name);
            requestAttributes.put(name, requestAttribute);
        }
        requestParameters.putAll(request.getParameterMap());
    }

    public void insert(HttpServletRequest httpServletRequest) {
        requestAttributes.forEach(httpServletRequest::setAttribute);
        HttpSession httpSession = httpServletRequest.getSession();
        sessionAttributes.forEach(httpSession::setAttribute);
        if (isSessionInvalidate()) {
            httpSession.invalidate();
        }
    }

    public String getRequestParameter(String name) {
        String[] parameters = requestParameters.get(name);
        if (parameters == null) {
            return null;
        }
        //logger.debug("GET REQUEST PARAMETER: " + Arrays.toString(parameters));
        return parameters[FIRST_ARRAY_ELEMENT_INDEX];
    }

    public Object getRequestAttributes(String name) {
        return requestAttributes.get(name);
    }

    public String[] getRequestParameters(String name) {
        return requestParameters.get(name);
    }

    public Object getSessionAttributes(String name) {
        return sessionAttributes.get(name);
    }

    public void setRequestAttributes(String name, Object object) {
        requestAttributes.put(name, object);
    }

    public void setRequestParameters(String name, String[] strings) {
        requestParameters.put(name, strings);
    }

    public void setSessionAttributes(String name, Object object) {
        sessionAttributes.put(name, object);
    }

    public boolean isSessionInvalidate() {
        return invalidateSession;
    }

    public void invalidateSession() {
        this.invalidateSession = true;
    }
}
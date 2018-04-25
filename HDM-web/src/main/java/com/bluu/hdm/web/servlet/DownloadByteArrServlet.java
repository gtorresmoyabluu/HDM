package com.bluu.hdm.web.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = {"/DownloadByteArrServlet"})
public class DownloadByteArrServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String CONTENTTYPE = "contentType";
    public static final String FILECONTENT = "fileContent";
    public static final String FILENAME = "fileName";

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;
        try {
            String fileName = (String) request.getSession().getAttribute(FILENAME);
            String contentType = (String) request.getSession().getAttribute(CONTENTTYPE);
            byte[] fileContent = (byte[]) request.getSession().getAttribute(FILECONTENT);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            response.setContentType(contentType);
            out = response.getOutputStream();
            out.write(fileContent);
            out.flush();
            out.close();
        } catch (IOException ignore) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {
            }

            request.getSession().removeAttribute(FILENAME);
            request.getSession().removeAttribute(CONTENTTYPE);
            request.getSession().removeAttribute(FILECONTENT);
        }
    }
}

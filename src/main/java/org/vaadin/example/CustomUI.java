package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomUI extends UI {

    @WebServlet(urlPatterns = "/*", name = "myservlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CustomUI.class, productionMode = false)
    public static class MyServlet extends VaadinServlet {

        @Override
        protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) throws ServiceException {
            VaadinServletService service = new VaadinServletService(this,
                    deploymentConfiguration) {
            };
            service.init();
            return service;
        }

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if (request.getQueryString() != null && request.getQueryString().contains("logout")) {
                SessionData.invalidate(request);
            }
            super.service(request, response);
        }
    }

    @Override
    protected void init(VaadinRequest request) {
        super.init(request);
        SessionData.getInstance(((VaadinServletRequest) request).getHttpServletRequest());
    }
}

package org.vaadin.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionData {

    public static SessionData getInstance(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            SessionData sessionData = (SessionData) session.getAttribute("sessionData");
            if (sessionData == null) {
                sessionData = new SessionData();
                session.setAttribute("sessionData", sessionData);
            }
            return sessionData;
        }
        return null;
    }

    public static void invalidate(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("sessionData");
            session.invalidate();
        }
    }
}

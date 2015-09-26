/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Files;

import javax.faces.context.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author António Silva
 */
public class PDF {
//    public void createPDF() {
////    FacesContext facesContext = FacesContext.getCurrentInstance();
////    ExternalContext externalContext = facesContext.getExternalContext();
////    HttpSession session = (HttpSession) externalContext.getSession(true);
////    String url = "http://localhost:8080/MyPROJECT/faces/page1.xhtml;JSESSIONID=" + session.getId();
////    try {
////    ITextRenderer renderer = new ITextRenderer();
////    renderer.setDocument(url);
////    renderer.layout();
////    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
////    response.reset();
////    response.setContentType("application/pdf");
////    response.setHeader("Content-Disposition","C://user//first.pdf");
////    OutputStream browserStream = response.getOutputStream();
////    renderer.createPDF(browserStream);
////    browserStream.close();
////    session.invalidate();
////    } catch (Exception ex) {
//       ex.printStackTrace();
//    }
//    facesContext.responseComplete();
//}
}

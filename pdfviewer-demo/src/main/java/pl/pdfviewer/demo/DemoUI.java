package pl.pdfviewer.demo;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.pdfviewer.PdfViewer;

@Title("PdfViewer Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        
        PdfViewer c = new PdfViewer(new File("/home/radek/Pulpit/pdf.pdf"));
        c.setHeight(100	,Unit.PERCENTAGE);
        c.setWidth(100,Unit.PERCENTAGE);
        c.setBackAngleButtonCaption(FontAwesome.ROTATE_LEFT.getHtml());
        c.setNextAngleButtonCaption(FontAwesome.ROTATE_RIGHT.getHtml());
        c.setIncreaseButtonCaption(FontAwesome.SEARCH_PLUS.getHtml());
        c.setDecreaseButtonCaption(FontAwesome.SEARCH_MINUS.getHtml());
        c.setPreviousPageCaption(FontAwesome.ANGLE_LEFT.getHtml()+" Back");
        c.setNextPageCaption("Next "+FontAwesome.ANGLE_RIGHT.getHtml());
		layout.addComponent(c);
        setContent(layout);
    }
}

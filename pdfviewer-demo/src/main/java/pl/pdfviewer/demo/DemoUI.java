package pl.pdfviewer.demo;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.pdfviewer.PdfViewer;

@Theme("demo")
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
        layout.setSizeFull();
        PdfViewer c = new PdfViewer(new File("/home/radek/Pulpit/pdf.pdf"));
        c.setBackAngleButtonCaption(FontAwesome.ROTATE_LEFT.getHtml());
        c.setNextAngleButtonCaption(FontAwesome.ROTATE_RIGHT.getHtml());
        System.out.println(FontAwesome.ROTATE_LEFT.getHtml());
        System.out.println(FontAwesome.ROTATE_RIGHT.getHtml());
		layout.addComponent(c);
        setContent(layout);
    }
}

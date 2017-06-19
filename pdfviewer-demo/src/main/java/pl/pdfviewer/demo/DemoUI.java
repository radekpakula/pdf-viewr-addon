package pl.pdfviewer.demo;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
        c.setHeight(400	,Unit.PIXELS);
        c.setWidth(800,Unit.PIXELS);
        c.setBackAngleButtonCaption(VaadinIcons.ROTATE_LEFT.getHtml());
        c.setNextAngleButtonCaption(VaadinIcons.ROTATE_RIGHT.getHtml());
        c.setIncreaseButtonCaption(VaadinIcons.SEARCH_PLUS.getHtml());
        c.setDecreaseButtonCaption(VaadinIcons.SEARCH_MINUS.getHtml());
        c.setPreviousPageCaption(VaadinIcons.ANGLE_LEFT.getHtml()+" Back");
        c.setNextPageCaption("Next "+VaadinIcons.ANGLE_RIGHT.getHtml());
		layout.addComponent(c);
        setContent(layout);
        Button b = new Button("dupa");
        b.addClickListener(new ClickListener() {
        	int b=0;
			@Override
			public void buttonClick(ClickEvent event) {
				b++;
	        	c.setAngleButtonVisible(b%2==0);
	        	c.setNextPageCaption("DUPA");
			}
		});
        layout.addComponent(b);
    }
}
package pl.pdfviewer.example;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.tiffviewer.PdfViewer;

@SuppressWarnings("serial")
@Theme("pdfviewer")
@PreserveOnRefresh
public class PdfViewerUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PdfViewerUI.class, 
			widgetset = "pl.pdfviewer.widgetset.PdfViewerWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		File file = new File("/home/radek/Pulpit/pdf.pdf");
		PdfViewer c = new PdfViewer(file);
		c.setWidth(500,Unit.PIXELS);
		layout.addComponent(c);
	}
}
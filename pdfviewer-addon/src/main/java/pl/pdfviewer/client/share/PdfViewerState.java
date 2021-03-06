package pl.pdfviewer.client.share;


public class PdfViewerState extends com.vaadin.shared.AbstractComponentState {

	private static final long serialVersionUID = 1L;
	public String resourceFile;
	public String externalFile;
	public int page;
	public int angle;
	public String previousPageCaption;
	public String nextPageCaption;
	public String pageCaption;
	public String toPageCaption;
	public String incraseCaption;
	public String decreaseCaption;
	public String nextAngle;
	public String backAngle;
	public String printCaption;
	public String downloadCaption;
	public boolean angleVisible;
	public boolean additionalVisible;

}
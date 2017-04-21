package pl.pdfviewer.client.share;


public class PdfViewerState extends com.vaadin.shared.AbstractComponentState {

	private static final long serialVersionUID = 1L;
	public String resourceFile;
	public String externalFile;
	public int page=0;
	public String previousPageCaption;
	public String nextPageCaption;
	public String pageCaption;
	public String toPageCaption;
	public String incraseCaption;
	public String decreaseCaption;
	public String nextAngle;
	public String backAngle;

}
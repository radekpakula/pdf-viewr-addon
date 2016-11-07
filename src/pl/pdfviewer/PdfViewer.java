package pl.pdfviewer;

import java.io.File;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.AbstractComponent;

import pl.pdfviewer.widgetset.client.share.PdfViewerState;
import pl.pdfviewer.widgetset.client.ui.PdfViewerServerRpc;

@JavaScript({"pdf.worker.js","pdf.js","dragscroll.js","pdf.viewer.js"})
public class PdfViewer extends AbstractComponent{

	private static final long serialVersionUID = 1L;
	private PdfViewerServerRpc rpc = new PdfViewerServerRpc() {
		private static final long serialVersionUID = 1L;
	};
	public PdfViewer(){
		registerRpc(rpc);
	}
	public PdfViewer(File file) {
		registerRpc(rpc);
		loadFile(file);
	}
	public PdfViewer(FileResource file) {
		registerRpc(rpc);
		setResource("resourceFile", file);
	}
	public PdfViewer(StreamResource file) {
		registerRpc(rpc);
		setResource("resourceFile", file);
	}
	public void setFile(File file){
		loadFile(file);
	}
	public void setFile(StreamResource sourceFile){
		setResource("resourceFile", sourceFile);
	}
	private void loadFile(File file) {
		FileResource resource = new FileResource(file);
		setResource("resourceFile", resource);
	}
	
	@Override
	public PdfViewerState getState() {
		return (PdfViewerState) super.getState();
	}
	
	public void setPage(int i){
		getState().page=i;
	}
	public void setPreviousPageCaption(String htmlCaption){
		getState().previousPageCaption=htmlCaption;
	}
	public void setNextPageCaption(String htmlCaption){
		getState().nextPageCaption=htmlCaption;
	}
	public void setPageCaption(String htmlCaption) {
		getState().pageCaption=htmlCaption;
	}
	public void setToPageCaption(String htmlCaption) {
		getState().toPageCaption=htmlCaption;
	}
	public void setIncreaseButtonCaption(String htmlCaption) {
		getState().incraseCaption=htmlCaption;
	}
	public void setDecreaseButtonCaption(String htmlCaption) {
		getState().decreaseCaption=htmlCaption;
	}
}

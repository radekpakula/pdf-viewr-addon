package pl.pdfviewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.AbstractComponent;

import pl.pdfviewer.client.share.PdfViewerState;
import pl.pdfviewer.client.ui.PdfViewerServerRpc;
import pl.pdfviewer.listener.AngleChangeListener;
import pl.pdfviewer.listener.DownloadTiffListener;
import pl.pdfviewer.listener.PageChangeListener;

@JavaScript({"pdf.worker.js","pdf.js","dragscroll.js","pdf.viewer.js","print.js"})
public class PdfViewer extends AbstractComponent{

	private static final long serialVersionUID = 1L;
	
	private List<PageChangeListener> pageChangeListener = new ArrayList<>();
	private List<AngleChangeListener> angleChangeListener = new ArrayList<>();
	private List<DownloadTiffListener> downloadListener = new ArrayList<>();
	
	private PdfViewerServerRpc rpc = new PdfViewerServerRpc() {
		private static final long serialVersionUID = 1L;
		@Override
		public void angleChange(Integer angle) {
			if(getState().angle!=angle){
				getState().angle=angle;
				angleChangeListener.forEach(e->{
					e.angleChange(angle);
				});
				
			}
		}
		@Override
		public void pageChange(Integer page) {
			if(getState().page!= page){
				getState().page=page;
				pageChangeListener.forEach(e->{
					e.pageChange(page);
				});
			}
		}
		@Override
		public void download() {
			downloadListener.forEach(e->{
				e.download();
			});
		}
	};
	public PdfViewer(){
		registerRpc(rpc);
	}
	public PdfViewer(File file) {
		registerRpc(rpc);
		loadFile(file);
		configure();
	}
	private void configure() {
		setAngleButtonVisible(true);
		setDownloadBtnVisible(true);
		
	}
	public PdfViewer(FileResource file) {
		registerRpc(rpc);
		setResource("resourceFile", file);
		configure();
	}
	public PdfViewer(StreamResource file) {
		registerRpc(rpc);
		setResource("resourceFile", file);
		configure();
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
	public void setNextAngleButtonCaption(String htmlCaption) {
		getState().nextAngle=htmlCaption;
	}
	public void setBackAngleButtonCaption(String htmlCaption) {
		getState().backAngle=htmlCaption;
	}
	public void setPrintButtonCaption(String htmlCaption) {
		getState().printCaption=htmlCaption;
	}
	public void setDownloadButtonCaption(String htmlCaption) {
		getState().downloadCaption=htmlCaption;
	}
	public void setAngleButtonVisible(boolean visible) {
		getState().angleVisible=visible;
	}
	public void setDownloadBtnVisible(boolean visible) {
		getState().additionalVisible=visible;
	}
	public void addPageChangeListener(PageChangeListener listener){
		pageChangeListener.add(listener);
	}
	public void removePageChangeListener(PageChangeListener listener){
		pageChangeListener.remove(listener);
	}
	public void addAngleChangeListener(AngleChangeListener listener){
		angleChangeListener.add(listener);
	}
	public void removeAngleChangeListener(AngleChangeListener listener){
		angleChangeListener.remove(listener);
	}
	public void addDownloadTiffListener(DownloadTiffListener listener){
		downloadListener.add(listener);
	}
	public void removeDownloadTiffListener(DownloadTiffListener listener){
		downloadListener.remove(listener);
	}
	public int getPage() {
		return getState().page;
	}
}

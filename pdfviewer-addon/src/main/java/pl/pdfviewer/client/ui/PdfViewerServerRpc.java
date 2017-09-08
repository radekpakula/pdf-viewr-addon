package pl.pdfviewer.client.ui;
import com.vaadin.shared.communication.ServerRpc;

public interface PdfViewerServerRpc extends ServerRpc {
	public void angleChange(Integer angle);
	public void pageChange(Integer page);
	public void download();
}

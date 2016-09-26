package pl.pdfviewer.widgetset.client.ui;

import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.HTML;

public class VPdfViewer extends HTML {
	private static final String CLASSNAME = "pdf-viewer";
	private Element root;
	private DivElement canvasDiv;
	private DivElement buttonBar;
	private Element counter;
	private DivElement previousBtn;
	private DivElement nextBtn;
	private DivElement counterBox;
	private DivElement sizeBox;
	private DivElement additionalBox;
	private String fileResource;
	private Element inputCounter;
	private Element pageText;
	private Element toText;
	private SelectElement selectSize;
	private DivElement increaseBtn;
	private DivElement decreaseBtn;
	private CanvasElement canvas;
	
	public VPdfViewer() {
		root = Document.get().createDivElement();
		root.setClassName(CLASSNAME);
		root.getStyle().setVisibility(Visibility.VISIBLE); // required for FF to show in popup windows repeatedly
		canvasDiv = Document.get().createDivElement();
		canvasDiv.setClassName(CLASSNAME+"-canvas dragscroll");
		canvasDiv.getStyle().setOverflow(Overflow.AUTO);
		canvasDiv.getStyle().setProperty("width","100%");
		canvasDiv.getStyle().setProperty("height","100%");
		canvas = Document.get().createCanvasElement();
		canvasDiv.appendChild(canvas);
		buttonBar = Document.get().createDivElement();
		buttonBar.setClassName(CLASSNAME+"-button-bar");
		
		Element groupSpan = Document.get().createSpanElement();
		groupSpan.setClassName(CLASSNAME+"-navigation");
		
		previousBtn = Document.get().createDivElement();
		previousBtn.setInnerHTML("Previous");
		previousBtn.addClassName("v-button v-widget v-button-previous");
		
		nextBtn = Document.get().createDivElement();
		nextBtn.setInnerHTML("Next");
		nextBtn.addClassName("v-button v-widget v-button-next");

		counterBox = Document.get().createDivElement(); 
		counterBox.setClassName(CLASSNAME+"-counter-box");
		
		pageText = Document.get().createSpanElement();
		pageText.setClassName("page");
		pageText.setInnerText("Page: ");
		
		toText = Document.get().createSpanElement();
		toText.setClassName("to-page");
		toText.setInnerText(" from ");
		
		inputCounter = Document.get().createTextInputElement();
		inputCounter.setClassName("v-textfield v-widget input-counter");
		counter = Document.get().createSpanElement();
		counter.setClassName("counter");
		sizeBox = Document.get().createDivElement(); 
		sizeBox.setClassName(CLASSNAME+"-size-box");
		
		additionalBox = Document.get().createDivElement(); 
		additionalBox.setClassName(CLASSNAME+"-additional-box");
		
		selectSize = Document.get().createSelectElement();
		String[][] vals = new String[][]{
			{"0"," Auto "},{"0.5"," 5%"},{"0.5"," 50%"},{"0.75"," 75%"},
			{"1"," 100%"},{"1.5"," 15%"},{"1.5"," 150%"},{"1.75"," 175%"},
			{""," 00%"},{".5"," 5%"},{".5"," 50%"},{"3"," 300%"},
			{"4"," 400%"},{"5"," 500%"},{"10"," 1000%"}};
		for (String[] e : vals) {
			OptionElement el =Document.get().createOptionElement();
			el.setValue(e[0]);
			el.setInnerHTML(e[1]);
			selectSize.appendChild(el);
		}
		increaseBtn = Document.get().createDivElement();
		increaseBtn.setInnerHTML("+");
		increaseBtn.addClassName("v-button v-widget v-button-increase");
		
		decreaseBtn = Document.get().createDivElement();
		decreaseBtn.setInnerHTML("-");
		decreaseBtn.addClassName("v-button v-widget v-button-decrease");
		
		sizeBox.appendChild(decreaseBtn);
		sizeBox.appendChild(increaseBtn);
		sizeBox.appendChild(selectSize);
		
		counterBox.appendChild(pageText);
		counterBox.appendChild(inputCounter);
		counterBox.appendChild(toText);
		counterBox.appendChild(counter);
		
		groupSpan.appendChild(previousBtn);
		groupSpan.appendChild(nextBtn);
		buttonBar.appendChild(groupSpan);
		buttonBar.appendChild(counterBox);
		buttonBar.appendChild(sizeBox);
		buttonBar.appendChild(additionalBox);
		
		
		inputCounter.setInnerText("1");
		
		root.appendChild(buttonBar);
		root.appendChild(canvasDiv);
		setElement(root);
		initTiff(this);
	}
	public native void initTiff(VPdfViewer instance)/*-{
		window.pdfviewer=new $wnd.PdfViewer(instance);
		console.log('create pdfviewer');
		pdfviewer.canvas = instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::canvas;
		pdfviewer.canvasDiv=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::canvasDiv;
		pdfviewer.root=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::root;
		pdfviewer.counter=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::counter;
		pdfviewer.input=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::inputCounter;
		pdfviewer.selectSize=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::selectSize;
		pdfviewer.bar=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::buttonBar;
		pdfviewer.nextBtn =instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::nextBtn;
		pdfviewer.prevBtn =instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::previousBtn;
		pdfviewer.increaseBtn=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::increaseBtn;
		pdfviewer.decreaseBtn=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::decreaseBtn;
		pdfviewer.selectSize=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::selectSize;
		pdfviewer.init();
		console.log('end bind data');
	}-*/;
	public native void loadResourcePdf(String fileName)/*-{
		console.log('read pdfviewer = ');
		console.log(window.pdfviewer);
		if(window.pdfviewer.fileName==null || window.pdfviewer.fileName!=fileName){
			$wnd.PDFJS.disableStream = true;
			$wnd.PDFJS.workerSrc ='APP/PUBLISHED/pdf.worker.js';
			$wnd.PDFJS.getDocument(fileName).then(function(pdf) {
				console.log('end load doc');
			  	window.pdfviewer.pdfFile = pdf;
			  	window.pdfviewer.fileName=fileName;
			  	window.pdfviewer.pageCount=pdf.numPages;
			  	console.log('display page');
			  	if(window.pdfviewer.pageNumber==0 && pdf.numPages>0){
			  		window.pdfviewer.pageNumber=1;
			  	}
			  	window.pdfviewer.showPdfPage(window.pdfviewer.pageNumber);
			});
		}
	}-*/;
	public void setResourceFile(String fileResource) {
		this.fileResource=fileResource;
		loadResourcePdf(fileResource);
	}
	public void setPage(int page) {
		updatePage(page);
	}
	public native void updatePage(int page)/*-{
		window.pdfviewer.pageNumber=page;
		if(window.pdfviewer.pdfFile!=null){
			window.pdfviewer.showPdfPage(page);
		}
	}-*/;
	public void setPreviousButtonCaption(String caption) {
		updateInnerHtml(caption,previousBtn);
	}
	public void setNextButtonCaption(String caption) {
		updateInnerHtml(caption,nextBtn);
	}
	public void setPageCaption(String caption) {
		updateInnerHtml(caption,pageText);
	}
	public void setToPageCaption(String caption) {
		updateInnerHtml(caption,toText);
	}
	public native void updateInnerHtml(String caption, Element elem)/*-{
		if(caption!=null && caption!=''){
			elem.innerHTML=caption;
		}
	}-*/;
	public void setIncreaseButtonCaption(String caption) {
		updateInnerHtml(caption,increaseBtn);
	}
	public void setDecreaseButtonCaption(String caption) {
		updateInnerHtml(caption,decreaseBtn);
	}
}
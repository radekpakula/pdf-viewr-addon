package pl.pdfviewer.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
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
	private Element inputCounter;
	private Element pageText;
	private Element toText;
	private SelectElement selectSize;
	private DivElement increaseBtn;
	private DivElement decreaseBtn;
	private CanvasElement canvas;

	private String fileName;
	private String pageCount;
	private String inputValue;
	private String selectSizeValue;
	private JavaScriptObject jsObject;
	public VPdfViewer() {
		root = Document.get().createDivElement();
		root.setClassName(CLASSNAME);
		root.getStyle().setVisibility(Visibility.VISIBLE); // required for FF to
															// show in popup
															// windows
															// repeatedly
		canvasDiv = Document.get().createDivElement();
		canvasDiv.setClassName(CLASSNAME + "-canvas dragscroll");
		canvasDiv.getStyle().setOverflow(Overflow.AUTO);
		canvasDiv.getStyle().setProperty("width", "100%");
		canvasDiv.getStyle().setProperty("height", "100%");
		canvas = Document.get().createCanvasElement();
		canvasDiv.appendChild(canvas);
		buttonBar = Document.get().createDivElement();
		buttonBar.setClassName(CLASSNAME + "-button-bar");

		Element groupSpan = Document.get().createSpanElement();
		groupSpan.setClassName(CLASSNAME + "-navigation");

		previousBtn = Document.get().createDivElement();
		previousBtn.setInnerHTML("Previous");
		previousBtn.addClassName("v-button v-widget v-button-previous");

		nextBtn = Document.get().createDivElement();
		nextBtn.setInnerHTML("Next");
		nextBtn.addClassName("v-button v-widget v-button-next");

		counterBox = Document.get().createDivElement();
		counterBox.setClassName(CLASSNAME + "-counter-box");

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
		sizeBox.setClassName(CLASSNAME + "-size-box");

		additionalBox = Document.get().createDivElement();
		additionalBox.setClassName(CLASSNAME + "-additional-box");

		selectSize = Document.get().createSelectElement();
		selectSize.setClassName(CLASSNAME + "-select-size");
		String[][] vals = new String[][] { { "0", " Auto " }, { "0.25", " 25%" }, { "0.5", " 50%" }, { "0.75", " 75%" },
				{ "1", " 100%" }, { "1.25", " 125%" }, { "1.5", " 150%" }, { "1.75", " 175%" }, { "2", " 200%" },
				{ "2.25", " 225%" }, { "2.5", " 250%" }, { "3", " 300%" }, { "4", " 400%" }, { "5", " 500%" },
				{ "10", " 1000%" } };
		for (String[] e : vals) {
			OptionElement el = Document.get().createOptionElement();
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
		var pdfviewer=new $wnd.PdfViewer();
		pdfviewer.canvas = instance.@pl.pdfviewer.client.ui.VPdfViewer::canvas;
		pdfviewer.canvasDiv=instance.@pl.pdfviewer.client.ui.VPdfViewer::canvasDiv;
		pdfviewer.root=instance.@pl.pdfviewer.client.ui.VPdfViewer::root;
		pdfviewer.counter=instance.@pl.pdfviewer.client.ui.VPdfViewer::counter;
		pdfviewer.input=instance.@pl.pdfviewer.client.ui.VPdfViewer::inputCounter;
		pdfviewer.selectSize=instance.@pl.pdfviewer.client.ui.VPdfViewer::selectSize;
		pdfviewer.bar=instance.@pl.pdfviewer.client.ui.VPdfViewer::buttonBar;
		pdfviewer.nextBtn =instance.@pl.pdfviewer.client.ui.VPdfViewer::nextBtn;
		pdfviewer.prevBtn =instance.@pl.pdfviewer.client.ui.VPdfViewer::previousBtn;
		pdfviewer.increaseBtn=instance.@pl.pdfviewer.client.ui.VPdfViewer::increaseBtn;
		pdfviewer.decreaseBtn=instance.@pl.pdfviewer.client.ui.VPdfViewer::decreaseBtn;
		pdfviewer.selectSize=instance.@pl.pdfviewer.client.ui.VPdfViewer::selectSize;
		instance.@pl.pdfviewer.client.ui.VPdfViewer::setJsObject(Lcom/google/gwt/core/client/JavaScriptObject;)(pdfviewer);
		pdfviewer.init();
	}-*/;

	public native void loadResourcePdf(String fileName, VPdfViewer instance)/*-{
		var pdfviewer = instance.@pl.pdfviewer.client.ui.VPdfViewer::jsObject;
		pdfviewer.work=false;
		if((pdfviewer.fileName==null || pdfviewer.fileName!=fileName) && fileName!=null){
			$wnd.PDFJS.disableStream = true;
			$wnd.PDFJS.workerSrc ='APP/PUBLISHED/pdf.worker.js';
			$wnd.PDFJS.getDocument(fileName).then(function(pdf) {
			  	pdfviewer.pdfFile = pdf;
			  	pdfviewer.fileName=fileName;
			  	pdfviewer.pageCount=pdf.numPages;
			  	if(pdfviewer.pageNumber==0 && pdf.numPages>0){
			  		pdfviewer.pageNumber=1;
			  	}
			  	pdfviewer.showPdfPage(pdfviewer.pageNumber);
			});
		}
	
	}-*/;

	public void setResourceFile(String fileName) {
		this.fileName = fileName;
		loadResourcePdf(fileName, this);
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public void setPage(int page) {
		updatePage(page);
	}

	public native void updatePage(int page)/*-{
											}-*/;

	public void setPreviousButtonCaption(String caption) {
		updateInnerHtml(caption, previousBtn);
	}

	public void setNextButtonCaption(String caption) {
		updateInnerHtml(caption, nextBtn);
	}

	public void setPageCaption(String caption) {
		updateInnerHtml(caption, pageText);
	}

	public void setToPageCaption(String caption) {
		updateInnerHtml(caption, toText);
	}

	public native void updateInnerHtml(String caption, Element elem)/*-{
		if(caption!=null && caption!=''){
		elem.innerHTML=caption;
		}
	}-*/;

	public void setIncreaseButtonCaption(String caption) {
		updateInnerHtml(caption, increaseBtn);
	}

	public void setDecreaseButtonCaption(String caption) {
		updateInnerHtml(caption, decreaseBtn);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getSelectSizeValue() {
		return selectSizeValue;
	}

	public void setSelectSizeValue(String selectSizeValue) {
		this.selectSizeValue = selectSizeValue;
	}

	public JavaScriptObject getJsObject() {
		return jsObject;
	}

	public void setJsObject(JavaScriptObject jsObject) {
		this.jsObject = jsObject;
	}
}
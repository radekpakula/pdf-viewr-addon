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
			{"0"," Auto "},{"0.25"," 25%"},{"0.5"," 50%"},{"0.75"," 75%"},
			{"1"," 100%"},{"1.25"," 125%"},{"1.5"," 150%"},{"1.75"," 175%"},
			{"2"," 200%"},{"2.25"," 225%"},{"2.5"," 250%"},{"3"," 300%"},
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
		window.canvas2 = instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::canvas;
		window.canvas2Div=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::canvasDiv;
		window.root2=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::root;
		window.counter2=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::counter;
		window.input2=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::inputCounter;
		window.selectSize2=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::selectSize;
		window.bar2=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::buttonBar;
		
		var nextBtn =instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::nextBtn;
		var prevBtn =instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::previousBtn;
		var increaseBtn=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::increaseBtn;
		var decreaseBtn=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::decreaseBtn;
		
		window.showPdfPage=function(pageNumber){
			console.log('show page: '+pageNumber);
			if(window.pdfFile2==null){
				window.counter2.innerHTML=0;
				window.input2.value=0;
				console.log('null file');
				return;
			}
			if(pageNumber<=window.pageCount2 && pageNumber>0){
				console.log('draw canvas');
			    window.pdfFile2.getPage(pageNumber).then(function(page) {
				console.log('draw canvas function');
			    	window.page2=page;
			    	var viewport = page.getViewport(1);
			    	window.canvas2.height = viewport.height;
			    	window.canvas2.width = viewport.width;
			    	window.updateSize2();
			    	window.currentPage2=pageNumber;
			    	window.counter2.innerHTML=window.pdfFile2.numPages;
			    	window.input2.value=pageNumber;
			    	$wnd.dragscroll.reset();
			    	console.log('end draw canvas');
				});
			}
		}
		nextBtn.onclick=function(){window.showPdfPage(window.currentPage2+1)};
		prevBtn.onclick=function(){window.showPdfPage(window.currentPage2-1)};
		increaseBtn.onclick=function(){
			var list=window.selectSize2;
			list.value= list.value==0 ? 1 : list.value;
			if(list.selectedIndex<15){
				list.value=list.options[list.selectedIndex+1].value;
				window.updateSize2();
			}
		};
		decreaseBtn.onclick=function(){
			var list=window.selectSize2;
			list.value= list.value==0 ? 1 : list.value;
			if(list.selectedIndex>1){
				list.value=list.options[list.selectedIndex-1].value;
				window.updateSize2();
			}
		};
		
		window.checkInput2=function(){
		    var value = parseInt(window.input2.value);
  			if(isNaN(value)){
  				window.input2.value=1;
  			}else if(value<1){
    			window.input2.value=1;
			}else if(value>window.pageCount2){
				window.input2.value=window.pageCount2;
			}
			window.showPdfPage(parseInt(window.input2.value));
		};
		window.input2.onkeypress = function(e){
		    if (!e) e = window.event;
		    var keyCode = e.keyCode || e.which;
		    if (keyCode == '13'){
		      window.checkInput2();
		      return false;
		    }
	    };
	    
		window.input2.addEventListener('blur', function() { 
			window.checkInput2();
		});
		window.selectSize2=instance.@pl.pdfviewer.widgetset.client.ui.VPdfViewer::selectSize;
		window.selectSize2.onchange=function(){
			window.updateSize2();
		};
		window.updateSize2=function(){
		var value = parseFloat(window.selectSize2.value);
			if(window.selectSize2.value=='0'){
				value=1;
				var autoWidth = window.page2.getViewport(1).width;
				var boxWidth = window.root2.offsetWidth-20;
				value=boxWidth/autoWidth;
			}
			var viewport = window.page2.getViewport(value);
	    	window.canvas2.height = viewport.height;
	    	window.canvas2.width = viewport.width;
			var renderContext = {
	      		canvasContext: window.canvas2.getContext('2d'),
	      		viewport: viewport
	    	};
	    	window.page2.render(renderContext);
	    	window.canvas2Div.style.height=window.root2.offsetHeight-window.bar2.offsetHeight+'px';
		};
		
	}-*/;
	public native void loadResourcePdf(String fileName)/*-{
	console.log('init load file '+fileName+'  '+window.fileName2);
		if(fileName!=null && fileName!=window.fileName2){
			console.log('init file');
			window.fileName2=fileName;
			window.pageNumbe2r=1;
			$wnd.PDFJS.disableStream = true;
			$wnd.PDFJS.workerSrc ='APP/PUBLISHED/pdf.worker.js';
			$wnd.PDFJS.getDocument(fileName).then(function(pdf) {
				console.log('load succesfull');
			  	window.pdfFile2 = pdf;
			  	window.fileName2=fileName;
			  	window.pageCount2=window.pdfFile2.numPages;
			  	window.input2.value=1;
			  	window.selectSize2.value=0;
			  	window.showPdfPage(window.pageNumbe2r);
			});
		}
	}-*/;
	public void setResourceFile(String fileResource) {
		this.fileResource=fileResource;
		loadResourcePdf(fileResource);
	}
	public native void resetFileName()/*-{
		window.fileName2=null;
	}-*/;
	public void setPage(int page) {
		updatePage(page);
	}
	public native void updatePage(int page)/*-{
		window.pageNumbe2r=page;
		window.showPdfPage(page);
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
	@Override
	protected void onDetach() {
		super.onDetach();
		resetFileName();
	}
}
function PdfViewer(instance){
	this.canvas=null;
	this.canvasDiv=null;
	this.root=null;
	this.counter=null;
	this.input=null;
	this.selectSize=null;
	this.bar=null;
	this.nextBtn=null;
	this.prevBtn=null;
	this.increaseBtn=null;
	this.decreaseBtn=null;
	this.selectSize=null;
	this.fileName=null;
	this.pdfFile=null;
	this.pageNumber=0;
	this.pageCount0=null;
	this.fileName=null;
}
PdfViewer.prototype.showPdfPage=function(pageNumber){
	console.log('show pdf file');
	console.log(this.pdfFile);
	if(this.pdfFile==null){
		console.log('null file');
		this.counter.innerHTML=0;
		this.input.value=0;
		return;
	}
	console.log('dupa');
	console.log(pageNumber+"  "+this.pageCount);
	console.log('test: '+pageNumber+' '+this.pageCount);
	if(pageNumber<=this.pageCount && pageNumber>0){
		console.log('show file '+pageNumber);
		console.log(this.canvas);
		console.log(this);
		console.log(this.pdfFile.getPage(pageNumber));
	    this.pdfFile.getPage(pageNumber).then(function(page){
	    	console.log('fuck');
	    	console.log(page);
	    	console.log(this);
	    	console.log(window);
	    });
	}
	console.log('end refresh');
}
PdfViewer.prototype.refreshPage=function(page){
    	console.log('load page file ');
    	console.log(canvas);
    	console.log(page);
    	console.log('start page viewport ');
    	var viewport = page.getViewport(1);
    	console.log('page viewport ');
    	console.log('canvas');
    	console.log(this.canvas);
    	this.canvas.height = viewport.height;
    	this.canvas.width = viewport.width;
    	this.currentPage=pageNumber;
    	this.counter.innerHTML=this.pdfFile.numPages;
    	this.input.value=pageNumber;
    	console.log('call updatesize')
    	this.updateSize();
    	console.log('end call')
    	console.log('current page: '+this.currentPage);
    	$wnd.dragscroll.reset();
};
PdfViewer.prototype.checkInput=function(){
    var value = parseInt(this.input.value);
	if(isNaN(value)){
		this.input.value=1;
	}else if(value<1){
		this.input.value=1;
	}else if(value>this.pageCount){
		this.input.value=this.pageCount;
	}
	this.setPage(this.input.value);
};
PdfViewer.prototype.updateSize=function(){
	var value = parseFloat(this.selectSize.value);
		if(this.selectSize.value=='0'){
			value=1;
			var autoWidth = this.page.getViewport(1).width;
			var boxWidth = this.root.offsetWidth-0;
			console.log(autoWidth+' '+boxWidth);
			value=boxWidth/autoWidth;
		}
		var viewport = this.page.getViewport(value);
    	this.canvas.height = viewport.height;
    	this.canvas.width = viewport.width;
		var renderContext = {
      		canvasContext: this.canvas.getContext('d'),
      		viewport: viewport
    	};
    	this.page.render(renderContext);
    	this.canvasDiv.style.height=this.root.offsetHeight-this.bar.offsetHeight+'px';
};
PdfViewer.prototype.init=function(){
	console.log('init function');
	console.log('canvas');
	console.log(this.canvas);
	this.nextBtn.onclick=function(){console.log('current page '+this.currentPage);this.showPdfPage(this.currentPage+1)};
	this.prevBtn.onclick=function(){console.log('current page '+this.currentPage);this.showPdfPage(this.currentPage-1)};
	console.log(this.nextBtn);
	this.increaseBtn.onclick=function(){
		var list=this.selectSize;
		list.value= list.value==0 ? 1 : list.value;
		if(list.selectedIndex<15){
			list.value=list.options[list.selectedIndex+1].value;
			this.updateSize();
		}
	};
	this.decreaseBtn.onclick=function(){
		var list=this.selectSize;
		list.value= list.value==0 ? 1 : list.value;
		if(list.selectedIndex>1){
			list.value=list.options[list.selectedIndex-1].value;
			this.updateSize();
		}
	};
	this.input.onkeypress = function(e){
	    if (!e) e = window.event;
	    var keyCode = e.keyCode || e.which;
	    if (keyCode == '13'){
	      this.checkInput();
	      return false;
	    }
	};
	this.input.addEventListener('blur', function() { 
		this.checkInput();
	});
	this.selectSize.onchange=function(){
		this.updateSize();
	};
}

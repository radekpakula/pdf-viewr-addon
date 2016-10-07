function PdfViewer(){
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
	this.pdfPage=null;
	this.pageNumber=0;
	this.pageCount0=null;
	this.currentPage=0;
	this.fileName=null;
	this.work=false;
	console.log('init pdfVViewer constructor');
}
PdfViewer.prototype.showPdfPage=function(pageNumber){
	console.log('show pdf file numpage '+pageNumber);
	var self = this;
	if(this.pdfFile==null){
		console.log('null file');
		this.counter.innerHTML=0;
		this.input.value=0;
		return;
	}
	
	if(pageNumber<=this.pageCount && pageNumber>0){
		console.log('show file '+pageNumber+ 'canvas '+this.canvas);
		this.currentPage=pageNumber;
		this.counter.innerHTML=this.pdfFile.numPages;
		this.input.value=pageNumber;
		
	    this.pdfFile.getPage(pageNumber).then(function(page){
	    	console.log('draw canvas function');
	    	self.page=page;
	    	console.log('init update size');
	    	self.updateSize();
	    	window.dragscroll.reset();
	    	console.log('end draw canvas');
	    	self.work=false
	    });
		};
	console.log('end refresh');
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
	if(this.work){
		return;
	}
	console.log('update size');
	var value = parseFloat(this.selectSize.value);
	console.log(value);
	if(this.selectSize.value=='0'){
		value=1;
		var autoWidth = this.page.getViewport(1).width;
		var boxWidth = this.root.offsetWidth-0;
		console.log(autoWidth+' '+boxWidth);
		value=boxWidth/autoWidth;
	}
	this.canvasDiv.removeChild(this.canvas);
	this.canvas = document.createElement('canvas');
	this.canvasDiv.appendChild(this.canvas);
	var viewport = this.page.getViewport(value);
	this.canvas.height = viewport.height;
	this.canvas.width = viewport.width;
	
	
	var renderContext = {
  		canvasContext: this.canvas.getContext('2d'),
  		viewport: viewport,
	};
	this.page.render(renderContext);
	console.log(renderContext);
	this.canvasDiv.style.height=this.root.offsetHeight-this.bar.offsetHeight+'px';
	console.log('end update size');
};
PdfViewer.prototype.next=function(){
	console.log(this);
}
PdfViewer.prototype.init=function(){
	var self = this;
	this.nextBtn.onclick=function(){self.showPdfPage(self.currentPage+1)};
	this.prevBtn.onclick=function(){self.showPdfPage(self.currentPage-1)};
	this.increaseBtn.onclick=function(){
		var list=self.selectSize;
		list.value= list.value==0 ? 1 : list.value;
		if(list.selectedIndex<15){
			list.value=list.options[list.selectedIndex+1].value;
			console.log('incrase');
			self.updateSize();
		}
	};
	this.decreaseBtn.onclick=function(){
		var list=self.selectSize;
		list.value= list.value==0 ? 1 : list.value;
		if(list.selectedIndex>1){
			list.value=list.options[list.selectedIndex-1].value;
			console.log('decrase');
			self.updateSize();
		}
	};
	this.input.onkeypress = function(e){
	    if (!e) e = window.event;
	    var keyCode = e.keyCode || e.which;
	    if (keyCode == '13'){
	    	self.checkInput();
	      return false;
	    }
	};
	this.input.addEventListener('blur', function() { 
		self.checkInput();
	});
	this.selectSize.onchange=function(){
		console.log('on change');
		self.updateSize();
	};
};

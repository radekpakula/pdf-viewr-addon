# pdf-viewer-addon
	-Addon works on client side.
	-Display file only with .pdf (pdf) format
    -Supports multipage
    
-based on script http://mozilla.github.io/pdf.js/
-dragandscroll http://github.com/asvd/intence

# Simple usage
     PdfViewer c = new PdfViewer(file);
	 layout.addComponent(c);

# Additional method
	setPage(int i)
	setPreviousPageCaption(String htmlCaption)
	setNextPageCaption(String htmlCaption)
	setPageCaption(String htmlCaption)
	setToPageCaption(String htmlCaption)
	setIncreaseButtonCaption(String htmlCaption)
	setDecreaseButtonCaption(String htmlCaption)
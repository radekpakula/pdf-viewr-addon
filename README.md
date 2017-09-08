# PdfViewer Add-on for Vaadin

PdfViewer is a UI component add-on for Vaadin 7,8.

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/pdfviewer

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
	setNextAngleButtonCaption(String htmlCaption)
	setBackAngleButtonCaption(String htmlCaption)	
	setPrintButtonCaption(String htmlCaption)
	setDownloadButtonCaption(String htmlCaption) 
	setAngleButtonVisible(boolean visible)
	setDownloadBtnVisible(boolean visible)
	addPageChangeListener(PageChangeListener listener)
	removePageChangeListener(PageChangeListener listener)
	addAngleChangeListener(AngleChangeListener listener)
	removeAngleChangeListener(AngleChangeListener listener)
	addDownloadTiffListener(DownloadTiffListener listener)
	removeDownloadTiffListener(DownloadTiffListener listener)

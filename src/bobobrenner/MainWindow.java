package bobobrenner;

import java.io.*;
import java.util.*;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.*;



import com.cloudgarden.resource.SWTResourceManager;

public class MainWindow {

	protected Shell shell;
	Properties appSettings = new Properties();
	Cursor defaultCursor; // To change the cursor to an arrow at any point after MainWindow() has executed, use
							// shell.setCursor(defaultCursor);
	Cursor waitCursor; // To change the cursor to an hourglass at any point after MainWindow() has executed, use
						// shell.setCursor(waitCursor);
	private Composite clientArea;
	private Label statusText;

	
	//Specific variables
	
	
	private Button listInput;
	private Text warning1;
	private Text title;
	private Text yTitle;
	private Text yAngleText;
	private Text xTitle;
	private Text xAngleText;
	private Text targetTitle;
	private Text Warning;
	private Text listtitle;
	private Text cList;
	private Text title10;
	private MenuItem aboutMenuItem;
	private Menu helpMenu;
	private MenuItem helpMenuItem;
	private MenuItem exitMenuItem;
	private MenuItem closeFileMenuItem;
	private MenuItem saveFileMenuItem;
	private MenuItem newFileMenuItem;
	private MenuItem openFileMenuItem;
	private ToolItem newToolItem;
	private ToolItem saveToolItem;
	private ToolItem openToolItem;
	private ToolBar toolBar;
	@SuppressWarnings("unused")
	private MenuItem fileMenuSep2;
	@SuppressWarnings("unused")
	private MenuItem fileMenuSep1;
	private Label txtStatus;
	private Composite statusArea;
	private Button angleToggle;
	private Text variationOnDeg;
	private Text density;
	private Text title7;
	private Button append;
	private Text iterations;
	private Text title5;
	private Text title4;
	private Text title3;
	private Text text2;
	private Text title2;
	private Text title1;
	private Text text;
	private Button goButton;
	private Text yUpperRange;
	private Text text1;
	private Text yLowerRange;
	private Text xUpperRange;
	private Text xLowerRange;
	private Menu fileMenu;
	private MenuItem fileMenuItem;
	private boolean appendToggle=false;
	private boolean angleToggleBool=false;
	ArrayList<String> printer = new ArrayList<String>();
	int count=0;
	int centerOfIcelandX;   
	int centerOfIcelandY;
	private boolean listInputMode=false;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();

		setPreferences();
		waitCursor = shell.getDisplay().getSystemCursor(SWT.CURSOR_WAIT);
		defaultCursor = shell.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		clientArea.setFocus();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Open the window.
	 */
	public void openForUnitTesting() {
		createContents();

		setPreferences();
		waitCursor = shell.getDisplay().getSystemCursor(SWT.CURSOR_WAIT);
		defaultCursor = shell.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
		clientArea.setFocus();

		shell.open();
		shell.layout();
	}


	public void closeForUnitTesting() {
		shell.dispose();
	}


	// Load application settings from .ini file
	private void setPreferences() {
		try {
			appSettings.load(new FileInputStream("appsettings.ini"));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		
		int width = 740;
			
		int height=580;
		Rectangle screenBounds = shell.getDisplay().getBounds();
		int defaultTop = (screenBounds.height - height) / 2;
		int defaultLeft = (screenBounds.width - width) / 2;
		int top = Integer.parseInt(appSettings.getProperty("top", String.valueOf(defaultTop)));
		int left = Integer.parseInt(appSettings.getProperty("left", String.valueOf(defaultLeft)));
		shell.setSize(width, height);
		shell.setLocation(left, top);
		saveShellBounds();
	}


	// Save window location in appSettings hash table
	private void saveShellBounds() {
		// Save window bounds in app settings
		Rectangle bounds = shell.getBounds();
		appSettings.setProperty("top", String.valueOf(bounds.y));
		appSettings.setProperty("left", String.valueOf(bounds.x));
		appSettings.setProperty("width", String.valueOf(bounds.width));
		appSettings.setProperty("height", String.valueOf(bounds.height));
	}


	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.addControlListener(new ShellControlListener());
		shell.addDisposeListener(new ShellDisposeListener());
		shell.setImage(SWTResourceManager.getImage("images/16x16.png"));
		shell.setSize(640, 480);
		shell.setText("Random Double Generator");
		GridLayout gl_shell = new GridLayout(1, false);
		gl_shell.marginHeight = 3;
		gl_shell.marginWidth = 3;
		shell.setLayout(gl_shell);

		Menu menu1 = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu1);

		MenuItem fileMenuItem = new MenuItem(menu1, SWT.CASCADE);
		fileMenuItem.setText("&File");

		Menu menu_1 = new Menu(fileMenuItem);
		fileMenuItem.setMenu(menu_1);

		MenuItem newFileMenuItem = new MenuItem(menu_1, SWT.NONE);
		newFileMenuItem.setImage(SWTResourceManager.getImage( "/images/new.gif"));
		newFileMenuItem.setText("&New");

		MenuItem openFileMenuItem = new MenuItem(menu_1, SWT.NONE);
		openFileMenuItem.addSelectionListener(new OpenFileMenuItemSelectionListener());
		openFileMenuItem.setImage(SWTResourceManager.getImage("/images/open.gif"));
		openFileMenuItem.setText("&Open");

		MenuItem closeFileMenuItem = new MenuItem(menu_1, SWT.NONE);
		closeFileMenuItem.setText("&Close");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem saveFileMenuItem = new MenuItem(menu_1, SWT.NONE);
		saveFileMenuItem.setImage(SWTResourceManager.getImage("/images/save.gif"));
		saveFileMenuItem.setText("&Save");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem exitMenuItem = new MenuItem(menu_1, SWT.NONE);
		exitMenuItem.addSelectionListener(new ExitMenuItemSelectionListener());
		exitMenuItem.setText("E&xit");

		MenuItem helpMenuItem = new MenuItem(menu1, SWT.CASCADE);
		helpMenuItem.setText("&Help");

		Menu menu_2 = new Menu(helpMenuItem);
		helpMenuItem.setMenu(menu_2);

		MenuItem aboutMenuItem = new MenuItem(menu_2, SWT.NONE);
		aboutMenuItem.addSelectionListener(new AboutMenuItemSelectionListener());
		aboutMenuItem.setText("&About");

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		ToolItem newToolItem = new ToolItem(toolBar, SWT.NONE);
		newToolItem.setToolTipText("New");
		newToolItem.setImage(SWTResourceManager.getImage("/images/new.gif"));

		ToolItem openToolItem = new ToolItem(toolBar, SWT.NONE);
		openToolItem.addSelectionListener(new OpenToolItemSelectionListener());
		openToolItem.setToolTipText("Open");
		openToolItem.setImage(SWTResourceManager.getImage("/images/open.gif"));

		ToolItem saveToolItem = new ToolItem(toolBar, SWT.NONE);
		saveToolItem.setToolTipText("Save");
		saveToolItem.setImage(SWTResourceManager.getImage("/images/save.gif"));

		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_IN);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		clientArea = new Composite(shell, SWT.NONE);
		clientArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_IN);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Composite statusArea = new Composite(shell, SWT.NONE);
		//statusArea.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		FillLayout fl_statusArea = new FillLayout(SWT.HORIZONTAL);
		fl_statusArea.marginWidth = 2;
		fl_statusArea.marginHeight = 2;
		statusArea.setLayout(fl_statusArea);
		GridData gd_statusArea = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_statusArea.widthHint = 125;
		statusArea.setLayoutData(gd_statusArea);

		statusText = new Label(statusArea, SWT.NONE);
		statusText.setText("Ready");
		
		//Non-Default components
		
		
		txtStatus = new Label(statusArea, SWT.BORDER);
		txtStatus.setText("Count: 0");
		GridData txtStatusLData = new GridData();
		txtStatusLData.horizontalAlignment = GridData.FILL;
		txtStatusLData.grabExcessHorizontalSpace = true;
		txtStatusLData.verticalIndent = 3;
		txtStatus.setLayoutData(txtStatusLData);
		{
			warning1 = new Text(clientArea, SWT.NONE);
			FormData warning1LData = new FormData();
			warning1LData.left =  new FormAttachment(0, 1000, 532);
			warning1LData.top =  new FormAttachment(0, 1000, 240);
			warning1LData.width = 121;
			warning1LData.height = 15;
			warning1.setLayoutData(warning1LData);
			warning1.setText("(Not valid in list mode)");
		}
		{
			title = new Text(clientArea, SWT.NONE);
			FormData titleLData = new FormData();
			titleLData.left =  new FormAttachment(0, 1000, 67);
			titleLData.top =  new FormAttachment(0, 1000, 336);
			titleLData.width = 216;
			titleLData.height = 15;
			title.setLayoutData(titleLData);
			title.setText("Output is X,Y,Directional heading");
			title.setEditable(false);
		}
		{
			yTitle = new Text(clientArea, SWT.NONE);
			FormData yTitleLData = new FormData();
			yTitleLData.left =  new FormAttachment(0, 1000, 527);
			yTitleLData.top =  new FormAttachment(0, 1000, 280);
			yTitleLData.width = 7;
			yTitleLData.height = 15;
			yTitle.setLayoutData(yTitleLData);
			yTitle.setText("Y");
		}
		{
			yAngleText = new Text(clientArea, SWT.NONE);
			FormData yAngleTextLData = new FormData();
			yAngleTextLData.left =  new FormAttachment(0, 1000, 510);
			yAngleTextLData.top =  new FormAttachment(0, 1000, 301);
			yAngleTextLData.width = 41;
			yAngleTextLData.height = 15;
			yAngleText.setLayoutData(yAngleTextLData);
			yAngleText.setText("40");
		}
		{
			xTitle = new Text(clientArea, SWT.NONE);
			FormData xTitleLData = new FormData();
			xTitleLData.left =  new FormAttachment(0, 1000, 417);
			xTitleLData.top =  new FormAttachment(0, 1000, 280);
			xTitleLData.width = 7;
			xTitleLData.height = 15;
			xTitle.setLayoutData(xTitleLData);
			xTitle.setText("X");
		}
		{
			xAngleText = new Text(clientArea, SWT.NONE);
			FormData xAngleTextLData = new FormData();
			xAngleTextLData.left =  new FormAttachment(0, 1000, 399);
			xAngleTextLData.top =  new FormAttachment(0, 1000, 301);
			xAngleTextLData.width = 45;
			xAngleTextLData.height = 15;
			xAngleText.setLayoutData(xAngleTextLData);
			xAngleText.setText("69");
		}
		{
			targetTitle = new Text(clientArea, SWT.NONE);
			FormData targetTitleLData = new FormData();
			targetTitleLData.left =  new FormAttachment(0, 1000, 450);
			targetTitleLData.top =  new FormAttachment(0, 1000, 274);
			targetTitleLData.width = 65;
			targetTitleLData.height = 15;
			targetTitle.setLayoutData(targetTitleLData);
			targetTitle.setText("Target Point");
			targetTitle.setEditable(false);
		}
		{
			Warning = new Text(clientArea, SWT.NONE);
			FormData WarningLData = new FormData();
			WarningLData.left =  new FormAttachment(0, 1000, 96);
			WarningLData.top =  new FormAttachment(0, 1000, 363);
			WarningLData.width = 446;
			WarningLData.height = 15;
			Warning.setLayoutData(WarningLData);
			Warning.setText("File handeling methods have not been added yet so don't use save,new,open, etc yet");
			Warning.setEditable(false);
		}
		{
			listtitle = new Text(clientArea, SWT.NONE);
			FormData listtitleLData = new FormData();
			listtitleLData.left =  new FormAttachment(0, 1000, 687);
			listtitleLData.top =  new FormAttachment(0, 1000, 27);
			listtitleLData.width = 289;
			listtitleLData.height = 15;
			listtitle.setLayoutData(listtitleLData);
			listtitle.setText("Format is lowerX upperX lowerY upperY relativeDensity");
			listtitle.setEditable(false);
		}
		{
			listInput = new Button(clientArea, SWT.CHECK | SWT.LEFT);
			FormData listInputLData = new FormData();
			listInputLData.left =  new FormAttachment(0, 1000, 687);
			listInputLData.top =  new FormAttachment(0, 1000, 6);
			listInputLData.width = 121;
			listInputLData.height = 16;
			listInput.setLayoutData(listInputLData);
			listInput.setText("Check for list input");
			
		}
		{
			title10 = new Text(clientArea, SWT.NONE);
			FormData title10LData = new FormData();
			title10LData.left =  new FormAttachment(0, 1000, 379);
			title10LData.top =  new FormAttachment(0, 1000, 342);
			title10LData.width = 116;
			title10LData.height = 15;
			title10.setLayoutData(title10LData);
			title10.setText("Variation of degree:");
			title10.setEditable(false);
		}
		{
			angleToggle = new Button(clientArea, SWT.CHECK | SWT.LEFT);
			FormData angleToggleLData = new FormData();
			angleToggleLData.left =  new FormAttachment(0, 1000, 436);
			angleToggleLData.top =  new FormAttachment(0, 1000, 225);
			angleToggleLData.width = 99;
			angleToggleLData.height = 16;
			angleToggle.setLayoutData(angleToggleLData);
			angleToggle.setText("Include Angle?");
			
		
		{
			variationOnDeg = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData variationOnDegLData = new FormData();
			variationOnDegLData.left =  new FormAttachment(0, 1000, 522);
			variationOnDegLData.top =  new FormAttachment(0, 1000, 342);
			variationOnDegLData.width = 44;
			variationOnDegLData.height = 15;
			variationOnDeg.setLayoutData(variationOnDegLData);
			variationOnDeg.setText("12");
		}
		{
			FormData densityLData = new FormData();
			densityLData.left =  new FormAttachment(0, 1000, 557);
			densityLData.top =  new FormAttachment(0, 1000, 191);
			densityLData.width = 62;
			densityLData.height = 15;
			density = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			density.setLayoutData(densityLData);
			density.setText("2");
		}
		{
			title7 = new Text(clientArea, SWT.NONE);
			FormData title7LData = new FormData();
			title7LData.left =  new FormAttachment(0, 1000, 456);
			title7LData.top =  new FormAttachment(0, 1000, 191);
			title7LData.width = 83;
			title7LData.height = 15;
			title7.setLayoutData(title7LData);
			title7.setText("Relative Density:");
			title7.setEditable(false);
		}
		{
			FormData appendLData = new FormData();
			appendLData.left =  new FormAttachment(0, 1000, 547);
			appendLData.top =  new FormAttachment(0, 1000, 225);
			appendLData.width = 78;
			appendLData.height = 16;
			append = new Button(clientArea, SWT.CHECK | SWT.LEFT);
			append.setLayoutData(appendLData);
			append.setText("Append?");
			
		}
		{
			iterations = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData iterationsLData = new FormData();
			iterationsLData.left =  new FormAttachment(0, 1000, 487);
			iterationsLData.top =  new FormAttachment(0, 1000, 133);
			iterationsLData.width = 65;
			iterationsLData.height = 20;
			iterations.setLayoutData(iterationsLData);
			iterations.setText("1");
		}
		{
			title5 = new Text(clientArea, SWT.NONE);
			FormData title5LData = new FormData();
			title5LData.left =  new FormAttachment(0, 1000, 370);
			title5LData.top =  new FormAttachment(0, 1000, 112);
			title5LData.width = 273;
			title5LData.height = 22;
			title5.setLayoutData(title5LData);
			title5.setText("# of Coordinate Pairs per Square Unit at 1x Density");
			title5.setEditable(false);
		}
		{
			title3 = new Text(clientArea, SWT.NONE);
			FormData title3LData = new FormData();
			title3LData.left =  new FormAttachment(0, 1000, 419);
			title3LData.top =  new FormAttachment(0, 1000, 6);
			title3LData.width = 78;
			title3LData.height = 15;
			title3.setLayoutData(title3LData);
			title3.setText("Lower X Range");
			title3.setEditable(false);
		}
		{
			title2 = new Text(clientArea, SWT.NONE);
			FormData title2LData = new FormData();
			title2LData.left =  new FormAttachment(0, 1000, 558);
			title2LData.top =  new FormAttachment(0, 1000, 64);
			title2LData.width = 78;
			title2LData.height = 15;
			title2.setLayoutData(title2LData);
			title2.setText("Upper Y Range");
			title2.setEditable(false);
		}
		{
			title1 = new Text(clientArea, SWT.NONE);
			FormData title1LData = new FormData();
			title1LData.left =  new FormAttachment(0, 1000, 419);
			title1LData.top =  new FormAttachment(0, 1000, 64);
			title1LData.width = 78;
			title1LData.height = 15;
			title1.setLayoutData(title1LData);
			title1.setText("Upper X Range");
			title1.setEditable(false);
		}
		{
			text = new Text(clientArea, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
			FormData textLData = new FormData();
			textLData.left =  new FormAttachment(0, 1000, 0);
			textLData.top =  new FormAttachment(0, 1000, 1);
			textLData.width = 356;
			textLData.height = 319;
			text.setLayoutData(textLData);
			
		}
		{
			goButton = new Button(clientArea, SWT.PUSH | SWT.CENTER);
			FormData goButtonLData = new FormData();
			goButtonLData.left =  new FormAttachment(0, 1000, 582);
			goButtonLData.top =  new FormAttachment(0, 1000, 289);
			goButtonLData.width = 43;
			goButtonLData.height = 33;
			goButton.setLayoutData(goButtonLData);
			goButton.setText("GO!");
			
			goButton.addSelectionListener(new GoButtonItemSelectionListener());
			
			
		}
		{
			yUpperRange = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData yUpperRangeLData = new FormData();
			yUpperRangeLData.left =  new FormAttachment(0, 1000, 545);
			yUpperRangeLData.top =  new FormAttachment(0, 1000, 85);
			yUpperRangeLData.width = 98;
			yUpperRangeLData.height = 15;
			yUpperRange.setLayoutData(yUpperRangeLData);
			yUpperRange.setText("30");
		}
		{
			yLowerRange = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData yLowerRangeLData = new FormData();
			yLowerRangeLData.left =  new FormAttachment(0, 1000, 545);
			yLowerRangeLData.top =  new FormAttachment(0, 1000, 27);
			yLowerRangeLData.width = 98;
			yLowerRangeLData.height = 15;
			yLowerRange.setLayoutData(yLowerRangeLData);
			yLowerRange.setText("20");
			
		
		}
		{
			xUpperRange = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData xUpperRangeLData = new FormData();
			xUpperRangeLData.left =  new FormAttachment(0, 1000, 413);
			xUpperRangeLData.top =  new FormAttachment(0, 1000, 85);
			xUpperRangeLData.width = 97;
			xUpperRangeLData.height = 15;
			xUpperRange.setLayoutData(xUpperRangeLData);
			xUpperRange.setText("20");
		}
		{
			xLowerRange = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData xLowerLimitLData = new FormData();
			xLowerLimitLData.left =  new FormAttachment(0, 1000, 413);
			xLowerLimitLData.top =  new FormAttachment(0, 1000, 27);
			xLowerLimitLData.width = 97;
			xLowerLimitLData.height = 15;
			xLowerRange.setLayoutData(xLowerLimitLData);
			xLowerRange.setText("10");
		}
		{
			text1 = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			text1.setText("Lower y Parameter");
			FormData text1LData = new FormData();
			text1LData.left =  new FormAttachment(0, 1000, 361);
			text1LData.top =  new FormAttachment(0, 1000, -15);
			text1LData.width = 98;
			text1LData.height = 15;
			text1.setLayoutData(text1LData);
		}
		{
			text2 = new Text(clientArea, SWT.NONE);
			text2.setText("Upper Y Range");
			FormData text2LData = new FormData();
			text2LData.left =  new FormAttachment(0, 1000, 252);
			text2LData.top =  new FormAttachment(0, 1000, -32);
			text2LData.width = 78;
			text2LData.height = 15;
			text2.setLayoutData(text2LData);
		}
		{
			title4 = new Text(clientArea, SWT.NONE);
			FormData title4LData = new FormData();
			title4LData.left =  new FormAttachment(0, 1000, 558);
			title4LData.top =  new FormAttachment(0, 1000, 6);
			title4LData.width = 78;
			title4LData.height = 15;
			title4.setLayoutData(title4LData);
			title4.setText("Lower Y Range");
			title4.setEditable(false);
		}
		{
			cList = new Text(clientArea, SWT.MULTI | SWT.WRAP);
			FormData cListLData = new FormData();
			cListLData.left =  new FormAttachment(0, 1000, 687);
			cListLData.top =  new FormAttachment(0, 1000, 49);
			cListLData.width = 154;
			cListLData.height = 295;
			cList.setLayoutData(cListLData);
			cList.setText("1 2 3 4 5");
		}
	}
}


	
	


private void handleFileOpenRequest() {
	FileDialog dialog = new FileDialog(shell, SWT.OPEN);
	String filename = dialog.open();
	if (filename != null) {
		shell.setText(filename);
	}
}


//bla
	private class ExitMenuItemSelectionListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			try {
				// Save app settings to file
				appSettings.store(new FileOutputStream("appsettings.ini"), "");
			} catch (Exception ex) {
			}
			shell.dispose();
		}
	}


	private class OpenFileMenuItemSelectionListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			handleFileOpenRequest();
		}
	}


	private class OpenToolItemSelectionListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			handleFileOpenRequest();
		}
	}


	private class AboutMenuItemSelectionListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			MessageBox message = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
			message.setText("RandomCoordinatorGen");
			message.setMessage("Developed by Bobo Brenner\n\nApplicationName v1.0");
			message.open();
		}
	}
	
	private class saveFileMenuItemSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			MessageBox message = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
			
			message.setMessage("SUCKIT");
			message.open();
		}
	}
	
	private class GoButtonItemSelectionListener extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			Random rDF1 = new Random();
			String cListTemp=cList.getText();
			String[] cListTemp2;
			cListTemp2=new String[101];
			String[] cListTemp3;
			cListTemp3=new String[101];
			cListTemp3=cListTemp.split("\r\n");
			
			
			int count2=0;
			int lowerx;
			int upperx;
			int lowery;
			int uppery;
			int relativeDens;
			int line=0;
			int count4;
			
			
			centerOfIcelandX=Integer.parseInt(xAngleText.getText());
		if(listInputMode==false){
			lowerx=Integer.parseInt(xLowerRange.getText());
			upperx=Integer.parseInt(xUpperRange.getText());
			lowery=Integer.parseInt(yLowerRange.getText());
			uppery=Integer.parseInt(yUpperRange.getText());
			int xRange=upperx -lowerx;
			int yRange=uppery-lowery;
			int area=xRange*yRange;
			relativeDens=Integer.parseInt(density.getText());
			
			int variationOfDeg=Integer.parseInt(variationOnDeg.getText());
			int angleRange=2*variationOfDeg;
			
		
			printer.clear();
			int count1=Integer.parseInt(iterations.getText())*relativeDens*area;
			for(int i=0;i<count1;i++){
		
				double i1=rDF1.nextDouble();
				double x=rDF1.nextDouble()*(xRange)+lowerx;
				double y=rDF1.nextDouble()*(yRange)+lowery;
				
				i1=rDF1.nextDouble();
				double variation=(i1*angleRange)-10;
				double outAngle;
				//outAngle=angle+variation;
			if(angleToggleBool==true)
			{	if(x==centerOfIcelandX)  //needs work
					outAngle=270;
				else
					outAngle=Math.toDegrees(Math.atan((centerOfIcelandY-y)/(centerOfIcelandX-x)));//center of iceland is (45,27)
				outAngle+=variation;
				if(outAngle<0)
					outAngle+=360;
				printer.add("  "+x+"   " +y+"    "+outAngle);
			}
			else
				printer.add("  "+x+"   " +y);
			}
			if(appendToggle==true)
			{
				for(String print : printer)
				{
					count++;
					text.append(print+"\n");
				}
			}
			else
			{
				count=0;
				text.setText("");
				
				for(String print : printer)
				{
					count++;
					text.append(print+"\n");
					txtStatus.setText("Count: " +count);
				}
			}
			txtStatus.setText("Count: " +count);
			count2+=1;
			line++;
			}
			
		
		else{
			while(count2<cListTemp3.length)
			{
				cListTemp2=cListTemp3[count2].split(" ");
				count4=0;
				
				//while()
				lowerx=Integer.parseInt(cListTemp2[count4]);
				upperx=Integer.parseInt(cListTemp2[count4+1]);
				lowery=Integer.parseInt(cListTemp2[count4+2]);
				uppery=Integer.parseInt(cListTemp2[count4+3]);
				relativeDens=Integer.parseInt(cListTemp2[count4+4]);
				
				
			
//			int lowerx=Integer.parseInt(xLowerRange.getText());
//			int upperx=Integer.parseInt(xUpperRange.getText());
//			int lowery=Integer.parseInt(yLowerRange.getText());
//			int uppery=Integer.parseInt(yUpperRange.getText());
			int xRange=upperx -lowerx;
			int yRange=uppery-lowery;
			int area=xRange*yRange;
//			int relativeDens=Integer.parseInt(density.getText());
			
			int variationOfDeg=Integer.parseInt(variationOnDeg.getText());
			int angleRange=2*variationOfDeg;
			
		
			printer.clear();
			int count1=Integer.parseInt(iterations.getText())*relativeDens*area;
			for(int i=0;i<count1;i++){
		
				double i1=rDF1.nextDouble();
				double x=rDF1.nextDouble()*(xRange)+lowerx;
				double y=rDF1.nextDouble()*(yRange)+lowery;
				
				i1=rDF1.nextDouble();
				double variation=(i1*angleRange)-10;
				double outAngle;
				//outAngle=angle+variation;
			if(angleToggleBool==true)
			{	if(x==centerOfIcelandX)  //needs work
					outAngle=270;
				else
					outAngle=Math.toDegrees(Math.atan((centerOfIcelandY-y)/(centerOfIcelandX-x)));//center of iceland is (45,27)
				outAngle+=variation;
				if(outAngle<0)
					outAngle+=360;
				if(x<50)//just for now
					outAngle=20;
				printer.add("  "+x+"   " +y+"    "+outAngle);
			}
			else
				printer.add("  "+x+"   " +y);
			}
			if(appendToggle==true)
			{
				for(String print : printer)
				{
					count++;
					text.append(print+"\n");
				}
			}
			else
			{
				count=0;
				text.setText("");
				
				for(String print : printer)
				{
					count++;
					text.append(print+"\n");
					txtStatus.setText("Count: " +count);
				}
			}
			txtStatus.setText("Count: " +count);
			count2+=1;
			line++;
			}
		}
			
		}
	}


	private class ShellDisposeListener implements DisposeListener {

		public void widgetDisposed(DisposeEvent arg0) {
			try {
				// Save app settings to file
				appSettings.store(new FileOutputStream("appsettings.ini"), "");
			} catch (Exception ex) {
			}
		}
	}


	private class ShellControlListener extends ControlAdapter {

		@Override
		public void controlMoved(ControlEvent e) {
			try {
				saveShellBounds();
			} catch (Exception ex) {
				setStatus(ex.getMessage());
			}
		}


		@Override
		public void controlResized(ControlEvent e) {
			try {
				saveShellBounds();
			} catch (Exception ex) {
				setStatus(ex.getMessage());
			}
		}
	}
	
	

	private void setStatus(String message) {
		statusText.setText(message);
	}

}

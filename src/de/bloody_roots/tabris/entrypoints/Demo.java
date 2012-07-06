package de.bloody_roots.tabris.entrypoints;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.eclipsesource.tabris.demos.entrypoints.ButtonControlsDemo;
import com.eclipsesource.tabris.demos.entrypoints.InputControlsDemo;
import com.eclipsesource.tabris.demos.entrypoints.SimpleTreeDemo;

public class Demo implements IEntryPoint {

	private Display display;
	private Shell baseShell;
	private Composite composite;

	@Override
	public int createUI() {
		display = new Display();

		baseShell = new Shell(display, SWT.NO_TRIM);

		baseShell.setText("MAIN");
		baseShell.setMaximized(true);

		GridLayoutFactory.fillDefaults().applyTo(baseShell);

		createToolBar(baseShell);
		createContent(baseShell);
		createTabFolder(baseShell);

		baseShell.open();
		baseShell.setVisible(true);

		System.out.println(baseShell.getSize().toString());

		return 0;
	}

	private void createToolBar(Shell shell) {
		System.out.println("createToolBar");
		ToolBar toolBar = new ToolBar(shell, SWT.HORIZONTAL);
		toolBar.setBackground(Graphics.getColor(0, 255, 0));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		item.setText("Item");
		item.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (composite.isDisposed()) {
					composite = new Composite(baseShell, SWT.NONE);
					composite.setBackground(Graphics.getColor(255, 0, 0));
					GridLayoutFactory.fillDefaults().numColumns(3)
							.applyTo(composite);
					GridDataFactory.fillDefaults().grab(true, true)
							.applyTo(composite);
				} else {
					composite.dispose();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		new ToolItem(toolBar, SWT.SEPARATOR);
		item = new ToolItem(toolBar, SWT.PUSH);
		item.setText("TABRIS TEST");
		item.setData(WidgetUtil.CUSTOM_VARIANT, "TITLE");
		new ToolItem(toolBar, SWT.SEPARATOR);
		item = new ToolItem(toolBar, SWT.PUSH);
		item.setImage(new Image(display, this.getClass().getResourceAsStream(
				"/images/envelope.png")));
		item.setText("Send");
	}

	private void createContent(Composite parent) {
		System.out.println("createContent");
		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(Graphics.getColor(0, 0, 255));
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		// GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(composite);

		Button button1 = new Button(composite, SWT.NONE);
		button1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openCenterApplicationModalDialog();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
		button1.setText("openCenterApplicationModalDialog");

		Button button2 = new Button(composite, SWT.NONE);
		button2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openLeftApplicationModalDialog();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
		button2.setText("openLeftApplicationModalDialog");

		Button button3 = new Button(composite, SWT.NONE);
		button3.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openCloseableShell();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
		button3.setText("openCloseableShell");
	}

	private void openCenterApplicationModalDialog() {
		final Shell box = new Shell(baseShell, SWT.APPLICATION_MODAL
				| SWT.BORDER | SWT.TITLE);
		box.setText("openCenterApplicationModalDialog");
		GridLayout layout = new GridLayout();
		layout.marginWidth = 50;
		box.setLayout(layout);
		box.setBackground(Graphics.getColor(0, 0, 0));
		Label label = new Label(box, SWT.NONE);
		label.setText("openCenterApplicationModalDialog");
		label.setLayoutData(GridDataFactory.fillDefaults()
				.align(SWT.CENTER, SWT.CENTER).grab(true, true).create());
		appendCloseButton(box);
		centerDialog(box);
		box.open();
	}

	private void openLeftApplicationModalDialog() {
		final Shell box = new Shell(baseShell, SWT.BORDER | SWT.TITLE);
		box.setText("openLeftApplicationModalDialog");
		GridLayout layout = new GridLayout();
		box.setLayout(layout);
		GridDataFactory.swtDefaults().grab(false, true)
				.align(SWT.BEGINNING, SWT.FILL).hint(200, 800).applyTo(box);
		box.setBackground(Graphics.getColor(255, 0, 0));
		Label label = new Label(box, SWT.NONE);
		label.setText("MOEP MOEP");
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER)
				.grab(true, true).applyTo(label);
		label = new Label(box, SWT.NONE);
		label.setText("openLeftApplicationModalDialog");
		Composite test = new Composite(box, SWT.NONE);
		GridDataFactory.swtDefaults().grab(false, true)
				.align(SWT.BEGINNING, SWT.FILL).applyTo(test);
		appendCloseButton(box);
		box.setLocation(0, 100);
		box.open();
	}

	private void openCloseableShell() {
		System.out.println("createShell");
		final Shell box = new Shell(display, SWT.BORDER | SWT.APPLICATION_MODAL
				| SWT.TITLE | SWT.CLOSE);
		box.setText("openCloseableShell");
		box.setSize(230, 100);
		box.setLocation(100, 100);
		appendCloseButton(box);
		box.open();
	}

	private void appendCloseButton(final Shell box) {
		Button close = new Button(box, SWT.PUSH);
		close.setBackground(Graphics.getColor(225, 151, 7));
		close.setText("close");
		close.setLayoutData(GridDataFactory.fillDefaults()
				.align(SWT.FILL, SWT.CENTER).grab(true, false).create());
		close.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				box.close();
			}
		});
		box.pack();
		box.addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(ControlEvent e) {
				System.out.println("controlResized " + box.getText());
			}

			@Override
			public void controlMoved(ControlEvent e) {
				System.out.println("controlMoved " + box.getText());
			}

		});
		box.addShellListener(new ShellListener() {

			@Override
			public void shellDeactivated(ShellEvent e) {
				System.out.println("shellDeactivated " + box.getText());
			}

			@Override
			public void shellClosed(ShellEvent e) {
				System.out.println("shellClosed " + box.getText());
			}

			@Override
			public void shellActivated(ShellEvent e) {
				System.out.println("shellActivated " + box.getText());
			}
		});
		box.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent event) {
				System.out.println("focusLost " + box.getText());
			}

			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("focusGained " + box.getText());
			}
		});
	}

	private void centerDialog(Shell box) {
		System.out.println("centerDialog");
		int newX = (baseShell.getSize().x - box.getSize().x) / 2;
		int newY = (baseShell.getSize().y - box.getSize().y) / 2;
		box.setLocation(newX, newY);
	}

	private void createTabFolder(Shell shell) {
		System.out.println("createTabFolder");
		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		// tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true,
		// false));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tabFolder);

		final TabItem tab0 = new TabItem(tabFolder, SWT.NONE);
		tab0.setText("Tab0");
		tab0.setImage(new Image(display, this.getClass().getResourceAsStream(
				"/images/envelope.png")));

		createTabComposite(tabFolder, tab0);

		final TabItem tab1 = new TabItem(tabFolder, SWT.NONE);
		tab1.setText("InputControlsDemo");

		Composite tab1Composite = createTabComposite(tabFolder, tab1);
		InputControlsDemo inputControlsDemo = new InputControlsDemo();
		inputControlsDemo.createContent(display, tab1Composite);

		final TabItem tab2 = new TabItem(tabFolder, SWT.NONE);
		tab2.setText("ButtonControlsDemo");

		Composite tab2Composite = createTabComposite(tabFolder, tab2);
		ButtonControlsDemo buttonControlsDemo = new ButtonControlsDemo();
		buttonControlsDemo.createContent(display, tab2Composite);

		final TabItem tab3 = new TabItem(tabFolder, SWT.NONE);
		tab3.setText("SimpleTreeDemo");

		Composite tab3Composite = createTabComposite(tabFolder, tab3);
		SimpleTreeDemo simpleTreeDemo = new SimpleTreeDemo();
		simpleTreeDemo.createContent(display, tab3Composite);
	}

	private Composite createTabComposite(TabFolder tabFolder, TabItem tab) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(tabFolder.getDisplay().getSystemColor(
				SWT.COLOR_GRAY));
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER)
				.grab(true, true).applyTo(composite);

		tab.setControl(composite);

		return composite;
	}

}

package de.bloody_roots.tabris.entrypoints;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
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
		createContent(baseShell,-1);
//		createContent(baseShell, 120);
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
		item.setText("DISPOSE AND RECREATE");
		item.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (composite.isDisposed()) {
					createContent(baseShell,-1);
					composite.layout();
					baseShell.layout();
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

	private void createContent(Composite parent, int i) {
		System.out.println("createContent");
		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(Graphics.getColor(0, 0, 255));
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		// GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		GridDataFactory.fillDefaults().hint(SWT.DEFAULT, i).grab(true, false).applyTo(composite);

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
		button1.setText("CenterApplicationModal");

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
		button2.setText("LeftApplicationModal");

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
		button3.setText("CloseableShell");
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
		Composite tab0Composite = createTabComposite(tabFolder, tab0);
		this.createDisposeTest(tab0Composite);

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
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING)
				.grab(true, true).applyTo(composite);

		tab.setControl(composite);

		return composite;
	}

	private void createDisposeTest(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(Graphics.getColor(0, 0, 0));
		GridLayoutFactory.fillDefaults().numColumns(8).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING)
				.grab(true, true).applyTo(composite);

		Button button = new Button(composite, SWT.NONE);
		button.setText("withoutLayoutAndPack");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createLabelAndText(composite);
				RGB rgb = composite.getBackground().getRGB();

				rgb.blue += 10;
				if (rgb.blue > 255) {
					rgb.blue = 0;
				}
				rgb.red += 20;
				if (rgb.red > 255) {
					rgb.red = 0;
				}
				rgb.green += 30;
				if (rgb.green > 255) {
					rgb.green = 0;
				}

				composite.setBackground(Graphics.getColor(rgb));
			}
		});
		button = new Button(composite, SWT.NONE);
		button.setText("withLayoutAndPack");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createLabelAndText(composite);
				RGB rgb = composite.getBackground().getRGB();

				rgb.blue += 10;
				if (rgb.blue > 255) {
					rgb.blue = 0;
				}
				rgb.red += 20;
				if (rgb.red > 255) {
					rgb.red = 0;
				}
				rgb.green += 30;
				if (rgb.green > 255) {
					rgb.green = 0;
				}

				composite.setBackground(Graphics.getColor(rgb));
				composite.layout();
				composite.pack();
			}
		});
	}

	private void createLabelAndText(final Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText("LABEL");
		Text text = new Text(composite, SWT.NONE);
		text.setText(" TEXT");
	}

}

package de.bloody_roots.tabris.entrypoints;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Scaffolding implements IEntryPoint {

	private Display display;

	private Shell baseShell;

	@Override
	public int createUI() {
		display = new Display();

		baseShell = new Shell(display, SWT.NO_TRIM);

		baseShell.setText("MAIN");
		baseShell.setMaximized(true);

		GridLayoutFactory.fillDefaults().applyTo(baseShell);

		createTopToolBar(baseShell);
		createContent(baseShell);
		createBottomToolBar(baseShell);

		baseShell.open();
		baseShell.setVisible(true);

		return 0;
	}

	private void createTopToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		toolBar.setBackground(Graphics.getColor(0, 255, 0));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		item.setText("Item");
		item.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
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
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(Graphics.getColor(0, 0, 255));
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		
		Image image = new Image(display, this.getClass().getResourceAsStream(
				"/images/test.png"));
		Label label = new Label(composite, SWT.NONE);
		label.setImage(image);
	}

	private void createBottomToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL);
		toolBar.setBackground(Graphics.getColor(0, 255, 0));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		item.setText("Item");
		item.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
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

}

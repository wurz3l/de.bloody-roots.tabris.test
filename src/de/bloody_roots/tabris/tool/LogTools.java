package de.bloody_roots.tabris.tool;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class LogTools {

	public static void logAllControlListeners(Control control) {
		control.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("mouseUp");
			}

			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("mouseDown");
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("mouseDoubleClick");
			}
		});
		control.addGestureListener(new GestureListener() {

			@Override
			public void gesture(GestureEvent e) {
				System.out.println("gesture");
			}
		});
		control.addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(ControlEvent e) {
				System.out.println("controlResized");
			}

			@Override
			public void controlMoved(ControlEvent e) {
				System.out.println("controlMoved");
			}

		});
		control.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent event) {
				System.out.println("focusLost");
			}

			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("focusGained");
			}
		});
	}

	public static void logAllShellListeners(final Shell shell) {
		shell.addShellListener(new ShellListener() {

			@Override
			public void shellDeactivated(ShellEvent e) {
				System.out.println("shellDeactivated " + shell.getText());
			}

			@Override
			public void shellClosed(ShellEvent e) {
				System.out.println("shellClosed " + shell.getText());
			}

			@Override
			public void shellActivated(ShellEvent e) {
				System.out.println("shellActivated " + shell.getText());
			}
		});
		logAllControlListeners(shell);
	}
	
}

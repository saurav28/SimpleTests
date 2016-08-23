package org.saurav.simpletests.swt;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * Main code is inspired from
 * http://git.eclipse.org/c/platform/eclipse.platform.
 * swt.git/tree/examples/org.eclipse
 * .swt.snippets/src/org/eclipse/swt/snippets/Snippet225.java
 * 
 * 
 * This code creates a tool tip 250 times and pushes to the display queue to display
 * Code is created to solve a problem suspected in Windows 10
 */
public class SWTTooltip {

	private static Image image;
	private static TrayItem item;

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		// Image image = null;
		Tray tray = display.getSystemTray();
		if (tray != null) {
			item = new TrayItem(tray, SWT.NONE);
		}
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Press for balloon tip");
		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				for (int i = 0; i < 250; i++) {
					final ToolTip tip = new ToolTip(shell, SWT.BALLOON
							| SWT.ICON_INFORMATION);
					tip.setMessage("Here is a message for the user. When the message is too long it wraps. I should say something cool but nothing comes to my mind.");

					image = display.getSystemImage(SWT.ICON_INFORMATION);
					item.setImage(image);
					tip.setText("Notification from a tray item");
					item.setToolTip(tip);

					tip.setVisible(true);
				}

			}
		});
		Rectangle clientArea = shell.getClientArea();
		button.setLocation(clientArea.x, clientArea.y);
		button.pack();
		shell.setBounds(50, 50, 300, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		if (image != null)
			image.dispose();
		display.dispose();
	}
}
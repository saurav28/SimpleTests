package org.saurav.simpletests.swt;
/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
//package org.eclipse.swt.snippets;
/*
 * Tray example snippet: place an icon with a popup menu on the system tray
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.0
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class SystemTrayIconMenu {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    Image image = new Image(display,"C://Test//trayIcon.png");
    final Tray tray = display.getSystemTray();
    if (tray == null) {
      System.out.println("The system tray is not available");
    } else {
      final TrayItem item = new TrayItem(tray, SWT.NONE);
      final TrayItem item1 = new TrayItem(tray, SWT.NONE);
      item.setToolTipText("SWT TrayItem");
      item.addListener(SWT.Show, new Listener() {
        public void handleEvent(Event event) {
          System.out.println("show");
        }
      });
      item.addListener(SWT.Hide, new Listener() {
        public void handleEvent(Event event) {
          System.out.println("hide");
        }
      });
      item.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event event) {
          System.out.println("selection");
        }
      });
      item.addListener(SWT.DefaultSelection, new Listener() {
        public void handleEvent(Event event) {
          System.out.println("default selection");
        }
      });
      final Menu menu = new Menu(shell, SWT.POP_UP);
      for (int i = 0; i < 8; i++) {
        MenuItem mi = new MenuItem(menu, SWT.PUSH);
        mi.setText("Item" + i);
        mi.addListener(SWT.Selection, new Listener() {
          public void handleEvent(Event event) {
            System.out.println("selection " + event.widget);
          }
        });
        if (i == 0)
          menu.setDefaultItem(mi);
      }
      item.addListener(SWT.MenuDetect, new Listener() {
        public void handleEvent(Event event) {
          menu.setVisible(true);
        }
      });
      item1.addListener(SWT.MenuDetect, new Listener() {
          public void handleEvent(Event event) {
            menu.setVisible(true);
          }
        });
      item.setImage(image);
      item1.setImage(image);
    }
  //  shell.setBounds(50, 50, 300, 200);
//    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    image.dispose();
    display.dispose();
  }
}
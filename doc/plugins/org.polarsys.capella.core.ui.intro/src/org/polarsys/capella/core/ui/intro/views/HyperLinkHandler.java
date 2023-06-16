/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.intro.views;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;

public class HyperLinkHandler implements IHyperlinkListener {

  String href;

  public HyperLinkHandler() {
  }

  public void setHRef(String h) {
    href = h;
  }

  @Override
  public void linkActivated(HyperlinkEvent ev) {
    try {
      URL url = new URL(href);
      IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
      support.getExternalBrowser().openURL(url);
    } catch (PartInitException e) {
    } catch (MalformedURLException e) {
    }
  }

  @Override
  public void linkEntered(HyperlinkEvent e) {

  }

  @Override
  public void linkExited(HyperlinkEvent e) {
  }
}

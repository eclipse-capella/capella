/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Copy the marker view selection to the clipboard.
 * 
 *
 */
public class CopyHandler extends AbstractViewHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    String text = getView(event_p).getSelectionAsText();
    if (text != null){
      Clipboard c = new Clipboard(HandlerUtil.getActiveWorkbenchWindow(event_p).getShell().getDisplay());
      Transfer[] transfers = new Transfer[]{ TextTransfer.getInstance() };
      Object[] data = new Object[]{ text };
      c.setContents(data, transfers);
      c.dispose();
      
    }    
    return null;
  }

}

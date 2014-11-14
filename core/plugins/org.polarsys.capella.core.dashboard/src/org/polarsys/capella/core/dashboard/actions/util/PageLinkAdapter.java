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
package org.polarsys.capella.core.dashboard.actions.util;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;

/**
 * Page hyperlink adapter.
 */
public class PageLinkAdapter extends HyperlinkAdapter {
  /**
   * Editor reference.
   */
  private FormEditor _editor;
  /**
   * Status line manager.
   */
  private IStatusLineManager _statusLineManager;

  /**
   * Constructor.
   * @param editor_p
   */
  public PageLinkAdapter(FormEditor editor_p) {
    _editor = editor_p;
    _statusLineManager = _editor.getEditorSite().getActionBars().getStatusLineManager();
  }

  /**
   * Get the editor reference.
   * @return the editor
   */
  protected FormEditor getEditor() {
    return _editor;
  }

  /**
   * @see org.eclipse.ui.forms.events.HyperlinkAdapter#linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
   */
  @Override
  public void linkActivated(HyperlinkEvent e_p) {
    String pageId = (String) e_p.getHref();
    if (null != pageId) {
      _editor.setActivePage(pageId);
    }
  }

  /**
   * @see org.eclipse.ui.forms.events.HyperlinkAdapter#linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent)
   */
  @Override
  public void linkEntered(HyperlinkEvent e_p) {
    String pageId = (String) e_p.getHref();
    IFormPage page = _editor.findPage(pageId);
    if (null != page) {
      _statusLineManager.setMessage(StringHelper.formatMessage(Messages.CapellaDashboardPageLinkListener_GotoPage_Title, new String[] { page.getTitle() }));
    }
  }

  /**
   * @see org.eclipse.ui.forms.events.HyperlinkAdapter#linkExited(org.eclipse.ui.forms.events.HyperlinkEvent)
   */
  @Override
  public void linkExited(HyperlinkEvent e_p) {
    _statusLineManager.setMessage(ICommonConstants.EMPTY_STRING);
  }
}

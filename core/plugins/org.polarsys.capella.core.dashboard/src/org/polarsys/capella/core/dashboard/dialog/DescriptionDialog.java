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
package org.polarsys.capella.core.dashboard.dialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import org.polarsys.capella.common.ui.services.helper.FormHelper;

/**
 * Dialog that opens a popup dialog to display content in a {@link FormText}.
 */
public class DescriptionDialog extends PopupDialog {
  /**
   * Close the popup dialog.
   */
  private class CloseAction extends Action {
    /**
     * @see org.eclipse.jface.action.Action#getImageDescriptor()
     */
    @Override
    public ImageDescriptor getImageDescriptor() {
      return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
    }

    /**
     * @see org.eclipse.jface.action.Action#getToolTipText()
     */
    @Override
    public String getToolTipText() {
      return Messages.DescriptionDialog_CloseAction_Title;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
      close();
    }
  }

  private Point _anchor;
  private Composite _composite;
  private FormToolkit _toolkit;

  private String _content;

  /**
   * Constructor.
   * @param parent
   * @param content_p
   */
  public DescriptionDialog(Shell parent, String content_p) {
    super(parent, SWT.NONE, true, true, false, false, null, null);
    _anchor = parent.getDisplay().getCursorLocation();
    _toolkit = new FormToolkit(parent.getDisplay());
    _content = content_p;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#close()
   */
  @Override
  public boolean close() {
    if (_toolkit != null) {
      _toolkit.dispose();
      _toolkit = null;
    }
    return super.close();
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#createContents(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createContents(Composite parent) {
    getShell().setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
    initializeBounds();
    return createDialogArea(parent);
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected Control createDialogArea(Composite parent) {
    _composite = (Composite) super.createDialogArea(parent);

    ScrolledForm form = _toolkit.createScrolledForm(_composite);
    _toolkit.decorateFormHeading(form.getForm());

    // add a Close button to the toolbar
    form.getToolBarManager().add(new CloseAction());
    form.getToolBarManager().update(true);

    TableWrapLayout layout = new TableWrapLayout();
    layout.leftMargin = 10;
    layout.rightMargin = 10;
    layout.topMargin = 10;
    layout.verticalSpacing = 10;
    form.getBody().setLayout(layout);

    FormText richText = FormHelper.createRichText(_toolkit, form.getBody(), _content, null);
    TableWrapData layoutData = new TableWrapData();
    layoutData.maxWidth = 400;
    richText.setLayoutData(layoutData);
    return _composite;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#getFocusControl()
   */
  @Override
  protected Control getFocusControl() {
    return _composite;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#getInitialLocation(org.eclipse.swt.graphics.Point)
   */
  @Override
  protected Point getInitialLocation(Point size) {
    if (_anchor == null) {
      return super.getInitialLocation(size);
    }
    Point point = _anchor;
    Rectangle monitor = getShell().getMonitor().getClientArea();
    if (monitor.width < point.x + size.x) {
      point.x = Math.max(0, point.x - size.x);
    }
    if (monitor.height < point.y + size.y) {
      point.y = Math.max(0, point.y - size.y);
    }
    return point;
  }
}

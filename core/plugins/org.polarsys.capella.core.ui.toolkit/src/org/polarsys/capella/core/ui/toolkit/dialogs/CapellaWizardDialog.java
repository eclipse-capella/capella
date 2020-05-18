/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.common.ui.toolkit.editors.BasicEditorDialog;
import org.polarsys.capella.core.ui.resources.IImageKeys;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 * The Capella Editor Dialog.
 */
public class CapellaWizardDialog extends BasicEditorDialog {

  private static int _DEFAULT_WIZARD_WIDTH = 500;
  private static int _DEFAULT_WIZARD_HEIGHT = 500;

  private static int _MAX_WIZARD_WIDTH = 700;
  private static int _MAX_WIZARD_HEIGHT = 800;

  /**
   * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
   */
  @Override
  protected Point getInitialSize() {
    Point shellSize = super.getInitialSize();

    // TODO this is a temporary workaround: the dialog size will be limited to 600x800
    // FIXME when dialog size is limited, the layout is incorrect (must be investigated)
    int width = Math.min(_MAX_WIZARD_WIDTH, Math.max(_DEFAULT_WIZARD_WIDTH, shellSize.x));
    int height = Math.min(_MAX_WIZARD_HEIGHT, Math.max(_DEFAULT_WIZARD_HEIGHT, shellSize.y));

    return new Point(width, height);
  }

  /**
   * Constructs the Capella editor dialog. it displays the Capella icon into the window title bar.
   * @param shell_p The shell.
   * @param content_p The editor or wizard to display.
   */
  public CapellaWizardDialog(Shell shell_p, IWizard content_p) {
    super(shell_p, content_p);
    Window.setDefaultImage(CapellaUIResourcesPlugin.getDefault().getImage(IImageKeys.CAPELLA_APPLICATION_IMG_24));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent_p) {
    super.createButtonsForButtonBar(parent_p);

    Button btn = getButton(IDialogConstants.CANCEL_ID);
    if (null != btn) {
      btn.setText("&" + IDialogConstants.CANCEL_LABEL); //$NON-NLS-1$
    }
  }

  /**
   * @see org.eclipse.jface.window.Window#getShellStyle()
   */
  @Override
  public int getShellStyle() {
    return super.getShellStyle();
  }

  /**
   * @see org.eclipse.jface.window.Window#setShellStyle()
   */
  @Override
  public void setShellStyle(int newShellStyle_p) {
    super.setShellStyle(newShellStyle_p);
  }
}

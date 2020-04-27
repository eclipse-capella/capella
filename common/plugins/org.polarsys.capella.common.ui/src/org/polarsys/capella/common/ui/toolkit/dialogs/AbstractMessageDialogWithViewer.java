/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.dialogs;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Message dialog that contains a JFace viewer in the custom area.
 */
public abstract class AbstractMessageDialogWithViewer extends AbstractMessageDialog {
  /**
   * Self explanatory.
   */
  public static final int DEFAULT_DIALOG_HEIGHT = 550;
  /**
   * Self explanatory.
   */
  public static final int DEFAULT_DIALOG_WIDTH = 450;
  /**
   * Internal viewer displayed in the custom area.
   */
  private Viewer _viewer;

  /**
   * Constructor.
   * @param parentShell
   * @param dialogTitle
   * @param dialogTitleImage
   * @param dialogMessage
   * @param dialogImageType
   * @param dialogButtonLabels
   * @param defaultIndex
   */
  public AbstractMessageDialogWithViewer(Shell parentShell, String dialogTitle, Image dialogTitleImage, String dialogMessage, int dialogImageType,
      String[] dialogButtonLabels, int defaultIndex) {
    super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
  }

  /**
   * Center dialog on active parent shell.
   * @see org.eclipse.jface.dialogs.MessageDialog#configureShell(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    Shell activeShell = shell.getDisplay().getActiveShell();
    if (null != activeShell) {
      Rectangle bounds = activeShell.getBounds();
      int width = getDialogWidth();
      int height = getDialogHeight();
      shell.setBounds(bounds.x + ((bounds.width - width) / 2), bounds.y + ((bounds.height - height) / 2), width, height);
    }
  }

  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createCustomArea(Composite parent) {
    // Create a composing composite.
    Composite containingComposite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout(1, true);
    containingComposite.setLayout(layout);
    containingComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create the viewer area.
    createViewerArea(containingComposite);
    return containingComposite;
  }

  /**
   * Create the viewer displayed in the custom area.<br>
   * Implementors are responsible for setting the viewer layout data based on {@link GridLayout}.
   * @param parent
   * @return must not <code>null</code>
   */
  protected abstract Viewer createViewer(Composite parent);

  /**
   * Create the viewer area, {@link #createViewer(Composite)} is called within this method.
   * @param parent
   */
  protected void createViewerArea(Composite parent) {
    // Create the viewer.
    _viewer = createViewer(parent);
    // Set Input data.
    _viewer.setInput(getInitialInputData());
  }

  /**
   * Get dialog height.
   * @return default implementation returns {@link #DEFAULT_DIALOG_HEIGHT} {@value #DEFAULT_DIALOG_HEIGHT}.
   */
  protected int getDialogHeight() {
    return DEFAULT_DIALOG_HEIGHT;
  }

  /**
   * Get dialog width.
   * @return default implementation returns {@link #DEFAULT_DIALOG_WIDTH} {@value #DEFAULT_DIALOG_WIDTH}.
   */
  protected int getDialogWidth() {
    return DEFAULT_DIALOG_WIDTH;
  }

  /**
   * Get the initial input used by the viewer.
   * @return must be not <code>null</code>.
   */
  protected abstract Object getInitialInputData();

  /**
   * Get the viewer displayed inside the dialog.
   * @return the viewer
   */
  protected Viewer getViewer() {
    return _viewer;
  }

  /**
   * @see org.eclipse.jface.window.Window#setShellStyle(int)
   */
  @Override
  protected void setShellStyle(int newShellStyle) {
    // More user friendly if the dialog is resizable.
    super.setShellStyle(newShellStyle | SWT.RESIZE);
  }
}

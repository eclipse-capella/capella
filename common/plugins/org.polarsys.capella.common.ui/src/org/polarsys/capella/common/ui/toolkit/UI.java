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
package org.polarsys.capella.common.ui.toolkit;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * The UI internal tool.
 */
public abstract class UI {
  // Initializes the colors disposer.
  static {
    Display.getDefault().disposeExec(new Runnable() {

      public void run() {
        dispose();
      }
    });
  }

  /**
   * The error color (red color).
   */
  public static final Color ERROR_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_RED);

  /**
   * The default text color (black color).
   */
  public static final Color DEFAULT_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);

  /**
   * Disposes the color resources.
   */
  protected static void dispose() {
    ERROR_COLOR.dispose();
    DEFAULT_COLOR.dispose();
  }

  /**
   * Returns the number of pixels corresponding to the given number of horizontal dialog units.
   * <p>
   * Clients may call this framework method, but should not override it.
   * </p>
   * @param control The control being sized.
   * @param dlus The number of horizontal dialog units.
   * @return The number of pixel.
   */
  public static int convertHorizontalDLUsToPixels(Control control, int dlus) {
    GC gc = new GC(control);
    gc.setFont(control.getFont());
    int averageWidth = gc.getFontMetrics().getAverageCharWidth();
    gc.dispose();
    double horizontalDialogUnitSize = averageWidth * 0.25;
    return (int) Math.round(dlus * horizontalDialogUnitSize);
  }

  /**
   * Returns the number of pixels corresponding to the given number of vertical dialog units.
   * <p>
   * Clients may call this framework method, but should not override it.
   * </p>
   * @param control The control being sized.
   * @param dlus The number of vertical dialog units.
   * @return the number of pixels
   */
  public static int convertVerticalDLUsToPixels(Control control, int dlus) {
    GC gc = new GC(control);
    gc.setFont(control.getFont());
    int height = gc.getFontMetrics().getHeight();
    gc.dispose();
    double verticalDialogUnitSize = height * 0.125;
    return (int) Math.round(dlus * verticalDialogUnitSize);
  }

  /**
   * Sets the GridData on button to be one that is spaced for the current font.
   * @param button The button the data is being set on.
   */
  public static void setButtonLayoutData(Button button) {
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    // Compute and store a font metric
    GC gc = new GC(button);
    gc.setFont(button.getFont());
    FontMetrics fontMetrics = gc.getFontMetrics();
    gc.dispose();
    int widthHint = org.eclipse.jface.dialogs.Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.BUTTON_WIDTH);
    data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
    button.setLayoutData(data);
  }
}

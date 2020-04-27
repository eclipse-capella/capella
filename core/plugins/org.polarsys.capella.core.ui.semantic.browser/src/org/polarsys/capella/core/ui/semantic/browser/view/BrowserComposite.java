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
package org.polarsys.capella.core.ui.semantic.browser.view;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

/**
 * Composite that contains a Treeview and its label.
 */

public class BrowserComposite extends Composite {

  protected TreeViewer treeviewer;

  protected Label label;

  public BrowserComposite(Composite parent_p, ISemanticBrowserModel model, int style_p, String labelText_p, String browserID) {
    super(parent_p, SWT.NONE);
    GridLayout gridLayout = new GridLayout(1, true);
    gridLayout.verticalSpacing = 0;
    gridLayout.horizontalSpacing = 0;
    gridLayout.marginBottom = 0;
    gridLayout.marginTop = 0;
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    gridLayout.marginRight = 0;
    gridLayout.marginLeft = 0;
    setLayout(gridLayout);

    if ((labelText_p != null) && (labelText_p.length() > 0)) {
      label = new Label(this, SWT.NONE);
      label.setText(labelText_p);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      label.setLayoutData(gridData);

      label.setBackground(label.getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
      label.setForeground(label.getShell().getDisplay().getSystemColor(SWT.COLOR_BLACK));
    }
    // Style given to the Tree should contain SWT.MULTI (as in this constructor) to have a beautiful drag feedback (else it's an ugly rectangle...).
    treeviewer = new SemanticBrowserTree(this, model, browserID);
  }

  /**
   * @return the treeviewer
   */
  public TreeViewer getTreeviewer() {
    return treeviewer;
  }

  /**
   * @return the label
   */
  public Label getLabel() {
    return label;
  }
}

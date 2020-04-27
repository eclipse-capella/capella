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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;

/**
 * The general viewer displays basics fields like the name, a summary description and a complete description. This three fields represent the common attributes
 * for the large part of MDE model objects. The general viewer is graphically composed of two areas:
 * <ul>
 * <li>The upper area is <b>unmodifiable area</b> and contains the three fields defined above.</li>
 * <li>The lower area is a <b>developer customizeable area</b>. It will be customized according to the object type. The lower area is customized with overriding
 * <code>createClientContent()</code> method.</li>
 * </ul>
 */
public class GeneralViewer extends FieldsViewer {
  // The customizable area.
  private Composite _customPart;

  /**
   * Constructs the general viewer.
   * @param parent The parent composite.
   */
  public GeneralViewer(Composite parent) {
    super(parent);
  }

  /**
   * Creates the client area content. Default implementation of this method does nothing. This method is intented to be subclassed to specialize the
   * GeneralViewer.
   * @param clientArea The client area.
   */
  protected void createClientContent(Composite clientArea) {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createControl(final Composite parent) {
    super.createControl(parent);
    GridLayout layout = new GridLayout();
    parent.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    // Splitter (between fixed attributes part & customized attributes part).
    final Sash sash = new Sash(parent, SWT.HORIZONTAL);
    GridData gdData = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
    gdData.heightHint = 1;
    sash.setLayoutData(gdData);
    sash.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
    sash.addListener(SWT.Selection, new Listener() {
      /**
       * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
       */
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event event) {
        if (event.detail == SWT.DRAG) {
          return;
        }

        int delta = sash.getBounds().y - event.y;
        GridData data = (GridData) _customPart.getLayoutData();
        data.minimumHeight = data.minimumHeight + delta;
        data.heightHint = data.minimumHeight;

        parent.layout(true, true);
      }
    });
    
    // Customizable area.
    _customPart = new Composite(parent, SWT.NONE);
    gdData = new GridData(GridData.FILL_BOTH);
    gdData.horizontalSpan = 2;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = true;
    _customPart.setLayoutData(gdData);

    // Sets the second area layout.
    FillLayout fillLayout = new FillLayout();
    _customPart.setLayout(fillLayout);

    // Creates the client content.
    createClientContent(_customPart);

    // Displays the separator, if client area content isn't empty.
    if (0 < _customPart.getChildren().length) {
      sash.setVisible(true);
      // separator.setVisible(true);
    } else {
      sash.dispose();
      // separator.dispose();
      _customPart.dispose();
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getInput()
   */
  @Override
  public Object getInput() {
    // Do nothing
    return null;
  }

  /**
   * Gets the parent composite.
   * @return The parent composite.
   */
  public Composite getParentComposite() {
    return _customPart;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input) {
    // Do nothing.
  }
}

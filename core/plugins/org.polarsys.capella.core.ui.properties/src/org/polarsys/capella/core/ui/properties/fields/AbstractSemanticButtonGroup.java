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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Abstract based class to implement a semantic field based on buttons.
 */
public abstract class AbstractSemanticButtonGroup extends AbstractSemanticField {

  /**
   * Constructor.
   * @param widgetFactory_p
   */
  public AbstractSemanticButtonGroup(TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);
  }

  /**
   * Create either a radio button or a check box button according to the given style.
   * @param group_p
   * @param label_p
   * @param data_p
   * @param enabled_p
   * @param style_p shall be either {@link SWT.RADIO} or {@link SWT.CHECK}
   * @return a not <code>null</code> object.
   */
  protected Button createButton(Composite group_p, String label_p, Object data_p, boolean enabled_p, int style_p) {
    Button button = _widgetFactory.createButton(group_p, label_p, style_p);
    button.addSelectionListener(this);
    // Link this button to its semantic value.
    button.setData(data_p);
    button.setEnabled(enabled_p);
    return button;
  }

  /**
   * Enable or disable given button.
   * @param button_p
   * @param enabled_p
   */
  protected void enableButton(Button button_p, boolean enabled_p) {
    if (null != button_p && !button_p.isDisposed()) {
      button_p.setEnabled(enabled_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    for (Button button : getSemanticFields()) {
      enableButton(button, enabled_p);
    }
  }

  /**
   * Get all semantic fields in the current button group.
   */
  public abstract List<Button> getSemanticFields();
}

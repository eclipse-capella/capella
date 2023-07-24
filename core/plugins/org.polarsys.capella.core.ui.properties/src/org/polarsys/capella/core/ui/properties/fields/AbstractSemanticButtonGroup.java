/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Abstract based class to implement a semantic field based on buttons.
 */
public abstract class AbstractSemanticButtonGroup extends AbstractSemanticField {

  /**
   * Constructor.
   * @param widgetFactory
   */
  public AbstractSemanticButtonGroup(TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);
  }

  /**
   * Create either a radio button or a check box button according to the given style.
   * @param group
   * @param label
   * @param data
   * @param enabled
   * @param style shall be either {@link SWT.RADIO} or {@link SWT.CHECK}
   * @return a not <code>null</code> object.
   */
  protected Button createButton(Composite group, String label, Object data, boolean enabled, int style) {
    Button button = widgetFactory.createButton(group, label, style);
    button.addSelectionListener(this);
    // Link this button to its semantic value.
    button.setData(data);
    button.setEnabled(enabled);
    if(!enabled){
      button.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
    }
    return button;
  }

  /**
   * Enable or disable given button.
   * @param button
   * @param enabled
   */
  protected void enableButton(Button button, boolean enabled) {
    if (null != button && !button.isDisposed()) {
		button.setEnabled(enabled);
		if (enabled && semanticElement != null && button.getData() instanceof EAttribute
				&& semanticElement.eGet((EAttribute) button.getData()) instanceof Boolean) {
			// Refresh widget from semantic element in case of remote update
			button.setSelection((Boolean) semanticElement.eGet(((EAttribute) button.getData())));
		}
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    for (Button button : getSemanticFields()) {
      enableButton(button, enabled);
    }
  }

  /**
   * Get all semantic fields in the current button group.
   */
  public abstract List<Button> getSemanticFields();
}

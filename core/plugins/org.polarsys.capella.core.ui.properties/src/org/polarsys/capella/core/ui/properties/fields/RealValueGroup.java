/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 */
public class RealValueGroup extends IntegerValueGroup implements VerifyListener {

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   */
  public RealValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, label, widgetFactory, false, false);
  }

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param skipGroup
   */
  public RealValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      boolean skipGroup) {
    this(parent, label, widgetFactory, false, skipGroup);
  }

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   * @param showResetBtn
   * @param skipGroup
   */
  public RealValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      boolean hasResetBtn, boolean skipGroup) {
    super(parent, label, widgetFactory, hasResetBtn, skipGroup);
  }

  @Override
  public void verifyText(VerifyEvent e) {
    /* Notice how we combine the old and new below */
    String currentText = ((Text) e.widget).getText();
    String newText = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);
    if ("".equals(newText)) {
      e.doit = true;
      ((Text) e.widget).setText("0.0");
      return;
    }
    try {
      Double.parseDouble(newText);
      e.doit = true;
    } catch (NumberFormatException ex) {
      e.doit = false;
    }
  }

  /**
   * Handle Reset button click event.
   * 
   * @param button
   */
  @Override
  protected void handleResetButtonClicked(Button button) {
    setDataValue(semanticElement, semanticFeature, "0.0");
    setTextValue(valueField, semanticElement, semanticFeature);
  }
}

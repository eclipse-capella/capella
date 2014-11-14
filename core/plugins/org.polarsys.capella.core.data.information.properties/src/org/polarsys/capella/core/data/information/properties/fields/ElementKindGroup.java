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
package org.polarsys.capella.core.data.information.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ElementKindGroup extends AbstractSemanticKindGroup {
  private Button _elementBtnParameter;
  private Button _elementBtnType;

  /**
   * Constructor.
   * @param parent_p
   * @param style_p
   */
  public ElementKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(parent_p, widgetFactory_p, Messages.getString("ElementKind.Label"), 2); //$NON-NLS-1$

    _elementBtnParameter = createButton(ElementKind.PARAMETER);
    _elementBtnParameter.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent evt_p) {
        // do nothing
      }
      public void widgetSelected(SelectionEvent evt_p) {
        if (((Button) evt_p.widget).getSelection()) {
          selectionChanged(ElementKind.PARAMETER);
        }
      }
    });
    _elementBtnType = createButton(ElementKind.TYPE);
    _elementBtnType.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent evt_p) {
        // do nothing
      }
      public void widgetSelected(SelectionEvent evt_p) {
        if (((Button) evt_p.widget).getSelection()) {
          selectionChanged(ElementKind.TYPE);
        }
      }
    });
  }

  /**
   * This method will be called when selection has changed.
   * Shall be overloaded if a custom behavior is required.
   * @param selection_p
   */
  protected void selectionChanged(ElementKind selection_p) {
    // by default, do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_elementBtnParameter);
    fields.add(_elementBtnType);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _elementBtnParameter;
  }
}

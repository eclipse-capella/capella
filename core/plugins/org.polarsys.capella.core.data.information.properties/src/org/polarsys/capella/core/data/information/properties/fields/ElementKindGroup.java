/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
  private Button _elementBtnMember;
  private Button _elementBtnType;

  /**
   * Constructor.
   * @param parent
   * @param style
   */
  public ElementKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("ElementKind.Label"), 2); //$NON-NLS-1$

    _elementBtnMember = createButton(ElementKind.MEMBER);
    _elementBtnMember.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent evt) {
        // do nothing
      }

      public void widgetSelected(SelectionEvent evt) {
        if (((Button) evt.widget).getSelection()) {
          selectionChanged(ElementKind.MEMBER);
        }
      }
    });
    _elementBtnType = createButton(ElementKind.TYPE);
    _elementBtnType.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent evt) {
        // do nothing
      }

      public void widgetSelected(SelectionEvent evt) {
        if (((Button) evt.widget).getSelection()) {
          selectionChanged(ElementKind.TYPE);
        }
      }
    });
  }

  /**
   * This method will be called when selection has changed.
   * Shall be overloaded if a custom behavior is required.
   * @param selection
   */
  protected void selectionChanged(ElementKind selection) {
    // by default, do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_elementBtnMember);
    fields.add(_elementBtnType);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _elementBtnMember;
  }
}

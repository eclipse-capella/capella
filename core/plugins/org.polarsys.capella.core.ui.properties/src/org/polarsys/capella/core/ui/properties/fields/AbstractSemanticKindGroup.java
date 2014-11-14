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

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * Abstract based class to implement a semantic field based on an EMF generated Enumeration with radio buttons.
 */
public abstract class AbstractSemanticKindGroup extends AbstractSemanticButtonGroup {

  protected Group _group;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param groupLabel_p
   */
  public AbstractSemanticKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, String groupLabel_p, int numColumns_p) {
    super(widgetFactory_p);

    _group = createGroup(parent_p, groupLabel_p, numColumns_p);
  }

  /**
   * Create a radio button.
   * @param enumerated_p
   * @return a not <code>null</code> object.
   */
  protected Button createButton(Enumerator enumerated_p) {
    return createButton(enumerated_p, true);
  }

  /**
   * Create a radio button.
   * @param enumerated_p
   * @param enabled_p
   * @return a not <code>null</code> object.
   */
  protected Button createButton(Enumerator enumerated_p, boolean enabled_p) {
    return createButton(_group, enumerated_p.getLiteral(), enumerated_p, enabled_p, SWT.RADIO);
  }

  /**
   * Create a group.
   * @param parent_p
   * @param label_p
   * @param numColumns_p
   * @return a not <code>null</code> object.
   */
  protected Group createGroup(Composite parent_p, String label_p, int numColumns_p) {
    _group = _widgetFactory.createGroup(parent_p, label_p);
    _group.setLayout(new GridLayout(numColumns_p, true));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) parent_p.getLayout()).numColumns;
    _group.setLayoutData(gd);
    return _group;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, _semanticFeature);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(CapellaElement capellaElement_p, EStructuralFeature feature_p) {
    super.loadData(capellaElement_p, feature_p);

    Object value = _semanticElement.eGet(_semanticFeature);
    if (null == value) {
      Button defaultBtn = getDefaultSemanticField();
      if (null != defaultBtn) {
        defaultBtn.setSelection(true);
      }
    } else if (value instanceof Enumerator) {
      for (Button button : getSemanticFields()) {
        if (null != button) {
          selectButton(button, (Enumerator) value);
        }
      }
    }
  }

  /**
   * Select given radio button with specified enumerator.<br>
   * Specified enumerator is compared with button's data object.
   * @param button_p
   * @param enumerated_p
   */
  protected void selectButton(Button button_p, Enumerator enumerated_p) {
    button_p.setSelection(enumerated_p.equals(button_p.getData()));
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event_p) {
    Button source = (Button) event_p.widget;
    // Take into account the new selection.
    if (source.getSelection()) {
      setValue(source.getData());
    }
  }

  /**
   * @param value_p
   */
  protected void setValue(final Object value_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        _semanticElement.eSet(_semanticFeature, value_p);
      }
    };
    executeCommand(command);
  }

  /**
   * Get the default semantic field in the current kind group.
   */
  public abstract Button getDefaultSemanticField();

  /**
   * @return the group
   */
  public Group getGroup() {
    return _group;
  }
}

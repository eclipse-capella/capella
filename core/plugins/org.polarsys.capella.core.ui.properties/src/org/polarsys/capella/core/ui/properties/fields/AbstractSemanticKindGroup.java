/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * Abstract based class to implement a semantic field based on an EMF generated Enumeration with radio buttons.
 */
public abstract class AbstractSemanticKindGroup extends AbstractSemanticButtonGroup {

  protected Group _group;
  
  public AbstractSemanticKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, String groupLabel, boolean enabled, int numColumns) {
    super(widgetFactory);

    _group = createGroup(parent, groupLabel, enabled, numColumns);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param groupLabel
   */
  public AbstractSemanticKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, String groupLabel, int numColumns) {
    this(parent, widgetFactory, groupLabel, true, numColumns);
  }

  /**
   * Create a radio button.
   * @param enumerated
   * @return a not <code>null</code> object.
   */
  protected Button createButton(Enumerator enumerated) {
    return createButton(enumerated, true);
  }

  /**
   * Create a radio button.
   * @param enumerated
   * @param enabled
   * @return a not <code>null</code> object.
   */
  protected Button createButton(Enumerator enumerated, boolean enabled) {
    return createButton(_group, enumerated.getLiteral(), enumerated, enabled, SWT.RADIO);
  }

  /**
   * Create a group.
   * @param parent
   * @param label
   * @param numColumns
   * @return a not <code>null</code> object.
   */
  protected Group createGroup(Composite parent, String label, boolean enabled, int numColumns) {
    _group = widgetFactory.createGroup(parent, label);
    _group.setLayout(new GridLayout(numColumns, true));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = ((GridLayout) parent.getLayout()).numColumns;
    _group.setLayoutData(gd);
    _group.setEnabled(enabled);
    return _group;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, semanticFeature);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement, EStructuralFeature feature) {
    super.loadData(capellaElement, feature);

    Object value = semanticElement.eGet(semanticFeature);
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
   * @param button
   * @param enumerated
   */
  protected void selectButton(Button button, Enumerator enumerated) {
    button.setSelection(enumerated.equals(button.getData()));
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event) {
    Button source = (Button) event.widget;
    // Take into account the new selection.
    if (source.getSelection()) {
      setValue(source.getData());
    }
  }

  /**
   * @param value
   */
  protected void setValue(final Object value) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        semanticElement.eSet(semanticFeature, value);
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

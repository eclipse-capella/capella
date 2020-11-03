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
package org.polarsys.capella.core.data.common.properties.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class StateTransitionGroup extends AbstractSemanticField {

  private Text _triggerDescriptionField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public StateTransitionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createTriggerDescriptionTextField(textGroup);
  }

  /**
   * @param textGroup
   */
  private void createTriggerDescriptionTextField(Group textGroup) {
    widgetFactory.createCLabel(textGroup, Messages.getString("StateTransitionGroup.TriggerDescription.Label")); //$NON-NLS-1$
    _triggerDescriptionField = widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    _triggerDescriptionField.addFocusListener(this);
    _triggerDescriptionField.addKeyListener(this);
    _triggerDescriptionField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != semanticElement && null != _triggerDescriptionField) {
    	setTextValue(_triggerDescriptionField, semanticElement, CapellacommonPackage.eINSTANCE.getStateTransition_TriggerDescription());
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(_triggerDescriptionField)) {
      setDataValue(semanticElement, CapellacommonPackage.eINSTANCE.getStateTransition_TriggerDescription(), _triggerDescriptionField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if ((null != _triggerDescriptionField) && !_triggerDescriptionField.isDisposed()) {
      _triggerDescriptionField.setEnabled(enabled);
    }
  }
}

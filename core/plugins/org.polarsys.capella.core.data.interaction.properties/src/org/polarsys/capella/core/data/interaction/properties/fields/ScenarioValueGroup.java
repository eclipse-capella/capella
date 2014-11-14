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
package org.polarsys.capella.core.data.interaction.properties.fields;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class ScenarioValueGroup extends AbstractCapabilityValueGroup {

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public ScenarioValueGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(parent_p, widgetFactory_p);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    super.loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != _preConditionField) {
        setTextValue(_preConditionField, _semanticElement, InteractionPackage.eINSTANCE.getScenario_PreCondition());
      }
      if (null != _postConditionField) {
        setTextValue(_postConditionField, _semanticElement, InteractionPackage.eINSTANCE.getScenario_PostCondition());
      }
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_preConditionField)) {
      setDataValue(_semanticElement, InteractionPackage.eINSTANCE.getScenario_PreCondition(), _preConditionField.getText());
    } else if (textField_p.equals(_postConditionField)) {
      setDataValue(_semanticElement, InteractionPackage.eINSTANCE.getScenario_PostCondition(), _postConditionField.getText());
    }
  }
}

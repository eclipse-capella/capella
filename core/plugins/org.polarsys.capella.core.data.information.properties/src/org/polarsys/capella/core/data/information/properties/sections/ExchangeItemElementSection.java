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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.ExchangeItemElementController;
import org.polarsys.capella.core.data.information.properties.fields.ElementKindGroup;
import org.polarsys.capella.core.data.information.properties.fields.ExchangeItemElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.properties.fields.ParameterDirectionGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ExchangeItemElement section on the Button tab.
 */
public class ExchangeItemElementSection extends MultiplicityElementSection {

  private ExchangeItemElementBooleanPropertiesCheckbox exchangeItemElementBooleanPropertiesCheckbox;
  private ElementKindGroup elementKindGroup;
  protected ParameterDirectionGroup parameterDirectionGroup;

  private MultipleSemanticField _referencedProperties;

  /**
   * Default constructor.
   */
  public ExchangeItemElementSection() {
    super(true, true, false, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    exchangeItemElementBooleanPropertiesCheckbox = new ExchangeItemElementBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    exchangeItemElementBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    _referencedProperties =
        new MultipleSemanticField(getReferencesGroup(),
            Messages.getString("ExchangeItemElement_ReferencedProperties_Label"), getWidgetFactory(), new ExchangeItemElementController()); //$NON-NLS-1$
    _referencedProperties.setDisplayedInWizard(displayedInWizard);

    elementKindGroup = new ElementKindGroup(parent, getWidgetFactory()) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void selectionChanged(ElementKind selection) {
        refresh();
      }
    };
    elementKindGroup.setEnabled(false);
    elementKindGroup.setDisplayedInWizard(displayedInWizard);

    parameterDirectionGroup = new ParameterDirectionGroup(parent, getWidgetFactory());
    parameterDirectionGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    ExchangeItemElement element = (ExchangeItemElement) capellaElement;

    exchangeItemElementBooleanPropertiesCheckbox.loadData(element);

    elementKindGroup.loadData(element, InformationPackage.eINSTANCE.getExchangeItemElement_Kind());
    updateParameterGroup(element.getKind());
	  elementKindGroup.setEnabled(false);

    parameterDirectionGroup.loadData(element, InformationPackage.eINSTANCE.getExchangeItemElement_Direction());
    _referencedProperties.loadData(element, InformationPackage.eINSTANCE.getExchangeItemElement_ReferencedProperties());
  }

  /**
   * @param kind
   */
  protected void updateParameterGroup(ElementKind kind) {
    if (ElementKind.MEMBER.equals(kind)) {
    	parameterDirectionGroup.setEnabled(true);
    } else {
    	parameterDirectionGroup.setEnabled(false);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getExchangeItemElement()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(elementKindGroup);
    fields.add(parameterDirectionGroup);
    fields.add(_referencedProperties);
    fields.add(exchangeItemElementBooleanPropertiesCheckbox);

    return fields;
  }
}

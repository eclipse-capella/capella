/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Port section.
 */
public abstract class PortSection extends NamedElementSection {

  private MultipleSemanticField providedInterfacesField;
  private MultipleSemanticField requiredInterfacesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    providedInterfacesField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("PortSection_ProvidedInterfaces_Label"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), InformationPackage.Literals.PORT__PROVIDED_INTERFACES);
      }
    });
    providedInterfacesField.setDisplayedInWizard(displayedInWizard);

    requiredInterfacesField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("PortSection_RequiredInterfaces_Label"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), InformationPackage.Literals.PORT__REQUIRED_INTERFACES);
      }
    });
    requiredInterfacesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    providedInterfacesField.loadData(capellaElement, InformationPackage.eINSTANCE.getPort_ProvidedInterfaces());
    requiredInterfacesField.loadData(capellaElement, InformationPackage.eINSTANCE.getPort_RequiredInterfaces());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(providedInterfacesField);
    fields.add(requiredInterfacesField);

    return fields;
  }
}

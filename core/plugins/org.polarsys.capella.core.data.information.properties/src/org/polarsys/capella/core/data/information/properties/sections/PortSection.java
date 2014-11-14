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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Port section.
 */
public abstract class PortSection extends NamedElementSection {

  private MultipleSemanticField _providedInterfacesField;
  private MultipleSemanticField _requiredInterfacesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _providedInterfacesField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("PortSection_ProvidedInterfaces_Label"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), InformationPackage.Literals.PORT__PROVIDED_INTERFACES);
      }
    });
    _providedInterfacesField.setDisplayedInWizard(displayedInWizard);

    _requiredInterfacesField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("PortSection_RequiredInterfaces_Label"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), InformationPackage.Literals.PORT__REQUIRED_INTERFACES);
      }
    });
    _requiredInterfacesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _providedInterfacesField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getPort_ProvidedInterfaces());
    _requiredInterfacesField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getPort_RequiredInterfaces());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_providedInterfacesField);
    fields.add(_requiredInterfacesField);

    return fields;
  }
}

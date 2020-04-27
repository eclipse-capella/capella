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
package org.polarsys.capella.core.data.common.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.data.common.properties.controllers.StateController;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The State section.
 */
public class StateSection extends AbstractStateSection {

  private MultipleSemanticField functionsField;
  private MultipleSemanticField activityField;
  private MultipleSemanticField entryField;
  private MultipleSemanticField exitField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    Group main = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    entryField = new MultipleSemanticField(main, Messages.getString("State.Entry"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CapellacommonPackage.eINSTANCE.getState_Entry());
      }
    });
    entryField.setDisplayedInWizard(displayedInWizard);

    activityField = new MultipleSemanticField(main, Messages.getString("State.Activity"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CapellacommonPackage.eINSTANCE.getState_DoActivity());
      }
    });
    activityField.setDisplayedInWizard(displayedInWizard);

    exitField = new MultipleSemanticField(main, Messages.getString("State.Exit"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CapellacommonPackage.eINSTANCE.getState_Exit());
      }
    });
    exitField.setDisplayedInWizard(displayedInWizard);

    functionsField = new MultipleSemanticField(main, Messages.getString("State.Functions"), getWidgetFactory(), new StateController()) { //$NON-NLS-1$
          /**
           * {@inheritDoc}
           */
          @SuppressWarnings("unchecked")
          @Override
          protected void removeAllDataValue(EObject object, EStructuralFeature feature) {
            for (EObject referencer : EObjectExt.getReferencers(object, (EReference) feature)) {
              ((List<EObject>) referencer.eGet(feature)).remove(object);
            }
          }
        };
    functionsField.setDisplayedInWizard(displayedInWizard);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    entryField.loadData(capellaElement, CapellacommonPackage.Literals.STATE__ENTRY);
    activityField.loadData(capellaElement, CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
    exitField.loadData(capellaElement, CapellacommonPackage.Literals.STATE__EXIT);

    functionsField.loadData(capellaElement, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CapellacommonPackage.eINSTANCE.getState()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(entryField);
    fields.add(activityField);
    fields.add(exitField);
    fields.add(functionsField);

    return fields;
  }
}

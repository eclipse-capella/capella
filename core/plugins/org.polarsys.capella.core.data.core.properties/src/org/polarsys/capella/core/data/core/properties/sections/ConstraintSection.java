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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Constraint section.
 */
public class ConstraintSection extends NamedElementSection {

  private MultipleSemanticField _constrainedElementsField;

  private ConstraintReferenceGroup _specificationGroup;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _constrainedElementsField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("Constraint.ConstrainedElements.Label"), //$NON-NLS-1$
        getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
          @Override
          protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
            return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(),
                ModellingcorePackage.eINSTANCE.getAbstractConstraint_ConstrainedElements());
          }
        });
    _constrainedElementsField.setDisplayedInWizard(displayedInWizard);

    _specificationGroup = new ConstraintReferenceGroup(Collections.singletonMap(Messages.getString("Constraint.OwnedSpecification.Label"), ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION), false); //$NON-NLS-1$
    _specificationGroup.createControls(parent, getWidgetFactory(), isDisplayedInWizard());

            }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    _constrainedElementsField.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getAbstractConstraint_ConstrainedElements());
    _specificationGroup.loadData(capellaElement);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CapellacorePackage.eINSTANCE.getConstraint()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());
    fields.add(_constrainedElementsField);
    fields.addAll(_specificationGroup.getFields());
    return fields;
  }

  @Override
  public void dispose() {
    super.dispose();

    }

      }

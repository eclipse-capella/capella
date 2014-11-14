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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * The Constraint section.
 */
public class ConstraintSection extends NamedElementSection {

  private TextAreaValueGroup _contentGroup;
  private SimpleSemanticField _expressionField;
  private MultipleSemanticField _constrainedElementsField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _contentGroup = new TextAreaValueGroup(_rootParentComposite, Messages.getString("Constraint.Content.Label"), getWidgetFactory()); //$NON-NLS-1$
    _contentGroup.setDisplayedInWizard(displayedInWizard);
    
    _constrainedElementsField = new MultipleSemanticField(getReferencesGroup(),
      Messages.getString("Constraint.ConstrainedElements.Label"), //$NON-NLS-1$
      getWidgetFactory(),
      new AbstractMultipleSemanticFieldController() {
        @Override
        protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
          return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), ModellingcorePackage.eINSTANCE.getAbstractConstraint_ConstrainedElements());
        }
      }
    );
    _constrainedElementsField.setDisplayedInWizard(displayedInWizard);

    _expressionField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("Constraint.Expression.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _expressionField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _contentGroup.loadData(capellaElement_p, CapellacorePackage.eINSTANCE.getAbstractAnnotation_Content());
    _constrainedElementsField.loadData(capellaElement_p, ModellingcorePackage.eINSTANCE.getAbstractConstraint_ConstrainedElements());
    _expressionField.loadData(capellaElement_p, CapellacorePackage.eINSTANCE.getConstraint_Expression());
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
    fields.add(_contentGroup);
    fields.add(_constrainedElementsField);
    fields.add(_expressionField);

    return fields;
  }
}

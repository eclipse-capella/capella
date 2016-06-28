/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 */
public abstract class StateEventSection extends NamedElementSection {

  private SimpleEditableSemanticField _expression;

  /**
   * 
   */
  public StateEventSection() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    boolean displayedInWizard = isDisplayedInWizard();
    super.createControls(parent, aTabbedPropertySheetPage);

    _expression = new SimpleEditableSemanticField(getReferencesGroup(), Messages.getString("StateEvent.Expression"), getWidgetFactory(), //$NON-NLS-1$ 
        ICommonConstants.EMPTY_STRING, new AbstractSimpleEditableSemanticFieldController() {

          @Override
          public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
            semanticElement.eSet(semanticFeature, value);
            return value;
          }

          @Override
          public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName) {
            if (semanticElement instanceof StateEvent) {
              Constraint currentValue = (Constraint) semanticElement.eGet(semanticFeature);
              if (currentValue != null) {
                editValueWizard(currentValue);
              } else {
                Constraint newValue = CapellacoreFactory.eINSTANCE.createConstraint();
                StateEvent stateEvent = (StateEvent) semanticElement;
                stateEvent.getOwnedConstraints().add(newValue);

                semanticElement.eSet(semanticFeature, newValue);
                if (editValueWizard(newValue)) {
                  currentValue = newValue;
                } else {
                  throw new EditableSemanticFieldException();
                }
              }

              return currentValue;
            }
            return null;
          }

        });

    _expression.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public abstract boolean select(Object toTest);

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(CapellaElement capellaElement) {
    super.loadData(capellaElement);

    _expression.loadData(capellaElement, CapellacommonPackage.Literals.STATE_EVENT__EXPRESSION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    ArrayList<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());

    fields.add(_expression);

    return fields;
  }

}
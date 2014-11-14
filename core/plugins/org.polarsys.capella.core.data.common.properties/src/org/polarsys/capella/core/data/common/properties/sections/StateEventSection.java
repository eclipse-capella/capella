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

  private SimpleEditableSemanticField _guard;

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
  public void createControls(Composite parent_p, TabbedPropertySheetPage aTabbedPropertySheetPage_p) {
    boolean displayedInWizard = isDisplayedInWizard();
    super.createControls(parent_p, aTabbedPropertySheetPage_p);

    _guard = new SimpleEditableSemanticField(getReferencesGroup(), Messages.getString("StateEvent.Guard"), getWidgetFactory(), //$NON-NLS-1$ 
        ICommonConstants.EMPTY_STRING, new AbstractSimpleEditableSemanticFieldController() {

          @Override
          public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value) {
            semanticElement_p.eSet(semanticFeature_p, value);
            return value;
          }

          /**
           * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#editValue()
           */
          @Override
          public EObject editValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p) {
            if (semanticElement_p instanceof StateEvent) {
              Constraint currentValue = (Constraint) semanticElement_p.eGet(semanticFeature_p);
              if (currentValue != null) {
                editValueWizard(currentValue);
              } else {
                Constraint newValue = CapellacoreFactory.eINSTANCE.createConstraint();
                StateEvent stateEvent = (StateEvent) semanticElement_p;
                stateEvent.getOwnedConstraints().add(newValue);

                semanticElement_p.eSet(semanticFeature_p, newValue);
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

    _guard.setDisplayedInWizard(displayedInWizard);
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
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _guard.loadData(capellaElement_p, CapellacommonPackage.Literals.STATE_EVENT__CONDITION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    ArrayList<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());

    fields.add(_guard);

    return fields;
  }

}
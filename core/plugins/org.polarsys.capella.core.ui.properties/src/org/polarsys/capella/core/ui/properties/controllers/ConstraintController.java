/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.model.helpers.ConstraintExt;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 */
public class ConstraintController extends AbstractSimpleEditableSemanticFieldController {

  @Override
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName,
      EObject value) {
    semanticElement.eSet(semanticFeature, value);
    return value;
  }

  @Override
  public EObject loadValue(EObject semanticElement, EStructuralFeature semanticFeature) {
    if (semanticElement instanceof Constraint) {
      return semanticElement;
    }
    return super.loadValue(semanticElement, semanticFeature);
  }

  public EObject getElementToEdit(EObject semanticElement, EStructuralFeature semanticFeature) {
    EObject expectedConstraint = loadValue(semanticElement, semanticFeature);
    if (expectedConstraint == null || !(expectedConstraint instanceof Constraint)) {
      return null;
    }
    Constraint constraint = (Constraint) expectedConstraint;
    if (!(semanticElement instanceof Constraint)) {
      if (constraint.getName() != null && !constraint.getName().isEmpty()) {
        return constraint;
      }
    }
    ValueSpecification expectedOpaqueExpression = constraint.getOwnedSpecification();
    if (expectedOpaqueExpression == null || !(expectedOpaqueExpression instanceof OpaqueExpression)) {
      return null;
    }
    OpaqueExpression opaqueExpression = (OpaqueExpression) expectedOpaqueExpression;
    return opaqueExpression;
  }

  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String text, boolean isLocalEdit) {

    boolean wasCreatedSpec = false;
    boolean wasCreatedCons = false;

    // Get constraint on semantic element

    EObject elementToEdit = getElementToEdit(semanticElement, semanticFeature);
    
    if (elementToEdit instanceof Constraint) {
      ((Constraint) elementToEdit).setName(text);

    } else {
      
      Constraint constraint = (Constraint) loadValue(semanticElement, semanticFeature);

      // If element has no constraint, create one
      if (null == constraint) {
        constraint = CapellacoreFactory.eINSTANCE.createConstraint();
        ((CapellaElement) semanticElement).getOwnedConstraints().add(constraint);
        CreationHelper.performContributionCommands(constraint, semanticElement);
        semanticElement.eSet(semanticFeature, constraint);
        wasCreatedCons = true;
      }

      ValueSpecification specification = constraint.getOwnedSpecification();

      // In case constraint was created, need to create associated specification
      if (null == specification) {
        specification = DatavalueFactory.eINSTANCE.createOpaqueExpression();
        constraint.setOwnedSpecification(specification);
        CreationHelper.performContributionCommands(specification, constraint);
        wasCreatedSpec = true;
      }

      // Tell if user has clicked on finish or cancel
      boolean hasPerformedFinish = isLocalEdit || editValueWizard(specification);

      // In case of cancel, must remove the constraint and specification in case it has been created
      if (!hasPerformedFinish) {
        if (wasCreatedCons) {
          SimpleEditableSemanticField.deleteContainmentValue(constraint);
        } else if (wasCreatedSpec) {
          SimpleEditableSemanticField.deleteContainmentValue(specification);
        }
      }

      if (isLocalEdit && specification instanceof OpaqueExpression) {
        OpaqueExpression expression = (OpaqueExpression) specification;

        // If OpaqueExpression has no content, create an empty LINKED_TEXT content
        if (expression.getLanguages().isEmpty() || expression.getBodies().isEmpty()) {
          expression.getLanguages().add(ConstraintExt.OPAQUE_EXPRESSION_LINKED_TEXT);
          expression.getBodies().add("");
        }
        expression.getBodies().set(0, text);
      }

    }
    
    return (EObject) semanticElement.eGet(semanticFeature);
  }

  @Override
  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String text) {
    return editValue(semanticElement, semanticFeature, text, false);
  }
  
  public EObject editText(EObject semanticElement, EStructuralFeature semanticFeature, String text) {
    return editValue(semanticElement, semanticFeature, text, true);
  }

  /**
   * Return a non-empty body text
   */
  public String loadText(EObject semanticElement, EStructuralFeature semanticFeature) {
    EObject elementToEdit = getElementToEdit(semanticElement, semanticFeature);

    if (elementToEdit instanceof Constraint) {
      return ((Constraint) elementToEdit).getName();

    } else if (elementToEdit instanceof OpaqueExpression) {
      String body = ConstraintExt.getPrimaryBody((OpaqueExpression) elementToEdit);
      if (body != null) {
        return body;
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

}

/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.TimeEvent;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ConstraintExt;

/**
 * When an user has customized a gradient container, backgroundColor doesn't have a value but use by default the foregroundColor. With Sirius 3.0, they don't
 * have migrate this behavior so the backgroundColor become white and a gradient white to foregroundColor is created instead of foregroundColor-foregroundColor
 */
public class StateEventContribution extends AbstractMigrationContribution {

  HashMap<TimeEvent, String> values = new HashMap<TimeEvent, String>();

  @Override
  public String getFeatureName(String prefix, String name, boolean isElement, EObject peekObject, String value, Resource resource, MigrationContext context) {
    if (peekObject instanceof StateEvent) {
      if ("condition".equals(name)) {
        return CapellacommonPackage.Literals.STATE_EVENT__EXPRESSION.getName();
      }
    }
    return super.getFeatureName(prefix, name, isElement, peekObject, value, resource, context);
  }

  @Override
  public boolean ignoreUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value, XMLResource resource,
      MigrationContext context) {

    if (peekObject instanceof TimeEvent) {
      if ("time".equals(name)) {
        values.put((TimeEvent) peekObject, value);
        return true;
      }
    }

    return super.ignoreUnknownFeature(prefix, name, isElement, peekObject, value, resource, context);
  }

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (values.containsKey(currentElement)) {
      TimeEvent event = (TimeEvent) currentElement;
      String timeValue = values.get(currentElement);

      Constraint constraint = event.getExpression();

      // If element has no constraint, create one
      if (null == constraint) {
        constraint = CapellacoreFactory.eINSTANCE.createConstraint();
        event.getOwnedConstraints().add(constraint);
        CapellaElementExt.creationService(constraint);
        event.setExpression(constraint);
      }

      // In case constraint was created, need to create associated specification
      if (null == constraint.getOwnedSpecification()) {
        ValueSpecification expression = constraint.getOwnedSpecification();
        expression = DatavalueFactory.eINSTANCE.createOpaqueExpression();
        constraint.setOwnedSpecification(expression);
        CapellaElementExt.creationService(constraint);
      }

      // We append the timeValue to the LinkedText expression
      if (constraint.getOwnedSpecification() instanceof OpaqueExpression) {
        OpaqueExpression expression = (OpaqueExpression) constraint.getOwnedSpecification();

        String currentValue = ConstraintExt.getBody(expression, ConstraintExt.OPAQUE_EXPRESSION_LINKED_TEXT);
        if (currentValue == null) {
          currentValue = timeValue;
        } else {
          currentValue = currentValue + " " + timeValue;
        }
        ConstraintExt.setBody(expression, ConstraintExt.OPAQUE_EXPRESSION_LINKED_TEXT, currentValue);

      } else {
        // Otherwise, we append somewhere the timeValue
        constraint.getOwnedSpecification().setName(constraint.getOwnedSpecification().getName() + " " + timeValue);
      }
    }
  }

  @Override
  public void dispose(ExecutionManager manager, ResourceSet resourceSet, MigrationContext context) {
    super.dispose(manager, resourceSet, context);
    values.clear();
  }

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);
  }

}

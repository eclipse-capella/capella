/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check whether a constraint is not referenced.
 */
public class I_36_ConstraintNotReferenced extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObject = ctx.getTarget();
    if (eObject instanceof Constraint) {
      Constraint constraint = (Constraint) eObject;
      EObject container = eObject.eContainer();

      if (!constraint.getConstrainedElements().isEmpty()) {
        return ctx.createSuccessStatus();
      }

      List<EReference> references = new ArrayList<EReference>();
      references.add(CapellacommonPackage.Literals.STATE_TRANSITION__GUARD);
      references.add(CapellacommonPackage.Literals.STATE_EVENT__EXPRESSION);
      references.add(InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT);
      references.add(InteractionPackage.Literals.SCENARIO__PRE_CONDITION);
      references.add(InteractionPackage.Literals.SCENARIO__POST_CONDITION);
      references.add(InteractionPackage.Literals.ABSTRACT_CAPABILITY__PRE_CONDITION);
      references.add(InteractionPackage.Literals.ABSTRACT_CAPABILITY__POST_CONDITION);
      references.add(InteractionPackage.Literals.INTERACTION_OPERAND__GUARD);

      if (EObjectExt.getReferencers(constraint, references).isEmpty()) {
        
        if (container instanceof StateTransition) {
          return failMessage(ctx, constraint, "Guard", container);
          
        } else if (container instanceof StateEvent) {
          return failMessage(ctx, constraint, "Expression", container);
          
        } else if (container instanceof SequenceMessage) {
          return failMessage(ctx, constraint, "Exchange Context", container);
          
        } else if (container instanceof Scenario) {
          return failMessage(ctx, constraint, "Pre/Post Condition", container);
          
        } else if (container instanceof AbstractCapability) {
          return failMessage(ctx, constraint, "Pre/Post Condition", container);
          
        } else if (container instanceof InteractionOperand) {
          return failMessage(ctx, constraint, "Guard", container);

        } else {
          return failMessage(ctx, constraint);
        }
          
      }

    }
    return ctx.createSuccessStatus();
  }

  /**
   * 
   * @param ctx
   * @param constraint
   *          the constraint under validation
   * @param feature
   *          the name of the feature referencing the constraint
   * @param container
   *          the container of the constraint
   * @return
   */
  protected IStatus failMessage(IValidationContext ctx, Constraint constraint, String feature, EObject container) {
    String a = EObjectLabelProviderHelper.getText(constraint);
    String b = EObjectLabelProviderHelper.getText(container);
    String c = EObjectLabelProviderHelper.getMetaclassLabel(container, false);
    String result = NLS.bind(", specifically on {0} of the owning {1} ({2})", new String[] {feature, b, c});
    return ctx.createFailureStatus(new Object[] { a, result });
  }
  
  protected IStatus failMessage(IValidationContext ctx, Constraint constraint) {
    String a = EObjectLabelProviderHelper.getText(constraint);
    return ctx.createFailureStatus(new Object[] { a, ICommonConstants.EMPTY_STRING});
  }
}

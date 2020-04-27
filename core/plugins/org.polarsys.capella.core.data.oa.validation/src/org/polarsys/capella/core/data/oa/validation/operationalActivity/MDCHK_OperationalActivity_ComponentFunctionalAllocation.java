/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.oa.validation.operationalActivity;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Checks allocation consistency between Operational activities and Entities
 */
public class MDCHK_OperationalActivity_ComponentFunctionalAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      // validation applied only on leaf OA
      if (eObj instanceof OperationalActivity && !(eObj.eContainer() instanceof OperationalActivityPkg)) {
        OperationalActivity oa = (OperationalActivity) eObj;
        if (FunctionExt.isLeaf(oa)) {
          if (isRecursivelyAllocated(oa)) {
            // Allocation OK
            return ctx_p.createSuccessStatus();
          }
          return ctx_p.createFailureStatus(new Object[] { oa.getName() });
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

  /**
   * Allows to know if the given operational activity is recursively allocated to operational entities considering this rule:<br>
   * if one operational activity is allocated, then it is considered that its children are too.<br>
   * Basically, this method allows to know if the given operational activity is "fully" allocated or not, i.e, if one of its leaf children is not allocated, the
   * operational activity will not be considered as fully allocated.
   * @param oa_p the operational activity
   * @return <code>true</code> if the operational activity is "fully" allocated, <code>false</code> otherwise
   */
  protected boolean isRecursivelyAllocated(OperationalActivity oa_p) {
    if (isAllocated(oa_p)) {
      // Allocation OK
      return true;
    }
    if (FunctionExt.isLeaf(oa_p)) {
      // The OA is a leaf and is not allocated
      return false;
    }
    // The OA itself is not allocated, but is not a leaf
    // you have to check its children
    //
    // Gets the OA children
    for (OperationalActivity childOa : oa_p.getChildrenOperationalActivities()) {
      if (!isRecursivelyAllocated(childOa)) {
        // The child OA is for sure not allocated (i.e it is a non allocated leaf)
        return false;
      }
    }
    // No unallocated leaf
    return true;
  }

  /**
   * Allow to know if the given operational activity is directly allocated to an operational entity or not
   * @param oa_p the operational activity
   * @return <code>true</code> if it is allocated, <code>false</code> otherwise
   */
  protected static boolean isAllocated(OperationalActivity oa_p) {
    for (AbstractTrace trace : oa_p.getIncomingTraces()) {
      // Check if the current OA is linked directly to the Entity by a ComponentFunctionalAllocation
      if ((trace instanceof ComponentFunctionalAllocation) && (trace.getSourceElement() instanceof Entity)) {
        return true;
      } else if ((trace instanceof ActivityAllocation) && (trace.getSourceElement() instanceof Role)) {
        // Check if the current OA is linked indirectly to the Entity by a Role
        Role role = (Role) trace.getSourceElement();
        for (AbstractTrace trace2 : role.getIncomingTraces()) {
          if ((trace2 instanceof RoleAllocation) && (trace2.getSourceElement() instanceof Entity)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}

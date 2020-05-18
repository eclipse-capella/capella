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
package org.polarsys.capella.core.re.validation.design.consistency;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandler;

import com.google.common.base.Predicate;

/**
 * A blackbox compliance where selected features can be marked as modifiable.
 */
public class BlackBoxComplianceWithExceptions extends AbstractComplianceConstraint {

  private final Collection<EStructuralFeature> allowed;

  public BlackBoxComplianceWithExceptions(Predicate<CompliancyDefinition> compliancyDefinitionPredicate, Collection<EStructuralFeature> allowed) {
    super(compliancyDefinitionPredicate);
    this.allowed = allowed;
  }

  public BlackBoxComplianceWithExceptions(Collection<EStructuralFeature> allowed) {
    this(new Predicate<CompliancyDefinition>() {
        @Override
        public boolean apply(CompliancyDefinition input) {
          return ReplicableElementHandler.COMPLIANCY_BLACK_BOX_NAME.equals(input.getName());
        }
      }, allowed);
  }

  @Override
  protected IStatus validateAddReference(ComplianceValidationContext ctx, EReference ref, Object vRpl, Object expected) {
    if (allowed.contains(ref)){
        return Status.OK_STATUS;
    }
    return ctx.createFailureStatus(ref);
  }

  @Override
  protected IStatus validateRemoveReference(ComplianceValidationContext ctx, EReference ref, Object vRpl, Object vRec) {
    if (allowed.contains(ref)) {
        return Status.OK_STATUS;
    }
    return ctx.createFailureStatus(ref);
  }

  @Override
  protected IStatus validateDifferentReference(ComplianceValidationContext ctx, EReference ref, Object vRpl, Object vRec) {
    if (allowed.contains(ref)) {
      return Status.OK_STATUS;
    }
    return ctx.createFailureStatus(ref);
  }

}

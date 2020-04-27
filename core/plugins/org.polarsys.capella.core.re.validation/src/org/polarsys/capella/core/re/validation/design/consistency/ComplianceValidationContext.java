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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;

import com.google.common.base.Predicate;

/**
 * A validation context that describes a change to an element in a RPL.
 */
public class ComplianceValidationContext {

  final CatalogElementLink rplLink;
  final IValidationContext ctx;

  /**
   * Find out the set of readonly RPL with a compliance that matches the filter argument to which the constraint target belongs to
   *
   * @param ctx the current validation context
   * @param filter the filter to apply for compliance matching
   * @return
   */
  static Collection<ComplianceValidationContext> create(IValidationContext ctx, Predicate<CompliancyDefinition> filter) {

    Collection<ComplianceValidationContext> result = new ArrayList<ComplianceValidationContext>();
    for (CatalogElementLink rplLink : ReplicableElementExt.getReferencingLinks(ctx.getTarget())) {
      CatalogElement rpl = rplLink.getSource();
      if (rpl != null && rpl.getKind() == CatalogElementKind.RPL && rpl.getOrigin() != null && rpl.isReadOnly() && filter.apply(rpl.getCurrentCompliancy())) {
        CatalogElementLink recLink = rplLink.getOrigin();
        if (recLink != null) {
          if (recLink.getSource() == rpl.getOrigin()) {
            EObject recElement = recLink.getTarget();
            if (recElement != null && recElement.eClass() == ctx.getTarget().eClass()) {
              result.add(new ComplianceValidationContext(ctx, rplLink));
            }
          }
        }
      }
    }

    return result;
  }

  protected IStatus createFailureStatus() {
    return createFailureStatus(ctx.getFeature());
  }

  protected IStatus createFailureStatus(EStructuralFeature feature) {
    ctx.addResult(getRplElement());
    ctx.addResult(getRPL());
    return ctx.createFailureStatus(feature, getRplElement());
  }

  ComplianceValidationContext(IValidationContext ctx, CatalogElementLink rplLink) {
    this.ctx = ctx;
    this.rplLink = rplLink;
  }

  /**
   * The RPL element that was changed.
   */
  public EObject getRplElement() {
    return rplLink.getTarget();
  }

  /**
   * The rec element that corresponds to the changed rpl element.
   * @return
   */
  public EObject getRecElement() {
    return getRecLink().getTarget();
  }

  /**
   * The RPL in this context.
   * @return
   */
  public CatalogElement getRPL() {
    return rplLink.getSource();
  }

  /**
   * The compliancy definition of the RPL.
   */
  public CompliancyDefinition getCompliance() {
    return getRPL().getCurrentCompliancy();
  }

  /**
   * The corresponding REC link.
   * @return
   */
  public CatalogElementLink getRecLink() {
    return rplLink.getOrigin();
  }

  /**
   * The link that traces the changed element to this context's RPL.
   * @return
   */
  public CatalogElementLink getRplLink() {
    return rplLink;
  }

  /**
   * The REC in this context.
   * @return @Nullable
   */
  public CatalogElement getREC() {
    return getRPL().getOrigin();
  }


  /**
   * The validation context that describes the change to the RPL element.
   * @return
   */
  public IValidationContext getValidationContext() {
    return ctx;
  }

}

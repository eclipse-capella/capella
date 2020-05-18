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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.NotificationAnalysis;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

import com.google.common.base.Predicate;

/**
 * A base class for REC/RPL compliance validation.
 */
public abstract class AbstractComplianceConstraint extends AbstractValidationRule {

  private final boolean validateUnsynchronizedFeatures;
  private final EqualityHelper equalityHelper = new EqualityHelper();
  private final Predicate<CompliancyDefinition> compliancyPredicate;

  public AbstractComplianceConstraint(Predicate<CompliancyDefinition> compliancyPredicate, boolean validateUnsynchronizedFeatures){
    this.compliancyPredicate = compliancyPredicate;
    this.validateUnsynchronizedFeatures = validateUnsynchronizedFeatures;
  }

  public AbstractComplianceConstraint(Predicate<CompliancyDefinition> compliancyPredicate) {
    this(compliancyPredicate, false);
  }

  @Override
  public IStatus validate(IValidationContext ctx) {

    ArrayList<IStatus> results = new ArrayList<IStatus>();

    if (ctx.getEventType() == EMFEventType.NULL) {

      if (ctx.getTarget() instanceof CatalogElement) {
        CatalogElement elem = (CatalogElement) ctx.getTarget();
        if (elem.getKind() == CatalogElementKind.RPL && elem.getCurrentCompliancy() != null && compliancyPredicate.apply(elem.getCurrentCompliancy())) {
          results.addAll(validateBatch(elem, ctx));
        }
      }

    } else {

      if (ctx.getFeature() == RePackage.Literals.CATALOG_ELEMENT__OWNED_LINKS) {

        CatalogElement ce = (CatalogElement) ctx.getTarget();

        if (ce.isReadOnly() && ce.getKind() == CatalogElementKind.RPL) {

          for (CatalogElementLink removed : getRemovedLinks(ctx)) {
            IStatus s = handleRemovedLink((CatalogElement) ctx.getTarget(), removed, ctx);
            if (!s.isOK()) {
              results.add(s);
            }
          }
        }

      } else if (ctx.getFeature() == RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET) {

        if (ctx.getFeatureNewValue() == null) {
          IStatus s = handleClearedLink((CatalogElementLink) ctx.getTarget(), ctx);
          if (!s.isOK()) {
            results.add(s);
          }

        } else {
          // Changing the target of a link once the target has been set ...
          Object original = NotificationAnalysis.getOriginalSingleValue(ctx.getTarget(), ctx.getFeature(), ctx.getAllEvents());
          if (original != null) {
            // Can't just consider this illegal: Create RPL copies the REC link and then updates it..
            // TODO .. check if the link is new, then allow it
          }
        }
      } else {

        // handle changes to relements that are part of readonly rpls with the given compliance
        for (ComplianceValidationContext cvc : ComplianceValidationContext.create(ctx, compliancyPredicate)) {
          IStatus result = validate(cvc);
          if (!result.isOK()) {
            results.add(result);
          }
        }

      }
    }

    IStatus result = null;
    if (results.size() == 1 ) {
      result = results.get(0);
    } else if (results.size() > 1) {
      result = ConstraintStatus.createMultiStatus(ctx, results);
    } else {
      result = ctx.createSuccessStatus();
    }

    return result;

  }

  /**
   * Validate the given RPL in batch mode. Currently, this implementation only validates rpl element contents for compliance, crossreferences
   * are not yet available.
   *
   * @param rpl, the rpl to validate
   * @param ctx the constraint status. the target is the rpl, and it is guaranteed that the rpl compliancy matches the compliancy filter
   * @return
   */
  protected Collection<? extends IStatus> validateBatch(CatalogElement rpl, IValidationContext ctx) {

    Collection<IStatus> results = new ArrayList<IStatus>();

    Collection<EObject> externalContents = new ArrayList<EObject>();
    Map<EObject, CatalogElementLink> rplContents = new HashMap<EObject, CatalogElementLink>();
    for (CatalogElementLink l : rpl.getOwnedLinks()) {
      EObject rplElement = l.getTarget();
      if (rplElement != null) {
        externalContents.addAll(rplElement.eContents());
        rplContents.put(rplElement, l);
      }
    }
    externalContents.removeAll(rplContents.keySet());
    for (EObject e : externalContents) {
      ComplianceValidationContext cvctx = new ComplianceValidationContext(ctx, rplContents.get(e.eContainer()));
      if (e.eContainingFeature().isMany()) {
        results.add(validateAddReference(cvctx, e.eContainmentFeature(), e, e));
      } else {
        results.add(validateDifferentReference(cvctx, e.eContainmentFeature(), e, e));
      }
    }

    return results;
  }

  /**
   * </p>
   * Handle the case where a CatalogElementLink was removed from its owner RPL. Note that the link is not necessarily
   * deleted: It might have moved to a different CatalogElement in the same transaction, so removed.eResource()
   * is not necessarily null.
   * <p>
   * If the method returns a failure status, the transaction is invalidated.
   * </p>
   * This default implementation returns a failure status in each of the following cases:
   * <ul>
   * <li>The link was moved from one CatalogElement to a different CatalogElement
   * <li>The deleted link still has an origin link and that origin link isn't deleted itself
   * </ul>
   *
   * @param owner the original owner of the link
   * @param link the link that was removed (but possibly moved to a different owner during this transaction)
   * @param ctx the surrounding validation context
   * @return a status indicating if link removal is valid or not
   */
  protected IStatus handleRemovedLink(CatalogElement owner, CatalogElementLink removed, IValidationContext ctx) {

    IStatus result = Status.OK_STATUS;

    if (removed.eResource() != null) {

      // It isn't allowed to move a link to some other place
      result = ctx.createFailureStatus(owner, removed);

    } else {

      // The link was really deleted. This is only allowed if the link has no origin link.
      CatalogElementLink recLink = NotificationAnalysis.getOriginalSingleValue(removed, RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN, ctx.getAllEvents());

      if (recLink != null && recLink.eResource() != null) { // and if that rec link wasn't also deleted
        result = ctx.createFailureStatus(owner, removed);
      }
    }

    return result;

  }

  /**
   * Handle the case where a RPL CatalogElementLink's target reference was set to null.
   * This default implementation always returns an error status, which will invalidate the change.
   *
   * @param owner
   * @param cleared
   * @param ctx
   * @return
   */
  protected IStatus handleClearedLink(CatalogElementLink cleared, IValidationContext ctx) {
    return ctx.createFailureStatus(ctx.getTarget(), ctx.getFeature());
  }

  /**
   * Validates the given ComplianceValidationContext.
   * @param ctx
   * @return
   */
  protected IStatus validate(ComplianceValidationContext ctx) {

    IStatus result = Status.OK_STATUS;
    EStructuralFeature feature = ctx.getValidationContext().getFeature();

    if (validateUnsynchronizedFeatures || !ctx.getRecLink().getUnsynchronizedFeatures().contains(feature.getName())) {

      if (feature instanceof EAttribute) {

        result = validateAttribute(ctx);

      } else {

        result = validateReference(ctx);

      }
    }

    return result;
  }

  protected IStatus validateReference(ComplianceValidationContext ctx) {

    IValidationContext vc = ctx.getValidationContext();
    final boolean add = vc.getEventType() == EMFEventType.ADD || vc.getEventType() == EMFEventType.ADD_MANY;
    final boolean remove = vc.getEventType() == EMFEventType.REMOVE || vc.getEventType() == EMFEventType.REMOVE_MANY;
    final boolean set = vc.getEventType() == EMFEventType.SET;

    if (add || remove || set) {

      Collection<?> values = Collections.emptyList(); // values added, set, or removed from feature

      if (vc.getFeatureNewValue() instanceof Collection<?>) {
        values = (Collection<?>) vc.getFeatureNewValue();
      } else {
        values = Collections.singleton(vc.getFeatureNewValue());
      }

      for (Object vRpl : values) {

        if (vRpl != null) { // may be null in case of setXXX(null)

          Object vRec = vRpl;

          // is the added or removed value also part of this RPL?
          Collection<CatalogElementLink> vRplLinks = ReplicableElementExt.getReferencingLinks((EObject) vRpl);

          for (Iterator<CatalogElementLink> it = vRplLinks.iterator(); it.hasNext();) {
            CatalogElementLink next = it.next();
            if (next.getSource() == ctx.getRPL()) {
              if (next.getOrigin() != null) {
                vRec = next.getOrigin().getTarget();
              }
            } else {
              it.remove();
            }
          }

          if (vRec != null) {

            if (vc.getFeature().isMany()) {

              if (add && !((Collection<?>)ctx.getRecElement().eGet(vc.getFeature())).contains(vRec)){
                return validateAddReference(ctx, (EReference) vc.getFeature(), vRpl, vRec);
              }

              if (remove && ((Collection<?>)ctx.getRecElement().eGet(vc.getFeature())).contains(vRec)){
                return validateRemoveReference(ctx, (EReference) vc.getFeature(), vRpl, vRec);
              }

            } else {

              if (ctx.getRecElement().eGet(vc.getFeature()) != vRec) {
                validateDifferentReference(ctx, (EReference) vc.getFeature(), vRpl, vRec);
              }

            }
          } else {

            // the added/removed vRpl is part of this context rpl, but we cannot find a corresponding vRec
            if (add ) {
              return validateAddReference(ctx, (EReference) vc.getFeature(), vRpl, vRec);
            }

            if (set) {
              return validateDifferentReference(ctx, (EReference) vc.getFeature(), vRpl, vRec);
            }

          }

        } else {

          // setXXX(null), allowed, but only if the rec element has it also set to null
          if (ctx.getRecElement().eGet(vc.getFeature()) != null) {
            validateDifferentReference(ctx, (EReference) vc.getFeature(), null, null);
          }

        }
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * Handle the case where an object <code>vRpl</code> was added to a multi-valued reference of a RPL element,
   * but the corresponding object <code>vRec</code> is <b>not</b> referenced by the complementing REC element.
   * <p>
   * If the added object <code>vRpl</code> is also part of the RPL, the corresponding object
   * <code>vRec</code> is its complementing element in the REC. If the complementing object cannot be found
   * <code>vRec</code> will be null.
   * Otherwise vRpl and vRec are the same object.
   * </p>
   * @param ctx the current context
   * @param ref the changed reference
   * @param vRpl the value that was added to a many-valued reference on the rpl element. Never null.
   * @param expected the value that should be referenced by the complementing rec element but isn't. Can be null as described above.
   * @return
   */
  protected abstract IStatus validateAddReference(ComplianceValidationContext ctx, EReference ref, Object vRpl, Object expected);

  /**
   * Handle the case where an object <code>vRpl</code> was removed from a many-valued reference of a RPL element,
   * but the corresponding object <code>vRec</code> is still referenced by the complementing REC element.
   * <p>
   * If the removed object <code>vRpl</code> is also part of the RPL, the corresponding object
   * <code>vRec</code> is its complementing element in the REC.
   * <code>vRec</code> will be null.
   * Otherwise vRpl and vRec are the same object.
   * </p>
   * @param ctx the current context
   * @param ref the changed reference
   * @param vRpl the value that was removed from a many-valued reference on the rpl element. Never null.
   * @param vRec the value that is still referenced by the complementing rec element but shouldn't be. Never null.
   * @return
   */
  protected abstract IStatus validateRemoveReference(ComplianceValidationContext ctx, EReference ref, Object vRpl, Object vRec);

  /**
   * Handle the case where an single-valued reference of an RPL element was set to <code>vRpl</code>,
   * but the corresponding REC element still has it set to something that is not the expected <code>vRec</code>.
   * <p>
   * If the set object <code>vRpl</code> is also part of the RPL, the corresponding object
   * <code>vRec</code> is its complementing element in the REC. If the complementing object cannot be found
   * <code>vRec</code> will be null.
   * Otherwise vRpl and vRec are the same object.
   * </p>
   * @param ctx the current context
   * @param ref the changed reference
   * @param vRpl the value that was removed from a many-valued reference on the rpl element. May be null, and in that case vRec is also null.
   * @param vRec the value that is still referenced by the complementing rec element but shouldn't be. Can be null as described above.
   * @return
   */
  protected abstract IStatus validateDifferentReference(ComplianceValidationContext ctx, EReference ref, Object vRpl, Object vRec);

  /**
   * Validate a change of an attribute of a RPL element. This default implementation returns an Error status
   * if the corresponding REC element's attribute isn't equal to the RPL element attribute according to
   * EcoreUtil.EqualityHelper
   * @param ctx
   * @return
   */
  protected IStatus validateAttribute(ComplianceValidationContext ctx) {
    if (ctx.getValidationContext().getFeature() == ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME) {
      return validateName(ctx);
    } else if (!equalityHelper.validateAttribute(ctx.getRecElement(), ctx.getRplElement(), (EAttribute) ctx.getValidationContext().getFeature())) {
      return ctx.createFailureStatus();
    }
    return Status.OK_STATUS;
  }

  /**
   * Validate a name change of a RPL element. This is a special case because of REC/RPL suffixes.
   * @param ctx
   * @return
   */
  protected IStatus validateName(ComplianceValidationContext ctx) {
    String rplName = ((AbstractNamedElement)ctx.getRplElement()).getName();
    String recName = ((AbstractNamedElement)ctx.getRecElement()).getName();
    String suffix = ""; //$NON-NLS-1$

    if (ctx.getRplLink().isSuffixed() && ctx.getRPL().getSuffix() != null) {
      suffix = ctx.getRPL().getSuffix();
    }
    if (recName == null || ((recName + suffix).equals(rplName))) {
      return Status.OK_STATUS;
    }
    return ctx.createFailureStatus();
  }


  @SuppressWarnings("serial")
  static class EqualityHelper extends EcoreUtil.EqualityHelper {
    public boolean validateAttribute(EObject eObject1, EObject eObject2, EAttribute attribute) {
      return super.haveEqualFeature(eObject1, eObject2, attribute);
    }
  }

  @SuppressWarnings("unchecked")
  private Collection<CatalogElementLink> getRemovedLinks(IValidationContext ctx){
    if (ctx.getEventType() == EMFEventType.REMOVE) {
      return Collections.singleton((CatalogElementLink)ctx.getFeatureNewValue());
    }
    if (ctx.getEventType() == EMFEventType.REMOVE_MANY) {
      return (Collection<CatalogElementLink>) ctx.getFeatureNewValue();
    }
    return Collections.emptyList();
  }


}
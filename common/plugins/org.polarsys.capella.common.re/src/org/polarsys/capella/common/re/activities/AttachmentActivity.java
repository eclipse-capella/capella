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
package org.polarsys.capella.common.re.activities;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.transition.common.activities.AbstractActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.core.transition.common.rules.AbstractRule;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.traceability.MatchConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 *
 */
public class AttachmentActivity extends AbstractActivity {

  public static final String ID = AttachmentActivity.class.getCanonicalName();

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus _run(ActivityParameters activityParams_p) {

    IContext context = getContext(activityParams_p);

    initializeTraceabilityAttachmentHandler(context, activityParams_p);

    CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    Collection<EObject> sourceAddedElements = ContextScopeHandlerHelper.getInstance(context).getCollection(IReConstants.SOURCE__ADDED_ELEMENTS, context);
    for (EObject object : sourceAddedElements) {
      attachContainment(context, object, true, sourceElement);
    }

    Collection<EObject> targetAddedElements = ContextScopeHandlerHelper.getInstance(context).getCollection(IReConstants.TARGET__ADDED_ELEMENTS, context);
    for (EObject object : targetAddedElements) {
      attachContainment(context, object, false, targetElement);
      updateElement(context, object, false, targetElement);
      renameElement(context, object, false, targetElement);
    }

    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value) || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value)
        || IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {

      //We set unsynchronize features from values set in the wizard by the user
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context).getElementsLinks(targetElement)) {
        storeUnsynchronizedFeatures(context, link, link, targetElement);
      }

      if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

        //For all links of the replica, add a unsynchronizable on parent
        for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context).getElementsLinks(sourceElement)) {
          storeUnsynchronizedFeatures(context, link.getOrigin(), link, targetElement);
        }

      }

    } else if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value) || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value)) {

      //We copy unsychronizedFeatures into RPL from its REC
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context).getElementsLinks(targetElement)) {
        storeUnsynchronizedFeatures(context, link, link.getOrigin(), targetElement);
      }

    }

    //Update user-modified features
    for (EObject custom : AttributesHandlerHelper.getInstance(context).getCustoms(context)) {
      if (custom instanceof CatalogElementLink) {
        EObject target = ((CatalogElementLink) custom).getTarget();
        if (target != null) {
          EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
          if (feature != null) {
            target.eSet(feature, AttributesHandlerHelper.getInstance(context).getCustomName(custom, context));
          }
        }
      }
      if (custom instanceof CatalogElement) {
        EObject target = custom;
        if (target != null) {
          EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
          if (feature != null) {
            target.eSet(target.eClass().getEStructuralFeature("name"), AttributesHandlerHelper.getInstance(context).getCustomName(custom, context));
          }
        }
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param object_p
   * @param b_p
   * @param targetElement_p
   */
  protected void updateElement(IContext context_p, EObject object_p, boolean b_p, CatalogElement targetElement_p) {
    if (object_p instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) object_p;
      EObject target = link.getTarget();
      if ((target != null) && (target instanceof CatalogElement)) {
        CatalogElement element = (CatalogElement) target;
        if (link.getSource().getKind() != CatalogElementKind.RPL) {
          if (element.getKind() == CatalogElementKind.RPL) {
            element.setKind(CatalogElementKind.REC_RPL);
          }
          //maybe we should set also the kind for all element.subrpls
        }
      }
    }
  }

  /**
   * @param context_p
   * @param object_p
   * @param b_p
   * @param target_p
   */
  protected void storeUnsynchronizedFeatures(IContext context_p, EObject link_p, EObject linkSuffixable_p, CatalogElement target_p) {
    if (link_p instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) link_p;

      CatalogElementLink suffixLink = (CatalogElementLink) linkSuffixable_p;

      if (linkSuffixable_p == null) {
        return;
      }
      EObject target = ((CatalogElementLink) linkSuffixable_p).getTarget();
      if (target != null) {

        if (link_p != linkSuffixable_p) {
          for (String feature : new ArrayList<String>(link.getUnsynchronizedFeatures())) {
            if (!suffixLink.getUnsynchronizedFeatures().contains(feature)) {
              link.getUnsynchronizedFeatures().remove(feature);
            }
          }
          for (String feature : suffixLink.getUnsynchronizedFeatures()) {
            if (!link.getUnsynchronizedFeatures().contains(feature)) {
              link.getUnsynchronizedFeatures().add(feature);
            }
          }
        }
        if (AttributesHandlerHelper.getInstance(context_p).isSuffixable(target, context_p)) {
          if (!(link.getUnsynchronizedFeatures().contains("name"))) {
            link.getUnsynchronizedFeatures().add("name");
          }
        } else {
          if (link.getUnsynchronizedFeatures().contains("name")) {
            link.getUnsynchronizedFeatures().remove("name");
          }
        }

      }

    }
  }

  /**
   * @param context_p
   * @param object_p
   * @param b_p
   * @param target_p
   */
  protected void renameElement(IContext context_p, EObject object_p, boolean isSource, CatalogElement target_p) {
    if (object_p instanceof CatalogElementLink) {
      EObject target = ((CatalogElementLink) object_p).getTarget();
      if (target == null) {
        return;
      }
      if (!context_p.exists(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX)) {
        return;
      }

      String suffix = (String) context_p.get(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX);
      if (((CatalogElementLink) object_p).getOrigin().getUnsynchronizedFeatures().contains("name")) {
        EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
        String name = (String) target.eGet(feature);
        if (!((name != null) && name.endsWith(suffix))) {
          target.eSet(feature, name + suffix);
        }
      }

    }
  }

  /**
   * Initialize the transformation traceability handler and set it into context via TRANSFORMATION_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTraceabilityAttachmentHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(IReConstants.TRACEABILITY_ATTACHMENT_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultTraceabilityTransformationHandler();
    }
    context_p.put(IReConstants.TRACEABILITY_ATTACHMENT_HANDLER, handler);
    context_p.put(ITransitionConstants.TRACEABILITY_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default transformation traceability handler for common transition
   * @return
   */
  protected IHandler createDefaultTraceabilityTransformationHandler() {
    return new CompoundTraceabilityHandler(new MatchConfiguration());
  }

  public void attachContainment(IContext context_p, EObject source_p, boolean isSource, CatalogElement element_p) {

    CatalogElementLink link = (CatalogElementLink) source_p;

    //Don't attach an invalid link
    if ((link == null) || (link.getTarget() == null)) {
      return;
    }
    //Don't move such element, element must not be copied, and so on, not moved from its original location
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.UNMERGEABLE_ELEMENTS, link.getTarget(), context_p)) {
      return;
    }
    //If the target is already attached somewhere, we don't wan't to move it!
    if ((link.getTarget() != null) && (link.getTarget().eContainer() != null)) {
      return;
    }

    Role destinationRole = isSource ? Role.REFERENCE : Role.TARGET;
    Role oppositeRole = !isSource ? Role.REFERENCE : Role.TARGET;
    ExtendedComparison comparison = (ExtendedComparison) context_p.get(ITransitionConstants.MERGE_COMPARISON);

    EObject targetElement = ((CatalogElementLink) source_p).getTarget();
    IMatch match = comparison.getMapping().getMatchFor(targetElement, destinationRole);

    EObject source = match.get(oppositeRole);
    EObject target = match.get(destinationRole);

    CatalogElementLink oppositeLink = AttributesHandlerHelper.getInstance(context_p).getOppositeLink(link, context_p);

    //For a REC to RPL, all location have been set as current after the first wizard. location should not be null for these modes.
    EObject location = AttributesHandlerHelper.getInstance(context_p).getCurrentLocation(link, context_p);
    if (location == null) {
      //For a RPL to REC, we can go in that case.
      //When an element have been added from the RPL to the REC. There is no wizard to set the location (sic)
      //but since we have a new element created, we need to store it somewhere...
      location = AttributesHandlerHelper.getInstance(context_p).getLocation(link, oppositeLink, context_p);
      if (location == null) {
        location = AttributesHandlerHelper.getInstance(context_p).getDefaultLocation(link, oppositeLink, context_p);
      }
    }

    if (location != null) {
      if (location instanceof CatalogElementLink) {
        IMatch match2 = comparison.getMapping().getMatchFor(((CatalogElementLink) location).getTarget(), oppositeRole);
        if (match2 == null) {
          match2 = comparison.getMapping().getMatchFor(((CatalogElementLink) location).getTarget(), destinationRole);
        }
        location = match2.get(destinationRole);
      }
    }

    if (location != null) {
      attachElement(context_p, target, location, getFeature(source, target, location, context_p));
    }

  }

  /**
   * @param source_p
   * @param target_p
   * @param currentLocation_p
   * @return
   */
  protected EStructuralFeature getFeature(EObject source_p, EObject target_p, EObject currentLocation_p, IContext context_p) {

    if (source_p == null) {
      return null;
    }
    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);
    AbstractRule arule = null;
    try {
      if (ruleHandler != null) {
        MappingPossibility mapping = ruleHandler.getApplicablePossibility(source_p);
        if (mapping != null) {
          IRule<?> rule = ruleHandler.getApplicablePossibility(source_p).getCompleteRule();
          if ((rule != null) && (rule instanceof AbstractRule)) {
            arule = (AbstractRule) rule;
          }
        }
      }
    } catch (MappingPossibilityResolutionException exception_p) {
      //Nothing to report
    }

    if (arule != null) {
      EStructuralFeature targetFeature = arule._getTargetContainementFeature(source_p, target_p, currentLocation_p, context_p);
      if (targetFeature != null) {
        if (AttachmentHelper.getInstance(context_p).isApplicable(currentLocation_p.eClass(), targetFeature)) {
          return targetFeature;
        }
      }
    }
    return source_p.eContainingFeature();
  }

  /**
   * @param source_p
   * @param currentLocation_p
   * @return
   */
  protected boolean attachElement(IContext context_p, EObject source_p, EObject container, EStructuralFeature feature) {

    if (container != null) {
      if (AttachmentHelper.getInstance(context_p).isApplicable(container.eClass(), feature)) {
        AttachmentHelper.getInstance(context_p).attachElementByReference(container, source_p, (EReference) feature);
        return true;
      }
    }

    return false;
  }

}

/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.location.LocationHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.activities.AbstractActivity;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AttachmentActivity extends AbstractActivity {

  public static final String ID = AttachmentActivity.class.getCanonicalName();

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus _run(ActivityParameters activityParams) {

    IContext context = getContext(activityParams);

    CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    Collection<EObject> sourceAddedElements = ContextScopeHandlerHelper.getInstance(context)
        .getCollection(IReConstants.SOURCE__ADDED_ELEMENTS, context);
    for (EObject object : sourceAddedElements) {
      if (object instanceof CatalogElementLink) {
        attachContainment(context, (CatalogElementLink) object, true, sourceElement);
      }
    }

    Collection<EObject> targetAddedElements = ContextScopeHandlerHelper.getInstance(context)
        .getCollection(IReConstants.TARGET__ADDED_ELEMENTS, context);
    for (EObject object : targetAddedElements) {
      if (object instanceof CatalogElementLink) {
        attachContainment(context, (CatalogElementLink) object, false, targetElement);
        updateElement(context, (CatalogElementLink) object, targetElement);
      }
    }

    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)
        || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value)
        || IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {

      // We set unsynchronize features from values set in the wizard by the user
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context)
          .getElementsLinks(targetElement)) {
        storeUnsynchronizedFeatures(context, link, link, targetElement);
      }

      if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

        // For all links of the replica, add a unsynchronizable on parent
        for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context)
            .getElementsLinks(sourceElement)) {
          storeUnsynchronizedFeatures(context, link.getOrigin(), link, targetElement);
        }

      }

    } else if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value)
        || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value)) {

      // We copy unsychronizedFeatures into RPL from its REC
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context)
          .getElementsLinks(targetElement)) {
        storeUnsynchronizedFeatures(context, link, link.getOrigin(), targetElement);
      }

    }

    // Update user-modified features
    for (EObject custom : AttributesHandlerHelper.getInstance(context).getCustomNameElements(context)) {
      if (custom instanceof CatalogElement) {
        EObject target = custom;
        EStructuralFeature feature = AttributesHandlerHelper.getInstance(context).getSuffixableFeature(target, context);
        if (feature != null) {
          target.eSet(feature, AttributesHandlerHelper.getInstance(context).getCustomName(custom, context));
        }
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param object
   * @param b
   * @param targetElement
   */
  protected void updateElement(IContext context, CatalogElementLink link, CatalogElement targetElement) {
    EObject target = link.getTarget();
    if ((target != null) && (target instanceof CatalogElement)) {
      CatalogElement element = (CatalogElement) target;
      if (link.getSource().getKind() != CatalogElementKind.RPL) {
        if (element.getKind() == CatalogElementKind.RPL) {
          element.setKind(CatalogElementKind.REC_RPL);
        }
        // maybe we should set also the kind for all element.subrpls
      }
    }
  }

  /**
   * @param context
   * @param link1
   * @param linkSuffixable
   * @param target1
   */
  protected void storeUnsynchronizedFeatures(IContext context, EObject link1, EObject linkSuffixable,
      CatalogElement target1) {
    if (link1 instanceof CatalogElementLink) {
      CatalogElementLink link = (CatalogElementLink) link1;

      CatalogElementLink suffixLink = (CatalogElementLink) linkSuffixable;

      if (linkSuffixable == null) {
        return;
      }
      EObject target = suffixLink.getTarget();
      if (target != null) {
        if (link1 != linkSuffixable) {
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

        // If the command is "Update the selected RPL from its REC", do not update the 'suffixed' feature of the link
        String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
        if (!IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value)) {
          if (AttributesHandlerHelper.getInstance(context).isSuffixable(target, context)) {
            if (!(link.isSuffixed())) {
              link.setSuffixed(true);
            }
          } else {
            if (link.isSuffixed()) {
              link.setSuffixed(false);
            }
          }
        }
      }
    }
  }

  public void attachContainment(IContext context, CatalogElementLink link, boolean isSource, CatalogElement element) {

    // Don't attach an invalid link
    if ((link == null) || (link.getTarget() == null)) {
      return;
    }
    // Don't move such element, element must not be copied, and so on, not moved from its original location
    if (ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.UNMERGEABLE_ELEMENTS, link.getTarget(),
        context)) {
      return;
    }
    // If the target is already attached somewhere, we don't wan't to move it!
    if ((link.getTarget() != null) && (link.getTarget().eContainer() != null)) {
      return;
    }

    Role destinationRole = isSource ? Role.REFERENCE : Role.TARGET;
    Role oppositeRole = !isSource ? Role.REFERENCE : Role.TARGET;
    ExtendedComparison comparison = (ExtendedComparison) context.get(ITransitionConstants.MERGE_COMPARISON);

    EObject targetElement = link.getTarget();
    IMatch match = comparison.getMapping().getMatchFor(targetElement, destinationRole);

    if (match == null) {
      return;
    }
    EObject source = match.get(oppositeRole);
    EObject target = match.get(destinationRole);

    CatalogElementLink oppositeLink = ReplicableElementHandlerHelper.getInstance(context).getOppositeLink(link,
        context);

    // For a REC to RPL, all location have been set as current after the first wizard. location should not be null for
    // these modes.
    EObject location = LocationHandlerHelper.getInstance(context).getCurrentLocation(link, context);
    if (location == null) {
      // For a RPL to REC, we can go in that case.
      // When an element have been added from the RPL to the REC. There is no wizard to set the location (sic)
      // but since we have a new element created, we need to store it somewhere...
      location = LocationHandlerHelper.getInstance(context).getLocation(link, oppositeLink, context);
      if (location == null) {
        location = LocationHandlerHelper.getInstance(context).getDefaultLocation(link, oppositeLink, context);
      }
    }

    if (location instanceof CatalogElementLink) {
      IMatch match2 = comparison.getMapping().getMatchFor(((CatalogElementLink) location).getTarget(), oppositeRole);
      if (match2 == null) {
        match2 = comparison.getMapping().getMatchFor(((CatalogElementLink) location).getTarget(), destinationRole);
      }
      if (match2 != null) {
        location = match2.get(destinationRole);
      } else {
        // No match, location is not in the scope of the diffmerge.
        // This can occurs when instanciating sub replicas: a sub replica's element can be located inside
        // a super replica's element which is not present in the scope at the time where instanciating the sub replica.
        // It will be stored when re-updating the super-replica.
      }
    }

    if (location != null) {
      EStructuralFeature feature = LocationHandlerHelper.getInstance(context).getFeature(source, target, location,
          context);
      attachElement(context, target, location, feature);

    } else {
      LogHelper.getInstance().debug(
          NLS.bind("Element ''{0}'' has not been attached.", LogHelper.getInstance().getText(target)),
          Messages.Activity_Transformation);

    }

  }

  /**
   * @param context
   * @param source
   * @param container
   * @param feature
   * @return
   */
  protected boolean attachElement(IContext context, EObject source, EObject container, EStructuralFeature feature) {

    if (container != null && feature != null && AttachmentHelper.getInstance(context).isApplicable(container.eClass(), feature)) {
      AttachmentHelper.getInstance(context).attachElementByReference(container, source, (EReference) feature);
      return true;
    }

    return false;
  }
}

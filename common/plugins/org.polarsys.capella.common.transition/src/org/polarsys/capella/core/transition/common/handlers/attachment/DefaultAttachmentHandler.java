/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.handlers.attachment;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class DefaultAttachmentHandler implements IAttachmentHandler {

  public boolean attachElementByReference(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached, EObject targetAttached,
      EReference sourceFeature, EReference targetFeature) {

    if (isApplicable(targetAttaching.eClass(), targetFeature)) {
      if (targetFeature.isChangeable() && !targetFeature.isDerived()) {

        if (!targetFeature.isMany()) {
          targetAttaching.eSet(targetFeature, targetAttached);

        } else {
          boolean isAttached = false;
          if (isHandlingOrdering(sourceAttaching, targetAttaching, sourceAttached, targetAttached, sourceFeature, targetFeature)) {
            if ((sourceAttaching != null) && (sourceAttached != null)) {
              if (isApplicable(sourceAttaching.eClass(), sourceFeature)) {
                int index = ((EList) sourceAttaching.eGet(sourceFeature)).indexOf(sourceAttached);
                if (index != -1) {
                  EList list = ((EList) targetAttaching.eGet(targetFeature));
                  if (!(targetFeature.isUnique() && list.contains(targetAttached))) {
                    list.add(Math.min(index, list.size()), targetAttached);
                  }
                  isAttached = true;
                }
              }
            }
          }
          if (!isAttached) {
            ((EList) targetAttaching.eGet(targetFeature)).add(targetAttached);
          }
        }

        LogHelper.getInstance()
            .debug(
                NLS.bind(
                    "Element ''{0}'' attached to ''{1}'' [{2}].",
                    new Object[] { LogHelper.getInstance().getText(targetAttached), LogHelper.getInstance().getText(targetAttaching),
                                  targetFeature.getName() }), Messages.Activity_Transformation);

        return true;
      }
      LogHelper.getInstance().debug(
          NLS.bind("Feature ''{0}'' of ''{1}'' is not changeable or derived.", targetFeature.getName(), ((EClass) (targetFeature.eContainer())).getName()),
          Messages.Activity_Transformation);
    } else {
      LogHelper.getInstance().warn(
          NLS.bind("Feature ''{0}'' of ''{1}'' is not applicable on  ''{1}''.",
              new Object[] { targetFeature.getName(), ((EClass) (targetFeature.eContainer())).getName(), targetAttaching.eClass().getName() }),
          Messages.Activity_Transformation);
    }

    return false;
  }

  /**
   * @param ordered_p
   * @return
   */
  protected boolean isHandlingOrdering(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached, EObject targetAttached,
      EReference sourceFeature, EReference targetFeature) {
    return (sourceFeature != null) && sourceFeature.isMany() && sourceFeature.isOrdered() && (targetFeature != null) && targetFeature.isMany()
           && targetFeature.isOrdered();
  }

  /**
   * Attach the given element into the given container_p according to the given feature_p
   */
  public boolean attachElementByReference(EObject element, EObject relatedElement, EReference relationship) {
    return attachElementByReference(null, element, null, relatedElement, null, relationship);
  }

  @Deprecated
  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  public boolean attachElementByRel(EObject element, EObject relatedElement, EReference relationship) {
    return attachElementByReference(null, element, null, relatedElement, null, relationship);
  }

  /**
   * Returns whether the feature is available in the clazz
   */
  public boolean isApplicable(EClass clazz, EStructuralFeature feature) {
    return EcoreUtil2.isEqualOrSuperClass(feature.getEContainingClass(), clazz);
  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement The source element
   * @param property_p The name of the property
   * @param transfo_p The transformation
   */
  @SuppressWarnings("unchecked")
  public void updateElementAttribute(EObject sourceElement, EObject targetElement, EAttribute feature, IContext context) {
    EAttribute attribute = feature;
    if (isApplicable(sourceElement.eClass(), attribute)) {
      Object valueSource = sourceElement.eGet(attribute);

      if (isApplicable(targetElement.eClass(), attribute)) {
        Object valueTarget = targetElement.eGet(attribute);

        if (attribute.isChangeable() && !attribute.isDerived()) {
          if (shouldUpdateAttribute(sourceElement, targetElement, feature, valueSource, valueTarget, context)) {
            if (valueTarget != null) {
              LogHelper.getInstance().debug(
                  NLS.bind("Update Attribute ''{0}'' of ''{1}''.",
                      new Object[] { attribute.getName(), LogHelper.getInstance().getText(targetElement), LogHelper.getInstance().getText(valueTarget),
                                    LogHelper.getInstance().getText(valueSource) }), targetElement, Messages.Activity_Transformation);
            }

            if (!attribute.isMany()) {
              targetElement.eSet(attribute, valueSource);
            } else if ((valueSource instanceof EList) && (valueTarget instanceof EList)) {
              EList<Object> sourceList = (EList<Object>) valueSource;
              EList<Object> targetList = (EList<Object>) valueTarget;
              for (Object value : sourceList) {
                targetList.add(value);
              }
            }
          }
        } else {
          LogHelper.getInstance().debug(
              NLS.bind("Attribute ''{0}'' of ''{1}'' is not changeable or derived.",
                  new Object[] { attribute.getName(), LogHelper.getInstance().getText(targetElement) }), targetElement, Messages.Activity_Transformation);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  protected Collection<EObject> retrieveReferenceAsList(EObject object, EReference reference) {
    if (isApplicable(object.eClass(), reference)) {
      Object sourceReference = object.eGet(reference);
      if (sourceReference instanceof Collection<?>) {
        return (Collection<EObject>) sourceReference;
      }
      return Collections.singleton((EObject) sourceReference);
    }
    return Collections.emptyList();
  }

  public void attachTracedElements(EObject source, EObject target, EReference feature, IContext context) {
    for (EObject traced : retrieveReferenceAsList(source, feature)) {
      for (EObject related : TraceabilityHandlerHelper.getInstance(context).retrieveTracedElements(traced, context)) {
        attachElementByReference(source, target, traced, related, feature, feature);
      }
    }
  }
  
  public void invertedAttachTracedElements(EObject source, EObject target, EReference feature, EReference targetFeature, IContext context) {
    for (EObject traced : retrieveReferenceAsList(source, feature)) {
      for (EObject related : TraceabilityHandlerHelper.getInstance(context).retrieveTracedElements(traced, context)) {
        attachElementByReference(traced, related, source, target, targetFeature, targetFeature);
      }
    }
  }

  protected boolean shouldUpdateAttribute(EObject sourceElement, EObject targetElement, EAttribute feature, Object valueSource, Object valueTarget,
      IContext context) {
    return (((valueSource == null) && (valueTarget != null)) || ((valueSource != null) && !valueSource.equals(valueTarget)));
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeElements(Collection<EObject> objects, IContext context) {
    //Nothing yet
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createdElement(EObject element, EObject result, IContext context) {

  }

}

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

  public boolean attachElementByReference(EObject sourceAttaching_p, EObject targetAttaching_p, EObject sourceAttached_p, EObject targetAttached_p,
      EReference sourceFeature_p, EReference targetFeature_p) {

    if (isApplicable(targetAttaching_p.eClass(), targetFeature_p)) {
      if (targetFeature_p.isChangeable() && !targetFeature_p.isDerived()) {

        if (!targetFeature_p.isMany()) {
          targetAttaching_p.eSet(targetFeature_p, targetAttached_p);

        } else {
          boolean isAttached = false;
          if (isHandlingOrdering(sourceAttaching_p, targetAttaching_p, sourceAttached_p, targetAttached_p, sourceFeature_p, targetFeature_p)) {
            if ((sourceAttaching_p != null) && (sourceAttached_p != null)) {
              if (isApplicable(sourceAttaching_p.eClass(), sourceFeature_p)) {
                int index = ((EList) sourceAttaching_p.eGet(sourceFeature_p)).indexOf(sourceAttached_p);
                if (index != -1) {
                  EList list = ((EList) targetAttaching_p.eGet(targetFeature_p));
                  if (!(targetFeature_p.isUnique() && list.contains(targetAttached_p))) {
                    list.add(Math.min(index, list.size()), targetAttached_p);
                  }
                  isAttached = true;
                }
              }
            }
          }
          if (!isAttached) {
            ((EList) targetAttaching_p.eGet(targetFeature_p)).add(targetAttached_p);
          }
        }

        LogHelper.getInstance()
            .debug(
                NLS.bind(
                    "Element ''{0}'' attached to ''{1}'' [{2}].",
                    new Object[] { LogHelper.getInstance().getText(targetAttached_p), LogHelper.getInstance().getText(targetAttaching_p),
                                  targetFeature_p.getName() }), Messages.Activity_Transformation);

        return true;
      }
      LogHelper.getInstance().debug(
          NLS.bind("Feature ''{0}'' of ''{1}'' is not changeable or derived.", targetFeature_p.getName(), ((EClass) (targetFeature_p.eContainer())).getName()),
          Messages.Activity_Transformation);
    } else {
      LogHelper.getInstance().warn(
          NLS.bind("Feature ''{0}'' of ''{1}'' is not applicable on  ''{1}''.",
              new Object[] { targetFeature_p.getName(), ((EClass) (targetFeature_p.eContainer())).getName(), targetAttaching_p.eClass().getName() }),
          Messages.Activity_Transformation);
    }

    return false;
  }

  /**
   * @param ordered_p
   * @return
   */
  protected boolean isHandlingOrdering(EObject sourceAttaching_p, EObject targetAttaching_p, EObject sourceAttached_p, EObject targetAttached_p,
      EReference sourceFeature_p, EReference targetFeature_p) {
    return (sourceFeature_p != null) && sourceFeature_p.isMany() && sourceFeature_p.isOrdered() && (targetFeature_p != null) && targetFeature_p.isMany()
           && targetFeature_p.isOrdered();
  }

  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  public boolean attachElementByReference(EObject element_p, EObject relatedElement_p, EReference relationship_p) {
    return attachElementByReference(null, element_p, null, relatedElement_p, null, relationship_p);
  }

  @Deprecated
  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  public boolean attachElementByRel(EObject element_p, EObject relatedElement_p, EReference relationship_p) {
    return attachElementByReference(null, element_p, null, relatedElement_p, null, relationship_p);
  }

  /**
   * Returns whether the feature is available in the clazz
   */
  public boolean isApplicable(EClass clazz_p, EStructuralFeature feature_p) {
    return EcoreUtil2.isEqualOrSuperClass(feature_p.getEContainingClass(), clazz_p);
  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement_p The source element
   * @param property_p The name of the property
   * @param transfo_p The transformation
   */
  @SuppressWarnings("unchecked")
  public void updateElementAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, IContext context_p) {
    EAttribute attribute = feature_p;
    if (isApplicable(sourceElement_p.eClass(), attribute)) {
      Object valueSource = sourceElement_p.eGet(attribute);

      if (isApplicable(targetElement_p.eClass(), attribute)) {
        Object valueTarget = targetElement_p.eGet(attribute);

        if (attribute.isChangeable() && !attribute.isDerived()) {
          if (shouldUpdateAttribute(sourceElement_p, targetElement_p, feature_p, valueSource, valueTarget, context_p)) {
            if (valueTarget != null) {
              LogHelper.getInstance().debug(
                  NLS.bind("Update Attribute ''{0}'' of ''{1}''.",
                      new Object[] { attribute.getName(), LogHelper.getInstance().getText(targetElement_p), LogHelper.getInstance().getText(valueTarget),
                                    LogHelper.getInstance().getText(valueSource) }), targetElement_p, Messages.Activity_Transformation);
            }

            if (!attribute.isMany()) {
              targetElement_p.eSet(attribute, valueSource);
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
                  new Object[] { attribute.getName(), LogHelper.getInstance().getText(targetElement_p) }), targetElement_p, Messages.Activity_Transformation);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  protected Collection<EObject> retrieveReferenceAsList(EObject object_p, EReference reference_p) {
    if (isApplicable(object_p.eClass(), reference_p)) {
      Object sourceReference = object_p.eGet(reference_p);
      if (sourceReference instanceof Collection<?>) {
        return (Collection<EObject>) sourceReference;
      }
      return Collections.singleton((EObject) sourceReference);
    }
    return Collections.emptyList();
  }

  public void attachTracedElements(EObject source_p, EObject target_p, EReference feature_p, IContext context_p) {
    for (EObject traced : retrieveReferenceAsList(source_p, feature_p)) {
      for (EObject related : TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(traced, context_p)) {
        attachElementByReference(source_p, target_p, traced, related, feature_p, feature_p);
      }
    }
  }

  protected boolean shouldUpdateAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, Object valueSource, Object valueTarget,
      IContext context_p) {
    return (((valueSource == null) && (valueTarget != null)) || ((valueSource != null) && !valueSource.equals(valueTarget)));
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeElements(Collection<EObject> objects_p, IContext context_p) {
    //Nothing yet
  }

}

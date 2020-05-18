/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers.attachment;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.tiger.Messages;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class DefaultAttachmentHandler implements IAttachmentHandler {

  /**
   * Returns whether the feature is available in the clazz
   */
  public boolean isApplicable(EClass clazz, EStructuralFeature feature) {
    return EcoreUtil2.isEqualOrSuperClass(feature.getEContainingClass(), clazz);
  }

  @SuppressWarnings("unchecked")
  public boolean attachElementByRel(EObject element, EObject relatedElement, EReference relationship) {
    if (!isApplicable(element.eClass(), relationship)) {
      LogHelper.getInstance().warn(
          NLS.bind(Messages.TigerRelationshipHelper_FeatureNonApplicable,
              new Object[] { relationship.getName(), ((EClass) (relationship.eContainer())).getName(), element.eClass().getName() }),
          ProjectionMessages.Activity_Transformation);

    } else if (!(relationship.isChangeable() && !relationship.isDerived())) {
      LogHelper.getInstance()
          .warn(
              NLS.bind(Messages.TigerRelationshipHelper_FeatureDerivedOrNonChangeable, relationship.getName(),
                  ((EClass) (relationship.eContainer())).getName()), ProjectionMessages.Activity_Transformation);

    } else {

      boolean done = false;
      boolean alreadyExist = false;

      try {

        if (relationship.isContainment()) {
          HoldingResourceHelper.ensureMoveElement(relatedElement, element);
        }

        if (relationship.isMany()) {
          EList<EObject> tmp = ((EList<EObject>) element.eGet(relationship));
          if (tmp.contains(relatedElement)) {
            alreadyExist = true;
          } else {
            tmp.add(relatedElement);
            done = true;
          }

        } else {
          if (relatedElement.equals(element.eGet(relationship))) {
            alreadyExist = true;
          } else {
            element.eSet(relationship, relatedElement);
            done = true;
          }
        }
      } catch (ArrayStoreException | IllegalArgumentException exception) {
        done = false;
      } 

      if (done) {

        if (relationship.isContainment()) {

          LogHelper.getInstance().info(
              NLS.bind(Messages.TigerRelationshipHelper_ContainedBy, new Object[] { EObjectLabelProviderHelper.getText(relatedElement),
                                                                                   EObjectLabelProviderHelper.getText(element), relationship.getName() }),
              new Object[] { relatedElement, element }, ICommonConstants.EMPTY_STRING);
        } else {
          LogHelper.getInstance().info(
              NLS.bind(Messages.TigerRelationshipHelper_ReferencedBy, new Object[] { EObjectLabelProviderHelper.getText(relatedElement),
                                                                                    EObjectLabelProviderHelper.getText(element), relationship.getName() }),
              new Object[] { relatedElement, element }, ICommonConstants.EMPTY_STRING);

        }
      } else if (!alreadyExist) {
        if (relationship.isContainment()) {
          LogHelper.getInstance()
              .warn(
                  NLS.bind(
                      Messages.TigerRelationshipHelper_ShouldBeContainedBy,
                      new Object[] { EObjectLabelProviderHelper.getText(relatedElement), EObjectLabelProviderHelper.getText(element),
                                    relationship.getName() }), new Object[] { relatedElement, element }, ICommonConstants.EMPTY_STRING);
        } else {
          LogHelper.getInstance()
              .warn(
                  NLS.bind(
                      Messages.TigerRelationshipHelper_ShouldBeReferencedBy,
                      new Object[] { EObjectLabelProviderHelper.getText(relatedElement), EObjectLabelProviderHelper.getText(element),
                                    relationship.getName() }), new Object[] { relatedElement, element }, ICommonConstants.EMPTY_STRING);

        }
      }
    }

    return false;
  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement The source element
   * @param property The name of the property
   * @param transfo The transformation
   */
  public void updateElementAttribute(EObject sourceElement, EObject targetElement, EAttribute feature, IContext context) {
    EAttribute attribute = feature;
    if (isApplicable(sourceElement.eClass(), attribute)) {
      Object valueSource = sourceElement.eGet(attribute);
      if (isApplicable(targetElement.eClass(), attribute)) {
        Object valueTarget = targetElement.eGet(attribute);
        if (shouldUpdateAttribute(sourceElement, targetElement, feature, valueSource, valueTarget, context)) {
          if (valueTarget != null) {
            LogHelper.getInstance().debug(
                NLS.bind(Messages.TigerRelationshipHelper_UpdateAttribute, new Object[] { attribute.getName(),
                                                                                         LogHelper.getInstance().getText(targetElement),
                                                                                         LogHelper.getInstance().getText(valueTarget),
                                                                                         LogHelper.getInstance().getText(valueSource) }), targetElement,
                ProjectionMessages.Activity_Transformation);
          }
          targetElement.eSet(attribute, valueSource);
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
        attachElementByRel(target, related, feature);
      }
    }
  }

  public void attachToBestElement(EObject element, EObject result, EReference reference, IContext context) {
    TigerRelationshipHelper.attachToBestElement(element, reference, context.getTransfo());
  }

  protected boolean shouldUpdateAttribute(EObject sourceElement, EObject targetElement, EAttribute feature, Object valueSource, Object valueTarget,
      IContext context) {
    return (((valueSource == null) && (valueTarget != null)) || ((valueSource != null) && !valueSource.equals(valueTarget)));
  }

  /**
   * {@inheritDoc}
   */
  public void init(IContext context) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public void dispose(IContext context) {
    //Nothing here
  }
}

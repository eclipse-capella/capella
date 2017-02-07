/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  public boolean isApplicable(EClass clazz_p, EStructuralFeature feature_p) {
    return EcoreUtil2.isEqualOrSuperClass(feature_p.getEContainingClass(), clazz_p);
  }

  @SuppressWarnings("unchecked")
  public boolean attachElementByRel(EObject element_p, EObject relatedElement_p, EReference relationship_p) {
    if (!isApplicable(element_p.eClass(), relationship_p)) {
      LogHelper.getInstance().warn(
          NLS.bind(Messages.TigerRelationshipHelper_FeatureNonApplicable,
              new Object[] { relationship_p.getName(), ((EClass) (relationship_p.eContainer())).getName(), element_p.eClass().getName() }),
          ProjectionMessages.Activity_Transformation);

    } else if (!(relationship_p.isChangeable() && !relationship_p.isDerived())) {
      LogHelper.getInstance()
          .warn(
              NLS.bind(Messages.TigerRelationshipHelper_FeatureDerivedOrNonChangeable, relationship_p.getName(),
                  ((EClass) (relationship_p.eContainer())).getName()), ProjectionMessages.Activity_Transformation);

    } else {

      boolean done = false;
      boolean alreadyExist = false;

      try {

        if (relationship_p.isContainment()) {
          HoldingResourceHelper.ensureMoveElement(relatedElement_p, element_p);
        }

        if (relationship_p.isMany()) {
          EList<EObject> tmp = ((EList<EObject>) element_p.eGet(relationship_p));
          if (tmp.contains(relatedElement_p)) {
            alreadyExist = true;
          } else {
            tmp.add(relatedElement_p);
            done = true;
          }

        } else {
          if (relatedElement_p.equals(element_p.eGet(relationship_p))) {
            alreadyExist = true;
          } else {
            element_p.eSet(relationship_p, relatedElement_p);
            done = true;
          }
        }
      } catch (ArrayStoreException exception) {
        done = false;
      } catch (IllegalArgumentException exception) {
        done = false;
      }

      if (done) {

        if (relationship_p.isContainment()) {

          LogHelper.getInstance().info(
              NLS.bind(Messages.TigerRelationshipHelper_ContainedBy, new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p),
                                                                                   EObjectLabelProviderHelper.getText(element_p), relationship_p.getName() }),
              new Object[] { relatedElement_p, element_p }, ICommonConstants.EMPTY_STRING);
        } else {
          LogHelper.getInstance().info(
              NLS.bind(Messages.TigerRelationshipHelper_ReferencedBy, new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p),
                                                                                    EObjectLabelProviderHelper.getText(element_p), relationship_p.getName() }),
              new Object[] { relatedElement_p, element_p }, ICommonConstants.EMPTY_STRING);

        }
      } else if (!done && !alreadyExist) {
        if (relationship_p.isContainment()) {
          LogHelper.getInstance()
              .warn(
                  NLS.bind(
                      Messages.TigerRelationshipHelper_ShouldBeContainedBy,
                      new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p), EObjectLabelProviderHelper.getText(element_p),
                                    relationship_p.getName() }), new Object[] { relatedElement_p, element_p }, ICommonConstants.EMPTY_STRING);
        } else {
          LogHelper.getInstance()
              .warn(
                  NLS.bind(
                      Messages.TigerRelationshipHelper_ShouldBeReferencedBy,
                      new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p), EObjectLabelProviderHelper.getText(element_p),
                                    relationship_p.getName() }), new Object[] { relatedElement_p, element_p }, ICommonConstants.EMPTY_STRING);

        }
      }
    }

    return false;
  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement_p The source element
   * @param property_p The name of the property
   * @param transfo_p The transformation
   */
  public void updateElementAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, IContext context_p) {
    EAttribute attribute = feature_p;
    if (isApplicable(sourceElement_p.eClass(), attribute)) {
      Object valueSource = sourceElement_p.eGet(attribute);
      if (isApplicable(targetElement_p.eClass(), attribute)) {
        Object valueTarget = targetElement_p.eGet(attribute);
        if (shouldUpdateAttribute(sourceElement_p, targetElement_p, feature_p, valueSource, valueTarget, context_p)) {
          if (valueTarget != null) {
            LogHelper.getInstance().debug(
                NLS.bind(Messages.TigerRelationshipHelper_UpdateAttribute, new Object[] { attribute.getName(),
                                                                                         LogHelper.getInstance().getText(targetElement_p),
                                                                                         LogHelper.getInstance().getText(valueTarget),
                                                                                         LogHelper.getInstance().getText(valueSource) }), targetElement_p,
                ProjectionMessages.Activity_Transformation);
          }
          targetElement_p.eSet(attribute, valueSource);
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
    return Collections.EMPTY_LIST;
  }

  public void attachTracedElements(EObject source_p, EObject target_p, EReference feature_p, IContext context_p) {
    for (EObject traced : retrieveReferenceAsList(source_p, feature_p)) {
      for (EObject related : TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(traced, context_p)) {
        attachElementByRel(target_p, related, feature_p);
      }
    }
  }

  public void attachToBestElement(EObject element_p, EObject result_p, EReference reference_p, IContext context_p) {
    TigerRelationshipHelper.attachToBestElement(element_p, reference_p, context_p.getTransfo());
  }

  protected boolean shouldUpdateAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, Object valueSource, Object valueTarget,
      IContext context_p) {
    return (((valueSource == null) && (valueTarget != null)) || ((valueSource != null) && !valueSource.equals(valueTarget)));
  }

  /**
   * {@inheritDoc}
   */
  public void init(IContext context_p) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public void dispose(IContext context_p) {
    //Nothing here
  }

}

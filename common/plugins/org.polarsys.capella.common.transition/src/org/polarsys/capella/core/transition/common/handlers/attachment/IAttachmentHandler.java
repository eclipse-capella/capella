/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IAttachmentHandler extends IHandler {

  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  boolean attachElementByReference(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached,
      EObject targetAttached, EReference sourceFeature, EReference targetFeature);

  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  boolean attachElementByReference(EObject targetAttaching, EObject targetAttached, EReference targetFeature);

  /**
   * Attach the given element into the given container according to the given feature
   */
  @Deprecated
  boolean attachElementByRel(EObject container, EObject element, EReference feature);

  /**
   * Return whether the given feature exists in the given eClass
   */
  boolean isApplicable(EClass eClass, EStructuralFeature feature);

  /**
   * Return whether the given feature exists in the given eClass_p
   */
  void updateElementAttribute(EObject sourceElement, EObject targetElement, EAttribute feature, IContext context);

  /**
   * Attach the traced elements of sourceElement.eGet(reference) into targetElement.eGet(reference)
   * According to policy/feature, it can attach directly the referencedElement, its traced element or any other related element
   */
  void attachTracedElements(EObject sourceElement, EObject targetElement, EReference reference, IContext context);

  /**
   * Attach the traced elements of sourceElement.eGet(reference) into traced elements of targetElement.eGet(reference) by the eGet(targetReference).
   * According to policy/feature, it can attach directly the referencedElement, its traced element or any other related element
   */
  void invertedAttachTracedElements(EObject sourceElement, EObject targetElement, EReference reference,
      EReference targetReference, IContext context);

  void removeElements(Collection<EObject> objects, IContext context);

  /**
   * @param source
   * @param createdElement
   * @param context
   */
  void createdElement(EObject source, EObject createdElement, IContext context);
}

/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  boolean attachElementByReference(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached, EObject targetAttached,
      EReference sourceFeature, EReference targetFeature);

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
   * Attach the referencedElement_p into traced elements of the sourceElement.eGet(reference)
   * according to policy/feature, it can attach directly the referencedElement_p, its traced element or any other related element
   */
  //Should be moved...
  void attachTracedElements(EObject sourceElement, EObject targetElement, EReference reference, IContext context);

  void removeElements(Collection<EObject> objects, IContext context);

  /**
   * @param source
   * @param createdElement
   * @param context
   */
  void createdElement(EObject source, EObject createdElement, IContext context);
}

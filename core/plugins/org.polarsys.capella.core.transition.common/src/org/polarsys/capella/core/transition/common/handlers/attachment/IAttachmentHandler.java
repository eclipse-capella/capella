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
  boolean attachElementByReference(EObject sourceAttaching_p, EObject targetAttaching_p, EObject sourceAttached_p, EObject targetAttached_p,
      EReference sourceFeature_p, EReference targetFeature_p);

  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  boolean attachElementByReference(EObject targetAttaching_p, EObject targetAttached_p, EReference targetFeature_p);

  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
  @Deprecated
  boolean attachElementByRel(EObject container_p, EObject element_p, EReference feature_p);

  /**
   * Return whether the given feature_p exists in the given eClass_p
   */
  boolean isApplicable(EClass eClass_p, EStructuralFeature feature_p);

  /**
   * Return whether the given feature_p exists in the given eClass_p
   */
  void updateElementAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, IContext context_p);

  /**
   * Attach the referencedElement_p into traced elements of the sourceElement_p.eGet(reference_p)
   * according to policy/feature, it can attach directly the referencedElement_p, its traced element or any other related element
   */
  //Should be moved...
  void attachTracedElements(EObject sourceElement_p, EObject targetElement_p, EReference reference_p, IContext context_p);

  void removeElements(Collection<EObject> objects_p, IContext context_p);
}

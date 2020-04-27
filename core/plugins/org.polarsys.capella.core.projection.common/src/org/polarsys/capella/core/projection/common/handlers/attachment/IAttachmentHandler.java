/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.IHandler;

/**
 */
public interface IAttachmentHandler extends IHandler {

  /**
   * Attach the given element_p into the given container_p according to the given feature_p
   */
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
   * Attach the referencedElement_p into traced elements of the sourceElement_p.eGet(reference_p) according to policy/feature, it can attach directly the
   * referencedElement_p, its traced element or any other related element
   */
  //Should be moved...
  void attachTracedElements(EObject sourceElement_p, EObject targetElement_p, EReference reference_p, IContext context_p);

  void attachToBestElement(EObject element_p, EObject targetElement_p, EReference reference_p, IContext context_p);

}

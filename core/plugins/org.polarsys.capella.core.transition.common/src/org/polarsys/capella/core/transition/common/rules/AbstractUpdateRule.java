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
package org.polarsys.capella.core.transition.common.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public abstract class AbstractUpdateRule extends AbstractRule implements IRuleUpdateAttribute {

  private List<EAttribute> updatedAttributes = new ArrayList<EAttribute>();

  private List<EReference> updatedReferences = new ArrayList<EReference>();

  public boolean isUpdatedAttribute(EAttribute attribute_p) {
    return updatedAttributes.contains(attribute_p);
  }

  public List<EAttribute> getUpdatedAttributes() {
    return updatedAttributes;
  }

  protected void registerUpdatedAttribute(EAttribute attribute_p) {
    updatedAttributes.add(attribute_p);
  }

  protected void unregisterUpdatedAttribute(EAttribute attribute_p) {
    updatedAttributes.remove(attribute_p);
  }

  public boolean isUpdatedReference(EReference reference_p) {
    return updatedReferences.contains(reference_p);
  }

  public List<EReference> getUpdatedReferences() {
    return updatedReferences;
  }

  protected void registerUpdatedReference(EReference reference_p) {
    updatedReferences.add(reference_p);
  }

  protected void unregisterUpdatedAttribute(EReference reference_p) {
    updatedReferences.remove(reference_p);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   */
  @Override
  protected void updateElement(EObject element_p, EObject result_p, IContext context_p) {
    super.updateElement(element_p, result_p, context_p);

    //Update registered updatedAttributes
    for (EObject target : retrieveTracedElements(element_p, context_p)) {
      for (EAttribute attribute : getUpdatedAttributes()) {
        AttachmentHelper.getInstance(context_p).updateElementAttribute(element_p, target, attribute, context_p);
      }
    }
  }

}

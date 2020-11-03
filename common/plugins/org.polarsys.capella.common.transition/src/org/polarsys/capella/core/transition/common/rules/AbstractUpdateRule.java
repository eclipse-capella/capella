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
public abstract class AbstractUpdateRule extends AbstractRule implements IRuleUpdateAttribute, IRuleUpdateReference {

  private List<EAttribute> updatedAttributes = new ArrayList<EAttribute>();

  private List<EReference> updatedReferences = new ArrayList<EReference>();

  public boolean isUpdatedAttribute(EAttribute attribute) {
    return updatedAttributes.contains(attribute);
  }

  public List<EAttribute> getUpdatedAttributes() {
    return updatedAttributes;
  }

  protected void registerUpdatedAttribute(EAttribute attribute) {
    updatedAttributes.add(attribute);
  }

  protected void unregisterUpdatedAttribute(EAttribute attribute) {
    updatedAttributes.remove(attribute);
  }

  public boolean isUpdatedReference(EReference reference) {
    return updatedReferences.contains(reference);
  }

  public List<EReference> getUpdatedReferences() {
    return updatedReferences;
  }

  protected void registerUpdatedReference(EReference reference) {
    updatedReferences.add(reference);
  }

  protected void unregisterUpdatedAttribute(EReference reference) {
    updatedReferences.remove(reference);
  }

  /**
   * @param element
   * @param result
   * @param context
   */
  @Override
  protected void updateElement(EObject element, EObject result, IContext context) {
    super.updateElement(element, result, context);

    //Update registered updatedAttributes
    for (EObject target : retrieveTracedElements(element, context)) {
      for (EAttribute attribute : getUpdatedAttributes()) {
        AttachmentHelper.getInstance(context).updateElementAttribute(element, target, attribute, context);
      }
    }
  }

}

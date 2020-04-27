/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.handlers.attributes;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IAttributeHandler extends IHandler {

  /**
   * Returns whether the user has customized the name of the given element
   */
  public boolean hasCustomName(EObject object, IContext context);

  /**
   * Returns list of name-customized elements
   */
  public Collection<EObject> getCustomNameElements(IContext context);

  /**
   * Return the custom name of the given object
   */
  public String getCustomName(EObject object, IContext context);

  /**
   * Set the custom name of the given object
   */
  public void setCustomName(EObject object, String value, IContext context);

  /**
   * Retrieve the current name of the given object. If customized, it returns the custom name, otherwize, returns the computed value of the name from parameters
   * put in the pContext. This method is used while Create Replica wizard.
   */
  public String getCurrentName(EObject object, IContext iContext, IPropertyContext pContext);

  /**
   * Remove the current name
   */
  public void unsetCustomName(EObject object, String value, IContext iContext, IPropertyContext pContext);

  /**
   * Returns whether ths user has modified the suffix value for the given object
   */
  public boolean isManualSuffixable(Object object, IContext context);

  /**
   * User-made choice about suffix for the given element
   */
  public void setManualSuffixable(EObject object, boolean value, IContext context);

  /**
   * Returns whether the object is suffixable
   */
  public boolean isSuffixable(Object object, IContext context);

  /**
   * Set the suffix value for the given object
   */
  public void setSuffixable(EObject object, boolean value, IContext context);

  /**
   * Returns the feature that will be suffixed
   */
  public EStructuralFeature getSuffixableFeature(EObject object, IContext context);

  /**
   * Returns whether an element can be suffixed
   */
  public boolean isSuffixableElement(Object object, IContext context);

}

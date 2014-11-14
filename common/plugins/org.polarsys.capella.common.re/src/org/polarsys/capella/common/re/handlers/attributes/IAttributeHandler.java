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
package org.polarsys.capella.common.re.handlers.attributes;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
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
  public boolean hasCustomName(EObject object_p, IContext context_p);

  /**
   * Returns list of name-customized elements
   */
  public Collection<EObject> getCustomNameElements(IContext context_p);

  /**
   * Return the custom name of the given object
   */
  public String getCustomName(EObject object_p, IContext context_p);

  /**
   * Set the custom name of the given object
   */
  public void setCustomName(EObject object_p, String value_p, IContext context_p);

  /**
   * Retrieve the current name of the given object. If customized, it returns the custom name, otherwize, 
   * returns the computed value of the name from parameters put in the pContext_p.
   * This method is used while Create Replica wizard.
   */
  public String getCurrentName(EObject object_p, IContext context_p, IPropertyContext pContext_p);

  /**
   * Remove the current name
   */
  public void unsetCustomName(EObject element_p, String value_p, IContext iContext_p, IPropertyContext pContext_p);

  /**
   * Returns whether ths user has modified the suffix value for the given object_p
   */
  public boolean isManualSuffixable(Object object_p, IContext context_p);

  /**
   * User-made choice about suffix for the given element
   */
  public void setManualSuffixable(EObject object_p, boolean value_p, IContext context_p);

  /**
   * Returns whether the object is suffixable
   */
  public boolean isSuffixable(Object object_p, IContext context_p);

  /**
   * Set the suffix value for the given object
   */
  public void setSuffixable(EObject object_p, boolean value_p, IContext context_p);

  /**
   * Returns whether an element can be suffixed
   */
  public boolean isSuffixableElement(Object object_p, IContext context_p);

}

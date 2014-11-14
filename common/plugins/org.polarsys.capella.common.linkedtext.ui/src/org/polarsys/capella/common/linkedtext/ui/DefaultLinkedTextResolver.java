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
package org.polarsys.capella.common.linkedtext.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Predicate;


public class DefaultLinkedTextResolver implements LinkedTextDocument.Resolver {

  private final Predicate<Resource> _include;
  
  public DefaultLinkedTextResolver(Predicate<Resource> include_p){
    _include = include_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getTarget(Object base_p, String href_p) {
    if (base_p instanceof EObject){
      ResourceSet rs = ((EObject) base_p).eResource().getResourceSet();
      for (Resource res : rs.getResources()){
        if (_include.apply(res)){
          EObject target = res.getEObject(href_p);
          if (target != null){
            return target;
          }
        }
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHref(Object base_p, Object target_p) {
    if (target_p instanceof EObject){
      return EcoreUtil.getID((EObject) target_p);
    }
    return null;
  }

}

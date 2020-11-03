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

package org.polarsys.capella.common.linkedtext.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Predicate;


public class DefaultLinkedTextResolver implements LinkedTextDocument.Resolver {

  private final Predicate<Resource> _include;
  
  public DefaultLinkedTextResolver(Predicate<Resource> include){
    _include = include;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getTarget(Object base, String href) {
    if (base instanceof EObject){
      ResourceSet rs = ((EObject) base).eResource().getResourceSet();
      for (Resource res : rs.getResources()){
        if (_include.apply(res)){
          EObject target = res.getEObject(href);
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
  public String getHref(Object base, Object target) {
    if (target instanceof EObject){
      return EcoreUtil.getID((EObject) target);
    }
    return null;
  }

}

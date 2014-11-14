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
package org.polarsys.capella.shared.id.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 */
public class XMLResourceIdHandler extends AbstractIdHandler {

  /**
   * Default constructor
   */
  public XMLResourceIdHandler() {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  public String getId(EObject element_p) {
    if (element_p != null) {
      Resource eResource = element_p.eResource();
      if (eResource instanceof XMLResource) {
        return ((XMLResource) eResource).getID(element_p);
      }
    }
    return null;
  }
}

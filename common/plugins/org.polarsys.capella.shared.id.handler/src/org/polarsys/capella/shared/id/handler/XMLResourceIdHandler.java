/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public String getId(EObject element) {
    if (element != null) {
      Resource eResource = element.eResource();
      if (eResource instanceof XMLResource) {
        return ((XMLResource) eResource).getID(element);
      }
    }
    return null;
  }
}

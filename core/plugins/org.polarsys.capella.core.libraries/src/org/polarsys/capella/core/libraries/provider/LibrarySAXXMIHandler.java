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
package org.polarsys.capella.core.libraries.provider;

import java.util.Map;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;

/**
 * A custom SAX Handler which loads only Library related elements
 */
public class LibrarySAXXMIHandler extends SAXXMIHandler {

  public LibrarySAXXMIHandler(XMLResource xmiResource_p, XMLHelper helper_p, Map<?, ?> options_p) {
    super(xmiResource_p, helper_p, options_p);
  }

  /**
   * This method is deprecated by EMF/XMI but still used by default
   */
  @Override
  @Deprecated
  protected EObject createObjectFromFactory(EFactory factory_p, String typeName_p) {
    if (CapellamodellerPackage.Literals.PROJECT.getName().equals(typeName_p)) {
      return super.createObjectFromFactory(factory_p, typeName_p);

    } else if (CapellamodellerPackage.Literals.LIBRARY.getName().equals(typeName_p)) {
      return super.createObjectFromFactory(factory_p, typeName_p);

    } else if (LibrariesFactory.eINSTANCE.equals(factory_p)) {
      return super.createObjectFromFactory(factory_p, typeName_p);
    }

    return null;
  }

  @Override
  protected void validateCreateObjectFromFactory(EFactory factory, String typeName, EObject newObject) {
    //don't raise an error
  }
}

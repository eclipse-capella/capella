/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.project;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.capellamodeller.Library;

public class ReProjectPropertyTester extends PropertyTester {

  /**
   * Is the currently selected EObject in a library. TODO move somewhere else for 1.4.0
   */
  public final static String P_IN_LIBRARY = "inLibrary"; //$NON-NLS-1$


  /**
   * FIXME merge with isRecElement in RePropertyTester, see
   * https://bugs.polarsys.org/show_bug.cgi?id=1843
   */
  public final static String P_IS_REC_ELEMENT = "isRecElement"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

    if (P_IN_LIBRARY.equals(property)) {
        return EcoreUtil.getRootContainer((EObject) receiver) instanceof Library;
    }

    if (P_IS_REC_ELEMENT.equals(property)) {
      return isRecElement(receiver);
    }

    throw new IllegalArgumentException("unknown property"); //$NON-NLS-1$

  }

  private boolean isRecElement(Object receiver) {
    if (receiver instanceof EObject) {
      for (CatalogElementLink link : EObjectExt.<CatalogElementLink> getReferencers((EObject) receiver,
          RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET)) {
        if (isRecCatalogElement(link.getSource())) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isRecCatalogElement(Object receiver) {
    if (receiver instanceof CatalogElement) {
      CatalogElement ce = (CatalogElement) receiver;
      return ce.getKind() == CatalogElementKind.REC || ce.getKind() == CatalogElementKind.REC_RPL;
    }
    return false;
  }

}

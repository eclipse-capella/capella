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
package org.polarsys.capella.common.re.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

/**
 *
 */
public class ReplicableElementExt {

  /**
   * Returns replicable elements referencing the given element_p
   */
  public static Collection<CatalogElement> getReferencingReplicableElements(EObject element_p) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    Collection<EObject> links = EObjectExt.getReferencers(element_p, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    for (EObject object : links) {
      if (object instanceof CatalogElementLink) {
        CatalogElementLink link = (CatalogElementLink) object;
        if (link.getSource() != null) {
          elements.add(link.getSource());
        }
      }
    }
    return elements;
  }

  /**
   * Returns replicable elements links referencing the given element_p
   */
  public static Collection<CatalogElementLink> getReferencingLinks(EObject source_p) {
    ArrayList<CatalogElementLink> elements = new ArrayList<CatalogElementLink>();
    Collection<EObject> links = EObjectExt.getReferencers(source_p, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    for (EObject object : links) {
      if (object instanceof CatalogElementLink) {
        CatalogElementLink link = (CatalogElementLink) object;
        elements.add(link);
      }
    }
    return elements;
  }

  public static EObject getReferencingElement(CatalogElement source_p, EObject originElement_p) {
    for (CatalogElementLink link : source_p.getOwnedLinks()) {
      if ((link.getTarget() != null) && (link.getOrigin() != null)) {
        if ((link.getOrigin().getTarget() != null) && link.getOrigin().getTarget().equals(originElement_p)) {
          return link.getTarget();
        }
      }
    }
    return null;
  }

  /**
   * Returns replicable elements links referencing the given element_p
   */
  public static Collection<CatalogElement> getReplicas(CatalogElement source_p) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    Collection<EObject> links = EObjectExt.getReferencers(source_p, RePackage.Literals.CATALOG_ELEMENT__ORIGIN);
    for (EObject object : links) {
      if (object instanceof CatalogElement) {
        CatalogElement link = (CatalogElement) object;
        elements.add(link);
      }
    }
    return elements;
  }

}

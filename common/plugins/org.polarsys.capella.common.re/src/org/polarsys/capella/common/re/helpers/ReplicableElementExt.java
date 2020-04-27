/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

/**
 *
 */
public class ReplicableElementExt {

  /**
   * Returns replicable elements referencing the given element
   */
  public static Collection<CatalogElement> getReferencingReplicableElements(EObject element) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    Collection<EObject> links = EObjectExt.getReferencers(element, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
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
   * Returns replicable elements referencing the given element
   */
  public static Collection<CatalogElement> getReferencingReplicas(EObject element) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    Collection<EObject> links = EObjectExt.getReferencers(element, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    for (EObject object : links) {
      if (object instanceof CatalogElementLink) {
        CatalogElementLink link = (CatalogElementLink) object;
        if (link.getSource() != null && link.getSource().getKind()!= CatalogElementKind.REC) {
          elements.add(link.getSource());
        }
      }
    }
    return elements;
  }

  /**
   * Returns replicable elements links referencing the given element via the links 'target' reference
   */
  public static Collection<CatalogElementLink> getReferencingLinks(EObject source) {
    ArrayList<CatalogElementLink> elements = new ArrayList<CatalogElementLink>();
    Collection<EObject> links = EObjectExt.getReferencers(source, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    for (EObject object : links) {
      if (object instanceof CatalogElementLink) {
        CatalogElementLink link = (CatalogElementLink) object;
        elements.add(link);
      }
    }
    return elements;
  }

  public static EObject getReferencingElement(CatalogElement source, EObject originElement) {
    for (CatalogElementLink link : source.getOwnedLinks()) {
      if ((link.getTarget() != null) && (link.getOrigin() != null)) {
        if ((link.getOrigin().getTarget() != null) && link.getOrigin().getTarget().equals(originElement)) {
          return link.getTarget();
        }
      }
    }
    return null;
  }

  /**
   * Returns replicable elements links referencing the given element
   */
  public static Collection<CatalogElement> getReplicas(CatalogElement source) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    Collection<EObject> links = EObjectExt.getReferencers(source, RePackage.Literals.CATALOG_ELEMENT__ORIGIN);
    for (EObject object : links) {
      if (object instanceof CatalogElement) {
        CatalogElement link = (CatalogElement) object;
        elements.add(link);
      }
    }
    return elements;
  }


}

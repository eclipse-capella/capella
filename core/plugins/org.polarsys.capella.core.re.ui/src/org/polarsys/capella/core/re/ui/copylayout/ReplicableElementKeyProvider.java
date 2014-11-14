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
package org.polarsys.capella.core.re.ui.copylayout;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.sirius.ui.copylayout.AbstractCapellaLayoutDataKey;
import org.polarsys.capella.core.sirius.ui.copylayout.keyproviders.IKeyProvider;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;

/**
 * Copy Layout traceability for Replicable Element Mechanism
 */
public class ReplicableElementKeyProvider implements IKeyProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<LayoutDataKey> getKeys(LayoutDataKey key_p) {
    Collection<LayoutDataKey> keys = new ArrayList<LayoutDataKey>();

    if (key_p instanceof AbstractCapellaLayoutDataKey) {
      AbstractCapellaLayoutDataKey mKey = (AbstractCapellaLayoutDataKey) key_p;
      if ((mKey.getSemantic() == null) || mKey.getSemantic().eIsProxy()) {
        return keys;
      }

      Collection<CatalogElementLink> referencingLinks = ReplicableElementExt.getReferencingLinks(mKey.getSemantic());

      //For a RPL, find a layout of its REC (recursively)
      for (CatalogElementLink link : referencingLinks) {
        while ((link.getOrigin() != null) && (link.getOrigin().getTarget() != null)) {
          keys.add(new ReplicableElementLayoutDataKey(mKey, link.getOrigin().getTarget()));
          link = link.getOrigin();
        }
      }

      //For a REC, find a layout of its RPL (not recursively)
      for (CatalogElementLink link : referencingLinks) {
        for (EObject rplLink : EObjectExt.getReferencers(link, RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN)) {
          if (rplLink instanceof CatalogElementLink) {
            CatalogElementLink rplLin2 = (CatalogElementLink) rplLink;
            if (rplLin2.getTarget() != null) {
              keys.add(new ReplicableElementLayoutDataKey(mKey, rplLin2.getTarget()));
            }
          }
        }
      }

      //For a RPL, find a layout of a sister RPL (not recursively)
      for (CatalogElementLink link : referencingLinks) {
        if ((link.getOrigin() != null) && (link.getOrigin().getTarget() != null)) {
          CatalogElementLink origin = link.getOrigin();

          for (EObject rplLink : EObjectExt.getReferencers(origin, RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN)) {
            if (rplLink instanceof CatalogElementLink) {
              CatalogElementLink rplLin2 = (CatalogElementLink) rplLink;
              if ((link != rplLin2) && (rplLin2.getTarget() != null)) {
                keys.add(new ReplicableElementLayoutDataKey(mKey, rplLin2.getTarget()));
              }
            }
          }
        }
      }

    }

    return keys;
  }

}

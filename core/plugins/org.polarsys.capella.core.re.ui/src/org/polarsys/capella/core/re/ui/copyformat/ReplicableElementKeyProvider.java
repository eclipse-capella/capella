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
package org.polarsys.capella.core.re.ui.copyformat;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.sirius.ui.copyformat.AbstractCapellaFormatDataKey;
import org.polarsys.capella.core.sirius.ui.copyformat.keyproviders.IKeyProvider;

/**
 * Copy Format traceability for Replicable Element Mechanism
 */
public class ReplicableElementKeyProvider implements IKeyProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<FormatDataKey> getKeys(FormatDataKey key) {
    Collection<FormatDataKey> keys = new ArrayList<FormatDataKey>();

    if (key instanceof AbstractCapellaFormatDataKey) {
      AbstractCapellaFormatDataKey mKey = (AbstractCapellaFormatDataKey) key;
      if ((mKey.getSemantic() == null) || mKey.getSemantic().eIsProxy()) {
        return keys;
      }

      Collection<CatalogElementLink> referencingLinks = ReplicableElementExt.getReferencingLinks(mKey.getSemantic());

      //For a RPL, find a format of its REC (recursively)
      for (CatalogElementLink link : referencingLinks) {
        while ((link.getOrigin() != null) && (link.getOrigin().getTarget() != null)) {
          keys.add(new ReplicableElementFormatDataKey(mKey, link.getOrigin().getTarget()));
          link = link.getOrigin();
        }
      }

      //For a REC, find a format of its RPL (not recursively)
      for (CatalogElementLink link : referencingLinks) {
        for (EObject rplLink : EObjectExt.getReferencers(link, RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN)) {
          if (rplLink instanceof CatalogElementLink) {
            CatalogElementLink rplLin2 = (CatalogElementLink) rplLink;
            if (rplLin2.getTarget() != null) {
              keys.add(new ReplicableElementFormatDataKey(mKey, rplLin2.getTarget()));
            }
          }
        }
      }

      //For a RPL, find a format of a sister RPL (not recursively)
      for (CatalogElementLink link : referencingLinks) {
        if ((link.getOrigin() != null) && (link.getOrigin().getTarget() != null)) {
          CatalogElementLink origin = link.getOrigin();

          for (EObject rplLink : EObjectExt.getReferencers(origin, RePackage.Literals.CATALOG_ELEMENT_LINK__ORIGIN)) {
            if (rplLink instanceof CatalogElementLink) {
              CatalogElementLink rplLin2 = (CatalogElementLink) rplLink;
              if ((link != rplLin2) && (rplLin2.getTarget() != null)) {
                keys.add(new ReplicableElementFormatDataKey(mKey, rplLin2.getTarget()));
              }
            }
          }
        }
      }

    }

    return keys;
  }

}

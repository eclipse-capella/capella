/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;

/**
 * Set the suffix
 */
public class RPLSuffixContribution extends AbstractMigrationContribution {

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    // if this element is a RPL
    if (currentElement instanceof CatalogElement) {
      CatalogElement rpl = (CatalogElement) currentElement;

      if ((rpl.getKind() == CatalogElementKind.RPL) || (rpl.getKind() == CatalogElementKind.REC_RPL)) {

        // For each RPL element, compute if it must be suffixed:
        for (CatalogElementLink link : rpl.getOwnedLinks()) {
          if (link.getUnsynchronizedFeatures().contains("name") && !link.isSuffixed()) {
            link.getUnsynchronizedFeatures().remove("name");
            link.setSuffixed(true);
          }
        }

        // its RPL suffix to find and set:
        if (rpl.getSuffix() == null) {
          String rplSuffix = getCommonSuffix(rpl);
          if (rplSuffix != null) {
            rpl.setSuffix(rplSuffix);
          }
        }

      }
    }
  }

  protected String getCommonSuffix(CatalogElement rpl) {
    String suffix = null;

    // For each RPL element suffixed found, we check if they all ending with the same suffix
    // if it is not equals, suffix is uncertain and set to empty

    for (CatalogElementLink link : rpl.getOwnedLinks()) {
      if (link.isSuffixed()) {
        CatalogElementLink recLink = link.getOrigin();
        if (recLink != null) {
          EObject rplElement = link.getTarget();
          EObject recElement = recLink.getTarget();

          if ((rplElement instanceof AbstractNamedElement) && (recElement instanceof AbstractNamedElement)) {
            String rplName = ((AbstractNamedElement) rplElement).getName();
            String recName = ((AbstractNamedElement) recElement).getName();

            // if the RPL element name starts with the name of its corresponding REC element, retrieve suffix
            if ((rplName != null) && (recName != null) && rplName.startsWith(recName)) {
              String currentSuffix = rplName.substring(recName.length());

              if (suffix == null) {
                suffix = currentSuffix;
              } else if (!suffix.equals(currentSuffix)) {
                suffix = null;
                break;
              }
            }
          }
        }
      }
    }

    return suffix;
  }
}

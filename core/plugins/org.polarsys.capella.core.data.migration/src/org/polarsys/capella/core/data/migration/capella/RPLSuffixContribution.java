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
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.capellacore.NamedElement;
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
        String rplSuffix = null;

        // For each RPL element suffixed found
        for (CatalogElementLink link : rpl.getOwnedLinks()) {
          if (link.isSuffixed()) {

            CatalogElementLink recLink = link.getOrigin();
            if (recLink != null) {
              EObject rplElement = link.getTarget();
              EObject recElement = recLink.getTarget();

              if (rplElement instanceof NamedElement && recElement instanceof NamedElement) {
                String rplName = ((NamedElement) rplElement).getName();
                String recName = ((NamedElement) recElement).getName();

                String elementSuffix = "";

                // if the RPL element name starts with the name of its corresponding REC element
                if (rplName != null && recName != null && rplName.startsWith(recName)) {

                  // then the element suffix is its element name minus its REC element name
                  elementSuffix = rplName.substring(recName.length());
                }

                // if it is the first suffix find
                if (rplSuffix == null) {
                  rplSuffix = elementSuffix;
                }
                // if not, compare with the first suffix found
                else if (!rplSuffix.equals(elementSuffix)) {
                  // if it is not equals, the rplSuffix is uncertain and set to empty
                  rplSuffix = "";
                  break;
                  // if it is equals, continue to next RPL element
                }
              }
            }
          }
        }

        if (rplSuffix != null) {
          rpl.setSuffix(rplSuffix);
        }
      }
    }
  }
}

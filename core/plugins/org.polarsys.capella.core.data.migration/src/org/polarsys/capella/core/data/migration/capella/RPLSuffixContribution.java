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
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;

/**
 * Set the suffix
 */
public class RPLSuffixContribution extends AbstractMigrationContribution {

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (currentElement instanceof CatalogElement) {
      CatalogElement rpl = (CatalogElement) currentElement;
      if ((rpl.getKind() == CatalogElementKind.RPL) || (rpl.getKind() == CatalogElementKind.REC_RPL)) {

        // if the RPL starts with the name of its REC,
        CatalogElement rec = rpl.getOrigin();
        if (rec != null) {
          String recName = rec.getName();
          String rplName = rpl.getName();
          if ((rplName != null) && rplName.startsWith(recName)) {

            // then its suffix is its name minus its REC name
            String suffix = rplName.substring(recName.length());
            rpl.setSuffix(suffix);
          }
        }
      }

      for (CatalogElementLink link : rpl.getOwnedLinks()) {
        if (link.getUnsynchronizedFeatures().contains("name") && !link.isSuffixed()) {
          link.getUnsynchronizedFeatures().remove("name");
          link.setSuffixed(true);
        }
      }
    }
  }

}

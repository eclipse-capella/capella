/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.capella;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;

public class EOppositeMigrationContribution extends AbstractMigrationContribution {

  @Override
  public boolean ignoreSetFeatureValue(EObject peekObject, EStructuralFeature feature, Object value, int position,
      XMLResource resource, MigrationContext context) {
    
    // This is handled by the FunctionalChainMigrationContribution
    if (feature == FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS) {
      return false;
    }

    // This is handled by the SiriusMigrationContribution
    if (feature == ViewpointPackage.Literals.DREPRESENTATION__NAME
        || feature == ViewpointPackage.Literals.DREPRESENTATION__DOCUMENTATION) {
      return false;
    }

    if (!feature.isChangeable() && feature.isDerived()) {
      return true;
    }
    
    return super.ignoreSetFeatureValue(peekObject, feature, value, position, resource, context);
  }

}

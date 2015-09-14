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

package org.polarsys.capella.patterns.migration.contribution;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.CorepatternsPackage;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.predefined.PredefinedPackage;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogResourceHelper;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonpatternsupportPackage;
import org.eclipse.emf.diffmerge.patterns.templates.gen.templatepatterns.TemplatepatternsPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.patterns.migration.PatternsMigrationConstants;
import org.polarsys.capella.patterns.migration.PatternsMigrationHelper;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * 
 */
public class PatternsMigrationContribution extends AbstractMigrationContribution {

  /**
   * Constructor
   */
  public PatternsMigrationContribution() {
    // Nothing needed
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unaryMigrationExecute(EObject object, MigrationContext context) {
    String typeName = object.eClass().getName();
    if (CorepatternsPackage.eINSTANCE.getPatternRepository().getName().equals(typeName)) {
      // Pattern repository
      PatternsMigrationHelper.getInstance().migrateCatalog(object);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void globalPostMigrationExecute() {
  }

  /**
   * {@inheritDoc}
   */
  public void populateRegistry(Registry registry) {
    // Register the new schema against the new namespace URI
    registry.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_CORE_NSURI, CorepatternsPackage.eINSTANCE);
    registry.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_CORE_PREDEFINED_NSURI, PredefinedPackage.eINSTANCE);
    registry.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_SUPPORT_EMDE_NSURI, CommonpatternsupportPackage.eINSTANCE);
    registry.put(PatternsMigrationConstants.OLD_FULL_PATTERNS_TEMPLATES_NSURI, TemplatepatternsPackage.eINSTANCE);
    registry.put(PatternsMigrationConstants.OLD_FULL_EMDE_NSURI, EmdePackage.eINSTANCE);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isValidURI(URI uri) {
    return PatternCatalogResourceHelper.isPatternCatalogResource(uri);
  }

  /**
   * {@inheritDoc}
   */
  public boolean isValidResource(IResource resource) {
    return PatternCatalogResourceHelper.isPatternCatalogResource(resource);
  }

}

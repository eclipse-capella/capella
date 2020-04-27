/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.patterns.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternRole;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.AbstractPattern;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.CorepatternsPackage;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.PatternRepository;
import org.eclipse.emf.diffmerge.patterns.templates.gen.templatepatterns.TemplatePatternRole;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * This class migrates Pattern-related elements.
 */
public class PatternsMigrationHelper {

  /** Singleton instance */
  private static PatternsMigrationHelper __instance = null;

  /** The default description for elements that must have one */
  protected static final String DEFAULT_DESCRIPTION = ""; //$NON-NLS-1$

  /** The default version for patterns */
  protected static final String DEFAULT_VERSION = "1.0"; //$NON-NLS-1$

  /** The identifier for the main multipart */
  protected static final String MAIN_MULTIPART = "MAIN"; //$NON-NLS-1$

  /** The modelling environments in which the migrated patterns are applicable */
  private List<String> _environments;

  /**
   * Return the singleton instance of this class
   * 
   * @return a non-null object
   */
  public static PatternsMigrationHelper getInstance() {
    if (__instance == null)
      __instance = new PatternsMigrationHelper();
    return __instance;
  }

  /**
   * Constructor
   */
  public PatternsMigrationHelper() {
    _environments = null;
  }

  /**
   * Compute and return the modelling environments in which the migrated patterns are applicable
   * 
   * @return a non-null, potentially empty, unmodifiable list
   */
  protected List<String> computeEnvironments() {
    List<String> result = Collections.emptyList();
    String currentProductLabel = null;
    IProduct product = Platform.getProduct();
    if (product != null) {
      currentProductLabel = product.getName();
      if (currentProductLabel != null) {
        Bundle mainBundle = Platform.getProduct().getDefiningBundle();
        if (mainBundle != null) {
          Version version = mainBundle.getVersion();
          if (version != null) {
            currentProductLabel = currentProductLabel + ' ' + version.getMajor() + '.' + version.getMinor() + '.' + 'x';
          }
        }
      }
    }
    if (currentProductLabel != null)
      result = Collections.singletonList(currentProductLabel);
    return result;
  }

  /**
   * Return the modeling environments in which the migrated patterns are applicable
   * 
   * @return a non-null, potentially empty, unmodifiable list
   */
  protected List<String> getEnvironments() {
    if (_environments == null)
      _environments = computeEnvironments();
    return _environments;
  }

  /**
   * Migrate the given pattern catalog
   * 
   * @param oldCatalog_p
   *          a non-null catalog
   */
  public void migrateCatalog(EObject oldCatalog_p) {
    if (oldCatalog_p instanceof PatternRepository) {
      PatternRepository rp = (PatternRepository) oldCatalog_p;
      for (AbstractPattern pat : rp.getPatterns()) {
        migratePattern(pat);
      }
    }
  }

  /**
   * Migrate the given pattern
   * 
   * @param oldPattern_p
   *          a non-null AbstractPattern
   */
  protected void migratePattern(AbstractPattern oldPattern_p) {
    List<String> envs = getEnvironments();
    if (envs != null && !envs.isEmpty())
      setNewExecutionEnvironmentForPattern(oldPattern_p, envs.get(0));
    for (IPatternRole role : oldPattern_p.getRoles()) {
      migratePatternRole(role);
    }
  }

  /**
   * Migrate the given pattern role
   * 
   * @param oldRole_p
   *          a non-null role
   */
  protected void migratePatternRole(IPatternRole oldRole_p) {
    if (oldRole_p instanceof TemplatePatternRole) {
      TemplatePatternRole role = (TemplatePatternRole) oldRole_p;
      role.setPreferredContainment(null);
    }
  }

  /**
   * Sets the new execution environment for the given pattern
   * 
   * @param pattern_p
   *          any non-null EObject
   * @param environment_pa
   *          non-null String
   */
  protected void setNewExecutionEnvironmentForPattern(EObject pattern_p, String environment_p) {
    List<String> newExecutionEnv = new ArrayList<String>();
    newExecutionEnv.add(environment_p);
    if (pattern_p != null) {
      final String EXECUTION_ENVIRONMENTS = CorepatternsPackage.eINSTANCE.getAbstractPattern_ExecutionEnvironments()
          .getName();
      EStructuralFeature patternEnvironment = pattern_p.eClass().getEStructuralFeature(EXECUTION_ENVIRONMENTS);
      if (patternEnvironment != null)
        pattern_p.eSet(patternEnvironment, newExecutionEnv);
    }
  }

}

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

import org.eclipse.emf.diffmerge.patterns.support.PatternSupportPlugin;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.kitalpha.patterns.emde.EmdePatternSupportPlugin;

/**
 * Constants involved in the migration of pattern elements.
 */
public class PatternsMigrationConstants extends MigrationConstants {

  private static final String OLD_PATTERNS_URI_PREFIX = "http://org.eclipse.com/emf/diffmerge/"; //$NON-NLS-1$
  /** EPackage prefixes */
  private static final String OLD_PATTERNS_NSURI = OLD_PATTERNS_URI_PREFIX + "patterns/"; //$NON-NLS-1$
  private static final String OLD_PATTERNS_CORE_NSURI = OLD_PATTERNS_NSURI + "core/"; //$NON-NLS-1$
  private static final String OLD_PATTERNS_CORE_PREDEFINED_NSURI = OLD_PATTERNS_CORE_NSURI + "predefined/"; //$NON-NLS-1$
  private static final String OLD_PATTERNS_SUPPORT_NSURI = OLD_PATTERNS_NSURI + "support/"; //$NON-NLS-1$
  private static final String OLD_PATTERNS_SUPPORT_EMDE_NSURI = OLD_PATTERNS_SUPPORT_NSURI + "emde/"; //$NON-NLS-1$
  private static final String OLD_PATTERNS_TEMPLATES_NSURI = OLD_PATTERNS_NSURI + "templates/"; //$NON-NLS-1$
  /** Full NS URIs */
  private static final String OLD_PATTERNS_VERSION = "1.0.0"; //$NON-NLS-1$
  public static final String OLD_FULL_PATTERNS_CORE_NSURI = OLD_PATTERNS_CORE_NSURI + OLD_PATTERNS_VERSION;
  public static final String OLD_FULL_PATTERNS_CORE_PREDEFINED_NSURI = OLD_PATTERNS_CORE_PREDEFINED_NSURI
      + OLD_PATTERNS_VERSION;
  public static final String OLD_FULL_PATTERNS_SUPPORT_EMDE_NSURI = OLD_PATTERNS_SUPPORT_EMDE_NSURI
      + OLD_PATTERNS_VERSION;
  public static final String OLD_FULL_PATTERNS_TEMPLATES_NSURI = OLD_PATTERNS_TEMPLATES_NSURI + OLD_PATTERNS_VERSION;
  public static final String OLD_FULL_EMDE_NSURI = "http://www.polarsys.org/kitalpha/patterns/emde/1.0.0"; //$NON-NLS-1$

  // OLD XMI TYPE NAMES
  private static final String OLD_XMI_PATTERN_SUPPORT_CLASS_NAME_PREFIX = "org.eclipse.com.emf.diffmerge.patterns.support:"; //$NON-NLS-1$
  public static final String OLD_XMI_INSTANCE_CONTAINER_CLASS_NAME = OLD_XMI_PATTERN_SUPPORT_CLASS_NAME_PREFIX
      + "PatternInstanceSetExtension"; //$NON-NLS-1$
  public static final String OLD_XMI_INSTANCE_CLASS_NAME = OLD_XMI_PATTERN_SUPPORT_CLASS_NAME_PREFIX
      + "EmdePatternInstance"; //$NON-NLS-1$

  // NEW XMI TYPE PREFIXES
  /** The type prefix for concepts of default pattern support */
  public static final String DEFAULT_PATTERN_SUPPORT_PREFIX = PatternSupportPlugin.getDefault().getPluginId();
  /** The type prefix for model elements of default pattern support */
  public static final String DEFAULT_PATTERN_SUPPORT_GEN_PREFIX = DEFAULT_PATTERN_SUPPORT_PREFIX + ".gen"; //$NON-NLS-1$
  /** The type prefix for concepts of Kitalpha pattern support */
  public static final String KITALPHA_PATTERN_SUPPORT_PREFIX = EmdePatternSupportPlugin.getDefault().getPluginId();
  /** The type prefix for model elements of Kitalpha pattern support */
  public static final String KITALPHA_PATTERN_SUPPORT_GEN_PREFIX = KITALPHA_PATTERN_SUPPORT_PREFIX + ".gen"; //$NON-NLS-1$

}

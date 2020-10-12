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
package org.polarsys.capella.core.data.migration;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 *
 */
public class MigrationConstants {

  public static final String FILE_SEPARATOR = "" + ICommonConstants.SLASH_CHARACTER;

  public static final String PREFIX_PREFIX = "org.polarsys.capella."; //$NON-NLS-1$
  public static final String CAPELLA_PREFIX = PREFIX_PREFIX + "core.data."; //$NON-NLS-1$
  public static final String SHARED_PREFIX = PREFIX_PREFIX + "common."; //$NON-NLS-1$

  public static final String CAPELLA_COMMON_PREFIX = CAPELLA_PREFIX + "capellacommon"; //$NON-NLS-1$
  public static final String CAPELLA_CORE_PREFIX = CAPELLA_PREFIX + "capellacore"; //$NON-NLS-1$
  public static final String COMPOSITE_STRUCTURE_PREFIX = CAPELLA_PREFIX + "cs"; //$NON-NLS-1$
  public static final String CONTEXT_ARCHITECTURE_PREFIX = CAPELLA_PREFIX + "ctx"; //$NON-NLS-1$
  public static final String EPBS_ARCHITECTURE_PREFIX = CAPELLA_PREFIX + "epbs"; //$NON-NLS-1$
  public static final String FUNCTIONAL_ANALYSIS_PREFIX = CAPELLA_PREFIX + "fa"; //$NON-NLS-1$
  public static final String INFORMATION_PREFIX = CAPELLA_PREFIX + "information"; //$NON-NLS-1$
  public static final String INFORMATION_COMMUNICATION_PREFIX = INFORMATION_PREFIX + ".communication"; //$NON-NLS-1$
  public static final String INFORMATION_DATATYPE_PREFIX = INFORMATION_PREFIX + ".datatype"; //$NON-NLS-1$
  public static final String INFORMATION_DATAVALUE_PREFIX = INFORMATION_PREFIX + ".datavalue"; //$NON-NLS-1$
  public static final String INFORMATION_REFERENCE_PREFIX = INFORMATION_PREFIX + ".reference"; //$NON-NLS-1$
  public static final String INTERACTION_PREFIX = CAPELLA_PREFIX + "interaction"; //$NON-NLS-1$
  public static final String LOGICAL_ARCHITECTURE_PREFIX = CAPELLA_PREFIX + "la"; //$NON-NLS-1$
  public static final String CAPELLA_MODELLER_PREFIX = CAPELLA_PREFIX + "capellamodeller"; //$NON-NLS-1$
  public static final String OPERATIONAL_ANALYSIS_PREFIX = CAPELLA_PREFIX + "oa"; //$NON-NLS-1$
  public static final String PHYSICAL_ARCHITECTURE_PREFIX = CAPELLA_PREFIX + "pa"; //$NON-NLS-1$
  public static final String PHYSICAL_ARCHITECTURE_DEPLOYMENT_PREFIX = PHYSICAL_ARCHITECTURE_PREFIX + ".deployment"; //$NON-NLS-1$
  public static final String REQUIREMENT_PREFIX = CAPELLA_PREFIX + "requirement"; //$NON-NLS-1$
  public static final String SHARED_MODEL_PREFIX = CAPELLA_PREFIX + "sharedmodel"; //$NON-NLS-1$
  public static final String ACTIVITY_PREFIX = SHARED_PREFIX + "activity"; //$NON-NLS-1$
  public static final String BEHAVIOR_PREFIX = SHARED_PREFIX + "behavior"; //$NON-NLS-1$
  public static final String MODELLING_CORE_PREFIX = SHARED_PREFIX + "core"; //$NON-NLS-1$
  public static final String LIBRARIES_PREFIX = SHARED_PREFIX + "libraries";
  public static final String RE_PREFIX = SHARED_PREFIX + "re";

  public static final String CAPELLA_COMMON_OLD_NSURI = "http://www.polarsys.org/capella/core/common/1.4.0";
  public static final String CAPELLA_CORE_OLD_NSURI = "http://www.polarsys.org/capella/core/core/1.4.0";
  public static final String COMPOSITE_STRUCTURE_OLD_NSURI = "http://www.polarsys.org/capella/core/cs/1.4.0";
  public static final String CONTEXT_ARCHITECTURE_OLD_NSURI = "http://www.polarsys.org/capella/core/ctx/1.4.0";
  public static final String EPBS_ARCHITECTURE_OLD_NSURI = "http://www.polarsys.org/capella/core/epbs/1.4.0";
  public static final String FUNCTIONAL_ANALYSIS_OLD_NSURI = "http://www.polarsys.org/capella/core/fa/1.4.0";
  public static final String INFORMATION_OLD_NSURI = "http://www.polarsys.org/capella/core/information/1.4.0";
  public static final String INFORMATION_COMMUNICATION_OLD_NSURI = "http://www.polarsys.org/capella/core/information/communication/1.4.0";
  public static final String INFORMATION_DATATYPE_OLD_NSURI = "http://www.polarsys.org/capella/core/information/datatype/1.4.0";
  public static final String INFORMATION_DATAVALUE_OLD_NSURI = "http://www.polarsys.org/capella/core/information/datavalue/1.4.0";
  public static final String INTERACTION_OLD_NSURI = "http://www.polarsys.org/capella/core/interaction/1.4.0";
  public static final String LOGICAL_ARCHITECTURE_OLD_NSURI = "http://www.polarsys.org/capella/core/la/1.4.0";
  public static final String CAPELLA_MODELLER_OLD_NSURI = "http://www.polarsys.org/capella/core/modeller/1.4.0";
  public static final String OPERATIONAL_ANALYSIS_OLD_NSURI = "http://www.polarsys.org/capella/core/oa/1.4.0";
  public static final String PHYSICAL_ARCHITECTURE_OLD_NSURI = "http://www.polarsys.org/capella/core/pa/1.4.0";
  public static final String PHYSICAL_ARCHITECTURE_DEPLOYMENT_OLD_NSURI = "http://www.polarsys.org/capella/core/pa/deployment/1.4.0";
  public static final String REQUIREMENT_OLD_NSURI = "http://www.polarsys.org/capella/core/requirement/1.4.0";
  public static final String SHARED_MODEL_OLD_NSURI = "http://www.polarsys.org/capella/core/sharedmodel/1.4.0";

  public static final String ACTIVITY_OLD_NSURI = "http://www.polarsys.org/capella/common/activity/1.4.0";
  public static final String BEHAVIOR_OLD_NSURI = "http://www.polarsys.org/capella/common/behavior/1.4.0";
  public static final String MODELLING_CORE_OLD_NSURI = "http://www.polarsys.org/capella/common/core/1.4.0";

  public static final String LIBRARIES_OLD_NSURI = "http://www.polarsys.org/capella/common/libraries/1.4.0";
  public static final String RE_OLD_NSURI = "http://www.polarsys.org/capella/common/re/1.4.0";

  // public static final String MAPPING_FOLDER = "/" + OLD_VERSION + "_" + NEW_VERSION + "/"; //$NON-NLS-1$
  // //$NON-NLS-2$ //$NON-NLS-3$

  public static final String MIGRATION_KIND__SCF = "MIGRATION_KIND__SCF";
  @Deprecated
  public static final String MIGRATION_KIND__PATTERN = "MIGRATION_KIND__PATTERN";
  public static final String MIGRATION_KIND__CHECK_MISSING_VP = "MIGRATION_KIND__CHECK_MISSING_VP";
  @Deprecated
  public static final String MIGRATION_KIND__AFM = "MIGRATION_KIND__AFM";
  public static final String MIGRATION_KIND__SEMANTIC = "MIGRATION_KIND__SEMANTIC";
  public static final String MIGRATION_KIND__DIAGRAM = "MIGRATION_KIND__DIAGRAM";
  public static final String MIGRATION_KIND__FCDDIAGRAM = "MIGRATION_KIND__FCDDIAGRAM";
  public static final String MIGRATION_KIND__VIEWPOINT = "MIGRATION_KIND__VIEWPOINT";

  public static final String[] DEFAULT_KIND_ORDER = { MIGRATION_KIND__CHECK_MISSING_VP, MIGRATION_KIND__PATTERN,
      MIGRATION_KIND__SEMANTIC, MIGRATION_KIND__DIAGRAM, MIGRATION_KIND__FCDDIAGRAM, MIGRATION_KIND__AFM,
      MIGRATION_KIND__VIEWPOINT, MIGRATION_KIND__SCF };
}

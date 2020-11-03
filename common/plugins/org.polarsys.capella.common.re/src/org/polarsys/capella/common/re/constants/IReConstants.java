/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.constants;

/**
 */
public class IReConstants {

  public static final String PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE = "isource"; //$NON-NLS-1$
  public static final String PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET = "itarget"; //$NON-NLS-1$

  public static final String PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE = "source"; //$NON-NLS-1$
  public static final String PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET = "target"; //$NON-NLS-1$

  @Deprecated
  public static final String PROPERTY__REPLICABLE_ELEMENT__SOURCE = "source"; //$NON-NLS-1$
  public static final String PROPERTY__REPLICABLE_ELEMENT__SUFFIX = "suffix"; //$NON-NLS-1$
  public static final String PROPERTY__REPLICABLE_ELEMENT__SUFFIXES = "suffixes"; //$NON-NLS-1$
  @Deprecated
  public static final String PROPERTY__REPLICABLE_ELEMENT__TARGET = "target"; //$NON-NLS-1$

  public static final String PROPERTY__CURRENT_COMPLIANCY = "currentCompliancy"; //$NON-NLS-1$
  public static final String PROPERTY__CHILDREN_COMPLIANCY = "childrenCompliancy"; //$NON-NLS-1$

  public static final String PROPERTY__SCOPE = "source.scopeElements"; //$NON-NLS-1$
  public static final String PROPERTY__ALL_SCOPE = "source.allScopeElements"; //$NON-NLS-1$

  public static final String PROPERTY__PARENT_LOCATOR = "parentLocatorOption"; //$NON-NLS-1$
  public static final String LOCATOR_OPTION_DEFAULT = "defaultLocator"; //$NON-NLS-1$
  public static final String LOCATOR_OPTION_SPECIFIC_PACKAGES = "packageLocator"; //$NON-NLS-1$
  public static final String LOCATOR_OPTION_MANUAL = "manualLocator"; //$NON-NLS-1$

  public static final String PROPERTY__MERGE_SOURCE_SCOPE = "merge_sourceScope"; //$NON-NLS-1$
  public static final String PROPERTY__MERGE_TARGET_SCOPE = "merge_targetScope"; //$NON-NLS-1$

  public static final String PROPERTY__CLICK_SELECTION = "org.polarsys.capella.common.re.selection"; //$NON-NLS-1$

  public static final String PROPERTY__COPY_ELEMENTS = "copyElements"; //$NON-NLS-1$

  public static final String UPDATE_REPLICA = "org.polarsys.capella.common.re.updateReplica";
  public static final String UPDATE_REPLICABLE_ELEMENT = "org.polarsys.capella.common.re.updateCur";
  public static final String DELETE_REPLICABLE_ELEMENT_AND_RELATED_ELEMENTS = "org.polarsys.capella.common.re.deleteReplicaAndRelatedElements";

  public static final String COMMAND__CURRENT_VALUE = "COMMAND__CURRENT_VALUE";
  public static final String COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE = "COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE";
  public static final String COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE = "COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE";
  public static final String COMMAND__DELETE_A_REPLICA_AND_RELATED_ELEMENTS = "COMMAND__DELETE_A_REPLICA_AND_RELATED_ELEMENTS";
  public static final String COMMAND__DELETE_A_REPLICA_PRESERVE_RELATED_ELEMENTS = "COMMAND__DELETE_A_REPLICA_PRESERVE_RELATED_ELEMENTS";
  public static final String COMMAND__DETACH_ELEMENT_FROM_REPLICA = "COMMAND__DETACH_ELEMENT_FROM_REPLICA";

  public static final String COMMAND__CREATE_REPLICABLE_ELEMENT = "COMMAND__CREATE_REPLICABLE_ELEMENT";
  public static final String COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA = "COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA";
  public static final String COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA = "COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA";

  public static final String SOURCE__ADDED_ELEMENTS = "capella.re.SOURCE__ADDED_ELEMENTS";
  public static final String TARGET__ADDED_ELEMENTS = "capella.re.TARGET__ADDED_ELEMENTS";

  public static final String UNMERGEABLE_ELEMENTS = "capella.re.UNMERGEABLE_ELEMENTS";
  public static final String UNMODIFIABLE_ELEMENTS = "capella.re.UNMODIFIABLE_ELEMENTS";
  public static final String SHARED_ELEMENTS = "capella.re.SHARED_ELEMENTS";

  public static final String TRACEABILITY_LOCATION_HANDLER = "TRACEABILITY_LOCATION_HANDLER";
  
  public static final String ATTRIBUTE_HANDLER = "ATTRIBUTE_HANDLER";
  public static final String LOCATION_HANDLER = "LOCATION_HANDLER";
  public static final String REPLICABLE_ELEMENT_HANDLER = "REPLICABLE_ELEMENT_HANDLER";
  public static final String PROPERTY__TARGET_NAME = "targetName";
  public static final String PROPERTY__READONLY = "readOnly";

  // Virtual links are:
  // When we create a replica from a rec, we create for all elements of the
  // rec a link, that we will reconnect further to the
  // element of the replica created while diffmerge.
  // When attached to the source element (of the rec), the link is located in
  // the VIRTUAL_LINKS.
  // On diffmerge, when the link is linked to the element of the rpl, the link
  // is located in the VIRTUAL_LINKS_2. Since it is a real link, it should not
  // be
  // removed
  // except if a CANCEL is triggered.
  // At the end of the operation, VIRTUAL_LINKS_2 is copied into
  // VIRTUAL_LINKS_3, list of links which should not be deleted at the
  // FinalizationActivity.

  // All links created while using tooling
  public static final String CREATED_LINKS = "CREATED_LINKS";
  // All links of a RPL towards semantic elements of the REC. updated while
  // diffmerge process
  public static final String VIRTUAL_LINKS = "VIRTUAL_LINKS";
  // All links to keep after tooling. Some links will be deleted
  public static final String CREATED_LINKS_TO_KEEP = "CREATED_LINKS_TO_KEEP";
  public static final String ADDITIONAL_ELEMENTS_TO_DELETE = "ADDITIONAL_ELEMENTS_TO_DELETE";

  public static final String PLUGIN_ID = "org.polarsys.capella.common.re"; //$NON-NLS-1$
  public static final String PROPERTY__LOCATION_TARGET = "locationTarget"; //$NON-NLS-1$
  public static final String PROPERTY__LOCATION_SOURCE = "locationSource"; //$NON-NLS-1$
  public static final String PROPERTY__DEPENDENCIES = "dependencies";
  public static final String PROPERTY__SHARED_ELEMENTS = "sharedElements";
  public static final String PROPERTY__INVALID_SHARED_ELEMENTS = "invalidSharedElements";
  public static final String REQUIRED_ELEMENTS = "REQUIRED_ELEMENTS";
  public static final String DEPENDENCIES_HANDLER = "DEPENDENCIES_HANDLER";

  public static final String RELATED_ELEMENTS_SCOPE_HANDLER = "RELATED_ELEMENTS_SCOPE_HANDLER";
  public static final String SHARED_ELEMENTS_SCOPE_HANDLER = "SHARED_ELEMENTS_SCOPE_HANDLER";
  public static final String VALID_SHARED_ELEMENTS_SCOPE_HANDLER = "VALID_SHARED_ELEMENTS_SCOPE_HANDLER";
  public static final String DEPENDENCIES_SCOPE_HANDLER = "DEPENDENCIES_SCOPE_HANDLER";
  public static final Object SCOPE_COMPUTATION_SCOPE_HANDLER = "SCOPE_COMPUTATION_SCOPE_HANDLER";
  public static final Object SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER = "SCOPE_COMPLEMENTARY_COMPUTATION_SCOPE_HANDLER";
  public static final Object ORIGINAL_SUFFIX = "ORIGINAL_SUFFIX";

  public static final String RE_MAPPING = "org.polarsys.capella.common.re"; //$NON-NLS-1$
  public static final String RE_PURPOSE__SHARED_ELEMENTS = "org.polarsys.capella.common.re.sharedElements"; //$NON-NLS-1$
  public static final String RE_PURPOSE__VALID_SHARED_ELEMENTS = "org.polarsys.capella.common.re.validSharedElements"; //$NON-NLS-1$
  public static final String RE_PURPOSE__DEPENDENCIES = "org.polarsys.capella.common.re.dependencies";
  public static final String RE_PURPOSE__RELATED_ELEMENTS = "org.polarsys.capella.common.re.relatedElements";
  public static final String RE_PURPOSE__SCOPE_ELEMENTS = "org.polarsys.capella.common.re.scopeElements";
  public static final String RE_PURPOSE__COMPLEMENTARY_SCOPE_ELEMENTS = "org.polarsys.capella.common.re.complementaryScopeElements";

  public static boolean ENABLE_SUB_INSTANCIATION() {
    return false;
  }

}

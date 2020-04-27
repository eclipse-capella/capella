/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.transposer.current;

/**
 *
 */
public interface IRuleMappingExtensionConstants {

  String RULES_MAPPING_EXTENSION_POINT = "org.polarsys.kitalpha.transposer.rules.handler.mapping";
  String MAPPING_TAG_ENGINE = "mapping";
  String EXTENDED_MAPPING_EXTENSION_ID = "extendedMappingExtensionID";
  String MAPPING_PURPOSE = "mappingPurpose";
  String MAPPING_NAME = "mappingName";
  String MAPPING_DESCRIPTION = "description";
  String MAPPING_DOMAIN_HELPER = "domainHelper";
  String MAPPING_DEFAULT_CONTEXT = "context";
  String MAPPING_PACKAGE_TAG_ENGINE = "mappingPackage";
  String MAPPING_PACKAGE_NAME = "name";
  String MAPPING_ELEMENT_TAG_ENGINE = "mappingElement";
  String MAPPING_ELEMENT_NAME = "name";
  String MAPPING_ELEMENT_DOMAIN_METACLASS = "domainMetaClass";
  String MAPPING_ELEMENT_REUSE_DEFAULT_POSSIBILITY = "reuseExtendedElementDefaultPossibility";
  String MAPPING_ELEMENT_REUSE_POSSIBILITIES = "reuseExtendedElementPossibilities";
  String MAPPING_POSSIBILITY_TAG_ENGINE = "mappingPossibility";
  String MAPPING_POSSIBILITY_ENABLED = "enabled";
  String MAPPING_POSSIBILITY_NAME = "name";
  String MAPPING_POSSIBILITY_COMPLETE_RULE = "completeRule";
  String MAPPING_POSSIBILITY_INCOMPLETE_RULE = "incompleteRule";
  String MAPPING_POSSIBILITY_CONTEXT = "context";
  String MAPPING_DEFAUT_POSSIBILITY_TAG_ENGINE = "defaultMappingPossibility";
}

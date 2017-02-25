/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.transposer.current;

/**
 *
 */
public interface IRuleMappingExtensionConstants {

  static final String RULES_MAPPING_EXTENSION_POINT = "org.polarsys.kitalpha.transposer.rules.handler.mapping";
  static final String MAPPING_TAG_ENGINE = "mapping";
  static final String EXTENDED_MAPPING_EXTENSION_ID = "extendedMappingExtensionID";
  static final String MAPPING_PURPOSE = "mappingPurpose";
  static final String MAPPING_NAME = "mappingName";
  static final String MAPPING_DESCRIPTION = "description";
  static final String MAPPING_DOMAIN_HELPER = "domainHelper";
  static final String MAPPING_DEFAULT_CONTEXT = "context";
  static final String MAPPING_PACKAGE_TAG_ENGINE = "mappingPackage";
  static final String MAPPING_PACKAGE_NAME = "name";
  static final String MAPPING_ELEMENT_TAG_ENGINE = "mappingElement";
  static final String MAPPING_ELEMENT_NAME = "name";
  static final String MAPPING_ELEMENT_DOMAIN_METACLASS = "domainMetaClass";
  static final String MAPPING_ELEMENT_REUSE_DEFAULT_POSSIBILITY = "reuseExtendedElementDefaultPossibility";
  static final String MAPPING_ELEMENT_REUSE_POSSIBILITIES = "reuseExtendedElementPossibilities";
  static final String MAPPING_POSSIBILITY_TAG_ENGINE = "mappingPossibility";
  static final String MAPPING_POSSIBILITY_ENABLED = "enabled";
  static final String MAPPING_POSSIBILITY_NAME = "name";
  static final String MAPPING_POSSIBILITY_COMPLETE_RULE = "completeRule";
  static final String MAPPING_POSSIBILITY_INCOMPLETE_RULE = "incompleteRule";
  static final String MAPPING_POSSIBILITY_CONTEXT = "context";
  static final String MAPPING_DEFAUT_POSSIBILITY_TAG_ENGINE = "defaultMappingPossibility";
}

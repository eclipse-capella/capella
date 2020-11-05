/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Composed Oracle Definition.
 * 
 * Most Validation rule that are executed on a Target Element X provide a marker and quick fix for that Target Element
 * X. </br>
 * 
 * In some cases, the target of the validation rule and the provided markers for the quick fixes are not the same, see
 * {@link org.polarsys.capella.core.data.interaction.validation.scenario.InstanceRoleHasDifferentNameThanRepresentedInstance}
 *
 * The purpose of the Composed Oracle Definition is to verify that the children Oracle Definitions are also valid. </br>
 * For more details check {@link org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase.test()}
 * 
 */
public class ComposedOracleDefinition extends OracleDefinition {

  private Collection<IOracleDefinition> childOracleDefinitions;

  public ComposedOracleDefinition(String objectID, int nbExpectedErrors,
      List<IOracleDefinition> childOracleDefinitions) {
    super(objectID, nbExpectedErrors);
    this.childOracleDefinitions = childOracleDefinitions;
  }

  public Collection<IOracleDefinition> getChildren() {
    return childOracleDefinitions;
  }

  public static OracleDefinition create(String rootElementId, String... childrenElementIds) {
    List<IOracleDefinition> children = Arrays.stream(childrenElementIds).map(id -> new OracleDefinition(id, 1))
        .collect(Collectors.toList());

    return new ComposedOracleDefinition(rootElementId, 1, children);
  }

}

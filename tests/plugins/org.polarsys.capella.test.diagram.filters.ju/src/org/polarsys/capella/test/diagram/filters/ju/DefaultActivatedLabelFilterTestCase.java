/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju;

public abstract class DefaultActivatedLabelFilterTestCase extends LabelFilterTestCase {

  @Override
  protected void checkAndInsertFilter() {

    // We only need to check that the filter is already present on the diagram before the test starts
    assertFilterActive(diagram, filterName);
  }

}

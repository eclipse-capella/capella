/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

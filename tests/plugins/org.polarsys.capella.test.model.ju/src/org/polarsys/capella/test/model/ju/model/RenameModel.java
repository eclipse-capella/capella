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
package org.polarsys.capella.test.model.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class RenameModel extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(RenameModel.class.getSimpleName());
  }
}

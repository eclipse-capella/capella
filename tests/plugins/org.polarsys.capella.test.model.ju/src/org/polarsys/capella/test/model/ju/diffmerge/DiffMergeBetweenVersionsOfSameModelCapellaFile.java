/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.diffmerge;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class DiffMergeBetweenVersionsOfSameModelCapellaFile extends DiffMergeBetweenVersionsOfSameModelTestCase {

  @Override
  protected String getSourceResourceName() {
    return sourceModel + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
  }

  @Override
  protected String getTargetResourceName() {
    return sourceModel + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
  }
}

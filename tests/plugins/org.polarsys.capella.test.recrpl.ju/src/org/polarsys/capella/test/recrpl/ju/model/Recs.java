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
package org.polarsys.capella.test.recrpl.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

public abstract class Recs extends RecRplTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "recs" }); //$NON-NLS-1$
  }

  public static String REC1 = "REC1"; //$NON-NLS-1$ 
}

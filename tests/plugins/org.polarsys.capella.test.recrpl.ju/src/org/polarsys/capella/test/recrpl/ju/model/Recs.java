/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

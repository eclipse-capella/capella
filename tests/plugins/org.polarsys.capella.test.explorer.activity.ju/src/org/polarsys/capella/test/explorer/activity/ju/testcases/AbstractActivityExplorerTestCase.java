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
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Base class for Activity Explorer tests. 
 */
public abstract class AbstractActivityExplorerTestCase extends BasicTestCase {

  public static final String TEST_PROJECT_NAME = "EmptyModel";
  public static final int NB_ACTIVITY_EXPLORER_TABS = 7;
  
  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList(TEST_PROJECT_NAME);
  }
}

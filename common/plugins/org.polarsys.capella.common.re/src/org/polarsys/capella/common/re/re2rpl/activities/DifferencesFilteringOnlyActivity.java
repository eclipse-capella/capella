/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.re2rpl.activities;

import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 */
public class DifferencesFilteringOnlyActivity extends DifferencesFilteringActivity implements ITransposerWorkflow {

  public static final String ID = DifferencesFilteringOnlyActivity.class.getCanonicalName();

  @Override
  public void performMerge(IContext context) {
    // Do Nothing
  }

}

/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.re2rpl.activities;

import org.polarsys.capella.common.re.constants.IReConstants;

/**
 */
public class InitializeTransitionActivity extends org.polarsys.capella.common.re.activities.InitializeTransitionActivity {

  public static final String ID = InitializeTransitionActivity.class.getCanonicalName();

  @Override
  protected String getDefaultOptionsScope() {
    return IReConstants.UPDATE_REPLICA;
  }

}

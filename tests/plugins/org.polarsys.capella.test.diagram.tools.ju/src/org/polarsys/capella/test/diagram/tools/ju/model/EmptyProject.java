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
package org.polarsys.capella.test.diagram.tools.ju.model;

import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;

public abstract class EmptyProject extends AbstractDiagramTestCase {

  public static String SA__SYSTEM = "b121dd59-9d3f-4c21-94ae-87e957aaa2a9"; //$NON-NLS-1$ 
  public static String SA__SYSTEM__SYSTEM_STATE_MACHINE = "b3c846c5-2cc3-4cd6-8c21-aedb71cbb771"; //$NON-NLS-1$ 
  public static String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION = "555fc1d5-1910-4d77-8158-9302240fe0ca"; //$NON-NLS-1$ 

  @Override
  public String getRequiredTestModel() {
    return EmptyProject.class.getSimpleName();
  }
}

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

public abstract class SwitchCategory extends AbstractDiagramTestCase {

  public static String SA__FUNCTIONPKG = "015492b3-98d6-4507-8b0a-6bccc844e653";
  public static String SA__FUNCTIONPKG__CATEGORY1 = "75a95691-eac6-460b-938c-fed7e5dd80ff";
  public static String SA__FUNCTIONPKG__SF__FUNTIONALEXCHANGE1 = "20b2edb7-49ad-4295-a020-89af7974897e";
  public static final String FUNCTIONALEXCHANGE_3 = "973e6234-a468-4c82-9836-2e11d9174721"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_4 = "c216d4cf-b52e-4801-a481-b71e48a77789"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_5 = "d405695d-ae4c-45ec-a9d1-4a92ac5b0a71"; //$NON-NLS-1$
  public static final String EXCHANGECATEGORY_2 = "5e94610f-0697-416b-aee0-efaf89ff01c0"; //$NON-NLS-1$

  public static String SDFB_ROOT_SYSTEM_FUNCTION = "[SDFB] Root System Function - System Data Flow Blank";
  public static final String LAB_LOGICAL_SYSTEM = "[LAB] Logical System"; //$NON-NLS-1$

  @Override
  public String getRequiredTestModel() {
    return SwitchCategory.class.getSimpleName();
  }
}

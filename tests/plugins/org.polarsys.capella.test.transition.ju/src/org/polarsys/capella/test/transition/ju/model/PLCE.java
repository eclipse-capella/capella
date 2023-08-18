/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public abstract class PLCE extends TopDownTransitionTestCase {
  
  public static final String SA_8 = "10a9c35e-25b8-4fec-9045-7e2d358ba550"; //$NON-NLS-1$
  public static final String SA_6 = "395618e8-99b4-42ae-a630-35f8baeb7dcf"; //$NON-NLS-1$
  public static final String SA_5 = "0489531b-08ee-4078-ad80-4e3a04af7d90"; //$NON-NLS-1$
  public static final String SA_7 = "bd7fe9fb-f967-414c-9140-1b434adebed7"; //$NON-NLS-1$
  public static final String SA_3 = "c67eeaab-7661-4e68-bb37-70b2476c50b3"; //$NON-NLS-1$
  public static final String SA_2 = "fa9e62dc-a8a3-43d7-941e-4abae7cefe36"; //$NON-NLS-1$
  public static final String SA_9 = "54330877-909b-4c0b-9388-9e4098956d97"; //$NON-NLS-1$
  public static final String SA_4 = "120e4a2e-810e-4f0a-9470-2f2d2e6e91c1"; //$NON-NLS-1$
  public static final String C_2 = "66fb82d6-356f-4d53-8128-1fc8117c4edd"; //$NON-NLS-1$
  public static final String PL_2 = "584b4641-42a0-464d-81e3-1dbf7f7cc45f"; //$NON-NLS-1$
  public static final String C_1 = "d9768eb7-04b8-4224-beaf-6a60a6ad273b"; //$NON-NLS-1$
  public static final String PL_1 = "1f0f3144-1986-4416-8708-79106a33c5fb"; //$NON-NLS-1$
  public static final String C_3 = "0e8b58ff-9680-4693-82d9-5514215cce54"; //$NON-NLS-1$
  public static final String PL_3 = "3d065ece-167d-4a4f-add1-bc1cdc54429b"; //$NON-NLS-1$
  public static final String SA_STRUCTURE = "df137491-9774-4916-8337-db1f98117528"; //$NON-NLS-1$
  public static final String LA_STRUCTURE = "dbafb8e0-3cf2-4d52-9928-02d1ff2a3e4d"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM = "3a34e74d-5fbc-422d-b6cc-b06e610c0b41"; //$NON-NLS-1$
  public static final String LA_3 = "849cb0c2-b628-4583-adb4-46a7df82c27d"; //$NON-NLS-1$
  public static final String LA_6 = "16ee8770-3818-4ebb-9fd3-bfab85958b9d"; //$NON-NLS-1$
  
  public static final String C_4 = "76a56ee3-cc56-4a46-87a0-af6ae69d7ba3"; //$NON-NLS-1$
  public static final String PL_4 = "d6428b0e-be9f-4824-b395-c8e023b5d90a"; //$NON-NLS-1$
  public static final String SA_10 = "9e826396-40a8-4b31-a4b7-a69742db7599"; //$NON-NLS-1$
  public static final String SA_11 = "d9da48b0-ea6f-457f-be2f-59f8e10eb17a"; //$NON-NLS-1$
  
  public static final String C_5 = "94ff5610-4e02-4cd4-acf5-f38750756a7c"; //$NON-NLS-1$
  public static final String PL_5 = "03fe6876-3038-41e5-acfb-b0d7863d1890"; //$NON-NLS-1$
  public static final String C_6 = "ef600d60-835c-4a28-8e63-8a7c16d94ea3"; //$NON-NLS-1$
  public static final String PL_6 = "76b98c13-9b95-4aee-838b-26f80eb3d7e8"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PLCE.class.getSimpleName());
  }
}

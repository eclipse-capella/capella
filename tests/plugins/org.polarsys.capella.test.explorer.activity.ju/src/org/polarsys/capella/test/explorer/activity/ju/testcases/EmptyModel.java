/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;

public abstract class EmptyModel extends NonDirtyTestCase {
  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA = "a388aa7e-ef09-4aad-86b3-d7c70c663b7e";
  public static final String OA__OPERATIONAL_CAPABILITIES = "e1b9e752-9415-49ac-b3ad-790c252dee92";
  public static final String OA__DATA = "f1bf66ce-697f-4cbf-b926-8bc8e6e7250f";
  public static final String OA__OPERATIONAL_CONTEXT = "f6985216-39d9-48a0-8b3d-ab47621b6b82";
  public static final String SA__ROOT_SF = "5261edbf-0fe6-4947-a234-74ab61c045c3";
  public static final String SA__CAPABILITIES = "63998d5f-1fc1-46c3-bcb9-75db29713736";
  public static final String SA__DATA = "90d110e9-6dc6-46ac-b236-3647282f2154";
  public static final String SA__SYSTEM_COMPONENT_PKG = "fea4f6b5-3863-4f5c-b048-b3cf7b6e7ba4";
  public static final String LA__ROOT_LF = "d2562c74-fb30-4e62-b623-51cb11534f8e";
  public static final String LA__CAPABILITIES = "3dc2d2c1-64e4-47dd-a80f-6d6680889853";
  public static final String LA__DATA = "f3ee54bb-1cc0-441e-8b1a-bc98d95be900";
  public static final String LA__LOGICAL_CONTEXT = "379a40b0-c136-47cd-b4e0-9e42e33b6e96";
  public static final String PA__ROOT_PF = "0315b465-b1d3-4f1c-8b80-e05f55460b58";
  public static final String PA__CAPABILITIES = "54e013c9-9708-4e87-b8f2-2932349ed9af";
  public static final String PA__DATA = "9fadabc3-bab1-430f-9858-b5a8148930de";
  public static final String PA__PHYSICAL_CONTEXT = "b78ef284-d837-4c56-937f-7b8657abb2fc";
  public static final String EPBS__EPBS_CONTEXT = "ed239283-9e2f-4849-a339-1fdeb6334d66";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("EmptyModel");
  }

  protected CapellaModel getTestModel() {
    return super.getTestModel(getRequiredTestModels().get(0));
  }
}

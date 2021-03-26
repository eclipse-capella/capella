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
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ComponentPkgExtTestCase extends BasicTestCase {

  public static String MODEL_NAME = "miscmodel"; //$NON-NLS-1$
  
  public static final String STRUCTURE = "64c920b7-afe6-4d7f-b993-986ab11b1de1"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM = "030e91dc-441e-4107-b0ea-0b08acaccf13"; //$NON-NLS-1$
  
  public static final String INTERNAL_LC_1 = "5b6b9d5b-d024-4423-9271-bcb3d77469c2"; //$NON-NLS-1$
  public static final String INTERNAL_LC_2 = "a30cf2b5-48d1-407e-9967-1e20e64e8e88"; //$NON-NLS-1$
  public static final String INTERNAL_ACTOR_1 = "aa969fee-8ba1-4159-9534-7a3c673ecb6c"; //$NON-NLS-1$
  public static final String INTERNAL_ACTOR_2 = "e2928bb7-f1a7-4429-9a87-0c8e79a5363f"; //$NON-NLS-1$
  public static final String INTERNAL_ACTOR_3 = "909071a7-a84b-4f6f-8ed7-7cfb65de0ec7"; //$NON-NLS-1$
  public static final String INTERNAL_ACTOR_4 = "5836f750-af83-446d-a5c3-e5da188f8940"; //$NON-NLS-1$
  public static final String INTERNAL_LOGICAL_COMPONENT_PKG_1 = "b1123d45-f6d3-411b-a3c6-ea2965a5da7b"; //$NON-NLS-1$
  
  public static final String EXTERNAL_LOGICALCOMPONENTPKG_3 = "5c7a8ce8-8143-484f-a995-035c994b4ee1"; //$NON-NLS-1$
  public static final String EXTERNAL_LOGICALCOMPONENTPKG_2 = "0e081fe7-60c4-4fdc-ac9b-f2c9c420f10a"; //$NON-NLS-1$
  public static final String EXTERNAL_LOGICALCOMPONENTPKG_1 = "379f621c-9326-4a29-a257-613abc3361e6"; //$NON-NLS-1$
  public static final String EXTERNAL_LC_1 = "85825d12-d200-46f7-be30-6d29351293ee"; //$NON-NLS-1$
  public static final String EXTERNAL_LC_2 = "04e39155-3750-4817-8a50-658779096d3f"; //$NON-NLS-1$
  public static final String EXTERNAL_ACTOR_1 = "8ae1f1b4-0b84-457b-bc67-2aff89b87589"; //$NON-NLS-1$
  public static final String EXTERNAL_ACTOR_2 = "b406eba9-368e-4583-bfd4-64e00bf65288"; //$NON-NLS-1$
  public static final String EXTERNAL_ACTOR_3 = "51dc6471-52d0-416d-ad70-908e5f7fb087"; //$NON-NLS-1$
  public static final String EXTERNAL_ACTOR_4 = "6040d11f-b504-449c-9f09-3cdf81008713"; //$NON-NLS-1$
  public static final String EXTERNAL_ACTOR_5 = "d91ea798-c1be-40b4-b107-ea7c368e6a4e"; //$NON-NLS-1$
  public static final String EXTERNAL_ACTOR_6 = "152d75ac-4cc4-4255-ba3d-2de5facf2f69"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }
  
  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    
    List<String> externalActors = ComponentPkgExt.getExternalActors((ComponentPkg) IdManager.getInstance().getEObject(STRUCTURE, scope)).stream().map(e -> e.getId()).collect(Collectors.toList());
    assertTrue(externalActors.containsAll(Arrays.asList(EXTERNAL_ACTOR_1, EXTERNAL_ACTOR_2, EXTERNAL_ACTOR_3, EXTERNAL_ACTOR_4, EXTERNAL_ACTOR_5, EXTERNAL_ACTOR_6)));
    assertEquals(externalActors.size(), 17);
    
    externalActors = ComponentPkgExt.getExternalActors((ComponentPkg) IdManager.getInstance().getEObject(EXTERNAL_LOGICALCOMPONENTPKG_2, scope)).stream().map(e -> e.getId()).collect(Collectors.toList());
    assertTrue(externalActors.containsAll(Arrays.asList(EXTERNAL_ACTOR_4, EXTERNAL_ACTOR_5, EXTERNAL_ACTOR_6)));
    assertEquals(externalActors.size(), 3);
    
    externalActors = ComponentPkgExt.getExternalActors((ComponentPkg) IdManager.getInstance().getEObject(INTERNAL_LOGICAL_COMPONENT_PKG_1, scope)).stream().map(e -> e.getId()).collect(Collectors.toList());
    assertTrue(externalActors.isEmpty());
  }
}

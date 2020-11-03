/*******************************************************************************
 * Copyright (c) 2018, 2020, THALES GLOBAL SERVICES.
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

public class Fragmented extends RecRplTestCase {

  public static final String TEST_MODEL_NAME = "frag-model";
  public static final String TEST_LIBRARY_NAME = "frag-library";

  public static final String OA_REC_ID = "a81b4a76-ee9a-4a81-bf9c-a34365ab2658";
  public static final String SA_REC_ID = "2c7eba9b-990d-4a3a-ae28-9f5169243cce";
  public static final String LA_REC_ID = "ceb1b45c-f1ee-44a1-83a5-67d739c0ff31";
  public static final String PA_REC_ID = "38a54924-4ee7-4b76-b810-5537317e8391";
  public static final String EPBS_REC_ID = "b9b65f66-bdc6-4343-8c46-27aec1dac15b";
  
  public static final String OA_OPENTITIES_ID = "65ac9c56-9014-4de9-86d4-138f5be686aa";
  public static final String SA_LAYER_ID = "9a0db4b9-1361-47fe-88b2-d1a67e2af59b";
  public static final String LA_LAYER_ID = "54066e5f-9249-4ddb-bc82-c47c1f968ee2";
  public static final String PA_LAYER_ID = "de248296-3a68-467a-a160-3110f2de547a";
  public static final String EPBS_LAYER_ID = "ae8bef27-9d07-4168-8893-cec4c2900ab4";  

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(TEST_MODEL_NAME, TEST_LIBRARY_NAME);
  }

}

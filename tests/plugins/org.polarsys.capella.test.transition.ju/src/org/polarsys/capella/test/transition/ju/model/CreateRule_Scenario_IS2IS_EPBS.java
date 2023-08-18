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

public abstract class CreateRule_Scenario_IS2IS_EPBS extends TopDownTransitionTestCase {
  public static final String IS_CAPABILITYREALIZATION_1 = "e7b729fb-1233-4add-942b-6ea45509b3d9"; //$NON-NLS-1$
  public static final String IR_PC_2 = "883e81db-fc18-4582-95ad-c6639c741b7a"; //$NON-NLS-1$
  public static final String IR_PC_3 = "4ab95a2d-b8f7-4000-8ebe-bc4884bc2bea"; //$NON-NLS-1$
  public static final String IR_PC_4 = "61323d96-a5ad-4b0e-bcf3-bc9d862088b8"; //$NON-NLS-1$
  public static final String IR_PC_5 = "85eba72d-8b45-4f2e-89f1-88c5e6562a54"; //$NON-NLS-1$
  public static final String IR_PC_6 = "c0e790d9-43a6-48dc-ab59-517b0af992f1"; //$NON-NLS-1$
  public static final String SM23 = "34ea1621-dce8-42e6-b343-11968225eb80"; //$NON-NLS-1$
  public static final String SM34 = "3f7134a5-e863-4c7a-8139-e5164656aeab"; //$NON-NLS-1$
  public static final String SM45 = "8fbe6cfd-d882-4ee0-ade0-f3e55b53a607"; //$NON-NLS-1$
  public static final String SM56 = "40e68ef9-2c04-4336-ae80-c12556e69a36"; //$NON-NLS-1$
  public static final String EI_SM23 = "13667a90-3589-47a6-99cf-1ff100939573"; //$NON-NLS-1$
  public static final String EI_SM34 = "bf3587f6-e4a3-4c70-aeab-95b39eb25771"; //$NON-NLS-1$
  public static final String EI_SM45 = "38ba1c9f-97c5-4d7a-8f51-081591102e1c"; //$NON-NLS-1$
  public static final String EI_SM56 = "e42d7427-574a-446b-ad94-7f6075b91e61"; //$NON-NLS-1$
  public static final String ITF_23 = "12ee59b7-ca16-4f5b-8702-f23dc04e1fe1"; //$NON-NLS-1$
  public static final String ITF_34 = "2257f9a1-9c28-4792-8e23-5d7ba101a268"; //$NON-NLS-1$
  public static final String ITF_45 = "5702e6de-36f9-46ce-bc1a-fbd61bf62811"; //$NON-NLS-1$
  public static final String ITF_56 = "7bbc02fb-ee11-4581-ac91-04d797450e2b"; //$NON-NLS-1$
  public static final String PC_2 = "49bb104c-1cd0-45bf-9399-16e7cac41d3a"; //$NON-NLS-1$
  public static final String PC_3 = "0641f468-7b54-499f-99f6-20430ebd9497"; //$NON-NLS-1$
  public static final String PC_4 = "0d80c00b-286b-4166-9f87-c28d3e4be6e1"; //$NON-NLS-1$
  public static final String PC_5 = "064dce8e-9436-4250-865d-5a18a84bde8e"; //$NON-NLS-1$
  public static final String PC_6 = "6348ddc7-e84f-491c-acd3-85c0856017c1"; //$NON-NLS-1$
  public static final String COTSCI_COTSCI_1 = "e8e40fc0-4650-4002-b34e-7bdc1d6762e3"; //$NON-NLS-1$
  public static final String COTSCI_COTSCI_2 = "bda958aa-7b7f-494f-be9f-fb5e54f89edf"; //$NON-NLS-1$
  
  public static final String CAPABILITYREALIZATION_2 = "1cc8d4fe-b338-4f96-952e-9e3bfb6eab4c"; //$NON-NLS-1$
  public static final String IS_CAPABILITYREALIZATION_2 = "3bf0e036-bcd0-411c-9878-bf5be221f95b"; //$NON-NLS-1$
  public static final String IR_PA_3 = "e117ef4f-0c4a-4dfa-ae88-1af22f2c9fbc"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

}

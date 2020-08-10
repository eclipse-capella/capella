/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.test.recrpl.ju.RecRplCommandManager;
import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

/**
 * Test whether a Physical Link or a Component Exchange associated to an Actor is located by default under root
 * ComponentPkg, not a Component
 */
public class Location_Part extends RecRplTestCase {
  public static final String SA_2 = "b824a601-9c17-4f6a-a451-32b43a0b628e"; //$NON-NLS-1$
  public static final String LA_2 = "80e2bd18-a33a-499d-9164-2774c7d27cb6"; //$NON-NLS-1$
  public static final String LC_1 = "b5d889e8-467e-47ad-ac6e-fabd6919415d"; //$NON-NLS-1$
  public static final String LIA_3 = "8dacff02-699c-47a3-85fc-85e3bd9a9b8c"; //$NON-NLS-1$
  public static final String PA_2 = "23c2979f-8c9e-4684-a1aa-195dabef5c99"; //$NON-NLS-1$
  public static final String PC_1 = "20a21706-a744-4f73-ba84-984a6b9f0724"; //$NON-NLS-1$
  public static final String PC_2 = "ff410eca-da68-469b-834f-3fe7a0863538"; //$NON-NLS-1$
  public static final String PIA_3 = "ac79e8fe-63e9-40fb-aca2-3795110df28e"; //$NON-NLS-1$
  public static final String LOCATIONPARTPROJECT = "62d639b9-270f-4478-a948-bb40b27859bb"; //$NON-NLS-1$
  public static final String REC1 = "62624233-a3b1-45df-a703-1ea3801300fd"; //$NON-NLS-1$
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "locationPart" }); //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {
    
    RecRplCommandManager.push(IReConstants.PROPERTY__TARGET_NAME, "specificPkgRPL"); //$NON-NLS-1$

    CapellaModelSkeleton project = new CapellaModelSkeleton.Builder(TransactionHelper.getExecutionManager(getObject(REC1))).build();
    CatalogElement rpl = createReplica(Arrays.asList(project.getProject()), (CatalogElement) getObject(REC1));
    
    for (CatalogElementLink link : rpl.getOwnedLinks()) {
      EObject target = link.getTarget();
      EObject origin = link.getOrigin().getTarget();

      if (origin instanceof Part) {
        if (origin.eContainer() instanceof Component) {
          assertTrue(target.eContainer() instanceof Component);
        }
        if (origin.eContainer() instanceof ComponentPkg) {
          assertTrue(target.eContainer() instanceof ComponentPkg);
        }
      }
    }
  }

}

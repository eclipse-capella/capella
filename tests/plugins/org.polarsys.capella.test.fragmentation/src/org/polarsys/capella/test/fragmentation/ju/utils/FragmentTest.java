/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fragmentation.ju.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 */
public class FragmentTest extends AbstractFragmentationTest {

  public FragmentTest(SessionContext context, String objectId) {
    super(context, objectId);
  }

  public FragmentTest(SessionContext context, EObject object) {
    super(context, object);
  }

  public void fragmentTest() {

    EObject objectToFragment = getTargetObject();

    // Check whether the eObject has not been fragmented yet.
    assertNull(
        NLS.bind(FragmentationMessages.abstractFragmentTest_isAlreadyFragmented, EObjectExt.getText(objectToFragment)),
        FragmentUtils.getDirectResource(objectToFragment));

    // Useful data before fragmentation
    int numberOfProperChildren = FragmentUtils.getNumberOfProperEObjects(objectToFragment);
    Map<EObject, Integer> eObjectRefCount = FragmentUtils.getProperContentsRefCount(objectToFragment);

    // The fragmentation itself.
    new GuiActions().fragment((CapellaElement) objectToFragment, _dRepresentations);

    postFragmentChecks(objectToFragment, numberOfProperChildren, eObjectRefCount);
  }

  protected void postFragmentChecks(EObject objectToFragment, int numberOfProperChildren,
      Map<EObject, Integer> eObjectRefCount) {
    // First, let's check whether new resource is ok.
    Resource newResource = FragmentUtils.getDirectResource(objectToFragment);

    // Not null
    assertNotNull(
        NLS.bind(FragmentationMessages.abstractFragmentTest_hasNotItsOwnResource, EObjectExt.getText(objectToFragment)),
        newResource);

    // Expected resource
    FragmentUtils.checkEResource(objectToFragment, newResource);

    // Is All proper children have been moved?
    int newNumberOfProperChildren = FragmentUtils.getNumberOfProperEObjects(objectToFragment);
    assertEquals(NLS.bind(FragmentationMessages.abstractFragmentTest_hasNotSameNumbersOfchildrenAfterFragmentation,
        EObjectExt.getText(objectToFragment)), numberOfProperChildren, newNumberOfProperChildren);

    // References to this object kept?
    Map<EObject, Integer> newEObjectRefCount = FragmentUtils.getProperContentsRefCount(objectToFragment);
    FragmentUtils.compareRefCountForTest(objectToFragment, eObjectRefCount, newEObjectRefCount, true);
  }

  @Override
  protected void postRunTest() {

    // Check DRepresentation, if needed
    if (isThereAnyDRepresentationToMove()) {
      // All DRepresentations should be in the same target aird.
      Set<Resource> newAIRDresources = FragmentUtils.getAirdResourceWithAnalysisOn(getTargetObject());

      for (DRepresentationDescriptor drepresentation : getDRepresentationDescriptorsToMove()) {
        if (!newAIRDresources.contains(drepresentation.eResource())) {
          assertTrue(NLS.bind(FragmentationMessages.abstractFragmentationTest_wrongAirdresourceAfterOps,
              drepresentation.getName()), false);
        }
        Resource newAIRDresource = drepresentation.eResource();
        boolean isnewAIRDresourceExist = WorkspaceSynchronizer.getFile(newAIRDresource) == null ? false : true;
        assertTrue("The new fragment '" + newAIRDresource.getURI().toString() + "' is not create",
            isnewAIRDresourceExist);
      }
    }
    _eObject = null;
    _dRepresentations = null;

    if (null != _oldAIRDResources) {
      _oldAIRDResources.clear();
    }
  }

  @Override
  public Object getResult() {
    return null;
  }

  @Override
  protected void runTest() {
    fragmentTest();
  }
}

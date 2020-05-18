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
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 */
public class UnfragmentTest extends AbstractFragmentationTest {

  public UnfragmentTest(SessionContext context, String objectId) {
    super(context, objectId);
  }

  public UnfragmentTest(SessionContext context, EObject object) {
    super(context, object);
  }

  public void unfragmentTest() {

    EObject objectToUnfragment = getTargetObject();

    // Check whether the eObject has not been fragmented yet.
    assertNotNull(NLS.bind(FragmentationMessages.abstractUnfragmentTest_isNotFragmented,
        EObjectExt.getText(objectToUnfragment)), FragmentUtils.getDirectResource(objectToUnfragment));

    // Useful data before unfragmentation
    int numberOfProperChildren = FragmentUtils.getNumberOfProperEObjects(objectToUnfragment);
    Map<EObject, Integer> eObjectRefCount = FragmentUtils.getProperContentsRefCount(objectToUnfragment);

    // The unfragmentation op. itself.
    new GuiActions().unfragment((CapellaElement) objectToUnfragment, true);

    postUnfragmentChecks(objectToUnfragment, numberOfProperChildren, eObjectRefCount);
    return;
  }

  protected void postUnfragmentChecks(EObject objectToUnfragment, int numberOfProperChildren,
      Map<EObject, Integer> eObjectRefCount) {
    // First, let's check whether new resource is ok.
    Resource directResource = FragmentUtils.getDirectResource(objectToUnfragment);

    // null
    assertNull(NLS.bind(FragmentationMessages.abstractunfragmentTest_hasItsOwnResource,
        EObjectExt.getText(objectToUnfragment)), directResource);

    // has been already moved to the target resource
    Resource targetResource = objectToUnfragment.eResource();

    // Expected resource
    FragmentUtils.checkEResource(objectToUnfragment, targetResource);

    //
    // Is All proper children have been moved?
    //
    int newNumberOfProperChildren = FragmentUtils.getNumberOfProperEObjects(objectToUnfragment);
    assertEquals(NLS.bind(FragmentationMessages.abstractFragmentTest_hasNotSameNumbersOfchildrenAfterFragmentation,
        EObjectExt.getText(objectToUnfragment)), numberOfProperChildren, newNumberOfProperChildren);

    //
    // References to this object kept?
    //
    Map<EObject, Integer> newEObjectRefCount = FragmentUtils.getProperContentsRefCount(objectToUnfragment);
    FragmentUtils.compareRefCountForTest(objectToUnfragment, eObjectRefCount, newEObjectRefCount, false);
  }

  @Override
  public Object getResult() {
    return null;
  }

  @Override
  protected void runTest() {
    unfragmentTest();
  }

  @Override
  protected void postRunTest() {

    // Check DRepresentation, if needed
    if (isThereAnyDRepresentationToMove()) {
      // All DRepresentations should be in the same target aird.
      Set<Resource> newAIRDresources = FragmentUtils.getAirdResourceWithAnalysisOn(getTargetObject());
      for (Resource newAIRDresource : newAIRDresources) {
      boolean isnewAIRDresourceExist = WorkspaceSynchronizer.getFile(newAIRDresource) == null ? false : true;
      assertTrue("The fragment is not removed",
          !isnewAIRDresourceExist);
      }
    }
    _eObject = null;
    _dRepresentations = null;

    if (null != _oldAIRDResources) {
      _oldAIRDResources.clear();
    }
  }
}

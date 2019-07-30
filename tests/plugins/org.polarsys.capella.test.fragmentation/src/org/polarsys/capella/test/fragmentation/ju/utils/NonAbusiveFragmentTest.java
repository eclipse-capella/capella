/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.utils;

import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 */
public class NonAbusiveFragmentTest extends FragmentTest {

  public NonAbusiveFragmentTest(SessionContext context, String objectId) {
    super(context, objectId);
  }

  public NonAbusiveFragmentTest(SessionContext context, EObject object) {
    super(context, object);
  }

  @Override
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

  /**
   * Implement a create and execute tool operation.
   * <p>
   * different of the AbstractExecuteTooCmdTest case A ResourceSetListener is added on ExecutionManager of
   * MDEAdapterFactory
   */
  public void testFragmentForNonAbusiveFragmentModification() {
    EObject objectToFragment = getTargetObject();

    //
    // Check whether the eObject has not been fragmented yet.
    //
    assertNull(NLS.bind(FragmentationMessages.abstractFragmentTest_isAlreadyFragmented,
        EObjectExt.getText(objectToFragment)), FragmentUtils.getDirectResource(objectToFragment));

    //
    // Useful data before fragmentation
    //
    int numberOfProperChildren = FragmentUtils.getNumberOfProperEObjects(objectToFragment);
    Map<EObject, Integer> eObjectRefCount = FragmentUtils.getProperContentsRefCount(objectToFragment);

    //
    // The fragmentation itself.
    //
    DesignerControlActionForNonAbusiveFragmentModifTest action = new DesignerControlActionForNonAbusiveFragmentModifTest();
    action.selectionChanged(new StructuredSelection(getTargetObject()));
    action.setDRepresentationDescriptorsToMove(_dRepresentations);
    action.setExpectedFilesToBeModified(getExpectedFilesToBeModified());
    action.run();

    //
    // First, let's check whether new resource is ok.
    //
    postFragmentChecks(objectToFragment, numberOfProperChildren, eObjectRefCount);

    return;
  }

  protected Set<IFile> getExpectedFilesToBeModified() {
    return new HashSet<IFile>();
  }

}

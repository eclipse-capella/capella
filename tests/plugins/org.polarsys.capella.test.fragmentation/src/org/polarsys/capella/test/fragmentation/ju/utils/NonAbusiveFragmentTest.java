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
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.framework.context.SessionContext;

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
    DesignerControlActionForNonAbusiveFragmentModifTest action = new DesignerControlActionForNonAbusiveFragmentModifTest();
    action.selectionChanged(new StructuredSelection(getTargetObject()));
    action.setDRepresentationDescriptorsToMove(_dRepresentations);
    action.setExpectedFilesToBeModified(getExpectedFilesToBeModified());
    action.run();

    postFragmentChecks(objectToFragment, numberOfProperChildren, eObjectRefCount);
  }

  protected Set<IFile> getExpectedFilesToBeModified() {
    return new HashSet<IFile>();
  }

}

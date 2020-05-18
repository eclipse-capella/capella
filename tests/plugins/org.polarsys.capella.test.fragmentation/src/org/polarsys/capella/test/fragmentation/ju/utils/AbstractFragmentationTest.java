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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class AbstractFragmentationTest extends AbstractTestStep {

  /** the target object */
  protected EObject _eObject = null;

  /** the DRepresentation to move */
  protected List<DRepresentationDescriptor> _dRepresentations = null;

  protected List<Resource> _oldAIRDResources = null;

  public AbstractFragmentationTest(SessionContext context, String objectId) {
    super(context);
    _eObject = context.getSemanticElement(objectId);
    setDRepresentationDescriptorsToMove();
  }

  public AbstractFragmentationTest(SessionContext context, EObject object) {
    super(context);
    _eObject = object;
    setDRepresentationDescriptorsToMove();
  }

  protected void setDRepresentationDescriptorsToMove() {
    _dRepresentations = FragmentUtils.getLinkedDRepresentationDescriptor(this._eObject);
  }

  /** Get the target {@link EObject} to (un)fragment */
  final public EObject getTargetObject() {

    assertNotNull(FragmentationMessages.abstractFragmentationTest_EObjectNotSetted, _eObject);

    return _eObject;
  }

  /**
   * Check whether some {@link DRepresentation} should change of {@link Resource} during operation
   * @return
   */
  final public boolean isThereAnyDRepresentationToMove() {
    return !getDRepresentationDescriptorsToMove().isEmpty();
  }

  final public List<DRepresentationDescriptor> getDRepresentationDescriptorsToMove() {

    if (null == _dRepresentations) { // Let's allows empty list by default
      _dRepresentations = new ArrayList<DRepresentationDescriptor>();
    }

    return _dRepresentations;
  }

  @Override
  protected void preRunTest() {


    if (isThereAnyDRepresentationToMove()) {
      _oldAIRDResources = new ArrayList<Resource>();
    }

    // Check whether the DRepresentation to move are ok
    // e.g. that target are in the same resource
    EObject eobj = null;
    for (DRepresentationDescriptor dRepresentation : getDRepresentationDescriptorsToMove()) {

      if (ViewpointPackage.Literals.DSEMANTIC_DECORATOR.isSuperTypeOf(dRepresentation.getRepresentation().eClass())) {

        eobj = (EObject) dRepresentation.getRepresentation().eGet(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET);
        // populate the "old" resources
        _oldAIRDResources.add(dRepresentation.eResource());

      } else {
        fail(NLS.bind(FragmentationMessages.abstractFragmentationTest_UnsupportedKindOfdRepresentation,
            dRepresentation.getName()));
      }

      assertEquals(NLS.bind(FragmentationMessages.abstractFragmentationTest_dRepresentationToMoveDoesNotMatch,
          dRepresentation.getName()),
          eobj.eResource(), getTargetObject().eResource());
    }
  }

}

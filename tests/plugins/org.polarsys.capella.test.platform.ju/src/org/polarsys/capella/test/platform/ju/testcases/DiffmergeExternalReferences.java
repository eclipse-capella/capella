/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import static org.junit.Assert.assertNotEquals;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.compare.CapellaScope;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test ensures that {@link CapellaScope} is handling references towards elements loaded in a different resource
 * set.
 * 
 * Element in the scope must references elements loaded in the current resource set instead of relying on the external
 * one.
 */
public class DiffmergeExternalReferences extends BasicTestCase {

  @Override
  public void test() throws Exception {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    Viewpoint sharedVp = ViewpointRegistry.getInstance().getViewpoints().iterator().next();

    EObject annotation = createAnnotation(manager);
    loadResource(manager, sharedVp.eResource());
    CapellaScope scope = new CapellaScope(annotation.eResource().getURI(), manager.getEditingDomain(), false);
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        scope.add(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES, sharedVp);
      }
    });

    Viewpoint currentVp = getCurrentElement(manager.getEditingDomain().getResourceSet(), sharedVp);
    assertEquals(currentVp.getName(), sharedVp.getName());
    assertTrue(!scope.get(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES).contains(sharedVp));
    assertTrue(scope.get(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES).contains(currentVp));
  }

  /**
   * For an element loaded in another resourceSet, load the corresponding one.
   * 
   * @implNote we assume that resource of sharedElement is already loaded in the current resource set
   */
  private <T extends EObject> T getCurrentElement(ResourceSet set, T sharedElement) {
    Resource rvp = set.getResource(sharedElement.eResource().getURI(), false);
    assertNotNull(rvp);
    T object = (T) rvp.getEObject(sharedElement.eResource().getURIFragment(sharedElement));
    assertNotNull(object);
    assertNotEquals(object, sharedElement);
    return object;
  }

  /**
   * Create an annotation
   * 
   * @param sharedElement,
   *          an element not contained in the given manager
   */
  private EAnnotation createAnnotation(ExecutionManager manager) {
    EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
    manager.execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        Resource resource = HoldingResourceHelper.getHoldingResource(manager.getEditingDomain());
        resource.getContents().add(annotation);
      }
    });
    return annotation;
  }

  private void loadResource(ExecutionManager manager, Resource sharedElement) {
    manager.execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        manager.getEditingDomain().getResourceSet().getResource(sharedElement.getURI(), true);
      }
    });
  }

}

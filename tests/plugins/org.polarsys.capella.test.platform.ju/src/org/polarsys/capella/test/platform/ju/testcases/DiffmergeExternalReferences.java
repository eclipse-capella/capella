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
 * If the external resource is also loaded in the current resource set, Element in the scope must references elements
 * loaded in the current resource set instead of relying on the external one.
 * 
 * Otherwise, element must reference the external one
 */
public class DiffmergeExternalReferences extends BasicTestCase {

  @Override
  public void test() throws Exception {
    externalLoadedReference();
    externalNotLoadedReference();
  }

  public void externalLoadedReference() {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    EAnnotation annotation = createAnnotation(manager);
    Viewpoint sharedVp = getViewpoint();

    // Load shared vp resource in the current resource set
    loadResource(manager, sharedVp.eResource());

    // Add reference from the current toward external viewpoint
    CapellaScope scope = new CapellaScope(annotation.eResource().getURI(), manager.getEditingDomain(), false);
    addReference(manager, scope, annotation, sharedVp);

    // As vp resource is also loaded in current resource set, Scope shall refer to the current vp, not the external one
    Viewpoint currentVp = getCurrentElement(manager.getEditingDomain().getResourceSet(), sharedVp);
    assertNotNull(currentVp);
    assertNotEquals(currentVp, sharedVp);
    assertEquals(currentVp.getName(), sharedVp.getName());
    assertTrue(!scope.get(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES).contains(sharedVp));
    assertTrue(scope.get(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES).contains(currentVp));
  }

  public void externalNotLoadedReference() {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    EAnnotation annotation = createAnnotation(manager);
    Viewpoint sharedVp = getViewpoint();

    // Add reference from the current toward external viewpoint
    CapellaScope scope = new CapellaScope(annotation.eResource().getURI(), manager.getEditingDomain(), false);
    addReference(manager, scope, annotation, sharedVp);

    // As vp resource is not loaded in current resource set, Scope shall refer to the external one
    Viewpoint currentVp = getCurrentElement(manager.getEditingDomain().getResourceSet(), sharedVp);
    assertNull(currentVp);
    assertTrue(scope.get(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES).contains(sharedVp));
  }

  /**
   * Returns a loaded viewpoint
   */
  private Viewpoint getViewpoint() {
    Viewpoint vp = ViewpointRegistry.getInstance().getViewpoints().iterator().next();
    assertNotNull(vp);
    return vp;
  }

  /**
   * Add annotation reference towards the given element
   */
  private void addReference(ExecutionManager manager, CapellaScope scope, EAnnotation annotation,
      EObject referencedElement) {
    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        scope.add(annotation, EcorePackage.Literals.EANNOTATION__REFERENCES, referencedElement);
      }
    });
  }

  /**
   * For an element loaded in another resourceSet, load the corresponding one.
   * 
   * @implNote we assume that resource of sharedElement is already loaded in the current resource set
   */
  private <T extends EObject> T getCurrentElement(ResourceSet set, T sharedElement) {
    Resource rvp = set.getResource(sharedElement.eResource().getURI(), false);
    T object = null;
    if (rvp != null) {
      object = (T) rvp.getEObject(sharedElement.eResource().getURIFragment(sharedElement));
    }
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

  /**
   * Load the given resource into the current manager
   */
  private void loadResource(ExecutionManager manager, Resource sharedElement) {
    manager.execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        manager.getEditingDomain().getResourceSet().getResource(sharedElement.getURI(), true);
      }
    });
  }

}

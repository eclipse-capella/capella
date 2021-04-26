/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.fwk;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * This test ensures that contributePackageRegistry is working properly
 */
public class PackageRegistryTest extends MigrationContributionTest {

  static final String OWNER = "a";
  static final String ONE = "b";
  static final String TWO = "c";

  static final String EOBJECT_PROCEEDED = "A";
  
  @Override
  public void test() throws Exception {
    checkNoPackageRegistry();
    checkPackageRegistry();
  }

  /**
   * We create a basic model with some EObject in it
   */
  private Factory initModel(IFile file) {
    Factory f = new Factory(file);

    f.init((Resource res) -> {
      EObject owner = f.create("Owner", OWNER);
      EObject one = f.create("One", ONE);
      f.set(owner, "ownedOne", one);
      res.getContents().add(owner);
    });
   
    return f;
  }

  /**
   * When the package is not registered in the Package Registry, the EObject are loaded as AnyType.
   */
  private void checkNoPackageRegistry() throws Exception {
    IFile file = getFile("test.model");
    Factory f = initModel(file);

    addContribution(new TestMigrationContribution() {

      @Override
      public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
        if ("Owner".equals(currentElement.eClass().getName())) {
          assertTrue(currentElement instanceof AnyType);
          f.values.put(EOBJECT_PROCEEDED, true);
        }
      }

    });

    launchMigration(file);
    assertTrue(f.values.containsKey(EOBJECT_PROCEEDED));
  }
  
  /**
   * When the package is registered in the Package Registry, the EObject are properly loaded. 
   * (here as a DynamicEObject rather than an AnyType)
   */
  private void checkPackageRegistry() throws Exception {
    IFile file = getFile("test2.model");
    Factory f = initModel(file);

    addContribution(new TestMigrationContribution() {

      @Override
      public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {
        super.contributePackageRegistry(packageRegistry, context);
        packageRegistry.put(f.PKG.getNsURI(), f.PKG);
      }

      @Override
      public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
        if ("Owner".equals(currentElement.eClass().getName())) {
          assertTrue(currentElement instanceof DynamicEObjectImpl);
          f.values.put(EOBJECT_PROCEEDED, true);
        }
      }

    });

    launchMigration(file);

    // Ensure that migration contribution has been proceeded
    assertTrue(f.values.containsKey(EOBJECT_PROCEEDED));
  }
}

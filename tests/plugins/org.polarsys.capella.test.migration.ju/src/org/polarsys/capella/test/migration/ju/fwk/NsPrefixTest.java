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
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * This test ensures that if the nsPrefix has changed, then :
 * 
 * - If the new prefix is provided in the migration, then it save the new one
 * 
 * - If not provided, the older one is still used
 */
public class NsPrefixTest extends MigrationContributionTest {

  static final String OWNER = "a";
  static final String ONE = "b";
  static final String EXT = "e";

  @Override
  public void test() throws Exception {
    prefixNotChanged();
    prefixChanged();
  }

  /**
   * We create a model with a model reference to an extension, through a reference with an Abtract Type (that way, the
   * type to create will be read from the file)
   */
  private Factory initModel(IFile file) {
    Factory f = new Factory(file);

    f.init((Resource res) -> {
      EObject owner = f.create("Owner", OWNER);
      EObject one = f.create("One", ONE);
      EObject extone = f.create("ExtOne", EXT);
      f.set(owner, "ownedOne", one);
      f.set(one, "ownedAbstracts", extone);
      res.getContents().add(owner);
    });

    return f;
  }

  private void changeEPackageNsPrefix(Factory f) {

    f.execute((Resource res) -> {
      f.PKG_EXT.setNsPrefix("dummy");
    });

  }

  /**
   * When the package is registered in the Package Registry, the EObject are properly loaded. (here as a DynamicEObject
   * rather than an AnyType)
   */
  private void prefixNotChanged() throws Exception {
    IFile file = getFile("test4.capella");
    Factory f = initModel(file);

    // If after the model has been created, the nsPrefix is changed, if we don't take it into account in migration, the
    // old one is still used.
    changeEPackageNsPrefix(f);

    addContribution(new TestMigrationContribution() {

      @Override
      public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
        if (prefix.equals("extension")) {
          return f.PKG_EXT.getEFactoryInstance();
        }
        return super.getEFactory(prefix, resource, context);
      }

      @Override
      public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {
        super.contributePackageRegistry(packageRegistry, context);

        // We register the root one and the extension
        // (as we are dynamic, and not registered by plugin.xml) (on the new nsUri)
        packageRegistry.put(f.PKG.getNsURI(), f.PKG);
        packageRegistry.put(f.PKG_EXT.getNsURI(), f.PKG_EXT);
      }

    });

    launchMigration(file);
    XMLHelper helper = ((XMLHelper) f.values.get("XMLHelper"));
    assertTrue(helper.getPrefixToNamespaceMap().containsKey("extension"));
    assertTrue(!helper.getPrefixToNamespaceMap().containsKey(f.PKG_EXT.getNsPrefix()));

  }
  
  /**
   * When the package is registered in the Package Registry, the EObject are properly loaded. (here as a DynamicEObject
   * rather than an AnyType)
   */
  private void prefixChanged() throws Exception {
    IFile file = getFile("test3.capella");
    Factory f = initModel(file);

    // If after the model has been created, the nsPrefix of the package change,
    // we need to contribute a getNSPrefix to take it into account in model
    changeEPackageNsPrefix(f);

    addContribution(new TestMigrationContribution() {

      @Override
      public String getNSPrefix(String prefix, MigrationContext context) {
        if ("xmlns:extension".equals(prefix)) {
          return "xmlns:" + f.PKG_EXT.getNsPrefix();
        }
        return super.getNSPrefix(prefix, context);
      }

      @Override
      public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
        if (prefix.equals("extension")) {
          return f.PKG_EXT.getEFactoryInstance();
        }
        return super.getEFactory(prefix, resource, context);
      }

      @Override
      public void contributePackageRegistry(Registry packageRegistry, MigrationContext context) {
        super.contributePackageRegistry(packageRegistry, context);

        // We register the root one and the extension
        // (as we are dynamic, and not registered by plugin.xml) (on the new nsUri)
        packageRegistry.put(f.PKG.getNsURI(), f.PKG);
        packageRegistry.put(f.PKG_EXT.getNsURI(), f.PKG_EXT);
      }

    });

    launchMigration(file);
    f.reload();
    XMLHelper helper = ((XMLHelper) f.values.get("XMLHelper"));
    assertTrue(!helper.getPrefixToNamespaceMap().containsKey("extension"));
    assertTrue(helper.getPrefixToNamespaceMap().containsKey(f.PKG_EXT.getNsPrefix()));

  }

}

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
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.platform.sirius.ui.session.Messages;
import org.polarsys.capella.test.framework.helpers.log.StatusValidator;

/**
 * This test ensures that if a nsUri has changed but not the prefix (filtering viewpoint case):
 * 
 * - migration raises an error in case where no EFactory for the prefix is provided
 * 
 * - or succeed in case EFactory is provided
 */
public class EFactoryTest extends MigrationContributionTest {

  static final String OWNER = "a";
  static final String ONE = "b";
  static final String TWO = "c";
  static final String EXT = "e";

  @Override
  public void test() throws Exception {
    checkUncontributedNsUriChangedSamePrefixRaiseError();
    checkContributedNsUriChangedSamePrefix();
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

  private void changeEPackageNsURI(Factory f) {

    f.execute((Resource res) -> {
      f.set.getPackageRegistry().remove(f.PKG_EXT.getNsURI());
      f.PKG_EXT.setNsURI("dummy");
      f.set.getPackageRegistry().put(f.PKG_EXT.getNsURI(), f.PKG_EXT);
    });

  }

  private void checkUncontributedNsUriChangedSamePrefixRaiseError() throws Exception {
    IFile file = getFile("test.capella");
    Factory f = initModel(file);

    // If after the model has been created, the nsUri of the package change
    // then it shall raise an error if there is no getEFactory for the old extension prefix (from the file).
    changeEPackageNsURI(f);

    addContribution(new TestMigrationContribution() {

      @Override
      public EFactory getEFactory(String prefix, Resource resource, MigrationContext context) {
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

    StatusValidator removeSomething = new StatusValidator(s -> s.getMessage().contains(Messages.CapellaSessionHelper_MissingExtensions_Message));
    Platform.addLogListener(removeSomething);
    launchMigration(file);
    Platform.removeLogListener(removeSomething);
    assertTrue("Has reported error", removeSomething.isValid());

  }

  /**
   * We create a model with a model reference to an extension, through a reference with an AbtractType (that way, the
   * type to create will be read from the file)
   */
  private void checkContributedNsUriChangedSamePrefix() throws Exception {
    IFile file = getFile("test2.capella");
    Factory f = initModel(file);

    // If after the model has been created, the nsUri of the package change
    // then it shall raise an error if there is no getEFactory for the old extension prefix (from the file).
    changeEPackageNsURI(f);

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

    StatusValidator removeSomething = new StatusValidator(s -> s.getMessage().contains(Messages.CapellaSessionHelper_MissingExtensions_Message));
    Platform.addLogListener(removeSomething);
    launchMigration(file);
    Platform.removeLogListener(removeSomething);
    assertTrue("Has reported error but it shall not", !removeSomething.isValid());

    // The model is loaded afterwards
    f.test((Resource res) -> {
      EObject owner = res.getEObject(OWNER);
      assertTrue(owner != null);
    });
  }

}

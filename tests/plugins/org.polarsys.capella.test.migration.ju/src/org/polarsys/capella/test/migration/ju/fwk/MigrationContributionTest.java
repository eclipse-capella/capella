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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.common.utils.ReflectUtil;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.data.migration.contribution.IMigrationContribution;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class MigrationContributionTest extends BasicTestCase {

  private static final String PROJECTNAME = "test";

  /**
   * A IMigrationContribution that can be dynamically added / removed while tests
   */
  class TestMigrationContribution extends AbstractMigrationContribution {

  }

  @FunctionalInterface
  public interface Runnable {
    public abstract void run(Resource modelResource);
  }

  /**
   * This method allows to register a new IMigrationContribution while the migration. It will be removed at the end of
   * the test.
   */
  @SuppressWarnings("unchecked")
  protected void addContribution(TestMigrationContribution contribution) {
    try {
      ArrayList<IMigrationContribution> migrations = (ArrayList) ReflectUtil
          .getInvisibleFieldValue(MigrationHelpers.getInstance(), "migrations");
      migrations.removeIf(TestMigrationContribution.class::isInstance);
      migrations.add(contribution);
    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
      assertFalse("Can't register a contribution", true);
    }
  }

  @SuppressWarnings("unchecked")
  private void removeContributions() {
    try {
      ArrayList<IMigrationContribution> migrations = (ArrayList) ReflectUtil
          .getInvisibleFieldValue(MigrationHelpers.getInstance(), "migrations");
      migrations.removeIf(TestMigrationContribution.class::isInstance);
    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
      assertFalse("Can't cleanup contributions", true);
    }
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    removeContributions();
  }

  /**
   * A Factory allowing EObject manipulation of the Migration.ecore of the model folder of test.migration.ju plugin. As
   * its a small model, we use DynamicEObjects rather than generate EMF code for this ecore.
   */
  protected class Factory {
    HashMap<String, Object> values = new HashMap<String, Object>();

    ResourceSet set;
    XMIResource modelResource;
    ExecutionManager manager;

    EPackage PKG;
    EPackage PKG_EXT;

    Factory(IFile modelFile) {
      this.manager = ExecutionManagerRegistry.getInstance().addNewManager();
      this.set = manager.getEditingDomain().getResourceSet();

      set.setResourceFactoryRegistry(new ResourceFactoryRegistryImpl() {
        private Resource.Factory _factory = new ResourceFactoryImpl() {
          @Override
          public Resource createResource(URI uri2) {
            return new XMIResourceImpl(uri2) {

              @Override
              protected XMLLoad createXMLLoad() {
                XMLLoad load = super.createXMLLoad();
                values.put("XMLLoad", load);
                return load;
              }

              @Override
              protected XMLHelper createXMLHelper() {
                XMLHelper helper = super.createXMLHelper();
                values.put("XMLHelper", helper);
                return helper;
              }

              public String getID(EObject eObject) {
                return EcoreUtil.getID(eObject);
              }
            };
          }
        };

        @Override
        public Resource.Factory delegatedGetFactory(URI uri) {
          if (!"ecore".equals(uri.fileExtension())) {
            return _factory;
          }
          return Resource.Factory.Registry.INSTANCE.getFactory(uri);
        }
      });

      manager.execute(new AbstractReadWriteCommand() {

        @Override
        public void run() {
          URI uri = URI.createPlatformPluginURI("org.polarsys.capella.test.migration.ju/model/Migration.ecore", false);
          Resource ecore = manager.getEditingDomain().getResourceSet().getResource(uri, true);
          PKG = (EPackage) ecore.getContents().get(0);
          PKG_EXT = (EPackage) PKG.getESubpackages().get(0);

          set.getPackageRegistry().put(PKG.getNsURI(), PKG);
          for (EPackage child : PKG.getESubpackages()) {
            set.getPackageRegistry().put(child.getNsURI(), child);
          }

          modelResource = (XMIResource) set.createResource(EcoreUtil2.getURI(modelFile));
        }
      });
    }

    /**
     * Allows to do some EObject manipulation in the given model resource. The resource will be saved afterwards
     */
    void init(Runnable object) {
      execute((Resource res) -> {
        object.run(modelResource);
      });
      try {
        modelResource.save(null);
      } catch (IOException e) {
        e.printStackTrace();
        assertFalse(e.getMessage(), true);
      }
    }

    /**
     * Reload the resource from file and allows to do some tests on the given resource
     */
    public void test(Runnable object) {
      reload();
      object.run(modelResource);
    }

    public void reload() {
      execute((Resource res) -> {
        res.unload();
        set.getResources().remove(res);
        this.modelResource = (XMIResource) set.getResource(res.getURI(), true);
      });
    }

    /**
     * Retrieve the EClass of the given name from Migration.ecore
     */
    EClass getEClass(String eClass) {
      EClass clazz = (EClass) PKG.getEClassifier(eClass);
      Iterator<EPackage> childs = PKG.getESubpackages().iterator();
      while (clazz == null && childs.hasNext()) {
        EPackage child = childs.next();
        clazz = (EClass) child.getEClassifier(eClass);
      }
      return clazz;
    }

    /**
     * Create an EObject from the given eClass name and a given id
     */
    EObject create(String eClass, String id) {
      EClass clazz = getEClass(eClass);
      EObject result = clazz.getEPackage().getEFactoryInstance().create(clazz);
      set(result, "id", id);
      return result;
    }

    /**
     * Create an EObject from the given eClass name and a random id
     */
    EObject create(String eClass) {
      return create(eClass, IdGenerator.createId());
    }

    /**
     * Add/Set value on the object.eGet(featureName)
     */
    void set(EObject object, String featureName, Object value) {
      EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);
      if (feature.isMany()) {
        ((EList) object.eGet(feature)).add(value);
      } else {
        object.eSet(feature, value);
      }
    }

    /**
     * Add/Set value on the object.eGet(featureName)
     */
    void add(EObject object, String featureName, EObject value) {
      set(object, featureName, value);
    }

    /**
     * Remove value on the object.eGet(featureName)
     */
    void remove(EObject object, String featureName, EObject value) {
      EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);
      if (feature.isMany()) {
        ((EList) object.eGet(feature)).remove(value);
      } else {
        object.eUnset(feature);
      }
    }

    void execute(Runnable object) {
      manager.execute(new AbstractReadWriteCommand() {

        @Override
        public void run() {
          object.run(modelResource);
        }

      });
    }

  }

  /**
   * Return a file in a "test" project. Create the project if not exist
   */
  protected IFile getFile(String modelFile) {

    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECTNAME);
    if (!project.exists()) {
      try {
        project.create(new NullProgressMonitor());
      } catch (CoreException e) {
        // Nothing here
      }
    }
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException e) {
      e.printStackTrace();
    }
    return project.getFile(modelFile);

  }

  /**
   * The the migration on the given file.
   * 
   * @apiNote use a ModelMigrationRunnable, can be capella or other, not aird as it will not use the
   *          AirdMigrationRunnable dedicated to such files.
   */
  protected void launchMigration(IFile file) {

    ModelMigrationRunnable runnable = new ModelMigrationRunnable(file);
    MigrationContext context = new MigrationContext();
    context.setBackupModel(false);
    context.setSkipConfirmation(true);
    context.setProgressMonitor(new NullProgressMonitor());
    context.setShell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
    runnable.run(context, false);

  }
}

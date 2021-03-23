
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
package org.polarsys.capella.test.model.ju.propertyviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistryFactory;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.PropertiesTabDescriptor;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelFactory;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * Property Views test.
 */
public class PropertyViewsTest extends BasicTestCase {

  protected List<EClass> excludedClasses = Arrays.asList(InteractionPackage.Literals.SEQUENCE_MESSAGE_VALUATION,
      RePackage.Literals.GROUPING_ELEMENT_PKG, LibrariesPackage.Literals.MODEL_INFORMATION,
      LibrariesPackage.Literals.LIBRARY_REFERENCE, LibrariesPackage.Literals.MODEL_VERSION);

  @Override
  public void test() throws Exception {

    Collection<EClass> concreteClasses = getConcreteClasses(getAllCapellaPackages());
    concreteClasses.removeAll(excludedClasses);

    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    Collection<EObject> objects = createEObjectFromClasses(manager, concreteClasses);

    System.out.println("Concrete classes: " + concreteClasses.size());

    checkCustomPropertyViews(manager.getEditingDomain(), objects);
  }

  public void checkCustomPropertyViews(TransactionalEditingDomain domain, Collection<EObject> objects) {

    CapellaCommonNavigator part = (CapellaCommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().findView(CapellaCommonNavigator.ID);

    TabbedPropertyRegistry registry = TabbedPropertyRegistryFactory.getInstance().createRegistry(part);

    Collection<String> errors = new ArrayList<String>();

    for (EObject object : objects) {
      ITabDescriptor[] result = registry.getTabDescriptors(part, new StructuredSelection(object));

      // Retrieve sections from the "BaseCapella" xml registration (first tab of properties views) or for
      // REC/RPL/Libraries the programmatic one.
      Predicate<ITabDescriptor> descriptors = new Predicate<ITabDescriptor>() {

        @Override
        public boolean test(ITabDescriptor t) {
          return "BaseCapella".equals(t.getId()) || (isLibraryOrRec(object) && t instanceof PropertiesTabDescriptor);
        }
      };

      Optional<ITabDescriptor> capella = Arrays.asList(result).stream().filter(descriptors).findFirst();

      if (!capella.isPresent()) {
        errors.add(NLS.bind("No Property Tab 'Capella' for {0}", object.eClass().getInstanceTypeName()));
      } else {
        ITabDescriptor tab = capella.get();
        if (tab.getSectionDescriptors().isEmpty()) {
          errors.add(NLS.bind("Property Tab 'Capella' is empty for {0}", object.eClass().getInstanceTypeName()));
        }
        for (Object section : tab.getSectionDescriptors()) {
          ISectionDescriptor descriptor = (ISectionDescriptor) section;
          if (descriptor.getSectionClass() == null) {
            errors.add(NLS.bind("Property Capella Section no longer exist {0}", descriptor.getSectionClass()));
          }
        }
      }
    }

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }

  /**
   * Retrieve all EPackages for Capella metamodel (excluding layout junit test one)
   */
  private Collection<EPackage> getAllCapellaPackages() {
    return EPackage.Registry.INSTANCE.keySet().stream()
        .filter(x -> x.contains("capella") && !x.contains("org.polarsys.capella.layout"))
        .map(x -> EPackage.Registry.INSTANCE.getEPackage(x)).collect(Collectors.toList());
  }

  /**
   * Retrieve all non abstract classes for a list of EPackages
   */
  private Collection<EClass> getConcreteClasses(Collection<EPackage> packages) {

    ArrayList<EClass> concreteClasses = new ArrayList<EClass>(0);

    for (EPackage pkg : packages) {
      for (EClassifier clazz : pkg.getEClassifiers()) {
        if (clazz instanceof EClass) {
          if (!((EClass) clazz).isAbstract()) {
            concreteClasses.add((EClass) clazz);
          }
        }
      }
    }

    return concreteClasses;
  }

  /**
   * For all classes create an EObject stored somehow.
   * 
   * The classical property views work only for EObject owned by a model element (CapellaAdapterHelper.resolveEObject
   * returns null on 'invalid' model EObjects)
   * 
   * For REC/Library, this requirement is not applicable as they don't use "BaseCapella" properties.
   */
  private Collection<EObject> createEObjectFromClasses(ExecutionManager manager, Collection<EClass> classes) {

    Collection<EObject> result = new ArrayList<EObject>();
    manager.execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        GenericPkg object = SharedmodelFactory.eINSTANCE.createGenericPkg();
        Resource resource = HoldingResourceHelper.getHoldingResource(manager.getEditingDomain());
        HoldingResourceHelper.attachToHoldingResource(object, resource);

        for (EClass eclass : classes) {
          EObject s = eclass.getEPackage().getEFactoryInstance().create(eclass);
          if (s instanceof ModelElement) {
            object.getOwnedMigratedElements().add((ModelElement) s);

          } else if (s instanceof ElementExtension) {
            object.getOwnedExtensions().add((ElementExtension) s);

          } else if (!isLibraryOrRec(s)) {
            assertFalse("Can't store an element in model. This might be unexpected for new elements. "
                + eclass.getInstanceTypeName(), true);
          }
          result.add(s);
        }
      }

    });

    return result;
  }

  private boolean isLibraryOrRec(EObject object) {
    return object.eClass().getEPackage() == LibrariesPackage.eINSTANCE
        || object.eClass().getEPackage() == RePackage.eINSTANCE;
  }

}

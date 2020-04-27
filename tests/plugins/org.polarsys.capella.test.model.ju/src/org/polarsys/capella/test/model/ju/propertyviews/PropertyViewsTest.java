
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
package org.polarsys.capella.test.model.ju.propertyviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistryFactory;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Property Views test.
 */
public class PropertyViewsTest extends BasicTestCase {
  
  protected List<EClass> excludedClasses;

  protected List<EClass> concreteClasses;

  @Override
  public void test() throws Exception {
    excludedClasses = Arrays.asList(InteractionPackage.Literals.SEQUENCE_MESSAGE_VALUATION);
    init();
    testPropertyViewsForAll();
    clear();
  }

  /**
   * Test for all possible values for {@link Trace} and {@link TraceableElement}.
   */
  public void testPropertyViewsForAll() throws Exception {
    int globalCount = concreteClasses.size();
    System.out.println("Concrete classes: " + globalCount);

    int customCount = countCustomPropertyViews(concreteClasses, CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR);
    System.out.println("[Capella Navigator] Custom property views: " + customCount);
    System.out.println("[Capella Navigator] Generated property views: " + (globalCount - customCount));
  }

  public int countCustomPropertyViews(List<EClass> cls_p, String contributorId_p) {
    int counter = 0;
    
    IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .findView(CapellaCommonNavigator.ID);

    TabbedPropertyRegistry registry = TabbedPropertyRegistryFactory.getInstance().createRegistry(new ITabbedPropertySheetPageContributor() {
      
      @Override
      public String getContributorId() {
        return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
      }
    });
    
    Collection<String> errors = new ArrayList<String>();
    
    for (EClass eclass : cls_p) {
      EObject object = eclass.getEPackage().getEFactoryInstance().create(eclass);
      ITabDescriptor[] result = registry.getTabDescriptors(part, new StructuredSelection(object));
      
      Optional<ITabDescriptor> capella = Arrays.asList(result).stream().filter(x -> "BaseCapella".equals(x.getId())).findFirst();
      if (!capella.isPresent()) {
        errors.add(NLS.bind("No Property Tab 'Capella' for {0}", eclass.getName()+eclass.getEPackage().getNsPrefix()));
      } else {
        ITabDescriptor tab = capella.get();
        if (tab.getSectionDescriptors().isEmpty()) {
          errors.add(NLS.bind("Property Tab 'Capella' is empty for {0}", eclass.getName()+eclass.getEPackage().getNsPrefix()));
        }
        for (Object section : tab.getSectionDescriptors()) {
          ISectionDescriptor descriptor = (ISectionDescriptor)section;
          if (descriptor.getSectionClass() == null) {
            errors.add(NLS.bind("Property Capella Section no longer exist {0}", descriptor.getSectionClass()));
          }
        }
      }
    }
    
    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
    
    return counter;
  }

  private void init() {
    concreteClasses = new ArrayList<EClass>(0);
    // Get all contents.
    ResourceSet resourceSet = TestHelper.getEditingDomain().getResourceSet();
    TreeIterator<Notifier> contents = resourceSet.getAllContents();
    while (contents.hasNext()) {
      Notifier type = contents.next();
      // Retain class only.
      if (type instanceof EClass) {
        EClass classType = EcoreHelper.getStaticClass((EClass) type);
        if (!classType.isAbstract()) {
          concreteClasses.add(classType);
        }
      }
    }
    
    concreteClasses.removeAll(excludedClasses);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    ResourceSet resourceSet;
    final ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    resourceSet = manager.getEditingDomain().getResourceSet();

    resourceSet.getResource(
        FileHelper.getFileFullUri("/org.polarsys.capella.core.data.gen/model/CapellaModeller.ecore"), true);
    int resourcesCount = resourceSet.getResources().size();
    int previousResourcesCount = 0;
    // Make sure the meta-model is fully loaded.
    while (resourcesCount != previousResourcesCount) {
      previousResourcesCount = resourcesCount;
      Collection<Resource> resources = new ArrayList<Resource>(resourceSet.getResources());
      for (Resource resource : resources) {
        EcoreUtil.resolveAll(resource);
      }
      resourcesCount = resourceSet.getResources().size();
    }
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  private void clear() {
    concreteClasses.clear();
    concreteClasses = null;
  }
}

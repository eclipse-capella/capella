
/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.propertyviews;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.sections.IAbstractSection;
import org.polarsys.capella.core.ui.properties.wizards.CustomPropertyHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Property Views test.
 */
public class PropertyViewsTest extends BasicTestCase {
  protected List<EClass> _concreteClasses;

  @Override
  public void test() throws Exception {
    init();
    testPropertyViewsForAll();
    clear();
  }

  /**
   * Test for all possible values for {@link Trace} and {@link TraceableElement}.
   */
  private void testPropertyViewsForAll() throws Exception {
    int globalCount = _concreteClasses.size();
    System.out.println("Concrete classes: " + globalCount);

    int customCount = countCustomPropertyViews(_concreteClasses, CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR);
    System.out.println("[Capella Navigator] Custom property views: " + customCount);
    System.out.println("[Capella Navigator] Generated property views: " + (globalCount - customCount));
  }

  private int countCustomPropertyViews(List<EClass> cls_p, String contributorId_p) {
    int counter = 0;
    for (EClass eclass : cls_p) {
      Map<String, IAbstractSection> custom = CustomPropertyHelper.getCustomPropertySection(eclass, contributorId_p);
      if (!custom.isEmpty()) {
        counter++;
      } else {
        System.err.println(
            "[Error] EClass '" + eclass.getEPackage().getName() + "." + eclass.getName() + "' has no property view");
      }
    }
    return counter;
  }

  private void init() {
    _concreteClasses = new ArrayList<EClass>(0);
    // Get all contents.
    ResourceSet resourceSet = TestHelper.getEditingDomain().getResourceSet();
    TreeIterator<Notifier> contents = resourceSet.getAllContents();
    while (contents.hasNext()) {
      Notifier type = contents.next();
      // Retain class only.
      if (type instanceof EClass) {
        EClass classType = EcoreHelper.getStaticClass((EClass) type);
        if (!classType.isAbstract()) {
          _concreteClasses.add(classType);
        }
      }
    }
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
    _concreteClasses.clear();
    _concreteClasses = null;
  }
}

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
package org.polarsys.capella.test.model.ju.crossreferencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Abstract reflective cross referencer test.<br>
 * Loads the capella metamodel, and allows for creation of types dynamically.
 */
public abstract class AbstractReflectiveCrossReferencerTest extends BasicTestCase {
  /**
   * Map of (Root Type, Child Hierarchy for this Root Type).
   */
  protected HashMap<EClass, List<EClass>> _hierarchies;

  private ExecutionManager _executionManager;

  protected ExecutionManager getExecutionManager() {
    if (null == _executionManager) {
      _executionManager = ExecutionManagerRegistry.getInstance().addNewManager();
    }
    return _executionManager;
  }

  /**
   * Create an object of specified type.
   * 
   * @param type_p
   * @return
   */
  protected EObject createType(EClass type_p) {
    assertNotNull(type_p);
    // Get static java representation, so as to avoid dynamic emf instantiations.
    EClass type = EcoreHelper.getStaticClass(type_p);
    EObject result = type.getEPackage().getEFactoryInstance().create(type);
    assertNotNull(result);
    HoldingResourceHelper.getHoldingResource(getExecutionManager().getEditingDomain()).getContents().add(result);
    return result;
  }

  /**
   * Execute test command.
   * 
   * @param command_p
   */
  protected void executeTestCommand(ICommand command_p) {
    assertNotNull(command_p);
    getExecutionManager().execute(command_p);
  }

  /**
   * Get root types for which hierarchies will be computed.
   * 
   * @return
   */
  protected abstract List<EClass> getRootTypes();

  /**
   * Get specified root type children at specified index in flat hierarchy.<br>
   * Note that this requires that this root type is specified in {@link #getRootTypes()}.
   * 
   * @param rootType_p
   * @param index_p
   * @return
   */
  protected EClass getSubType(EClass rootType_p, int index_p) {
    List<EClass> hierarchy = _hierarchies.get(rootType_p);
    return ((null != hierarchy) && (index_p < hierarchy.size())) ? hierarchy.get(index_p) : null;
  }

  /**
   * Get specified root type children types count.<br>
   * Note that this requires that this root type is specified in {@link #getRootTypes()}.
   * 
   * @param rootType_p
   * @return
   */
  protected int getSubTypesCount(EClass rootType_p) {
    List<EClass> hierarchy = _hierarchies.get(rootType_p);
    return (null != hierarchy) ? hierarchy.size() : 0;
  }

  /**
   * Is specified type a super type in specified super types ?
   * 
   * @param type_p
   * @param superTypes_p
   * @return
   */
  protected boolean isSuperType(EClass type_p, Collection<EClass> superTypes_p) {
    boolean result = false;
    // Precondition.
    if ((null == superTypes_p) || (null == type_p)) {
      return result;
    }
    // Cycle through super types.
    for (EClass superType : superTypes_p) {
      // Test type against specified one.
      if (EcoreHelper.equals(type_p, superType, false)) {
        result = true;
        break;
      }
    }
    return result;
  }

  protected void postRunTest() {
    if (null != _hierarchies) {
      // Empty contained hierarchies.
      for (List<EClass> hierarchy : _hierarchies.values()) {
        hierarchy.clear();
      }
      // Empty and nullify map.
      _hierarchies.clear();
      _hierarchies = null;
    }
    ExecutionManagerRegistry.getInstance().removeManager(_executionManager);
  }

  protected void preRunTest() {
    loadM2();

    _hierarchies = new HashMap<EClass, List<EClass>>(0);
    // Get root types.
    List<EClass> roots = getRootTypes();
    // Precondition.
    if ((null == roots) || roots.isEmpty()) {
      return;
    }
    // Get all contents.
    ResourceSet resourceSet = getExecutionManager().getEditingDomain().getResourceSet();
    TreeIterator<Notifier> contents = resourceSet.getAllContents();
    while (contents.hasNext()) {
      Notifier type = contents.next();
      // Retain class only.
      if (type instanceof EClass) {
        EClass classType = (EClass) type;
        // Do not add abstract or depreciated types.
        if (!classType.isAbstract() && !isDeprecated(classType)) {
          EList<EClass> eAllSuperTypes = classType.getEAllSuperTypes();
          // Find containing collection.
          for (EClass root : roots) {
            // If root is a super type of current class (type), retain it.
            if (isSuperType(root, eAllSuperTypes)) {
              List<EClass> container = _hierarchies.get(root);
              if (null == container) {
                container = new ArrayList<EClass>(0);
                _hierarchies.put(root, container);
              }
              // Add to container.
              container.add(classType);
            }
          }
        }
      }
    }
  }

  /**
   * Remove specified object from its containing resource.
   * 
   * @param object_p
   */
  protected void removeFromResource(EObject object_p) {
    assertNotNull(object_p);
    // Assuming elements where created out of any resource.
    // Then they are added to the default capella resource, as root elements.
    if (object_p.eResource() != null)
      object_p.eResource().getContents().remove(object_p);
  }

  /**
   * Is specified EClass depreciated ?
   * 
   * @param eclass_p
   * @return
   */
  protected boolean isDeprecated(EClass eclass_p) {
    EAnnotation annotation = eclass_p.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
    if (annotation != null) {
      EMap<String, String> details = annotation.getDetails();
      String value = details.get("documentation");
      if ((value != null) && value.startsWith("@deprecated")) {
        return true;
      }
    }
    return false;
  }

  protected void loadM2() {
    ResourceSet resourceSet = getExecutionManager().getEditingDomain().getResourceSet();
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
  protected void setUp() throws Exception {
    super.setUp();
    preRunTest();
  }
}

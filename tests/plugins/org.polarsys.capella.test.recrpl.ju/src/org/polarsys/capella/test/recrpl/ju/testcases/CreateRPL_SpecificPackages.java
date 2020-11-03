/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.Activator;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.test.recrpl.ju.RecRplCommandManager;
import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

import com.google.common.collect.ImmutableMap;

/**
 * Creates a REC of elements defined by subclasses. Then instanciates a RPL from this REC, setting the
 * 'specific package' parent locator option. Subclasses then validate the RPL.
 */
public abstract class CreateRPL_SpecificPackages extends RecRplTestCase {

  final Map<EClass, EClass> test = ImmutableMap.<EClass, EClass>builder()

      .put(OaPackage.Literals.OPERATIONAL_ACTIVITY, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG)
      .put(OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG)
      .put(OaPackage.Literals.ENTITY, OaPackage.Literals.ENTITY_PKG)
      .put(OaPackage.Literals.ENTITY_PKG, OaPackage.Literals.ENTITY_PKG)
      .put(OaPackage.Literals.OPERATIONAL_CAPABILITY, OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG)
      .put(OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG, OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG)
      .put(OaPackage.Literals.ROLE, OaPackage.Literals.ROLE_PKG)
      .put(OaPackage.Literals.ROLE_PKG, OaPackage.Literals.ROLE_PKG)

      .put(CtxPackage.Literals.SYSTEM_FUNCTION, CtxPackage.Literals.SYSTEM_FUNCTION_PKG)
      .put(CtxPackage.Literals.SYSTEM_COMPONENT, CtxPackage.Literals.SYSTEM_COMPONENT_PKG)
      .put(CtxPackage.Literals.SYSTEM_COMPONENT_PKG, CtxPackage.Literals.SYSTEM_COMPONENT_PKG)
      .put(CtxPackage.Literals.SYSTEM_FUNCTION_PKG, CtxPackage.Literals.SYSTEM_FUNCTION_PKG)
      .put(CtxPackage.Literals.MISSION, CtxPackage.Literals.MISSION_PKG)
      .put(CtxPackage.Literals.MISSION_PKG, CtxPackage.Literals.MISSION_PKG)
      .put(CtxPackage.Literals.CAPABILITY, CtxPackage.Literals.CAPABILITY_PKG)
      .put(CtxPackage.Literals.CAPABILITY_PKG, CtxPackage.Literals.CAPABILITY_PKG)

      .put(LaPackage.Literals.LOGICAL_FUNCTION, LaPackage.Literals.LOGICAL_FUNCTION_PKG)
      .put(LaPackage.Literals.LOGICAL_FUNCTION_PKG, LaPackage.Literals.LOGICAL_FUNCTION_PKG)
      .put(LaPackage.Literals.LOGICAL_COMPONENT, LaPackage.Literals.LOGICAL_COMPONENT_PKG)
      .put(LaPackage.Literals.LOGICAL_COMPONENT_PKG, LaPackage.Literals.LOGICAL_COMPONENT_PKG)
      .put(LaPackage.Literals.CAPABILITY_REALIZATION, LaPackage.Literals.CAPABILITY_REALIZATION_PKG)
      .put(LaPackage.Literals.CAPABILITY_REALIZATION_PKG, LaPackage.Literals.CAPABILITY_REALIZATION_PKG)

      .put(PaPackage.Literals.PHYSICAL_FUNCTION, PaPackage.Literals.PHYSICAL_FUNCTION_PKG)
      .put(PaPackage.Literals.PHYSICAL_FUNCTION_PKG, PaPackage.Literals.PHYSICAL_FUNCTION_PKG)
      .put(PaPackage.Literals.PHYSICAL_COMPONENT, PaPackage.Literals.PHYSICAL_COMPONENT_PKG)
      .put(PaPackage.Literals.PHYSICAL_COMPONENT_PKG, PaPackage.Literals.PHYSICAL_COMPONENT_PKG)

      .put(CsPackage.Literals.INTERFACE_PKG, CsPackage.Literals.INTERFACE_PKG)
      .put(CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE_PKG)

      .put(CapellacommonPackage.Literals.CHANGE_EVENT, InformationPackage.Literals.DATA_PKG)
      .put(CapellacommonPackage.Literals.TIME_EVENT, InformationPackage.Literals.DATA_PKG)

      .build();

    CapellaModelSkeleton project;

    private ExecutionManager manager;
    Resource resource;

  @Override
  public void setUp() throws Exception {

    super.setUp();

    InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID).put(IReConstants.PROPERTY__PARENT_LOCATOR, IReConstants.LOCATOR_OPTION_SPECIFIC_PACKAGES);

    manager = ExecutionManagerRegistry.getInstance().addNewManager();
    project = new CapellaModelSkeleton.Builder(manager).build();
    resource = project.getProject().eResource();

  }


  @Override
  protected Resource getModelResource() {
    return resource;
  }

  @Override
  public void tearDown() throws Exception {
    ExecutionManagerRegistry.getInstance().removeManager(manager);

    InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID).put(IReConstants.PROPERTY__PARENT_LOCATOR, IReConstants.LOCATOR_OPTION_DEFAULT);

    super.tearDown();
  }

  @Override
  public void test() throws Exception {
    CatalogElement rec = createREC(getRecElements(project));
    RecRplCommandManager.push(IReConstants.PROPERTY__TARGET_NAME, "specificPkgRPL"); //$NON-NLS-1$
    CatalogElement rpl = createReplica(Collections.singleton(project.getSystemEngineering()), rec);

    verify(rpl);
  }

/**
 * Which elements should be part of the REC?
 * @param project
 * @return
 */
  protected abstract Collection<EObject> getRecElements(CapellaModelSkeleton project);

  /**
   * Verifies the Replica
   * @param replica
   */
  protected void verify(CatalogElement replica) {

    final Map<Map.Entry<EClass, EClass>, EObject> packages = new HashMap<Map.Entry<EClass, EClass>,EObject>();

    for (CatalogElementLink link : replica.getOwnedLinks()) {

      EObject target = link.getTarget();
      EClass expectedContainerClass = getExpectedContainerClass(target);

      if (expectedContainerClass != null) {

        // Is the target in a container of the expected type?
        EObject targetContainer = target.eContainer();

        // if the container is not itself in the REC (sometimes createRec implicitly adds children, for
        // example for a datatype, its min/max values are in the rec automatically)
        if (EObjectExt.getReferencers(targetContainer, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET).isEmpty()) {

          assertTrue(targetContainer.eClass() == expectedContainerClass);

          // The target and origin element should always be in the same block
          BlockArchitecture originBlock = BlockArchitectureExt.getRootBlockArchitecture(link.getOrigin().getTarget());
          BlockArchitecture targetBlock = BlockArchitectureExt.getRootBlockArchitecture(target);

          assertSame(originBlock, targetBlock);

          // There should be at most one specific package of a given type per block architecture
          Map.Entry<EClass, EClass> key = new SimpleImmutableEntry<EClass, EClass>(targetBlock.eClass(), targetContainer.eClass());
          EObject previous = packages.put(key, targetContainer);
          if (previous != null && !(previous instanceof ComponentPkg)) {
            assertSame(targetContainer, previous);
          }

          assertSame(getExpectedPackageContainer(targetBlock, targetContainer.eClass(), target), targetContainer.eContainer());
        }

      } else {
        fail("No package mapping provided for " + target);
      }

    }

  }

  protected EClass getExpectedContainerClass(EObject target) {

    // Here it depends if the EI is in a data pkg or an interface pkg..
    if (target instanceof ExchangeItem) {
      return getExpectedContainerClass(target.eContainer());
    } else if (target instanceof Part) {
      return getExpectedContainerClass(((Part) target).getAbstractType());
    } else if (target instanceof PhysicalLink) {
      return getExpectedContainerClass(target.eContainer());
    } else if (target instanceof ComponentExchange) {
      return getExpectedContainerClass(target.eContainer());
    }

    EClass result = test.get(target.eClass());

    if (result == null) {

      // not 100% sure, but should be close..
      if (target.eClass().getEPackage() == InformationPackage.eINSTANCE || target.eClass().getEPackage().getESuperPackage() == InformationPackage.eINSTANCE) {
        result = InformationPackage.Literals.DATA_PKG;
      }
    }

    return result;
  }

  protected EObject getExpectedPackageContainer(BlockArchitecture arch, EClass packageClass, EObject target) {

    if (packageClass == OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG) {
      OperationalActivityPkg pkg = (OperationalActivityPkg) ((OperationalAnalysis) arch).getOwnedFunctionPkg();
      return pkg.getOwnedOperationalActivities().get(0);
    }

    if (packageClass == OaPackage.Literals.ENTITY_PKG) {
      return ((OperationalAnalysis) arch).getOwnedEntityPkg();
    }

    if (packageClass == OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG) {
      return arch.getOwnedAbstractCapabilityPkg();
    }

    if (packageClass == OaPackage.Literals.ROLE_PKG) {
      return ((OperationalAnalysis) arch).getOwnedRolePkg();
    }

    if (packageClass == CtxPackage.Literals.SYSTEM_FUNCTION_PKG) {
      SystemFunctionPkg pkg = (SystemFunctionPkg) ((SystemAnalysis) arch).getOwnedFunctionPkg();
      return pkg.getOwnedSystemFunctions().get(0);
    }

    if (packageClass == CtxPackage.Literals.SYSTEM_COMPONENT_PKG) {
      Component root = ComponentExt.getRootComponent(target);
      if (root != null && BlockArchitectureExt.isRootComponent(root)) {
        return ((SystemAnalysis) arch).getSystem();
      }
      return ((SystemAnalysis) arch).getOwnedSystemComponentPkg();
    }

    if (packageClass == CtxPackage.Literals.MISSION_PKG) {
      return ((SystemAnalysis) arch).getOwnedMissionPkg();
    }

    if (packageClass == CtxPackage.Literals.CAPABILITY_PKG) {
      return ((SystemAnalysis) arch).getOwnedAbstractCapabilityPkg();
    }

    if (packageClass == LaPackage.Literals.LOGICAL_FUNCTION_PKG) {
      LogicalFunctionPkg pkg = (LogicalFunctionPkg) ((LogicalArchitecture) arch).getOwnedFunctionPkg();
      return pkg.getOwnedLogicalFunctions().get(0);
    }

    if (packageClass == LaPackage.Literals.LOGICAL_COMPONENT_PKG) {
      Component root = ComponentExt.getRootComponent(target);
      if (root != null && BlockArchitectureExt.isRootComponent(root)) {
        return ((LogicalArchitecture) arch).getSystem();
      }
      return ((LogicalArchitecture) arch).getOwnedLogicalComponentPkg();
    }

    if (packageClass == LaPackage.Literals.CAPABILITY_REALIZATION_PKG) {
      return arch.getOwnedAbstractCapabilityPkg();
    }

    if (packageClass == PaPackage.Literals.PHYSICAL_FUNCTION_PKG) {
      PhysicalFunctionPkg pkg = (PhysicalFunctionPkg) ((PhysicalArchitecture) arch).getOwnedFunctionPkg();
      return pkg.getOwnedPhysicalFunctions().get(0);
    }

    if (packageClass == PaPackage.Literals.PHYSICAL_COMPONENT_PKG) {
      Component root = ComponentExt.getRootComponent(target);
      if (root != null && BlockArchitectureExt.isRootComponent(root)) {
        return ((PhysicalArchitecture) arch).getSystem();
      }
      return ((PhysicalArchitecture) arch).getOwnedPhysicalComponentPkg();
    }

    if (packageClass == CsPackage.Literals.INTERFACE_PKG) {
      return arch.getOwnedInterfacePkg();
    }

    if (packageClass == InformationPackage.Literals.DATA_PKG) {
      return arch.getOwnedDataPkg();
    }

    fail("Unexpected package class");

    return null;
  }





}

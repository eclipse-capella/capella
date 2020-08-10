/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers.location;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.util.CapellacommonSwitch;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.util.CapellacoreSwitch;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.util.CsSwitch;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.ctx.util.CtxSwitch;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.util.FaSwitch;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.communication.util.CommunicationSwitch;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.util.DatatypeSwitch;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.util.DatavalueSwitch;
import org.polarsys.capella.core.data.information.util.InformationSwitch;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.util.LaSwitch;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.oa.util.OaSwitch;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.pa.util.PaSwitch;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.model.helpers.SystemAnalysisExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 * This class creates 'packages' for created rpl elements stores the packages in the model. It is guaranteed that only
 * one package is created for multiple invocations with elements that should go to the same package, but this is limited
 * to invocations on a single instance.
 */
public class SpecificPackageSupplierFactory {

  private final ComposedSwitch<Supplier<EObject>> zwitch;

  /* this stores already created packages so for each setting only one package per instance is created */
  private final Map<Map.Entry<EObject, EStructuralFeature>, Supplier<EObject>> createdSuppliers = new HashMap<Map.Entry<EObject, EStructuralFeature>, Supplier<EObject>>();

  private final Resource destinationResource;

  private BlockArchitecture destinationBlock;

  private EObject packagedElement;

  /**
   * @param destinationResource
   *          the resource in which we create packages; the resource in which the RPL will 'live'
   */
  public SpecificPackageSupplierFactory(Resource destinationResource) {
    this.destinationResource = destinationResource;

    Collection<Switch<Supplier<EObject>>> theSwitches = Arrays.asList(new Capellacommon(), new Capellacore(),
        new Communication(), new Cs(), new Ctx(), new Datatype(), new Datavalue(), new Fa(), new Information(),
        new La(), new Oa(), new Pa());

    zwitch = new ComposedSwitch<Supplier<EObject>>(theSwitches);
  }

  /**
   * Returns a supplier that will create and add a specific package that may serve as a container for the given element.
   * Not all elements are stored in specific packages, so this may also return null.
   *
   * @param packagedElement
   *          the element for which a container package is needed
   * @return a supplier or null for elements that are not added to specific packages
   */
  public Supplier<EObject> getSpecificPackageSupplier(EObject packagedElement) {
    this.destinationBlock = findDestinationBlock(packagedElement);
    this.packagedElement = packagedElement;
    return zwitch.doSwitch(packagedElement);
  }

  /**
   * Find the first BlockArchitecture in the destination resource that has the same class as the BlockArchitecture in
   * which packagedElement belongs to.
   */
  private BlockArchitecture findDestinationBlock(EObject packagedElement) {
    BlockArchitecture result = null;
    BlockArchitecture packagedElementBlock = BlockArchitectureExt.getRootBlockArchitecture(packagedElement);

    if (packagedElementBlock != null) {
      EClass clazz = packagedElementBlock.eClass();
      Resource resource = destinationResource;
      Project project = ProjectExt.getProject(resource);
      result = BlockArchitectureExt.getBlockArchitecture(clazz, project);
    }
    return result;
  }

  // feature type expected to be must be concrete and isMany must be true
  @SuppressWarnings("unchecked")
  private Supplier<EObject> getSpecificPackageSupplier(EObject container, EReference feature) {

    final Map.Entry<EObject, EStructuralFeature> key = new SimpleImmutableEntry<EObject, EStructuralFeature>(container,
        feature);
    Supplier<EObject> created = createdSuppliers.get(key);

    if (created == null) {

      created = new Supplier<EObject>() {
        EObject suppliedObject;

        @Override
        public EObject get() {
          if (suppliedObject == null) {
            suppliedObject = EcoreUtil.create(feature.getEReferenceType());
            ((Collection<EObject>) container.eGet(feature)).add(suppliedObject);
          }
          return suppliedObject;
        }
      };
      createdSuppliers.put(key, created);
    }
    return created;
  }

  class La extends LaSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseLogicalFunction(LogicalFunction object) {
      return getLogicalFunctionPkg();
    }

    @Override
    public Supplier<EObject> caseLogicalFunctionPkg(LogicalFunctionPkg object) {
      return getLogicalFunctionPkg();
    }

    @Override
    public Supplier<EObject> caseLogicalComponentPkg(LogicalComponentPkg object) {
      return getLogicalComponentPkg();
    }

    @Override
    public Supplier<EObject> caseLogicalComponent(LogicalComponent object) {
      return getLogicalComponentPkg();
    }

    @Override
    public Supplier<EObject> caseCapabilityRealization(CapabilityRealization object) {
      return getCapabilityRealizationPkg();
    }

    @Override
    public Supplier<EObject> caseCapabilityRealizationPkg(CapabilityRealizationPkg object) {
      return getCapabilityRealizationPkg();
    }

  }

  class Pa extends PaSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> casePhysicalFunction(PhysicalFunction object) {
      return getPhysicalFunctionPkg();
    }

    @Override
    public Supplier<EObject> casePhysicalFunctionPkg(PhysicalFunctionPkg object) {
      return getPhysicalFunctionPkg();
    }

    @Override
    public Supplier<EObject> casePhysicalComponent(PhysicalComponent object) {
      return getPhysicalComponentPkg();
    }

    @Override
    public Supplier<EObject> casePhysicalComponentPkg(PhysicalComponentPkg object) {
      return getPhysicalComponentPkg();
    }

  }

  class Fa extends FaSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseComponentExchange(ComponentExchange object) {
      Type blockArchitectureType = BlockArchitectureExt
          .getBlockArchitectureType(BlockArchitectureExt.getRootBlockArchitecture(object));
      if (blockArchitectureType == Type.OA) {
        return getEntityPkg();
      } else if (blockArchitectureType == Type.SA) {
        return getSystemComponentPkg();
      } else if (blockArchitectureType == Type.LA) {
        return getLogicalComponentPkg();
      } else if (blockArchitectureType == Type.PA) {
        return getPhysicalComponentPkg();
      }
      return super.caseComponentExchange(object);
    }

    @Override
    public Supplier<EObject> caseExchangeCategory(ExchangeCategory object) {
      // here it depends on the parent
      EObject container = object.eContainer();
      if (container != null) {
        return zwitch.doSwitch(container);
      }
      return null;
    }

  }

  class Oa extends OaSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseOperationalActivityPkg(OperationalActivityPkg object) {
      return getOperationalActivityPkg();
    }

    @Override
    public Supplier<EObject> caseOperationalActivity(OperationalActivity object) {
      return getOperationalActivityPkg();
    }

    @Override
    public Supplier<EObject> caseOperationalCapabilityPkg(OperationalCapabilityPkg object) {
      return getOperationalCapabilityPkg();
    }

    @Override
    public Supplier<EObject> caseOperationalCapability(OperationalCapability object) {
      return getOperationalCapabilityPkg();
    }

    @Override
    public Supplier<EObject> caseRolePkg(RolePkg object) {
      return getRolePkg();
    }

    @Override
    public Supplier<EObject> caseRole(Role object) {
      return getRolePkg();
    }

    @Override
    public Supplier<EObject> caseEntityPkg(EntityPkg object) {
      return getEntityPkg();
    }

    @Override
    public Supplier<EObject> caseEntity(Entity object) {
      return getEntityPkg();
    }

  }

  class Ctx extends CtxSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseSystemFunction(SystemFunction object) {
      return getSystemFunctionPkg();
    }

    @Override
    public Supplier<EObject> caseSystemFunctionPkg(SystemFunctionPkg object) {
      return getSystemFunctionPkg();
    }

    @Override
    public Supplier<EObject> caseSystemComponentPkg(SystemComponentPkg object) {
      return getSystemComponentPkg();
    }

    @Override
    public Supplier<EObject> caseSystemComponent(SystemComponent object) {
      return getSystemComponentPkg();
    }

    @Override
    public Supplier<EObject> caseMission(Mission object) {
      return getMissionPkg();
    }

    @Override
    public Supplier<EObject> caseMissionPkg(MissionPkg object) {
      return getMissionPkg();
    }

    @Override
    public Supplier<EObject> caseCapability(Capability object) {
      return getCapabilityPkg();
    }

    @Override
    public Supplier<EObject> caseCapabilityPkg(CapabilityPkg object) {
      return getCapabilityPkg();
    }

  }

  class Datatype extends DatatypeSwitch<Supplier<EObject>> {
    @Override
    public Supplier<EObject> caseDataType(DataType object) {
      return getDataPkg();
    }
  }

  class Datavalue extends DatavalueSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseDataValue(DataValue object) {
      return getDataPkg();
    }

  }

  class Communication extends CommunicationSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseCommunicationItem(CommunicationItem object) {
      return getDataPkg();
    }

  }

  class Cs extends CsSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseInterfacePkg(InterfacePkg object) {
      return getInterfacePkg();
    }

    @Override
    public Supplier<EObject> caseInterface(Interface object) {
      return getInterfacePkg();
    }

    @Override
    public Supplier<EObject> casePart(Part object) {
      Type blockArchitectureType = BlockArchitectureExt
          .getBlockArchitectureType(BlockArchitectureExt.getRootBlockArchitecture(object));
      if (blockArchitectureType == Type.OA) {
        return getEntityPkg();
      } else if (blockArchitectureType == Type.SA) {
        return getSystemComponentPkg();
      } else if (blockArchitectureType == Type.LA) {
        return getLogicalComponentPkg();
      } else if (blockArchitectureType == Type.PA) {
        return getPhysicalComponentPkg();
      }
      return super.casePart(object);
    }

    @Override
    public Supplier<EObject> casePhysicalLink(PhysicalLink object) {
      Type blockArchitectureType = BlockArchitectureExt
          .getBlockArchitectureType(BlockArchitectureExt.getRootBlockArchitecture(object));
      if (blockArchitectureType == Type.OA) {
        return getEntityPkg();
      } else if (blockArchitectureType == Type.SA) {
        return getSystemComponentPkg();
      } else if (blockArchitectureType == Type.LA) {
        return getLogicalComponentPkg();
      } else if (blockArchitectureType == Type.PA) {
        return getPhysicalComponentPkg();
      }
      return super.casePhysicalLink(object);
    }

  }

  class Information extends InformationSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseDataPkg(DataPkg object) {
      return getDataPkg();
    }

    @Override
    public Supplier<EObject> caseExchangeItem(ExchangeItem object) {
      // here it depends if the ei is in an interfacepkg or a datapkg
      EObject aep = EcoreUtil2.getFirstContainer(object, CapellacorePackage.Literals.ABSTRACT_EXCHANGE_ITEM_PKG);
      if (aep != null) {
        return zwitch.doSwitch(aep);
      }
      return null;
    }

    @Override
    public Supplier<EObject> caseDataValue(DataValue object) {
      return getDataPkg();
    }

    @Override
    public Supplier<EObject> caseClass(Class object) {
      return getDataPkg();
    }

    @Override
    public Supplier<EObject> caseCollection(org.polarsys.capella.core.data.information.Collection object) {
      return getDataPkg();
    }

    @Override
    public Supplier<EObject> caseKeyPart(KeyPart object) {
      return getDataPkg();
    }

    @Override
    public Supplier<EObject> caseUnion(Union object) {
      return getDataPkg();
    }

    @Override
    public Supplier<EObject> caseUnit(Unit object) {
      return getDataPkg();
    }

  }

  class Capellacommon extends CapellacommonSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseStateEvent(StateEvent object) {
      return getDataPkg();
    }

  }

  class Capellacore extends CapellacoreSwitch<Supplier<EObject>> {

    @Override
    public Supplier<EObject> caseAbstractPropertyValue(AbstractPropertyValue object) {
      return getPropertyValuePkg();
    }

    @Override
    public Supplier<EObject> casePropertyValueGroup(PropertyValueGroup object) {
      return getPropertyValuePkg();
    }

    @Override
    public Supplier<EObject> casePropertyValuePkg(PropertyValuePkg object) {
      return getPropertyValuePkg();
    }

    @Override
    public Supplier<EObject> caseEnumerationPropertyType(EnumerationPropertyType object) {
      return getPropertyValuePkg();
    }

  }

  private Supplier<EObject> getDataPkg() {
    System.out.println(destinationBlock.eClass().getName());
    DataPkg dataPkg = BlockArchitectureExt.getDataPkg(destinationBlock, true);
    return getSpecificPackageSupplier(dataPkg, InformationPackage.Literals.DATA_PKG__OWNED_DATA_PKGS);
  }

  private Supplier<EObject> getInterfacePkg() {
    InterfacePkg interfacePkg = BlockArchitectureExt.getInterfacePkg(destinationBlock, true);
    return getSpecificPackageSupplier(interfacePkg, CsPackage.Literals.INTERFACE_PKG__OWNED_INTERFACE_PKGS);
  }

  private Supplier<EObject> getSystemComponentPkg() {
    Component root = ComponentExt.getRootComponent(packagedElement);
    if (root != null && BlockArchitectureExt.isRootComponent(root)) {
      SystemComponent rootComponent = (SystemComponent) ((BlockArchitecture) destinationBlock).getSystem();
      return getSpecificPackageSupplier(rootComponent,
          CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS);
    }
    SystemComponentPkg componentPkg = (SystemComponentPkg) BlockArchitectureExt.getComponentPkg(destinationBlock, true);
    return getSpecificPackageSupplier(componentPkg,
        CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS);
  }

  private Supplier<EObject> getSystemFunctionPkg() {
    AbstractFunction rootFunction = BlockArchitectureExt.getRootFunction(destinationBlock, true);
    return getSpecificPackageSupplier(rootFunction, CtxPackage.Literals.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS);
  }

  private Supplier<EObject> getMissionPkg() {
    if (destinationBlock instanceof SystemAnalysis) {
      MissionPkg missionPkg = SystemAnalysisExt.getMissionPkg((SystemAnalysis) destinationBlock);
      return getSpecificPackageSupplier(missionPkg, CtxPackage.Literals.MISSION_PKG__OWNED_MISSION_PKGS);
    }
    return null;
  }

  private Supplier<EObject> getPhysicalComponentPkg() {
    Component root = ComponentExt.getRootComponent(packagedElement);
    if (root != null && BlockArchitectureExt.isRootComponent(root)) {
      PhysicalComponent rootComponent = (PhysicalComponent) ((BlockArchitecture) destinationBlock).getSystem();
      return getSpecificPackageSupplier(rootComponent,
          PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS);
    }
    PhysicalComponentPkg componentPkg = (PhysicalComponentPkg) BlockArchitectureExt.getComponentPkg(destinationBlock,
        true);
    return getSpecificPackageSupplier(componentPkg,
        PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS);
  }

  private Supplier<EObject> getPhysicalFunctionPkg() {
    AbstractFunction rootFunction = BlockArchitectureExt.getRootFunction(destinationBlock, true);
    return getSpecificPackageSupplier(rootFunction, PaPackage.Literals.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS);
  }

  private Supplier<EObject> getLogicalComponentPkg() {
    Component root = ComponentExt.getRootComponent(packagedElement);
    if (root != null && BlockArchitectureExt.isRootComponent(root)) {
      LogicalComponent rootComponent = (LogicalComponent) ((BlockArchitecture) destinationBlock).getSystem();
      return getSpecificPackageSupplier(rootComponent,
          LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS);
    }
    LogicalComponentPkg componentPkg = (LogicalComponentPkg) BlockArchitectureExt.getComponentPkg(destinationBlock,
        true);
    return getSpecificPackageSupplier(componentPkg,
        LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS);
  }

  private Supplier<EObject> getOperationalCapabilityPkg() {
    OperationalCapabilityPkg pkg = (OperationalCapabilityPkg) BlockArchitectureExt
        .getAbstractCapabilityPkg(destinationBlock, true);
    return getSpecificPackageSupplier(pkg,
        OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITY_PKGS);
  }

  private Supplier<EObject> getCapabilityPkg() {
    CapabilityPkg pkg = (CapabilityPkg) BlockArchitectureExt.getAbstractCapabilityPkg(destinationBlock, true);
    return getSpecificPackageSupplier(pkg, CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS);
  }

  private Supplier<EObject> getCapabilityRealizationPkg() {
    CapabilityRealizationPkg pkg = (CapabilityRealizationPkg) BlockArchitectureExt
        .getAbstractCapabilityPkg(destinationBlock, true);
    return getSpecificPackageSupplier(pkg,
        LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS);
  }

  private Supplier<EObject> getEntityPkg() {
    EntityPkg pkg = (EntityPkg) BlockArchitectureExt.getComponentPkg(destinationBlock, true);
    return getSpecificPackageSupplier(pkg, OaPackage.Literals.ENTITY_PKG__OWNED_ENTITY_PKGS);
  }

  private Supplier<EObject> getRolePkg() {
    RolePkg pkg = ((OperationalAnalysis) destinationBlock).getOwnedRolePkg();
    if (pkg == null) {
      // FIXME move this to OperationalAnalysisExt
      pkg = OaFactory.eINSTANCE.createRolePkg(NamingConstants.CreateOpAnalysisCmd_roles_pkg_name);
      ((OperationalAnalysis) destinationBlock).setOwnedRolePkg(pkg);
    }
    return getSpecificPackageSupplier(pkg, OaPackage.Literals.ROLE_PKG__OWNED_ROLE_PKGS);
  }

  private Supplier<EObject> getLogicalFunctionPkg() {
    AbstractFunction rootFunction = BlockArchitectureExt.getRootFunction(destinationBlock, true);
    return getSpecificPackageSupplier(rootFunction, LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS);
  }

  private Supplier<EObject> getOperationalActivityPkg() {
    AbstractFunction rootFunction = BlockArchitectureExt.getRootFunction(destinationBlock, true);
    return getSpecificPackageSupplier(rootFunction,
        OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS);
  }

  private Supplier<EObject> getPropertyValuePkg() {
    return getSpecificPackageSupplier(destinationBlock,
        CapellacorePackage.Literals.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS);
  }

}

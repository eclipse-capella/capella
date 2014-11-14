/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityPkgExt;
import org.polarsys.capella.core.data.helpers.la.services.CapabilityRealizationPkgExt;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * CapellaElement helpers
 */
public class CapellaElementExt {

  /**
   * 
   */
  public static List<Component> getComponentHierarchy(CapellaElement element_p) {
    List<Component> result = new ArrayList<Component>();

    if (null != element_p) {
      EObject owner = element_p.eContainer();
      if (owner instanceof Component) {
        result.add((Component) owner);
      }
      result.addAll(getComponentHierarchy((CapellaElement) owner));
    }

    return result;
  }

  /**
   * Checks if the specified two capella elements are is in the same decomposition.
   * @param element1_p The first element.
   * @param element2_p The second element
   * @return <code>True</code> if they are in the same decomposition else <code>false</code>.
   */
  public static boolean areInSameDecompositionAlternative(CapellaElement element1_p, CapellaElement element2_p) {
    boolean result = false;

    ComponentArchitecture arch1 = (ComponentArchitecture) EcoreUtil2.getFirstContainer(element1_p, CsPackage.Literals.COMPONENT_ARCHITECTURE);
    ComponentArchitecture arch2 = (ComponentArchitecture) EcoreUtil2.getFirstContainer(element2_p, CsPackage.Literals.COMPONENT_ARCHITECTURE);
    if (arch1 == arch2) {
      result = true;
    }

    return result;
  }

  /**
   * Removes traceability links from/to the given CapellaElement 'elt_p'
   * @param elt_p
   */
  static public void cleanTraces(CapellaElement elt_p) {
    if (elt_p != null) {

      List<AbstractTrace> traces = new ArrayList<AbstractTrace>();
      traces.addAll(elt_p.getOutgoingTraces());
      traces.addAll(elt_p.getIncomingTraces());
      Namespace ns = null;
      for (AbstractTrace trace : traces) {
        trace.setSourceElement(null);
        trace.setTargetElement(null);
        ns = (Namespace) trace.eContainer();
        ns.getOwnedTraces().remove(trace);
      }
    }
  }

  /**
   * Gets list of AspectPkgs in LogicalArchitecture (recursively from its LCs, LCPkgs.)
   * @param logArch_p the Logical Architecture
   * @return list of AspectPkg
   */
  static public List<AbstractCapabilityPkg> getAbstractCapabilityPkgs(LogicalArchitecture logArch_p) {
    List<AbstractCapabilityPkg> list = new ArrayList<AbstractCapabilityPkg>(1);
    if (null != logArch_p) {
      list.add(logArch_p.getOwnedAbstractCapabilityPkg());
      list.add(logArch_p.getOwnedLogicalComponent().getOwnedAbstractCapabilityPkg());
      list.addAll(getAbstractCapabilityPkgs(logArch_p.getOwnedLogicalComponentPkg()));
    }
    return list;
  }

  /**
   * Gets list of AspectPkgs in LogicalComponentPkg (recursively from its subLCPkgs, LCs.)
   * @param lcPkg_p the LogicalComponentPkg
   * @return list of AspectPkg
   */
  static public List<AbstractCapabilityPkg> getAbstractCapabilityPkgs(LogicalComponentPkg lcPkg_p) {
    List<AbstractCapabilityPkg> list = new ArrayList<AbstractCapabilityPkg>(1);
    if (null != lcPkg_p) {
      for (LogicalComponent alc : lcPkg_p.getOwnedLogicalComponents()) {
        list.add(alc.getOwnedAbstractCapabilityPkg());
      }
    }
    return list;
  }

  /**
   * Gets list of AspectPkgs in AbstractPhysicalComponent (recursively)
   * @param component_p the AbstractPhysicalComponent
   * @return list of AspectPkg
   */
  static public List<AbstractCapabilityPkg> getAbstractCapabilityPkgs(PhysicalComponent component_p) {
    List<AbstractCapabilityPkg> list = new ArrayList<AbstractCapabilityPkg>(1);
    if (null != component_p) {
      list.add(component_p.getOwnedAbstractCapabilityPkg());
      for (Partition part : ComponentExt.getSubParts(component_p)) {
        list.add(((Block) part.getType()).getOwnedAbstractCapabilityPkg());
      }
    }
    return list;
  }

  /**
   * Gets all the {@link CapabilitySpecificationUseCase} from AspectPkg
   * @param element_p the {@link CapellaElement}
   * @return list of CapabilitySpecificationUseCase
   */
  static public List<Capability> getAllCapabilities(CapellaElement element_p) {
    List<Capability> list = new ArrayList<Capability>();
    if (null != element_p) {
      AbstractCapabilityPkg aspectPkg = getOwnedAbstractCapabilityPkg(element_p);
      if (element_p instanceof SystemEngineering) {
        aspectPkg = getOwnedAbstractCapabilityPkg(SystemEngineeringExt.getOwnedSystemAnalysis((SystemEngineering) element_p));
      } else {
        aspectPkg = getOwnedAbstractCapabilityPkg(element_p);
      }

      if ((aspectPkg != null) && (aspectPkg instanceof CapabilityPkg)) {
        for (Capability capability : ((CapabilityPkg) aspectPkg).getOwnedCapabilities()) {
          if (null != capability) {
            list.add(capability);
          }
        }
        for (CapabilityPkg capPkg : ((CapabilityPkg) aspectPkg).getOwnedCapabilityPkgs()) {
          for (Capability capability : CapabilityPkgExt.getAllCapabilities(capPkg)) {
            if (null != capability) {
              list.add(capability);
            }
          }
        }
      }
    }
    return list;
  }

  /**
   * This method retrieves all the capability realizations contained by the current element.
   * @param currentElement_p The source element.
   * @return The capability realizations.
   */
  public static List<CapabilityRealization> getAllCapabilityRealization(CapellaElement currentElement_p) {
    Set<EObject> realSet = EObjectExt.getAll(currentElement_p, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> realList = new ArrayList<CapabilityRealization>();
    for (EObject obj : realSet) {
      realList.add((CapabilityRealization) obj);
    }
    return realList;
  }

  /**
   * Gets all the CapabilityRealizations involved with the current CapellaElement
   * @param element_p the CapellaElement
   * @return list of the CapabilityRealizations
   */
  static public List<CapabilityRealization> getAllCapabilityRealizationInvolvedWith(CapellaElement element_p) {
    List<CapabilityRealization> list = new ArrayList<CapabilityRealization>();
    if (null != element_p) {
      if (element_p instanceof LogicalComponent) {
        // if it is a logical component get all the realizations from
        // all the aspect pkgs 
        list.addAll(getAllCapabilityRealizationsFromAbstractCapabilityPkg(((LogicalComponent) element_p).getOwnedAbstractCapabilityPkg()));
      } else {
        AbstractCapabilityPkg aspectPkg = getOwnedAbstractCapabilityPkg(element_p);
        list.addAll(getAllCapabilityRealizationsFromAbstractCapabilityPkg(aspectPkg));
      }
    }
    return list;
  }

  /**
   * Gets list of {@link CapabilityRealizationUseCase} from AspectPkg (and its sub pkgs recursively)
   * @param aspectPkg_p the AspectPkg
   * @return list of CapabilityRealizationUseCase
   */
  public static List<CapabilityRealization> getAllCapabilityRealizationsFromAbstractCapabilityPkg(AbstractCapabilityPkg aspectPkg_p) {
    List<CapabilityRealization> list = new ArrayList<CapabilityRealization>();
    if ((aspectPkg_p != null) && (aspectPkg_p instanceof CapabilityRealizationPkg)) {
      for (CapabilityRealization realization : ((CapabilityRealizationPkg) aspectPkg_p).getOwnedCapabilityRealizations()) {
        if (null != realization) {
          list.add(realization);
        }
      }
      for (CapabilityRealizationPkg capRealizationPkg : ((CapabilityRealizationPkg) aspectPkg_p).getOwnedCapabilityRealizationPkgs()) {
        for (CapabilityRealization realization : CapabilityRealizationPkgExt.getAllCapabilityRealization(capRealizationPkg)) {
          if (null != realization) {
            list.add(realization);
          }
        }
      }
    }
    return list;
  }

  /**
   * Gets all the {@link Missions} from SystemEng
   * @param element_p the {@link CapellaElement}
   * @return list of CapabilitySpecificationUseCase
   */
  static public List<Mission> getAllMissions(SystemEngineering element_p) {
    List<Mission> list = new ArrayList<Mission>();
    if (null != element_p) {
      SystemAnalysis arch = SystemEngineeringExt.getOwnedSystemAnalysis(element_p);
      list = getAllMissionsFromSystemAnalysis(arch);

    }

    return list;
  }

  /**
   * @param ownedMissionPkg_p
   * @return list of missions
   */
  private static List<Mission> getAllMissionsFromMissionPkg(MissionPkg ownedMissionPkg_p) {

    List<Mission> list = new ArrayList<Mission>(1);
    if (null != ownedMissionPkg_p) {
      list.addAll(ownedMissionPkg_p.getOwnedMissions());
      for (MissionPkg subClassPkg : ownedMissionPkg_p.getOwnedMissionPkgs()) {
        list.addAll(getAllMissionsFromMissionPkg(subClassPkg));
      }
    }
    return list;
  }

  /**
   * @param arch_p
   * @return list of missions
   */
  private static List<Mission> getAllMissionsFromSystemAnalysis(SystemAnalysis arch_p) {
    List<Mission> list = new ArrayList<Mission>();
    MissionPkg ownedMissionPkg = arch_p.getOwnedMissionPkg();
    list = getAllMissionsFromMissionPkg(ownedMissionPkg);
    return list;
  }

  /**
   * Gets list of AspectPkgs in LogicalComponent (recursively from its subLCs, LCDcmps, LCPkgs.)
   * @param component_p the LogicalComponent
   * @return list of AspectPkg
   */
  static public List<AbstractCapabilityPkg> getAspectPkgs(LogicalComponent component_p) {
    List<AbstractCapabilityPkg> list = new ArrayList<AbstractCapabilityPkg>(1);
    if (null != component_p) {
      list.add(component_p.getOwnedAbstractCapabilityPkg());
      for (LogicalComponent alc : component_p.getSubLogicalComponents()) {
        list.addAll(getAspectPkgs(alc));
      }
      for (LogicalArchitecture logArch : component_p.getOwnedLogicalArchitectures()) {
        list.addAll(getAbstractCapabilityPkgs(logArch));
      }
      for (LogicalComponentPkg lcPkg : component_p.getOwnedLogicalComponentPkgs()) {
        list.addAll(getAbstractCapabilityPkgs(lcPkg));
      }
    }
    return list;
  }

  /**
   * Gets the full path from a named element.
   * @param elt_p the element which path will be calculated
   * @return the calculated path of the given element
   */
  public static String getFullPath(NamedElement elt_p) {
    return getRecursiveFullPath(elt_p.getName(), elt_p, null);
  }

  /**
   * Gets the full path from a named element to its Model container.
   * @return the calculated path of the given element
   */
  public static String getFullPathFromModel(NamedElement elt_p) {
    return getRecursiveFullPath(elt_p.getName(), elt_p, CapellamodellerPackage.Literals.MODEL_ROOT);
  }

  /**
   * Gets the full path from a named element to its Project container.
   * @return the calculated path of the given element
   */
  public static String getFullPathFromProject(NamedElement elt_p) {
    return getRecursiveFullPath(elt_p.getName(), elt_p, CapellamodellerPackage.Literals.PROJECT);
  }

  /**
   * Return inverseReferences Elements of and given EObject.<br>
   * The use of this method is not recommended, it may not work as you expect in Capella Team.
   * @param eObj : EObject
   * @return Collection<Setting>
   * @deprecated
   */
  @Deprecated
  public static Collection<Setting> getInverseReferencesOfEObject(EObject eObj) {
    // gets the Semantic Editing Domain
    SemanticEditingDomain semEditDomain = (SemanticEditingDomain) MDEAdapterFactory.getEditingDomain();
    // Gets the Cross Referencer
    ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObj);
    return inverseReferences;
  }

  static public String getName(EObject element_p) {
    String name = ICommonConstants.EMPTY_STRING;

    if (element_p != null) {
      if (element_p instanceof AbstractNamedElement) {
        name = ((AbstractNamedElement) element_p).getName();
      }
    }

    return name;
  }

  static public String getName(List<EObject> element_p) {
    String a = ICommonConstants.EMPTY_STRING;
    if (element_p.size() > 0) {
      a += getName(element_p.get(0));
    }
    for (int i = 1; i < element_p.size(); i++) {
      a += getName(element_p.get(0));
    }
    return a;
  }

  /**
   * Gets the aspect package of the capella element
   * @param element_p the {@link CapellaElement}
   * @return the {@link AspectPkg}
   */
  static public AbstractCapabilityPkg getOwnedAbstractCapabilityPkg(CapellaElement element_p) {
    AbstractCapabilityPkg aspectPkg = null;

    if (null != element_p) {

      if (element_p instanceof PhysicalComponent) {
        PhysicalArchitecture arch = SystemEngineeringExt.getPhysicalArchitecture(element_p);
        if (null != arch) {
          aspectPkg = arch.getOwnedAbstractCapabilityPkg();
        }
      } else if (element_p instanceof ConfigurationItem) {
        EPBSArchitecture arch = SystemEngineeringExt.getEPBSArchitecture(element_p);
        if (null != arch) {
          aspectPkg = arch.getOwnedAbstractCapabilityPkg();
        }
      } else if (element_p instanceof ComponentArchitecture) {
        aspectPkg = ((ComponentArchitecture) element_p).getOwnedAbstractCapabilityPkg();
      }
    }
    return aspectPkg;
  }

  /**
   * @param name_p
   * @param elt_p the element which path will be calculated
   * @param cls_p
   * @return the calculated path of the given element
   */
  private static String getRecursiveFullPath(String name_p, NamedElement elt_p, EClass cls_p) {
    EObject container = elt_p.eContainer();

    if ((cls_p != null) && (cls_p.isSuperTypeOf(container.eClass()))) {
      return ((NamedElement) container).getName() + "." + name_p; //$NON-NLS-1$
    } else if ((container != null) && (container instanceof NamedElement)) {
      return getRecursiveFullPath(((NamedElement) container).getName() + "." + name_p, (NamedElement) container, cls_p); //$NON-NLS-1$
    }

    return name_p;
  }

  /**
   *
   */
  private static CapellaElement getRecursiveRoot(CapellaElement elt_p, EClass cls_p) {
    EObject container = elt_p;
    if (container != null) {
      if ((cls_p != null) && (cls_p.isSuperTypeOf(container.eClass()))) {
        return (CapellaElement) container;
      }
      container = container.eContainer();
      if ((container != null) && (container instanceof CapellaElement)) {
        return getRecursiveRoot((CapellaElement) container, cls_p);
      }
    }
    return null;
  }

  /**
   * @param srcElement_p
   * @param eclass_p
   * @param linkOwner_p
   */
  public static CapellaElement getRefinementSrcElement(CapellaElement srcElement_p, EClass eclass_p, EObject linkOwner_p) {
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(srcElement_p, eclass_p)) {
      if (elt.eContainer().equals(linkOwner_p)) {
        return elt;
      }
    }
    return null;
  }

  /**
   * @param srcElement_p
   * @param eclass_p
   * @param linkOwner_p
   */
  public static List<CapellaElement> getRefinementSrcElements(CapellaElement srcElement_p, EClass eclass_p, EObject linkOwner_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(srcElement_p, eclass_p)) {
      if (elt.eContainer().equals(linkOwner_p)) {
        result.add(elt);
      }
    }
    return result;
  }

  /**
   * @param srcElement_p
   * @param eclass_p
   */
  public static CapellaElement getRefinementTgtElement(CapellaElement srcElement_p, EClass eclass_p) {
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedTargetElements(srcElement_p, eclass_p)) {
      return elt;
    }
    return null;
  }

  /**
   *
   */
  public static CapellaElement getRoot(CapellaElement elt_p) {
    return getRecursiveRoot(elt_p, CapellamodellerPackage.Literals.PROJECT);
  }

  /**
   * @param component_p
   * @return use BlockArchitectureExt.getRootBlockArchitecture instead
   */
  @Deprecated
  public static ModellingArchitecture getArchi(EObject e_p) {
    EObject e = e_p;
    while (!(e instanceof ModellingArchitecture)) {
      e = e.eContainer();
    }
    return (ModellingArchitecture) e;
  }

  public static boolean isLegalArchitecture(ModellingArchitecture interfArchi, ModellingArchitecture componentArchi) {
    EClass classes[] =
        { OaPackage.Literals.OPERATIONAL_ANALYSIS, CtxPackage.Literals.SYSTEM_ANALYSIS, LaPackage.Literals.LOGICAL_ARCHITECTURE,
         PaPackage.Literals.PHYSICAL_ARCHITECTURE, EpbsPackage.Literals.EPBS_ARCHITECTURE };

    int interfIndex = 0;
    int componentIndex = 0;
    for (int i = 0; i < classes.length; i++) {
      if (classes[i] == interfArchi.eClass()) {
        interfIndex = i;
      }
      if (classes[i] == componentArchi.eClass()) {
        componentIndex = i;
      }
    }
    return interfIndex <= componentIndex;
  }

  /**
   * The list of element is converted to element names with comma
   * @param element_p, list of capella element (assume AbstractNamedElements)
   * @return
   */
  public static String getElementNamesSeperatedByComma(List<?> element_p) {
    StringBuffer result = new StringBuffer();
    boolean first = true;
    for (Object object : element_p) {
      if (object instanceof AbstractNamedElement) {
        String eleName = ((AbstractNamedElement) object).getName();
        if (first) {
          result.append(eleName);
          first = false;
        } else {
          result.append(ICommonConstants.COMMA_CHARACTER);
          result.append(ICommonConstants.WHITE_SPACE_CHARACTER);
          result.append(eleName);
        }
      }
    }
    return result.toString();
  }

  public static EObject creationService(EObject context_p, String namingPrefix_p) {
    if (context_p instanceof ModelElement) {
      EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
      StrictCompoundCommand command = CreationHelper.getAdditionnalCommand(editingDomain, (ModelElement) context_p, namingPrefix_p);
      if (command.canExecute()) {
        command.execute();
      }
    }
    return context_p;
  }

  public static EObject creationService(EObject context_p) {
    return creationService(context_p, context_p.eClass().getName());
  }

  /**
   * Return Capella Explorer Label
   * @param object_p
   * @return String
   */
  public static String getCapellaExplorerLabel(EObject object_p) {
    return EObjectLabelProviderHelper.getText(object_p);
  }

  public static String getValidationRuleMessagePrefix(AbstractNamedElement ele_p) {
    if (null != ele_p) {
      return ele_p.getName() + " (" + ele_p.eClass().getName() + ") "; //$NON-NLS-1$//$NON-NLS-2$
    }

    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Return true if given trace is used in transaction (note:ArtitectureAllocaiton is filtered)
   * @param abstractTrace_p
   */
  public static boolean isValidTransitionTrace(AbstractTrace abstractTrace_p) {
    if (null == abstractTrace_p) {
      return false;
    }

    if (abstractTrace_p instanceof ComponentAllocation) {
      return true;
    } else if (abstractTrace_p instanceof FunctionRealization) {
      return true;
    } else if (abstractTrace_p instanceof InformationRealization) {
      return true;
    } else if (abstractTrace_p instanceof PortRealization) {
      return true;
    } else if (abstractTrace_p instanceof ScenarioRealization) {
      return true;
    } else if (abstractTrace_p instanceof StateTransitionRealization) {
      return true;
    } else if (abstractTrace_p instanceof FunctionalExchangeRealization) {
      return true;
    } else if (abstractTrace_p instanceof FunctionalChainRealization) {
      return true;
    } else if (abstractTrace_p instanceof ComponentExchangeRealization) {
      return true;
    } else if (abstractTrace_p instanceof ExchangeItemRealization) {
      return true;
    } else if (abstractTrace_p instanceof AbstractStateRealization) {
      return true;
    } else if (abstractTrace_p instanceof AbstractCapabilityRealization) {
      return true;
    } else if (abstractTrace_p instanceof LogicalInterfaceRealization) {
      return true;
    } else if (abstractTrace_p instanceof ContextInterfaceRealization) {
      return true;
    } else if (abstractTrace_p instanceof TransfoLink) {
      return true;
    }
    return false;
  }
}

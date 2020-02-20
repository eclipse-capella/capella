/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
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
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.utils.NamingHelper;

/**
 * CapellaElement helpers
 */
public class CapellaElementExt {

  /**
   * 
   */
  public static List<Component> getComponentHierarchy(CapellaElement element) {
    List<Component> result = new ArrayList<Component>();
    if (null != element) {
      EObject owner = element.eContainer();
      if (owner instanceof Component) {
        result.add((Component) owner);
      }
      result.addAll(getComponentHierarchy((CapellaElement) owner));
    }

    return result;
  }

  /**
   * Checks if the specified two capella elements are is in the same decomposition.
   * @param element1 The first element.
   * @param element2 The second element
   * @return <code>True</code> if they are in the same decomposition else <code>false</code>.
   */
  public static boolean areInSameDecompositionAlternative(CapellaElement element1, CapellaElement element2) {
    boolean result = false;

    ComponentArchitecture arch1 = (ComponentArchitecture) EcoreUtil2.getFirstContainer(element1, CsPackage.Literals.COMPONENT_ARCHITECTURE);
    ComponentArchitecture arch2 = (ComponentArchitecture) EcoreUtil2.getFirstContainer(element2, CsPackage.Literals.COMPONENT_ARCHITECTURE);
    if (arch1 == arch2) {
      result = true;
    }

    return result;
  }

  /**
   * Removes traceability links from/to the given CapellaElement 'elt'
   * @param elt
   */
  static public void cleanTraces(CapellaElement elt) {
    if (elt != null) {

      List<AbstractTrace> traces = new ArrayList<AbstractTrace>();
      traces.addAll(elt.getOutgoingTraces());
      traces.addAll(elt.getIncomingTraces());
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
   * Gets the aspect package of the capella element
   * @param element the {@link CapellaElement}
   * @return the {@link AspectPkg}
   */
  static public AbstractCapabilityPkg getOwnedAbstractCapabilityPkg(CapellaElement element) {
    AbstractCapabilityPkg aspectPkg = null;

    if (null != element) {

      if (element instanceof PhysicalComponent) {
        PhysicalArchitecture arch = SystemEngineeringExt.getPhysicalArchitecture(element);
        if (null != arch) {
          aspectPkg = arch.getOwnedAbstractCapabilityPkg();
        }
      } else if (element instanceof ConfigurationItem) {
        EPBSArchitecture arch = SystemEngineeringExt.getEPBSArchitecture(element);
        if (null != arch) {
          aspectPkg = arch.getOwnedAbstractCapabilityPkg();
        }
      } else if (element instanceof ComponentArchitecture) {
        aspectPkg = ((ComponentArchitecture) element).getOwnedAbstractCapabilityPkg();
      }
    }
    return aspectPkg;
  }

  /**
   * Gets all the {@link CapabilitySpecificationUseCase} from AspectPkg
   * @param element the {@link CapellaElement}
   * @return list of CapabilitySpecificationUseCase
   */
  static public List<Capability> getAllCapabilities(CapellaElement element) {
    List<Capability> list = new ArrayList<Capability>();
    if (null != element) {
      AbstractCapabilityPkg aspectPkg = getOwnedAbstractCapabilityPkg(element);
      if (element instanceof SystemEngineering) {
        aspectPkg = getOwnedAbstractCapabilityPkg(SystemEngineeringExt.getOwnedSystemAnalysis((SystemEngineering) element));
      } else {
        aspectPkg = getOwnedAbstractCapabilityPkg(element);
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
   * @param currentElement The source element.
   * @return The capability realizations.
   */
  public static List<CapabilityRealization> getAllCapabilityRealization(CapellaElement currentElement) {
    Set<EObject> realSet = EObjectExt.getAll(currentElement, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> realList = new ArrayList<CapabilityRealization>();
    for (EObject obj : realSet) {
      realList.add((CapabilityRealization) obj);
    }
    return realList;
  }

  /**
   * Gets all the CapabilityRealizations involved with the current CapellaElement
   * @param element the CapellaElement
   * @return list of the CapabilityRealizations
   */
  static public List<CapabilityRealization> getAllCapabilityRealizationInvolvedWith(CapellaElement element) {
    List<CapabilityRealization> list = new ArrayList<CapabilityRealization>();
    if (null != element) {
      if (element instanceof LogicalComponent) {
        // if it is a logical component get all the realizations from
        // all the aspect pkgs 
        list.addAll(getAllCapabilityRealizationsFromAbstractCapabilityPkg(((LogicalComponent) element).getOwnedAbstractCapabilityPkg()));
      } else {
        AbstractCapabilityPkg aspectPkg = getOwnedAbstractCapabilityPkg(element);
        list.addAll(getAllCapabilityRealizationsFromAbstractCapabilityPkg(aspectPkg));
      }
    }
    return list;
  }

  /**
   * Gets list of {@link CapabilityRealizationUseCase} from AspectPkg (and its sub pkgs recursively)
   * @param aspectPkg the AspectPkg
   * @return list of CapabilityRealizationUseCase
   */
  public static List<CapabilityRealization> getAllCapabilityRealizationsFromAbstractCapabilityPkg(AbstractCapabilityPkg aspectPkg) {
    List<CapabilityRealization> list = new ArrayList<CapabilityRealization>();
    if ((aspectPkg != null) && (aspectPkg instanceof CapabilityRealizationPkg)) {
      for (CapabilityRealization realization : ((CapabilityRealizationPkg) aspectPkg).getOwnedCapabilityRealizations()) {
        if (null != realization) {
          list.add(realization);
        }
      }
      for (CapabilityRealizationPkg capRealizationPkg : ((CapabilityRealizationPkg) aspectPkg).getOwnedCapabilityRealizationPkgs()) {
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
   * Gets the full path from a named element.
   * @param elt the element which path will be calculated
   * @return the calculated path of the given element
   */
  public static String getFullPath(NamedElement elt) {
    return getRecursiveFullPath(elt.getName(), elt, null);
  }

  /**
   * Gets the full path from a named element to its Model container.
   * @return the calculated path of the given element
   */
  public static String getFullPathFromModel(NamedElement elt) {
    return getRecursiveFullPath(elt.getName(), elt, CapellamodellerPackage.Literals.MODEL_ROOT);
  }

  /**
   * Gets the full path from a named element to its Project container.
   * @return the calculated path of the given element
   */
  public static String getFullPathFromProject(NamedElement elt) {
    return getRecursiveFullPath(elt.getName(), elt, CapellamodellerPackage.Literals.PROJECT);
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
    SemanticEditingDomain semEditDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(eObj);
    // Gets the Cross Referencer
    ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObj);
    return inverseReferences;
  }

  static public String getName(EObject element) {
    String name = ICommonConstants.EMPTY_STRING;

    if (element != null) {
      if (element instanceof AbstractNamedElement) {
        name = ((AbstractNamedElement) element).getName();
      }
    }

    return name;
  }

  static public String getName(List<EObject> elements) {
    StringBuilder builder = new StringBuilder();
    for(EObject element : elements){
      builder.append(getName(element));
      builder.append(" ");
    }
    return builder.toString();
  }

  /**
   * @param name
   * @param elt the element which path will be calculated
   * @param cls
   * @return the calculated path of the given element
   */
  private static String getRecursiveFullPath(String name, NamedElement elt, EClass cls) {
    EObject container = elt.eContainer();

    if ((cls != null) && (cls.isSuperTypeOf(container.eClass()))) {
      return ((NamedElement) container).getName() + "." + name; //$NON-NLS-1$
    } else if ((container != null) && (container instanceof NamedElement)) {
      return getRecursiveFullPath(((NamedElement) container).getName() + "." + name, (NamedElement) container, cls); //$NON-NLS-1$
    }

    return name;
  }

  /**
   * @param srcElement
   * @param eclass
   * @param linkOwner
   */
  public static CapellaElement getRefinementSrcElement(CapellaElement srcElement, EClass eclass, EObject linkOwner) {
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(srcElement, eclass)) {
      if (elt.eContainer().equals(linkOwner)) {
        return elt;
      }
    }
    return null;
  }

  /**
   * @param srcElement
   * @param eclass
   * @param linkOwner
   */
  public static List<CapellaElement> getRefinementSrcElements(CapellaElement srcElement, EClass eclass, EObject linkOwner) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(srcElement, eclass)) {
      if (elt.eContainer().equals(linkOwner)) {
        result.add(elt);
      }
    }
    return result;
  }

  /**
   * @param srcElement
   * @param eclass
   */
  public static CapellaElement getRefinementTgtElement(CapellaElement srcElement, EClass eclass) {
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedTargetElements(srcElement, eclass)) {
      return elt;
    }
    return null;
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
   * @param element, list of capella element (assume AbstractNamedElements)
   * @return
   */
  public static String getElementNamesSeperatedByComma(List<?> element) {
    StringBuffer result = new StringBuffer();
    boolean first = true;
    for (Object object : element) {
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

  public static EObject creationService(EObject context, String namingPrefix) {
    if (context instanceof ModelElement) {
      EditingDomain editingDomain = TransactionHelper.getEditingDomain(context);
      StrictCompoundCommand command = CreationHelper.getAdditionnalCommand(editingDomain, (ModelElement) context, namingPrefix);
      if (command.canExecute()) {
        command.execute();
      }
    }
    return context;
  }

  public static EObject creationService(EObject context) {
    if (context instanceof ModelElement) {
      EditingDomain editingDomain = TransactionHelper.getEditingDomain(context);
      StrictCompoundCommand command = CreationHelper.getAdditionnalCommand(editingDomain, (ModelElement) context);
      if (command.canExecute()) {
        command.execute();
      }
    }
    return context;
  }

  /**
   * Return Capella Explorer Label
   * @param object
   * @return String
   */
  public static String getCapellaExplorerLabel(EObject object) {
    return EObjectExt.getText(object);
  }

  public static String getValidationRuleMessagePrefix(AbstractNamedElement ele) {
    if (null != ele) {
      if (ele instanceof Component) {
        return ele.getName() + " " + NamingHelper.getTitleLabel(ele);
      }
      return ele.getName() + " (" + ele.eClass().getName() + ") "; //$NON-NLS-1$//$NON-NLS-2$
    }

    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Return true if given trace is used in transaction (note:ArtitectureAllocaiton is filtered)
   * @param abstractTrace
   */
  public static boolean isValidTransitionTrace(AbstractTrace abstractTrace) {
    if (null == abstractTrace) {
      return false;
    }

    if (abstractTrace instanceof ComponentRealization) {
      return true;
    } else if (abstractTrace instanceof FunctionRealization) {
      return true;
    } else if (abstractTrace instanceof InformationRealization) {
      return true;
    } else if (abstractTrace instanceof PortRealization) {
      return true;
    } else if (abstractTrace instanceof ScenarioRealization) {
      return true;
    } else if (abstractTrace instanceof StateTransitionRealization) {
      return true;
    } else if (abstractTrace instanceof FunctionalExchangeRealization) {
      return true;
    } else if (abstractTrace instanceof FunctionalChainRealization) {
      return true;
    } else if (abstractTrace instanceof ComponentExchangeRealization) {
      return true;
    } else if (abstractTrace instanceof ExchangeItemRealization) {
      return true;
    } else if (abstractTrace instanceof AbstractStateRealization) {
      return true;
    } else if (abstractTrace instanceof AbstractCapabilityRealization) {
      return true;
    } else if (abstractTrace instanceof LogicalInterfaceRealization) {
      return true;
    } else if (abstractTrace instanceof ContextInterfaceRealization) {
      return true;
    } else if (abstractTrace instanceof TransfoLink) {
      return true;
    }
    return false;
  }
}

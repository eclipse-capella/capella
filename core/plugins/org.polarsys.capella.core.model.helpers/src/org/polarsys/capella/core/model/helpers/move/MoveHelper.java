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
package org.polarsys.capella.core.model.helpers.move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;

/**
 * Moved from org.polarsys.capella.core.platform.sirius.ui.actions.CapellaPasteAction
 * This class checks if a list of elements can be moved into the target element
 */
public class MoveHelper {

  private static MoveHelper _instance;

  public static MoveHelper getInstance() {
    if (_instance == null) {
      _instance = new MoveHelper();
    }
    return _instance;
  }

  /**
   * Returns whether given elements_p can be moved into the specified targetElement_p
   * (check semantics and EMF rules)
   */
  public IStatus canMoveInto(List<EObject> elements_p, EObject targetElement_p) {
    IStatus isSemanticallyCorrect = checkSemanticRules(elements_p, targetElement_p);
    if (!isSemanticallyCorrect.isOK()) {
      return isSemanticallyCorrect;
    }

    // Checks all target eReferences compatibility with all selected model elements eClass.
    if (!checkEMFRules(elements_p, targetElement_p).isOK()) {
      return isSemanticallyCorrect;
    }
    return Status.OK_STATUS;
  }

  /**
   * @param selectedModelElements_p
   * @param targetElement_p
   * @return
   */
  public IStatus checkSemanticRules(List<EObject> selectedElements_p, EObject targetElement_p) {
    boolean result = true;

    for (EObject selectedElement : selectedElements_p) {
      if ((selectedElement instanceof ModelElement) && (targetElement_p instanceof ModelElement)) {
        ModelElement elt = (ModelElement) selectedElement;
        ModelElement targetElement = (ModelElement) targetElement_p;
        
        if ((elt instanceof FunctionPkg) && (targetElement instanceof FunctionPkg)) {
          result = areInSameLayer(elt, targetElement) && !(targetElement.eContainer() instanceof BlockArchitecture);
        } else if ((elt instanceof AbstractFunction) && (targetElement instanceof FunctionPkg)) {
          result = areInSameLayer(elt, targetElement) && !(targetElement.eContainer() instanceof BlockArchitecture);

        } else if ((elt instanceof LogicalComponentPkg) && (targetElement instanceof BlockArchitecture)) {
          // avoid dnd of pkg into architecture
          result = false;

        } else if ((elt instanceof PhysicalComponentPkg) && (targetElement instanceof BlockArchitecture)) {
          // avoid dnd of pkg into architecture
          result = false;

        } else if ((elt instanceof Component) && (elt.eContainer() instanceof BlockArchitecture)) {
          // Avoid dnd of root component
          result = false;

        } else if ((elt instanceof FunctionPkg) && (targetElement instanceof EPBSArchitecture)) {
          result = false;
        } else if ((elt instanceof InterfacePkg) && (targetElement instanceof EPBSArchitecture)) {
          result = false;
        } else if ((elt instanceof Capability) && !EcoreUtil2.isContainedBy(targetElement, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
          result = false;
        } else if ((elt instanceof CapabilityRealization) && EcoreUtil2.isContainedBy(targetElement, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
          result = false;
        } else if ((elt instanceof OperationalCapabilityPkg) && !EcoreUtil2.isContainedBy(targetElement, OaPackage.Literals.OPERATIONAL_ANALYSIS)) {
          result = false;
        } else if ((elt instanceof CapabilityPkg) && !EcoreUtil2.isContainedBy(targetElement, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
          result = false;
        } else if ((elt instanceof CapabilityRealizationPkg) && !EcoreUtil2.isContainedBy(targetElement, LaPackage.Literals.LOGICAL_ARCHITECTURE)
                   && !EcoreUtil2.isContainedBy(targetElement, PaPackage.Literals.PHYSICAL_ARCHITECTURE)
                   && !EcoreUtil2.isContainedBy(targetElement, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
          result = false;
        } else if ((elt instanceof AbstractFunction) && (targetElement instanceof AbstractFunction)) {
          if (!areInSameLayer(elt, targetElement)) {
            result = false;
          }
        } else if ((elt instanceof Component) && (targetElement instanceof Component)) {
          if (!areInSameLayer(elt, targetElement)) {
            result = false;
          }
        } else if ((elt instanceof Interface) && (targetElement instanceof InterfacePkg)) {
          result = isLegalInterfaceMode((Interface) elt, (InterfacePkg) targetElement);
        } else if ((elt instanceof Interface) && (targetElement instanceof Interface)) {
          result = isLegalInterfaceMode((Interface) elt, (Interface) targetElement);
        } else if (elt instanceof Part) {
          AbstractType type = ((Part) elt).getAbstractType();
          if (type != null) {
            if (type.equals(targetElement) || isDecomposedBy(targetElement, type)) {
              result = false;
            }
          }
        } else if (elt instanceof EnumerationLiteral) {
          result = targetElement instanceof Enumeration;
        }
      }
    }

    if (!result) {
      //We should explain why !
      return new Status(IStatus.ERROR, "model.helpers", "Semantic rules failed.");
    }
    return Status.OK_STATUS;
  }

  /**
   * @param type1
   * @param type2
   * @return
   */
  protected boolean isDecomposedBy(ModelElement type1, ModelElement type2) {
    for (EObject obj : EObjectExt.getReferencers(type1, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
      if (obj instanceof Part) {
        ModelElement cpnt = (ModelElement) obj.eContainer();
        if (cpnt != null) {
          if (cpnt.equals(type2)) {
            return true;
          }
          if (isDecomposedBy(cpnt, type2)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * @param elt_p
   * @param targetElement_p
   * @return
   */
  protected boolean isLegalInterfaceMode(Interface interf, EObject targetElement_p) {
    ModellingArchitecture iArchi = CapellaElementExt.getArchi(targetElement_p);
    for (Component component : interf.getUserComponents()) {
      ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }
    for (Component component : interf.getImplementorComponents()) {
      ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }

    // via component ports
    for (Component component : InterfaceExt.getRequireComponent(interf)) {
      ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }
    for (Component component : InterfaceExt.getProviderComponent(interf)) {
      ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }

    // if the interface is transited, it's false
    List<AbstractTrace> traces = new ArrayList<AbstractTrace>();
    traces.addAll(interf.getIncomingTraces());
    traces.addAll(interf.getOutgoingTraces());

    for (AbstractTrace abstractTrace : traces) {
      if (abstractTrace instanceof InterfaceAllocation) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if the specified two capella elements are is in the same layer.
   * @param element1_p The first element.
   * @param element2_p The second element
   * @return <code>True</code> if they are in the same layer else <code>false</code>.
   */
  protected boolean areInSameLayer(ModelElement element1_p, ModelElement element2_p) {
    BlockArchitecture arch1 = (BlockArchitecture) EcoreUtil2.getFirstContainer(element1_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
    BlockArchitecture arch2 = (BlockArchitecture) EcoreUtil2.getFirstContainer(element2_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
    if (arch1 == arch2) {
      return true;
    }
    if (arch1 == null) {
      // arch1 can be null when the object is a copy (clipboard) and have
      // no parent yet
      EPackage pkg = element1_p.eClass().getEPackage();
      if ((pkg.equals(OaPackage.eINSTANCE) && (arch2 instanceof OperationalAnalysis))
          || (pkg.equals(CtxPackage.eINSTANCE) && (arch2 instanceof SystemAnalysis))
          || (pkg.equals(LaPackage.eINSTANCE) && (arch2 instanceof LogicalArchitecture))
          || (pkg.equals(PaPackage.eINSTANCE) && (arch2 instanceof PhysicalArchitecture))
          || (pkg.equals(EpbsPackage.eINSTANCE) && (arch2 instanceof EPBSArchitecture))) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param selectedModelElements_p
   * @param targetElement_p
   */
  public IStatus checkEMFRules(List<EObject> selectedModelElements_p, EObject targetElement_p) {
    EList<EReference> allReferences = targetElement_p.eClass().getEAllContainments();
    boolean result = checkCompatibility(selectedModelElements_p, allReferences, targetElement_p);

    if (!result) {
      //We should explain why !
      return new Status(IStatus.ERROR, "model.helpers", "EMF rules failed.");
    }
    return Status.OK_STATUS;
  }

  /**
   * Checks the compatibility between all specified references and all specified model elements.
   */
  protected boolean checkCompatibility(List<EObject> modelElements_p, EList<EReference> references_p, EObject target_p) {
    boolean areCompatible = true;

    Iterator<EObject> elementsIterator = modelElements_p.iterator();
    while (elementsIterator.hasNext() && areCompatible) {
      EObject modelElement = elementsIterator.next();

      boolean isElementCompatible = false;
      Iterator<EReference> referencesIterator = references_p.iterator();
      while (referencesIterator.hasNext() && !isElementCompatible) {
        EReference reference = referencesIterator.next();
        if (reference.getEType().isInstance(modelElement) && (reference != modelElement) ) {
        	Integer upperBound=reference.getUpperBound();
          if (upperBound==-1 || (upperBound == 1 && target_p.eGet(reference)== null) && modelElements_p.size()<=upperBound){
              isElementCompatible = true;
			}
          else if (upperBound > 1){
			EObjectContainmentEList<EObject> contList= (EObjectContainmentEList<EObject>) target_p.eGet(reference);
        	  if (contList.size() < upperBound) {
              isElementCompatible = true;
        	  }
          }
        }
      }
      areCompatible &= isElementCompatible;
    }
    return areCompatible;
  }

}

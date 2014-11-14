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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.util.InformationSwitch;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaSwitch;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.util.ModellingcoreSwitch;

/**
 * This is a base abstract rule for naming conflicts rules
 */
public abstract class Abstract_MDCHK_NamingConflictRule extends AbstractValidationRule {

  // we check for conflicts via emf switch classes
  private CapellaSwitch<Boolean> capellaSwitch;
  private InformationConflictSwitch informationSwitch;
  private ModellingcoreConflictSwitch modellingcoreSwitch;

  public Abstract_MDCHK_NamingConflictRule() {
    capellaSwitch = new CapellaSwitch<Boolean>();
    informationSwitch = new InformationConflictSwitch();
    modellingcoreSwitch = new ModellingcoreConflictSwitch();
    capellaSwitch.setInformation(informationSwitch);
    capellaSwitch.setModellingcore(modellingcoreSwitch);
  }

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  /**
   * Type suffix for validation error console
   */
  private static final String TYPE_SUFFIX = ") "; //$NON-NLS-1$
  /**
   * Type prefix for validation error console
   */
  private static final String TYPE_PREFIX = " ("; //$NON-NLS-1$

  List<AbstractNamedElement> conflictCandidates = new ArrayList<AbstractNamedElement>();

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      // Do not check "naming conflicts" under scenarios and capabilities
      if ((eObj instanceof CapellaElement) && !((eObj instanceof AbstractCapability) || (eObj instanceof Scenario))) {
        // This collection will store the conflicts statuses
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        boolean hasConflict = false;
        // Creates a Map which will map each type with a list of the instances names of this type
        Map<EClass, List<AbstractNamedElement>> typesAndNames = new HashMap<EClass, List<AbstractNamedElement>>();
        CapellaElement elt = (CapellaElement) eObj;

        for (EObject obj : elt.eContents()) {
          // Iterates over the inner elements
          if ((eObj instanceof Parameter) && (obj instanceof LiteralNumericValue)) {
            // In this case, you don't check the name. Several <code>LiteralNumericValue</code> can have the same name
            // under a <code>Parameter</code>
          } else if (obj instanceof AbstractNamedElement) {
            if (isImpactedByCurrentRule(obj)) {
              AbstractNamedElement namedElement = (AbstractNamedElement) obj;
              // Gets the name and the type of the current element
              String currentElementName = namedElement.getName();
              if (!((namedElement instanceof DataValue) || ((null == currentElementName) || currentElementName.equalsIgnoreCase(ICommonConstants.EMPTY_STRING) || currentElementName
                  .equalsIgnoreCase("null") //$NON-NLS-1$
              ))) {

                if (checkTheNamingConflict(ctx_p, statuses, typesAndNames, elt, namedElement, currentElementName)) {
                  hasConflict = true;
                }
              }
            }
          }
        }

        typesAndNames.clear();

        if (hasConflict && !statuses.isEmpty()) {
          // There are conflicts
          // Returns them as a multi-statuses status
          return ConstraintStatus.createMultiStatus(ctx_p, statuses);
        }
      }
    }
    // No conflict found
    return ctx_p.createSuccessStatus();
  }

  /**
   * create failure message if any naming conflict
   * @param ctx_p
   * @param statuses
   * @param hasConflict
   * @param typesAndNames
   * @param elt
   * @param currentElementName
   * @param currentElementType
   * @return
   */
  private boolean checkTheNamingConflict(IValidationContext ctx_p, Collection<IStatus> statuses, Map<EClass, List<AbstractNamedElement>> typesAndNames,
      CapellaElement elt, AbstractNamedElement currentElement, String currentElementName) {
    boolean hasConflict = false;

    if (!typesAndNames.containsKey(currentElement.eClass())) {
      // This type doesn't have a map entry
      // Creates the map entry for the type
      List<AbstractNamedElement> elements = new ArrayList<AbstractNamedElement>();
      elements.add(currentElement);
      typesAndNames.put(currentElement.eClass(), elements);

    } else {
      // this type already has a map entry
      conflictCandidates = typesAndNames.get(currentElement.eClass());

      Set<AbstractNamedElement> conflictingElements = new HashSet<AbstractNamedElement>();

      for (AbstractNamedElement conflictCandidate : conflictCandidates) {

        if (currentElement instanceof ComponentExchange) {
          hasConflictComponentExchange(conflictingElements, conflictCandidate, (ComponentExchange) currentElement);

        } else if (currentElement instanceof FunctionalExchange) {
          hasConflictFunctionalExchange(conflictingElements, conflictCandidate, (FunctionalExchange) currentElement);

        } else if (currentElement instanceof PhysicalLink) {
          hasConflictPhysicalLink(conflictingElements, conflictCandidate, (PhysicalLink) currentElement);

        } else if (hasConflict(currentElement, conflictCandidates)) {
          conflictingElements.add(currentElement);

        }
      }

      if ((conflictingElements != null) && !conflictingElements.isEmpty()) {
        hasConflict = true;
        for (Object element : conflictingElements) {
          AbstractNamedElement abstractNamedElement = (AbstractNamedElement) element;
          // Creates a conflict status
          String currentConflict = TYPE_PREFIX + abstractNamedElement.eClass().getName() + TYPE_SUFFIX + currentElementName;
          @SuppressWarnings("deprecation")
          IStatus failureStatus =
              createFailureStatus(ctx_p, new Object[] { elt.getLabel(), TYPE_PREFIX + elt.eClass().getName() + TYPE_SUFFIX, currentConflict });
          statuses.add(failureStatus);
        }

      }

      conflictCandidates.add(currentElement);
    }
    return hasConflict;
  }

  /**
  * @param currentElementInner_p
  * @param elementComponentExchange_p
  */
  protected abstract void hasConflictComponentExchange(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement currentElementInner_p,
      ComponentExchange elementComponentExchange_p);

  /**
   * @param conflictingElements_p
   * @param currentElementInner_p
   * @param componentExchange_p
   */
  protected abstract void hasConflictFunctionalExchange(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement currentElementInner_p,
      FunctionalExchange componentExchange_p);

  /**
   * @param conflictingElements_p
   * @param currentElementInner_p
   * @param componentExchange_p
   */
  protected abstract void hasConflictPhysicalLink(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement currentElementInner_p,
      PhysicalLink componentExchange_p);

  /**
     * @param currentElement_p
     * @param existingElementsForTypes_p
     * @return w
     */
  @SuppressWarnings("boxing")
  protected boolean hasConflict(AbstractNamedElement currentElement_p, List<AbstractNamedElement> conflictCandidates_p) {

    // update the switch internal state rather than recreating a new one for each element
    modellingcoreSwitch.setConflictCandidates(conflictCandidates_p);
    informationSwitch.setConflictCandidates(conflictCandidates_p);

    Boolean result = capellaSwitch.doSwitch(currentElement_p);
    return result == null ? false : result;
  }

  /**
   * Tells if the given <code>EObject</code> is impacted by the ruleor not
   * @param eObj_p the <code>EObject</code>
   * @return <code>true</code> if the given element should be impacted by the rule, <code>false</code> otherwise.
   */
  protected abstract boolean isImpactedByCurrentRule(EObject eObj_p);

  // checks elements in the information package
  class InformationConflictSwitch extends InformationSwitch<Boolean> {

    private List<? extends AbstractNamedElement> conflictCandidates = Collections.emptyList();

    @Override
    /**
     * Two exchange items conflict, if they have the same name 
     * (ignoring case, see ModellingcoreConflictSwitch below)
     * and the number, type and order of owned exchange item elements are identical.
     */
    public Boolean caseExchangeItem(ExchangeItem exchangeItem_p) {
      outer: for (AbstractNamedElement candidate : conflictCandidates) {
        ExchangeItem candidateItem = (ExchangeItem) candidate;
        if (candidateItem.getName().equalsIgnoreCase(exchangeItem_p.getName())) {
          if (candidateItem.getOwnedElements().size() == exchangeItem_p.getOwnedElements().size()) {
            List<ExchangeItemElement> listA = exchangeItem_p.getOwnedElements();
            List<ExchangeItemElement> listZ = candidateItem.getOwnedElements();
            for (int i = 0; i < candidateItem.getOwnedElements().size(); i++) {
              ExchangeItemElement e1 = listA.get(i);
              ExchangeItemElement e2 = listZ.get(i);
              if (e1.getType() != e2.getType()) {
                continue outer;
              }
            }
            return Boolean.TRUE;
          }
        }
      }
      return Boolean.FALSE;
    }

    public void setConflictCandidates(List<? extends AbstractNamedElement> conflictCandidates_p) {
      this.conflictCandidates = conflictCandidates_p;
    }
  }

  // checks elements in the modellingcore package
  class ModellingcoreConflictSwitch extends ModellingcoreSwitch<Boolean> {

    private List<? extends AbstractNamedElement> conflictCandidates = Collections.emptyList();

    @Override
    /**
     *  The general rule for abstract named elements (in the same container):
     *  Name must be different, ignoring case..
     */
    public Boolean caseAbstractNamedElement(AbstractNamedElement element_p) {
      for (AbstractNamedElement existingElement : conflictCandidates) {
        if ((existingElement.getName() != null) && (element_p.getName() != null) && existingElement.getName().equalsIgnoreCase(element_p.getName())) {
          return Boolean.TRUE;
        }
      }
      return Boolean.FALSE;
    }

    /**
     * @param conflictCandidates_p
     */
    public void setConflictCandidates(List<? extends AbstractNamedElement> conflictCandidates_p) {
      this.conflictCandidates = conflictCandidates_p;
    }
  }

}

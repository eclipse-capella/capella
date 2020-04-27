/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.merge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl;
import org.eclipse.emf.diffmerge.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.impl.helpers.DiffOperation;
import org.eclipse.emf.diffmerge.impl.helpers.UnidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.util.IExpensiveOperation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.transition.common.policies.diff.IDiffPolicy2;
import org.polarsys.capella.core.transition.common.policies.merge.IMergePolicy2;

/**
 *
 */
public class ExtendedComparison extends EComparisonImpl {

  /**
   * An extension of the default copier that fixes the "add element + add children" dependency problem
   */
  protected class FixedUnidirectionalComparisonCopier extends UnidirectionalComparisonCopier {

    private static final long serialVersionUID = -3437137941200063000L;

    /**
     * Constructor
     * @param role either TARGET or REFERENCE
     */
    public FixedUnidirectionalComparisonCopier(Role role) {
      super(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject copy(EObject element) {
      if (_mergePolicy instanceof IMergePolicy2) {
        if (!((IMergePolicy2) _mergePolicy).copy(element)) {
          return element;
        }
      }
      return super.copy(element);
    }

    @Override
    protected void copyReference(EReference reference, EObject source, EObject destination) {
      // This implementation assumes that values need only be added

      List<EObject> sourceValues = _sourceScope.get(source, reference);

      for (EObject sourceValue : sourceValues) {
        IMatch valueMatch = _mapping.getMatchFor(sourceValue, _sourceRole);
        if (valueMatch != null) {
          // Value in scope
          // If value is in copier or ref is unidirectional, it is not handled
          // by a ref presence diff so it must be copied
          boolean mustCopy = getCompletedMatches().contains(valueMatch) ||
          // Being a containment means there is an implicit opposite
                             ((reference.getEOpposite() == null) && !reference.isContainment());
          if (!mustCopy) {
            // Otherwise, check if it is actually handled by a ref presence diff
            // (it may not be because the opposite ref may not be covered by the diff policy)
            IMatch holderMatch = _mapping.getMatchFor(source, _sourceRole);
            if (holderMatch != null) {
              mustCopy = holderMatch.getReferenceValueDifference(reference, source) == null;
            }
          }
          if (mustCopy && reference.isContainment()) {
            mustCopy = valueMatch.getOwnershipDifference(_sourceRole.opposite()) == null;
          }
          if (mustCopy) {
            EObject destinationValue = valueMatch.get(_sourceRole.opposite());
            if (destinationValue != null) {
              _destinationScope.add(destination, reference, destinationValue);
            }
          } // Else handled by a ref presence diff
        } else {
          // Value out of scope: keep as is if no side effect due to bidirectionality or containment
          if (useOriginalReferences && (reference.getEOpposite() == null) && !reference.isContainment() && !reference.isContainer()) {
            _destinationScope.add(destination, reference, sourceValue);
          }
        }
      }
    }

  }

  public ExtendedComparison(IEditableModelScope targetScope, IEditableModelScope referenceScope) {
    this(targetScope, referenceScope, null);
  }

  public ExtendedComparison(IEditableModelScope targetScope, IEditableModelScope referenceScope, IEditableModelScope ancestorScope) {
    super(targetScope, referenceScope, ancestorScope);

    setMapping(new EMappingImpl() {
      private BidirectionalComparisonCopier _copier = new BidirectionalComparisonCopier() {
        private UnidirectionalComparisonCopier _referenceToTargetCopier = new FixedUnidirectionalComparisonCopier(Role.REFERENCE);
        private UnidirectionalComparisonCopier _targetToReferenceCopier = new FixedUnidirectionalComparisonCopier(Role.TARGET);

        @Override
        public EObject completeMatch(IMapping.Editable mapping, IMatch partialMatch) {
          assert partialMatch.isPartial();
          Role sourceRole = partialMatch.getUncoveredRole().opposite();
          UnidirectionalComparisonCopier involvedCopier = (sourceRole == Role.REFERENCE) ? _referenceToTargetCopier : _targetToReferenceCopier;
          EObject result = involvedCopier.completeMatch(partialMatch, mapping.getComparison());
          return result;
        }

        @Override
        public void completeReferences(IMapping.Editable mapping, Role role) {
          UnidirectionalComparisonCopier involvedCopier = (role == Role.TARGET) ? _referenceToTargetCopier : _targetToReferenceCopier;
          involvedCopier.completeReferences(mapping.getComparison());
        }
      };

      /**
       * {@inheritDoc}
       */
      @Override
      public EObject completeMatch(IMatch partialMatch) {
        return _copier.completeMatch(this, partialMatch);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void completeReferences(Role role) {
        _copier.completeReferences(this, role);
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IExpensiveOperation getDiffOperation(IDiffPolicy iDiffPolicy1, IMergePolicy mergePolicy) {
    return new DiffOperation(this, iDiffPolicy1, mergePolicy) {
      
      protected void setElementPresenceDependencies(IElementPresence presence) {
        super.setElementPresenceDependencies(presence);
        ((IMergePolicy2)getMergePolicy()).setDependencies(presence);
      }
      
      @Override
      protected void setReferencedValueDependencies(IReferenceValuePresence presence) {
        super.setReferencedValueDependencies(presence);
        ((IMergePolicy2)getMergePolicy()).setDependencies(presence);
      }

      /**
       * Detect the differences related to the given reference for the given match
       * @param match a non-null, non-partial match
       * @param reference a non-null, non-container reference
       * @param create whether differences must actually be created
       * @return whether at least one difference was detected
       */
      @Override
      protected boolean detectReferenceDifferences(IMatch match, EReference reference, Role role1, Role role2,
          boolean create) {
        assert match != null && !match.isPartial() && reference != null;
        assert !reference.isContainer();
        boolean result = false;
        // Get reference values in different roles
        IFeaturedModelScope targetScope = getComparison().getScope(Role.TARGET);
        IFeaturedModelScope referenceScope = getComparison().getScope(Role.REFERENCE);
        EObject targetElement = match.get(Role.TARGET);
        EObject referenceElement = match.get(Role.REFERENCE);
        List<EObject> targetValues = targetScope.get(targetElement, reference);
        List<EObject> referenceValues = referenceScope.get(referenceElement, reference);
        List<EObject> remainingReferenceValues = new FArrayList<EObject>(
            referenceValues, IEqualityTester.BY_REFERENCE);
        boolean checkOrder = reference.isMany() && getDiffPolicy().considerOrdered(reference);
        int maxIndex = -1;
        // Check which ones match
        Map<IMatch, EObject> isolatedTargetMatches = new HashMap<IMatch, EObject>();
        for (EObject targetValue : targetValues) {
          // For every value in TARGET, get its corresponding match (if none, uncovered)
          IMatch targetValueMatch = getMapping().getMatchFor(targetValue, Role.TARGET);
          if (targetValueMatch != null) {
            // Get the matching value in REFERENCE
            EObject matchReference = targetValueMatch.get(Role.REFERENCE);
            boolean isIsolated = matchReference == null;
            if (!isIsolated) {
              // Check value presence and ordering
              int index = remainingReferenceValues.indexOf(matchReference);
              isIsolated = index < 0;
              if (checkOrder && !isIsolated) {
                if (index < maxIndex) {
                  // Ordering difference
                  if (!create)
                    return true;
                  createReferenceOrderDifference(match, reference, targetValue, targetValueMatch);
                  result = true;
                  checkOrder = false;
                } else {
                  maxIndex = index;
                }
              }
            }
            if (isIsolated)
              // None found or not in referenced values: mark as isolated
              isolatedTargetMatches.put(targetValueMatch, targetValue);
            else
              remainingReferenceValues.remove(matchReference);
          }
        }
        // For every remaining value in REFERENCE, get its corresponding isolated match
        // if the value is covered
        Map<IMatch, EObject> isolatedReferenceMatches = new HashMap<IMatch, EObject>();
        for (EObject remainingReferenceValue : remainingReferenceValues) {
          IMatch referenceValueMatch = getMapping().getMatchFor(
              remainingReferenceValue, Role.REFERENCE);
          if (referenceValueMatch != null)
            isolatedReferenceMatches.put(referenceValueMatch, remainingReferenceValue);      
        }

        IDiffPolicy diffPolicy = getDiffPolicy();
        
        // Create differences for isolated values
        for (Entry<IMatch, EObject> entry : isolatedTargetMatches.entrySet()) {
          IMatch isolatedTargetMatch = entry.getKey();
          EObject value = entry.getValue();
          if (diffPolicy instanceof IDiffPolicy2) {
            if (((IDiffPolicy2) diffPolicy).coverMatchOnReference(isolatedTargetMatch, reference)) {
              createReferenceValueDifference(match, reference, value, isolatedTargetMatch, Role.TARGET, false);
              result = true;
            }
          } else if (diffPolicy.coverMatch(isolatedTargetMatch)) {
            createReferenceValueDifference(match, reference, value, isolatedTargetMatch, Role.TARGET, false);
            result = true;
          }
        }
        for (Entry<IMatch, EObject> entry : isolatedReferenceMatches.entrySet()) {
          IMatch isolatedReferenceMatch = entry.getKey();
          EObject value = entry.getValue();
          if (((IDiffPolicy2) diffPolicy).coverMatchOnReference(isolatedReferenceMatch, reference)) {
            createReferenceValueDifference(match, reference, value, isolatedReferenceMatch, Role.REFERENCE, false);
            result = true;
          } else if (diffPolicy.coverMatch(isolatedReferenceMatch)) {
            createReferenceValueDifference(match, reference, value, isolatedReferenceMatch, Role.REFERENCE, false);
            result = true;
          }
        }
        
        return result;
      }
    };
  }
}

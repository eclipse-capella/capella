/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.generic.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.generic.impl.helpers.DiffOperation;
import org.eclipse.emf.diffmerge.generic.impl.helpers.UnidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
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
  protected class FixedUnidirectionalComparisonCopier extends UnidirectionalComparisonCopier<EObject> {

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
          _mapping.mapIncrementally(element, _sourceRole, element, _sourceRole.opposite());
          return element;
        }
      }
      return super.copy(element);
    }

    @Override
    protected void copyReference(Object reference, EObject source, EObject destination) {
      // This implementation assumes that values need only be added

      EReference eReference = (EReference) reference;
      List<EObject> sourceValues = _sourceScope.getReferenceValues(source, reference);

      for (EObject sourceValue : sourceValues) {
        IMatch<EObject> valueMatch = _mapping.getMatchFor(sourceValue, _sourceRole);
        if (valueMatch != null) {
          // Value in scope
          // If value is in copier or ref is unidirectional, it is not handled
          // by a ref presence diff so it must be copied
          boolean mustCopy = getCompletedMatches().contains(valueMatch) ||
          // Being a containment means there is an implicit opposite
                             ((eReference.getEOpposite() == null) && !eReference.isContainment());
          if (!mustCopy) {
            // Otherwise, check if it is actually handled by a ref presence diff
            // (it may not be because the opposite ref may not be covered by the diff policy)
            IMatch holderMatch = _mapping.getMatchFor(source, _sourceRole);
            if (holderMatch != null) {
              mustCopy = holderMatch.getReferenceValueDifference(eReference, source) == null;
            }
          }
          if (mustCopy && eReference.isContainment()) {
            mustCopy = valueMatch.getOwnershipDifference(_sourceRole.opposite()) == null;
          }
          if (mustCopy) {
            EObject destinationValue = valueMatch.get(_sourceRole.opposite());
            if (destinationValue != null) {
              _destinationScope.addReferenceValue(destination, eReference, destinationValue);
            }
          } // Else handled by a ref presence diff
        } else {
          // Value out of scope: keep as is if no side effect due to bidirectionality or containment
          if (eReference.getEOpposite() == null && !eReference.isContainment() && !eReference.isContainer()) {
            _destinationScope.addReferenceValue(destination, eReference, sourceValue);
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
      private BidirectionalComparisonCopier<EObject> _copier = new BidirectionalComparisonCopier<EObject>() {
        private UnidirectionalComparisonCopier<EObject> _referenceToTargetCopier = new FixedUnidirectionalComparisonCopier(Role.REFERENCE);
        private UnidirectionalComparisonCopier<EObject> _targetToReferenceCopier = new FixedUnidirectionalComparisonCopier(Role.TARGET);

        @Override
        public EObject completeMatch(IMapping.Editable mapping, IMatch partialMatch) {
          assert partialMatch.isPartial();
          Role sourceRole = partialMatch.getUncoveredRole().opposite();
          UnidirectionalComparisonCopier<EObject> involvedCopier = (sourceRole == Role.REFERENCE) ? _referenceToTargetCopier : _targetToReferenceCopier;
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
    return new DiffOperation<EObject>(this, iDiffPolicy1, mergePolicy) {
      
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
      protected boolean detectReferenceDifferences(IMatch<EObject> match, Object reference, Role role1, Role role2, Role roleOfReference,
          boolean create) {
        assert match != null && !match.isPartial() && reference != null;
        EReference eReference = (EReference) reference;
        assert !eReference.isContainer();
        boolean result = false;
        // Get reference values in different roles
        ITreeDataScope<EObject> targetScope = getComparison().getScope(Role.TARGET);
        ITreeDataScope<EObject> referenceScope = getComparison().getScope(Role.REFERENCE);
        EObject targetElement = match.get(Role.TARGET);
        EObject referenceElement = match.get(Role.REFERENCE);
        ITreeDataScope<EObject> scopeOfReference = (roleOfReference == Role.TARGET)? targetScope: referenceScope;
        List<EObject> targetValues = targetScope.getReferenceValues(targetElement, reference);
        List<EObject> referenceValues = referenceScope.getReferenceValues(referenceElement, reference);
        List<EObject> remainingReferenceValues = new FArrayList<EObject>(
            referenceValues, IEqualityTester.BY_REFERENCE);
        boolean checkOrder = eReference.isMany() && getDiffPolicy().considerOrderedReference(reference, scopeOfReference);
        int maxIndex = -1;
        // Check which ones match
        Map<IMatch, EObject> isolatedTargetMatches = new HashMap<IMatch, EObject>();
        for (EObject targetValue : targetValues) {
          // For every value in TARGET, get its corresponding match (if none, uncovered)
          IMatch<EObject> targetValueMatch = getMapping().getMatchFor(targetValue, Role.TARGET);
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
          IMatch<EObject> isolatedTargetMatch = entry.getKey();
          EObject value = entry.getValue();
          if (diffPolicy instanceof IDiffPolicy2) {
            if (((IDiffPolicy2) diffPolicy).coverMatchOnReference(isolatedTargetMatch, eReference)) {
              createReferenceValueDifference(match, reference, value, isolatedTargetMatch, Role.TARGET, false);
              result = true;
            }
          } else if (diffPolicy.coverMatch(isolatedTargetMatch)) {
            createReferenceValueDifference(match, reference, value, isolatedTargetMatch, Role.TARGET, false);
            result = true;
          }
        }
        for (Entry<IMatch, EObject> entry : isolatedReferenceMatches.entrySet()) {
          IMatch<EObject> isolatedReferenceMatch = entry.getKey();
          EObject value = entry.getValue();
          if (((IDiffPolicy2) diffPolicy).coverMatchOnReference(isolatedReferenceMatch, eReference)) {
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

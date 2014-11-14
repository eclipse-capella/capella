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
package org.polarsys.capella.core.transition.common.merge;

import java.util.List;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl;
import org.eclipse.emf.diffmerge.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.impl.helpers.DiffOperation;
import org.eclipse.emf.diffmerge.impl.helpers.UnidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.util.IExpensiveOperation;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
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
     * @param role_p either TARGET or REFERENCE
     */
    public FixedUnidirectionalComparisonCopier(Role role_p) {
      super(role_p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject copy(EObject element_p) {
      if (_mergePolicy instanceof IMergePolicy2) {
        if (!((IMergePolicy2) _mergePolicy).copy(element_p)) {
          return element_p;
        }
      }
      return super.copy(element_p);
    }

    @Override
    protected void copyReference(EReference reference_p, EObject source_p, EObject destination_p) {
      // This implementation assumes that values need only be added

      List<EObject> sourceValues = _sourceScope.get(source_p, reference_p);

      for (EObject sourceValue : sourceValues) {
        IMatch valueMatch = _mapping.getMatchFor(sourceValue, _sourceRole);
        if (valueMatch != null) {
          // Value in scope
          // If value is in copier or ref is unidirectional, it is not handled
          // by a ref presence diff so it must be copied
          boolean mustCopy = getCompletedMatches().contains(valueMatch) ||
          // Being a containment means there is an implicit opposite
                             ((reference_p.getEOpposite() == null) && !reference_p.isContainment());
          if (!mustCopy) {
            // Otherwise, check if it is actually handled by a ref presence diff
            // (it may not be because the opposite ref may not be covered by the diff policy)
            IMatch holderMatch = _mapping.getMatchFor(source_p, _sourceRole);
            if (holderMatch != null) {
              mustCopy = holderMatch.getReferenceValueDifference(reference_p, valueMatch) == null;
            }
          }
          if (mustCopy && reference_p.isContainment()) {
            mustCopy = valueMatch.getOwnershipDifference(_sourceRole.opposite()) == null;
          }
          if (mustCopy) {
            EObject destinationValue = valueMatch.get(_sourceRole.opposite());
            if (destinationValue != null) {
              _destinationScope.add(destination_p, reference_p, destinationValue);
            }
          } // Else handled by a ref presence diff
        } else {
          // Value out of scope: keep as is if no side effect due to bidirectionality or containment
          if (useOriginalReferences && (reference_p.getEOpposite() == null) && !reference_p.isContainment() && !reference_p.isContainer()) {
            _destinationScope.add(destination_p, reference_p, sourceValue);
          }
        }
      }
    }

  }

  public ExtendedComparison(IEditableModelScope targetScope_p, IEditableModelScope referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  public ExtendedComparison(IEditableModelScope targetScope_p, IEditableModelScope referenceScope_p, IEditableModelScope ancestorScope_p) {
    super(targetScope_p, referenceScope_p, ancestorScope_p);

    setMapping(new EMappingImpl() {
      private BidirectionalComparisonCopier _copier = new BidirectionalComparisonCopier() {
        private UnidirectionalComparisonCopier _referenceToTargetCopier = new FixedUnidirectionalComparisonCopier(Role.REFERENCE);
        private UnidirectionalComparisonCopier _targetToReferenceCopier = new FixedUnidirectionalComparisonCopier(Role.TARGET);

        @Override
        public EObject completeMatch(IMapping.Editable mapping_p, IMatch partialMatch_p) {
          assert partialMatch_p.isPartial();
          Role sourceRole = partialMatch_p.getUncoveredRole().opposite();
          UnidirectionalComparisonCopier involvedCopier = (sourceRole == Role.REFERENCE) ? _referenceToTargetCopier : _targetToReferenceCopier;
          EObject result = involvedCopier.completeMatch(partialMatch_p, mapping_p.getComparison());
          return result;
        }

        @Override
        public void completeReferences(IMapping.Editable mapping_p, Role role_p) {
          UnidirectionalComparisonCopier involvedCopier = (role_p == Role.TARGET) ? _referenceToTargetCopier : _targetToReferenceCopier;
          involvedCopier.completeReferences(mapping_p.getComparison());
        }
      };

      /**
       * {@inheritDoc}
       */
      @Override
      public EObject completeMatch(IMatch partialMatch_p) {
        return _copier.completeMatch(this, partialMatch_p);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void completeReferences(Role role_p) {
        _copier.completeReferences(this, role_p);
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IExpensiveOperation getDiffOperation(IDiffPolicy diffPolicy_p, IMergePolicy mergePolicy_p) {
    return new DiffOperation(this, diffPolicy_p, mergePolicy_p) {
      /**
       * Create the differences related to the given reference for the given match
       * @param match_p a non-null, non-partial match
       * @param reference_p a non-null, non-container reference
       */
      @Override
      protected void createReferenceDifferences(IMatch match_p, EReference reference_p) {
        assert (match_p != null) && !match_p.isPartial() && (reference_p != null);
        assert !reference_p.isContainer();
        // Get reference values in different roles
        IEditableModelScope targetScope = getComparison().getScope(Role.TARGET);
        IEditableModelScope referenceScope = getComparison().getScope(Role.REFERENCE);
        EObject targetElement = match_p.get(Role.TARGET);
        EObject referenceElement = match_p.get(Role.REFERENCE);
        List<EObject> targetValues = targetScope.get(targetElement, reference_p);
        List<EObject> referenceValues = referenceScope.get(referenceElement, reference_p);
        List<EObject> remainingReferenceValues = new FArrayList<EObject>(referenceValues, IEqualityTester.BY_REFERENCE);
        boolean checkOrder = reference_p.isMany() && getDiffPolicy().considerOrdered(reference_p);
        int maxIndex = -1;
        // Check which ones match
        List<IMatch> isolatedTargetMatches = new FArrayList<IMatch>();
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
                  createReferenceOrderDifference(match_p, reference_p, targetValueMatch);
                  checkOrder = false;
                } else {
                  maxIndex = index;
                }
              }
            }
            if (isIsolated) {
              // None found or not in referenced values: mark as isolated
              isolatedTargetMatches.add(targetValueMatch);
            } else {
              remainingReferenceValues.remove(matchReference);
            }
          }
        }
        // For every remaining value in REFERENCE, get its corresponding isolated match
        // if the value is covered
        List<IMatch> isolatedReferenceMatches = new FArrayList<IMatch>();
        for (EObject remainingReferenceValue : remainingReferenceValues) {
          IMatch referenceValueMatch = getMapping().getMatchFor(remainingReferenceValue, Role.REFERENCE);
          if (referenceValueMatch != null) {
            isolatedReferenceMatches.add(referenceValueMatch);
          }
        }

        IDiffPolicy diffPolicy = getDiffPolicy();

        // Create differences for isolated values
        for (IMatch isolatedTargetMatch : isolatedTargetMatches) {
          if (diffPolicy instanceof IDiffPolicy2) {
            if (((IDiffPolicy2) diffPolicy).coverMatchOnReference(isolatedTargetMatch, reference_p)) {
              createReferenceValueDifference(match_p, reference_p, isolatedTargetMatch, Role.TARGET, false);
            }
          } else if (diffPolicy.coverMatch(isolatedTargetMatch)) {
            createReferenceValueDifference(match_p, reference_p, isolatedTargetMatch, Role.TARGET, false);
          }
        }
        for (IMatch isolatedReferenceMatch : isolatedReferenceMatches) {
          if (((IDiffPolicy2) diffPolicy).coverMatchOnReference(isolatedReferenceMatch, reference_p)) {
            createReferenceValueDifference(match_p, reference_p, isolatedReferenceMatch, Role.REFERENCE, false);
          } else if (diffPolicy.coverMatch(isolatedReferenceMatch)) {
            createReferenceValueDifference(match_p, reference_p, isolatedReferenceMatch, Role.REFERENCE, false);
          }
        }
      }
    };
  }

}

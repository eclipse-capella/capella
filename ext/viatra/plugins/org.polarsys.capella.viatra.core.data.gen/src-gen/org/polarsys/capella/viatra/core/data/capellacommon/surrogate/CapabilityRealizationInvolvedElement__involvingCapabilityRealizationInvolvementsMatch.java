/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsQuerySpecification;

/**
 * Pattern-specific match representation of the org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements pattern,
 * to be used in conjunction with {@link CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher
 * @see CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch extends BasePatternMatch {
  private CapabilityRealizationInvolvedElement fSelf;
  
  private CapabilityRealizationInvolvement fTarget;
  
  private static List<String> parameterNames = makeImmutableList("self", "target");
  
  private CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    this.fSelf = pSelf;
    this.fTarget = pTarget;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("self".equals(parameterName)) return this.fSelf;
    if ("target".equals(parameterName)) return this.fTarget;
    return null;
  }
  
  public CapabilityRealizationInvolvedElement getSelf() {
    return this.fSelf;
  }
  
  public CapabilityRealizationInvolvement getTarget() {
    return this.fTarget;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("self".equals(parameterName) ) {
        this.fSelf = (CapabilityRealizationInvolvedElement) newValue;
        return true;
    }
    if ("target".equals(parameterName) ) {
        this.fTarget = (CapabilityRealizationInvolvement) newValue;
        return true;
    }
    return false;
  }
  
  public void setSelf(final CapabilityRealizationInvolvedElement pSelf) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelf = pSelf;
  }
  
  public void setTarget(final CapabilityRealizationInvolvement pTarget) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTarget = pTarget;
  }
  
  @Override
  public String patternName() {
    return "org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements";
  }
  
  @Override
  public List<String> parameterNames() {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSelf, fTarget};
  }
  
  @Override
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch toImmutable() {
    return isMutable() ? newMatch(fSelf, fTarget) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"self\"=" + prettyPrintValue(fSelf) + ", ");
    
    result.append("\"target\"=" + prettyPrintValue(fTarget)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSelf == null) ? 0 : fSelf.hashCode());
    result = prime * result + ((fTarget == null) ? 0 : fTarget.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch)) { // this should be infrequent
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof IPatternMatch)) {
            return false;
        }
        IPatternMatch otherSig  = (IPatternMatch) obj;
        if (!specification().equals(otherSig.specification()))
            return false;
        return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch other = (CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch) obj;
    if (fSelf == null) {if (other.fSelf != null) return false;}
    else if (!fSelf.equals(other.fSelf)) return false;
    if (fTarget == null) {if (other.fTarget != null) return false;}
    else if (!fTarget.equals(other.fTarget)) return false;
    return true;
  }
  
  @Override
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsQuerySpecification specification() {
    try {
        return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsQuerySpecification.instance();
    } catch (ViatraQueryException ex) {
         // This cannot happen, as the match object can only be instantiated if the query specification exists
         throw new IllegalStateException (ex);
    }
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch newMutableMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    return new Mutable(pSelf, pTarget);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch newMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    return new Immutable(pSelf, pTarget);
  }
  
  private static final class Mutable extends CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch {
    Mutable(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
      super(pSelf, pTarget);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch {
    Immutable(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
      super(pSelf, pTarget);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}

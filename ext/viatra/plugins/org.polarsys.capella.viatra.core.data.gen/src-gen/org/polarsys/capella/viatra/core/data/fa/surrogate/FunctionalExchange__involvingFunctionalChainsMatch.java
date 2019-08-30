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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__involvingFunctionalChainsQuerySpecification;

/**
 * Pattern-specific match representation of the org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__involvingFunctionalChains pattern,
 * to be used in conjunction with {@link FunctionalExchange__involvingFunctionalChainsMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see FunctionalExchange__involvingFunctionalChainsMatcher
 * @see FunctionalExchange__involvingFunctionalChainsProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class FunctionalExchange__involvingFunctionalChainsMatch extends BasePatternMatch {
  private FunctionalExchange fSelf;
  
  private FunctionalChain fTarget;
  
  private static List<String> parameterNames = makeImmutableList("self", "target");
  
  private FunctionalExchange__involvingFunctionalChainsMatch(final FunctionalExchange pSelf, final FunctionalChain pTarget) {
    this.fSelf = pSelf;
    this.fTarget = pTarget;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("self".equals(parameterName)) return this.fSelf;
    if ("target".equals(parameterName)) return this.fTarget;
    return null;
  }
  
  public FunctionalExchange getSelf() {
    return this.fSelf;
  }
  
  public FunctionalChain getTarget() {
    return this.fTarget;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("self".equals(parameterName) ) {
        this.fSelf = (FunctionalExchange) newValue;
        return true;
    }
    if ("target".equals(parameterName) ) {
        this.fTarget = (FunctionalChain) newValue;
        return true;
    }
    return false;
  }
  
  public void setSelf(final FunctionalExchange pSelf) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSelf = pSelf;
  }
  
  public void setTarget(final FunctionalChain pTarget) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTarget = pTarget;
  }
  
  @Override
  public String patternName() {
    return "org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__involvingFunctionalChains";
  }
  
  @Override
  public List<String> parameterNames() {
    return FunctionalExchange__involvingFunctionalChainsMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSelf, fTarget};
  }
  
  @Override
  public FunctionalExchange__involvingFunctionalChainsMatch toImmutable() {
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
    if (!(obj instanceof FunctionalExchange__involvingFunctionalChainsMatch)) { // this should be infrequent
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
    FunctionalExchange__involvingFunctionalChainsMatch other = (FunctionalExchange__involvingFunctionalChainsMatch) obj;
    if (fSelf == null) {if (other.fSelf != null) return false;}
    else if (!fSelf.equals(other.fSelf)) return false;
    if (fTarget == null) {if (other.fTarget != null) return false;}
    else if (!fTarget.equals(other.fTarget)) return false;
    return true;
  }
  
  @Override
  public FunctionalExchange__involvingFunctionalChainsQuerySpecification specification() {
    try {
        return FunctionalExchange__involvingFunctionalChainsQuerySpecification.instance();
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
  public static FunctionalExchange__involvingFunctionalChainsMatch newEmptyMatch() {
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
  public static FunctionalExchange__involvingFunctionalChainsMatch newMutableMatch(final FunctionalExchange pSelf, final FunctionalChain pTarget) {
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
  public static FunctionalExchange__involvingFunctionalChainsMatch newMatch(final FunctionalExchange pSelf, final FunctionalChain pTarget) {
    return new Immutable(pSelf, pTarget);
  }
  
  private static final class Mutable extends FunctionalExchange__involvingFunctionalChainsMatch {
    Mutable(final FunctionalExchange pSelf, final FunctionalChain pTarget) {
      super(pSelf, pTarget);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends FunctionalExchange__involvingFunctionalChainsMatch {
    Immutable(final FunctionalExchange pSelf, final FunctionalChain pTarget) {
      super(pSelf, pTarget);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}

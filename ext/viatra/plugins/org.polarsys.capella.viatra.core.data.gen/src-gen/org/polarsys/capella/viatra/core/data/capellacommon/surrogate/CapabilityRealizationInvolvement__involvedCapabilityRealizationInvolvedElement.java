/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.api.scope.QueryScope;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.ViatraQueryRuntimeException;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         // End of user code
 *         
 *         {@literal @}Surrogate(feature="involvedCapabilityRealizationInvolvedElement")
 *         pattern CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement(self : CapabilityRealizationInvolvement, target : CapabilityRealizationInvolvedElement) {
 *         	CapabilityRealizationInvolvement.involved(self, target);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement extends BaseGeneratedEMFQuerySpecification<CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher> {
  /**
   * Pattern-specific match representation of the org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private CapabilityRealizationInvolvement fSelf;
    
    private CapabilityRealizationInvolvedElement fTarget;
    
    private static List<String> parameterNames = makeImmutableList("self", "target");
    
    private Match(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      this.fSelf = pSelf;
      this.fTarget = pTarget;
    }
    
    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "self": return this.fSelf;
          case "target": return this.fTarget;
          default: return null;
      }
    }
    
    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fSelf;
          case 1: return this.fTarget;
          default: return null;
      }
    }
    
    public CapabilityRealizationInvolvement getSelf() {
      return this.fSelf;
    }
    
    public CapabilityRealizationInvolvedElement getTarget() {
      return this.fTarget;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("self".equals(parameterName) ) {
          this.fSelf = (CapabilityRealizationInvolvement) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (CapabilityRealizationInvolvedElement) newValue;
          return true;
      }
      return false;
    }
    
    public void setSelf(final CapabilityRealizationInvolvement pSelf) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSelf = pSelf;
    }
    
    public void setTarget(final CapabilityRealizationInvolvedElement pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }
    
    @Override
    public String patternName() {
      return "org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement";
    }
    
    @Override
    public List<String> parameterNames() {
      return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fSelf, fTarget};
    }
    
    @Override
    public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match toImmutable() {
      return isMutable() ? newMatch(fSelf, fTarget) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"self\"=" + prettyPrintValue(fSelf) + ", ");
      result.append("\"target\"=" + prettyPrintValue(fTarget));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fSelf, fTarget);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match)) {
          CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match other = (CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match) obj;
          return Objects.equals(fSelf, other.fSelf) && Objects.equals(fTarget, other.fTarget);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }
    
    @Override
    public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement specification() {
      return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match newEmptyMatch() {
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
    public static CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match newMutableMatch(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
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
    public static CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match newMatch(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return new Immutable(pSelf, pTarget);
    }
    
    private static final class Mutable extends CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match {
      Mutable(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match {
      Immutable(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * // End of user code
   * 
   * {@literal @}Surrogate(feature="involvedCapabilityRealizationInvolvedElement")
   * pattern CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement(self : CapabilityRealizationInvolvement, target : CapabilityRealizationInvolvedElement) {
   * 	CapabilityRealizationInvolvement.involved(self, target);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement
   * 
   */
  public static class Matcher extends BaseMatcher<CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }
    
    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_SELF = 0;
    
    private static final int POSITION_TARGET = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher.class);
    
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }
    
    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match> getAllMatches(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return rawStreamAllMatches(new Object[]{pSelf, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match> streamAllMatches(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return rawStreamAllMatches(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match> getOneArbitraryMatch(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return rawGetOneArbitraryMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return rawHasMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return rawCountMatches(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget, final Consumer<? super CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pSelf, pTarget}, processor);
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
    public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match newMatch(final CapabilityRealizationInvolvement pSelf, final CapabilityRealizationInvolvedElement pTarget) {
      return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.newMatch(pSelf, pTarget);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<CapabilityRealizationInvolvement> rawStreamAllValuesOfself(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SELF, parameters).map(CapabilityRealizationInvolvement.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CapabilityRealizationInvolvement> getAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<CapabilityRealizationInvolvement> streamAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<CapabilityRealizationInvolvement> streamAllValuesOfself(final CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<CapabilityRealizationInvolvement> streamAllValuesOfself(final CapabilityRealizationInvolvedElement pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CapabilityRealizationInvolvement> getAllValuesOfself(final CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CapabilityRealizationInvolvement> getAllValuesOfself(final CapabilityRealizationInvolvedElement pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<CapabilityRealizationInvolvedElement> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(CapabilityRealizationInvolvedElement.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CapabilityRealizationInvolvedElement> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<CapabilityRealizationInvolvedElement> streamAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<CapabilityRealizationInvolvedElement> streamAllValuesOftarget(final CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<CapabilityRealizationInvolvedElement> streamAllValuesOftarget(final CapabilityRealizationInvolvement pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CapabilityRealizationInvolvedElement> getAllValuesOftarget(final CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<CapabilityRealizationInvolvedElement> getAllValuesOftarget(final CapabilityRealizationInvolvement pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match tupleToMatch(final Tuple t) {
      try {
          return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.newMatch((CapabilityRealizationInvolvement) t.get(POSITION_SELF), (CapabilityRealizationInvolvedElement) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match arrayToMatch(final Object[] match) {
      try {
          return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.newMatch((CapabilityRealizationInvolvement) match[POSITION_SELF], (CapabilityRealizationInvolvedElement) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match arrayToMatchMutable(final Object[] match) {
      try {
          return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.newMutableMatch((CapabilityRealizationInvolvement) match[POSITION_SELF], (CapabilityRealizationInvolvedElement) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher> querySpecification() {
      return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.instance();
    }
  }
  
  private CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher instantiate(final ViatraQueryEngine engine) {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher.on(engine);
  }
  
  @Override
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher instantiate() {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher.create();
  }
  
  @Override
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match newEmptyMatch() {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.newEmptyMatch();
  }
  
  @Override
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match newMatch(final Object... parameters) {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Match.newMatch((org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement) parameters[0], (org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement INSTANCE = new CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_self = new PParameter("self",
        "org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement",
        new EClassTransitiveInstancesKey(
            (EClass) getClassifierLiteralSafe("http://www.polarsys.org/capella/core/common/7.0.0",
                "CapabilityRealizationInvolvement")),
        PParameterDirection.INOUT);
    
    private final PParameter parameter_target = new PParameter("target",
        "org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement",
        new EClassTransitiveInstancesKey(
            (EClass) getClassifierLiteralSafe("http://www.polarsys.org/capella/core/common/7.0.0",
                "CapabilityRealizationInvolvedElement")),
        PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_self, parameter_target);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("self","target");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_self = body.getOrCreateVariableByName("self");
          PVariable var_target = body.getOrCreateVariableByName("target");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey(
              (EClass) getClassifierLiteral("http://www.polarsys.org/capella/core/common/7.0.0",
                  "CapabilityRealizationInvolvement")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey(
              (EClass) getClassifierLiteral("http://www.polarsys.org/capella/core/common/7.0.0",
                  "CapabilityRealizationInvolvedElement")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	CapabilityRealizationInvolvement.involved(self, target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey(
              (EClass) getClassifierLiteral("http://www.polarsys.org/capella/core/common/7.0.0",
                  "CapabilityRealizationInvolvement")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self, var__virtual_0_), new EStructuralFeatureInstancesKey(
              getFeatureLiteral("http://www.polarsys.org/capella/core/core/7.0.0", "Involvement", "involved")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey(
              (EClass) getClassifierLiteral("http://www.polarsys.org/capella/core/core/7.0.0", "InvolvedElement")));
          new Equality(body, var__virtual_0_, var_target);
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Surrogate");
          annotation.addAttribute("feature", "involvedCapabilityRealizationInvolvedElement");
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}

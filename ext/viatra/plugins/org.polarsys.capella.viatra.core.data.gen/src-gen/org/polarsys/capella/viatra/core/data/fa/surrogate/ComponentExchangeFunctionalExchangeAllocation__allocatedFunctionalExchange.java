/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

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
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
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
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         // End of user code
 *         
 *         {@literal @}Surrogate(feature="allocatedFunctionalExchange")
 *         pattern ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange(self : ComponentExchangeFunctionalExchangeAllocation, target : FunctionalExchange) {
 *         	ComponentExchangeFunctionalExchangeAllocation.targetElement(self, target);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange extends BaseGeneratedEMFQuerySpecification<ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher> {
  /**
   * Pattern-specific match representation of the org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange pattern,
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
    private ComponentExchangeFunctionalExchangeAllocation fSelf;
    
    private FunctionalExchange fTarget;
    
    private static List<String> parameterNames = makeImmutableList("self", "target");
    
    private Match(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
      this.fSelf = pSelf;
      this.fTarget = pTarget;
    }
    
    @Override
    public Object get(final String parameterName) {
      if ("self".equals(parameterName)) return this.fSelf;
      if ("target".equals(parameterName)) return this.fTarget;
      return null;
    }
    
    public ComponentExchangeFunctionalExchangeAllocation getSelf() {
      return this.fSelf;
    }
    
    public FunctionalExchange getTarget() {
      return this.fTarget;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("self".equals(parameterName) ) {
          this.fSelf = (ComponentExchangeFunctionalExchangeAllocation) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (FunctionalExchange) newValue;
          return true;
      }
      return false;
    }
    
    public void setSelf(final ComponentExchangeFunctionalExchangeAllocation pSelf) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSelf = pSelf;
    }
    
    public void setTarget(final FunctionalExchange pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }
    
    @Override
    public String patternName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange";
    }
    
    @Override
    public List<String> parameterNames() {
      return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fSelf, fTarget};
    }
    
    @Override
    public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match toImmutable() {
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
      if ((obj instanceof ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match)) {
          ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match other = (ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match) obj;
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
    public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange specification() {
      return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match newEmptyMatch() {
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
    public static ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match newMutableMatch(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
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
    public static ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match newMatch(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
      return new Immutable(pSelf, pTarget);
    }
    
    private static final class Mutable extends ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match {
      Mutable(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match {
      Immutable(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange pattern,
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
   * {@literal @}Surrogate(feature="allocatedFunctionalExchange")
   * pattern ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange(self : ComponentExchangeFunctionalExchangeAllocation, target : FunctionalExchange) {
   * 	ComponentExchangeFunctionalExchangeAllocation.targetElement(self, target);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange
   * 
   */
  public static class Matcher extends BaseMatcher<ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher on(final ViatraQueryEngine engine) {
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
    public static ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_SELF = 0;
    
    private static final int POSITION_TARGET = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher.class);
    
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
    public Collection<ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match> getAllMatches(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
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
    public Stream<ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match> streamAllMatches(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
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
    public Optional<ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match> getOneArbitraryMatch(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
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
    public boolean hasMatch(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
      return rawHasMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
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
    public boolean forOneArbitraryMatch(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget, final Consumer<? super ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match> processor) {
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
    public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match newMatch(final ComponentExchangeFunctionalExchangeAllocation pSelf, final FunctionalExchange pTarget) {
      return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.newMatch(pSelf, pTarget);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ComponentExchangeFunctionalExchangeAllocation> rawStreamAllValuesOfself(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SELF, parameters).map(ComponentExchangeFunctionalExchangeAllocation.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ComponentExchangeFunctionalExchangeAllocation> getAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ComponentExchangeFunctionalExchangeAllocation> streamAllValuesOfself() {
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
    public Stream<ComponentExchangeFunctionalExchangeAllocation> streamAllValuesOfself(final ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match partialMatch) {
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
    public Stream<ComponentExchangeFunctionalExchangeAllocation> streamAllValuesOfself(final FunctionalExchange pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ComponentExchangeFunctionalExchangeAllocation> getAllValuesOfself(final ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ComponentExchangeFunctionalExchangeAllocation> getAllValuesOfself(final FunctionalExchange pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<FunctionalExchange> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(FunctionalExchange.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<FunctionalExchange> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<FunctionalExchange> streamAllValuesOftarget() {
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
    public Stream<FunctionalExchange> streamAllValuesOftarget(final ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match partialMatch) {
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
    public Stream<FunctionalExchange> streamAllValuesOftarget(final ComponentExchangeFunctionalExchangeAllocation pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<FunctionalExchange> getAllValuesOftarget(final ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<FunctionalExchange> getAllValuesOftarget(final ComponentExchangeFunctionalExchangeAllocation pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match tupleToMatch(final Tuple t) {
      try {
          return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.newMatch((ComponentExchangeFunctionalExchangeAllocation) t.get(POSITION_SELF), (FunctionalExchange) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match arrayToMatch(final Object[] match) {
      try {
          return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.newMatch((ComponentExchangeFunctionalExchangeAllocation) match[POSITION_SELF], (FunctionalExchange) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match arrayToMatchMutable(final Object[] match) {
      try {
          return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.newMutableMatch((ComponentExchangeFunctionalExchangeAllocation) match[POSITION_SELF], (FunctionalExchange) match[POSITION_TARGET]);
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
    public static IQuerySpecification<ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher> querySpecification() {
      return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.instance();
    }
  }
  
  private ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher instantiate(final ViatraQueryEngine engine) {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher.on(engine);
  }
  
  @Override
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher instantiate() {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher.create();
  }
  
  @Override
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match newEmptyMatch() {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.newEmptyMatch();
  }
  
  @Override
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match newMatch(final Object... parameters) {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Match.newMatch((org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation) parameters[0], (org.polarsys.capella.core.data.fa.FunctionalExchange) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JvmGenericType: org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange (visibility: PUBLIC, simpleName: ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange, identifier: org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: org.polarsys.capella.viatra.core.data.fa.surrogate) (interface: false, strictFloatingPoint: false, anonymous: false)} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link JvmGenericType: org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange (visibility: PUBLIC, simpleName: ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange, identifier: org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange, deprecated: <unset>) (abstract: false, static: false, final: true, packageName: org.polarsys.capella.viatra.core.data.fa.surrogate) (interface: false, strictFloatingPoint: false, anonymous: false)#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange INSTANCE = new ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange();
    
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
    private static final ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_self = new PParameter("self", "org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.4.0", "ComponentExchangeFunctionalExchangeAllocation")), PParameterDirection.INOUT);
    
    private final PParameter parameter_target = new PParameter("target", "org.polarsys.capella.core.data.fa.FunctionalExchange", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionalExchange")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_self, parameter_target);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange";
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
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "ComponentExchangeFunctionalExchangeAllocation")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionalExchange")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	ComponentExchangeFunctionalExchangeAllocation.targetElement(self, target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "ComponentExchangeFunctionalExchangeAllocation")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.4.0", "AbstractTrace", "targetElement")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.4.0", "TraceableElement")));
          new Equality(body, var__virtual_0_, var_target);
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Surrogate");
          annotation.addAttribute("feature", "allocatedFunctionalExchange");
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}

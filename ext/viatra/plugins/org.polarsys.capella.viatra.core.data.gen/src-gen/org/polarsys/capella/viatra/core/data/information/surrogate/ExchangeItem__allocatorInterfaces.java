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
package org.polarsys.capella.viatra.core.data.information.surrogate;

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
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         // End of user code
 *         
 *         {@literal @}Surrogate(feature="allocatorInterfaces")
 *         pattern ExchangeItem__allocatorInterfaces(self : ExchangeItem, target : Interface) {
 *         	ExchangeItemAllocation.allocatedItem(eia, self);
 *         ExchangeItemAllocation.allocatingInterface(eia, target);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItem__allocatorInterfaces extends BaseGeneratedEMFQuerySpecification<ExchangeItem__allocatorInterfaces.Matcher> {
  /**
   * Pattern-specific match representation of the org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__allocatorInterfaces pattern,
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
    private ExchangeItem fSelf;
    
    private Interface fTarget;
    
    private static List<String> parameterNames = makeImmutableList("self", "target");
    
    private Match(final ExchangeItem pSelf, final Interface pTarget) {
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
    
    public ExchangeItem getSelf() {
      return this.fSelf;
    }
    
    public Interface getTarget() {
      return this.fTarget;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("self".equals(parameterName) ) {
          this.fSelf = (ExchangeItem) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (Interface) newValue;
          return true;
      }
      return false;
    }
    
    public void setSelf(final ExchangeItem pSelf) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSelf = pSelf;
    }
    
    public void setTarget(final Interface pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }
    
    @Override
    public String patternName() {
      return "org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__allocatorInterfaces";
    }
    
    @Override
    public List<String> parameterNames() {
      return ExchangeItem__allocatorInterfaces.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fSelf, fTarget};
    }
    
    @Override
    public ExchangeItem__allocatorInterfaces.Match toImmutable() {
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
      if ((obj instanceof ExchangeItem__allocatorInterfaces.Match)) {
          ExchangeItem__allocatorInterfaces.Match other = (ExchangeItem__allocatorInterfaces.Match) obj;
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
    public ExchangeItem__allocatorInterfaces specification() {
      return ExchangeItem__allocatorInterfaces.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static ExchangeItem__allocatorInterfaces.Match newEmptyMatch() {
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
    public static ExchangeItem__allocatorInterfaces.Match newMutableMatch(final ExchangeItem pSelf, final Interface pTarget) {
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
    public static ExchangeItem__allocatorInterfaces.Match newMatch(final ExchangeItem pSelf, final Interface pTarget) {
      return new Immutable(pSelf, pTarget);
    }
    
    private static final class Mutable extends ExchangeItem__allocatorInterfaces.Match {
      Mutable(final ExchangeItem pSelf, final Interface pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends ExchangeItem__allocatorInterfaces.Match {
      Immutable(final ExchangeItem pSelf, final Interface pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__allocatorInterfaces pattern,
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
   * {@literal @}Surrogate(feature="allocatorInterfaces")
   * pattern ExchangeItem__allocatorInterfaces(self : ExchangeItem, target : Interface) {
   * 	ExchangeItemAllocation.allocatedItem(eia, self);
   * ExchangeItemAllocation.allocatingInterface(eia, target);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see ExchangeItem__allocatorInterfaces
   * 
   */
  public static class Matcher extends BaseMatcher<ExchangeItem__allocatorInterfaces.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static ExchangeItem__allocatorInterfaces.Matcher on(final ViatraQueryEngine engine) {
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
    public static ExchangeItem__allocatorInterfaces.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_SELF = 0;
    
    private static final int POSITION_TARGET = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ExchangeItem__allocatorInterfaces.Matcher.class);
    
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
    public Collection<ExchangeItem__allocatorInterfaces.Match> getAllMatches(final ExchangeItem pSelf, final Interface pTarget) {
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
    public Stream<ExchangeItem__allocatorInterfaces.Match> streamAllMatches(final ExchangeItem pSelf, final Interface pTarget) {
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
    public Optional<ExchangeItem__allocatorInterfaces.Match> getOneArbitraryMatch(final ExchangeItem pSelf, final Interface pTarget) {
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
    public boolean hasMatch(final ExchangeItem pSelf, final Interface pTarget) {
      return rawHasMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final ExchangeItem pSelf, final Interface pTarget) {
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
    public boolean forOneArbitraryMatch(final ExchangeItem pSelf, final Interface pTarget, final Consumer<? super ExchangeItem__allocatorInterfaces.Match> processor) {
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
    public ExchangeItem__allocatorInterfaces.Match newMatch(final ExchangeItem pSelf, final Interface pTarget) {
      return ExchangeItem__allocatorInterfaces.Match.newMatch(pSelf, pTarget);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ExchangeItem> rawStreamAllValuesOfself(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SELF, parameters).map(ExchangeItem.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ExchangeItem> getAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ExchangeItem> streamAllValuesOfself() {
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
    public Stream<ExchangeItem> streamAllValuesOfself(final ExchangeItem__allocatorInterfaces.Match partialMatch) {
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
    public Stream<ExchangeItem> streamAllValuesOfself(final Interface pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ExchangeItem> getAllValuesOfself(final ExchangeItem__allocatorInterfaces.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ExchangeItem> getAllValuesOfself(final Interface pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<Interface> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(Interface.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Interface> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<Interface> streamAllValuesOftarget() {
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
    public Stream<Interface> streamAllValuesOftarget(final ExchangeItem__allocatorInterfaces.Match partialMatch) {
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
    public Stream<Interface> streamAllValuesOftarget(final ExchangeItem pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Interface> getAllValuesOftarget(final ExchangeItem__allocatorInterfaces.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<Interface> getAllValuesOftarget(final ExchangeItem pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected ExchangeItem__allocatorInterfaces.Match tupleToMatch(final Tuple t) {
      try {
          return ExchangeItem__allocatorInterfaces.Match.newMatch((ExchangeItem) t.get(POSITION_SELF), (Interface) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ExchangeItem__allocatorInterfaces.Match arrayToMatch(final Object[] match) {
      try {
          return ExchangeItem__allocatorInterfaces.Match.newMatch((ExchangeItem) match[POSITION_SELF], (Interface) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ExchangeItem__allocatorInterfaces.Match arrayToMatchMutable(final Object[] match) {
      try {
          return ExchangeItem__allocatorInterfaces.Match.newMutableMatch((ExchangeItem) match[POSITION_SELF], (Interface) match[POSITION_TARGET]);
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
    public static IQuerySpecification<ExchangeItem__allocatorInterfaces.Matcher> querySpecification() {
      return ExchangeItem__allocatorInterfaces.instance();
    }
  }
  
  private ExchangeItem__allocatorInterfaces() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static ExchangeItem__allocatorInterfaces instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ExchangeItem__allocatorInterfaces.Matcher instantiate(final ViatraQueryEngine engine) {
    return ExchangeItem__allocatorInterfaces.Matcher.on(engine);
  }
  
  @Override
  public ExchangeItem__allocatorInterfaces.Matcher instantiate() {
    return ExchangeItem__allocatorInterfaces.Matcher.create();
  }
  
  @Override
  public ExchangeItem__allocatorInterfaces.Match newEmptyMatch() {
    return ExchangeItem__allocatorInterfaces.Match.newEmptyMatch();
  }
  
  @Override
  public ExchangeItem__allocatorInterfaces.Match newMatch(final Object... parameters) {
    return ExchangeItem__allocatorInterfaces.Match.newMatch((org.polarsys.capella.core.data.information.ExchangeItem) parameters[0], (org.polarsys.capella.core.data.cs.Interface) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ExchangeItem__allocatorInterfaces} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link ExchangeItem__allocatorInterfaces#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final ExchangeItem__allocatorInterfaces INSTANCE = new ExchangeItem__allocatorInterfaces();
    
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
    private static final ExchangeItem__allocatorInterfaces.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_self = new PParameter("self", "org.polarsys.capella.core.data.information.ExchangeItem", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/information/7.0.0", "ExchangeItem")), PParameterDirection.INOUT);
    
    private final PParameter parameter_target = new PParameter("target", "org.polarsys.capella.core.data.cs.Interface", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/7.0.0", "Interface")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_self, parameter_target);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__allocatorInterfaces";
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
          PVariable var_eia = body.getOrCreateVariableByName("eia");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/7.0.0", "ExchangeItem")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/7.0.0", "Interface")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	ExchangeItemAllocation.allocatedItem(eia, self)
          new TypeConstraint(body, Tuples.flatTupleOf(var_eia), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/7.0.0", "ExchangeItemAllocation")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_eia, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/7.0.0", "ExchangeItemAllocation", "allocatedItem")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/7.0.0", "ExchangeItem")));
          new Equality(body, var__virtual_0_, var_self);
          // ExchangeItemAllocation.allocatingInterface(eia, target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_eia), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/7.0.0", "ExchangeItemAllocation")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_eia, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/7.0.0", "ExchangeItemAllocation", "allocatingInterface")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/7.0.0", "Interface")));
          new Equality(body, var__virtual_1_, var_target);
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Surrogate");
          annotation.addAttribute("feature", "allocatorInterfaces");
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}

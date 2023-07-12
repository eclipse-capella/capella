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
package org.polarsys.capella.viatra.common.data.activity.surrogate;

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
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         {@literal @}Surrogate(feature="outgoing")
 *         pattern ActivityNode__outgoing(self : ActivityNode, target : ActivityEdge) {
 *         	ActivityEdge.source(target, self);
 *         } or {
 *         	AbstractAction(self);
 *         	AbstractAction.outputs(self, OutputPort);
 *         	ActivityNode.outgoing(OutputPort, target);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class ActivityNode__outgoing extends BaseGeneratedEMFQuerySpecification<ActivityNode__outgoing.Matcher> {
  /**
   * Pattern-specific match representation of the org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__outgoing pattern,
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
    private ActivityNode fSelf;
    
    private ActivityEdge fTarget;
    
    private static List<String> parameterNames = makeImmutableList("self", "target");
    
    private Match(final ActivityNode pSelf, final ActivityEdge pTarget) {
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
    
    public ActivityNode getSelf() {
      return this.fSelf;
    }
    
    public ActivityEdge getTarget() {
      return this.fTarget;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("self".equals(parameterName) ) {
          this.fSelf = (ActivityNode) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (ActivityEdge) newValue;
          return true;
      }
      return false;
    }
    
    public void setSelf(final ActivityNode pSelf) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSelf = pSelf;
    }
    
    public void setTarget(final ActivityEdge pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }
    
    @Override
    public String patternName() {
      return "org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__outgoing";
    }
    
    @Override
    public List<String> parameterNames() {
      return ActivityNode__outgoing.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fSelf, fTarget};
    }
    
    @Override
    public ActivityNode__outgoing.Match toImmutable() {
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
      if ((obj instanceof ActivityNode__outgoing.Match)) {
          ActivityNode__outgoing.Match other = (ActivityNode__outgoing.Match) obj;
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
    public ActivityNode__outgoing specification() {
      return ActivityNode__outgoing.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static ActivityNode__outgoing.Match newEmptyMatch() {
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
    public static ActivityNode__outgoing.Match newMutableMatch(final ActivityNode pSelf, final ActivityEdge pTarget) {
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
    public static ActivityNode__outgoing.Match newMatch(final ActivityNode pSelf, final ActivityEdge pTarget) {
      return new Immutable(pSelf, pTarget);
    }
    
    private static final class Mutable extends ActivityNode__outgoing.Match {
      Mutable(final ActivityNode pSelf, final ActivityEdge pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends ActivityNode__outgoing.Match {
      Immutable(final ActivityNode pSelf, final ActivityEdge pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__outgoing pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * {@literal @}Surrogate(feature="outgoing")
   * pattern ActivityNode__outgoing(self : ActivityNode, target : ActivityEdge) {
   * 	ActivityEdge.source(target, self);
   * } or {
   * 	AbstractAction(self);
   * 	AbstractAction.outputs(self, OutputPort);
   * 	ActivityNode.outgoing(OutputPort, target);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see ActivityNode__outgoing
   * 
   */
  public static class Matcher extends BaseMatcher<ActivityNode__outgoing.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static ActivityNode__outgoing.Matcher on(final ViatraQueryEngine engine) {
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
    public static ActivityNode__outgoing.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_SELF = 0;
    
    private static final int POSITION_TARGET = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ActivityNode__outgoing.Matcher.class);
    
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
    public Collection<ActivityNode__outgoing.Match> getAllMatches(final ActivityNode pSelf, final ActivityEdge pTarget) {
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
    public Stream<ActivityNode__outgoing.Match> streamAllMatches(final ActivityNode pSelf, final ActivityEdge pTarget) {
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
    public Optional<ActivityNode__outgoing.Match> getOneArbitraryMatch(final ActivityNode pSelf, final ActivityEdge pTarget) {
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
    public boolean hasMatch(final ActivityNode pSelf, final ActivityEdge pTarget) {
      return rawHasMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final ActivityNode pSelf, final ActivityEdge pTarget) {
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
    public boolean forOneArbitraryMatch(final ActivityNode pSelf, final ActivityEdge pTarget, final Consumer<? super ActivityNode__outgoing.Match> processor) {
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
    public ActivityNode__outgoing.Match newMatch(final ActivityNode pSelf, final ActivityEdge pTarget) {
      return ActivityNode__outgoing.Match.newMatch(pSelf, pTarget);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ActivityNode> rawStreamAllValuesOfself(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SELF, parameters).map(ActivityNode.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ActivityNode> getAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ActivityNode> streamAllValuesOfself() {
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
    public Stream<ActivityNode> streamAllValuesOfself(final ActivityNode__outgoing.Match partialMatch) {
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
    public Stream<ActivityNode> streamAllValuesOfself(final ActivityEdge pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ActivityNode> getAllValuesOfself(final ActivityNode__outgoing.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ActivityNode> getAllValuesOfself(final ActivityEdge pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ActivityEdge> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(ActivityEdge.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ActivityEdge> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ActivityEdge> streamAllValuesOftarget() {
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
    public Stream<ActivityEdge> streamAllValuesOftarget(final ActivityNode__outgoing.Match partialMatch) {
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
    public Stream<ActivityEdge> streamAllValuesOftarget(final ActivityNode pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ActivityEdge> getAllValuesOftarget(final ActivityNode__outgoing.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ActivityEdge> getAllValuesOftarget(final ActivityNode pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected ActivityNode__outgoing.Match tupleToMatch(final Tuple t) {
      try {
          return ActivityNode__outgoing.Match.newMatch((ActivityNode) t.get(POSITION_SELF), (ActivityEdge) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ActivityNode__outgoing.Match arrayToMatch(final Object[] match) {
      try {
          return ActivityNode__outgoing.Match.newMatch((ActivityNode) match[POSITION_SELF], (ActivityEdge) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected ActivityNode__outgoing.Match arrayToMatchMutable(final Object[] match) {
      try {
          return ActivityNode__outgoing.Match.newMutableMatch((ActivityNode) match[POSITION_SELF], (ActivityEdge) match[POSITION_TARGET]);
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
    public static IQuerySpecification<ActivityNode__outgoing.Matcher> querySpecification() {
      return ActivityNode__outgoing.instance();
    }
  }
  
  private ActivityNode__outgoing() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static ActivityNode__outgoing instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ActivityNode__outgoing.Matcher instantiate(final ViatraQueryEngine engine) {
    return ActivityNode__outgoing.Matcher.on(engine);
  }
  
  @Override
  public ActivityNode__outgoing.Matcher instantiate() {
    return ActivityNode__outgoing.Matcher.create();
  }
  
  @Override
  public ActivityNode__outgoing.Match newEmptyMatch() {
    return ActivityNode__outgoing.Match.newEmptyMatch();
  }
  
  @Override
  public ActivityNode__outgoing.Match newMatch(final Object... parameters) {
    return ActivityNode__outgoing.Match.newMatch((org.polarsys.capella.common.data.activity.ActivityNode) parameters[0], (org.polarsys.capella.common.data.activity.ActivityEdge) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ActivityNode__outgoing} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link ActivityNode__outgoing#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final ActivityNode__outgoing INSTANCE = new ActivityNode__outgoing();
    
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
    private static final ActivityNode__outgoing.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_self = new PParameter("self", "org.polarsys.capella.common.data.activity.ActivityNode", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityNode")), PParameterDirection.INOUT);
    
    private final PParameter parameter_target = new PParameter("target", "org.polarsys.capella.common.data.activity.ActivityEdge", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityEdge")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_self, parameter_target);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__outgoing";
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
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityNode")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityEdge")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	ActivityEdge.source(target, self)
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityEdge")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_target, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityEdge", "source")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityNode")));
          new Equality(body, var__virtual_0_, var_self);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_self = body.getOrCreateVariableByName("self");
          PVariable var_target = body.getOrCreateVariableByName("target");
          PVariable var_OutputPort = body.getOrCreateVariableByName("OutputPort");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityNode")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityEdge")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	AbstractAction(self)
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "AbstractAction")));
          // 	AbstractAction.outputs(self, OutputPort)
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "AbstractAction")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "AbstractAction", "outputs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "OutputPin")));
          new Equality(body, var__virtual_0_, var_OutputPort);
          // 	ActivityNode.outgoing(OutputPort, target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_OutputPort), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityNode")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_OutputPort, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityNode", "outgoing")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/activity/7.0.0", "ActivityEdge")));
          new Equality(body, var__virtual_1_, var_target);
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Surrogate");
          annotation.addAttribute("feature", "outgoing");
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}

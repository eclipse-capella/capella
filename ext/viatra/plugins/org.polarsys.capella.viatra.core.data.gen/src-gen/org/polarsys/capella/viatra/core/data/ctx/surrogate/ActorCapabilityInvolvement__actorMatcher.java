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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorCapabilityInvolvement__actorMatch;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.ActorCapabilityInvolvement__actorQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorCapabilityInvolvement__actor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ActorCapabilityInvolvement__actorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="actor")
 * pattern ActorCapabilityInvolvement__actor(self : ActorCapabilityInvolvement, target : Actor) {
 * 	ActorCapabilityInvolvement.involved(self, target);
 * }
 * </pre></code>
 * 
 * @see ActorCapabilityInvolvement__actorMatch
 * @see ActorCapabilityInvolvement__actorProcessor
 * @see ActorCapabilityInvolvement__actorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ActorCapabilityInvolvement__actorMatcher extends BaseMatcher<ActorCapabilityInvolvement__actorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ActorCapabilityInvolvement__actorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ActorCapabilityInvolvement__actorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (ActorCapabilityInvolvement__actorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ActorCapabilityInvolvement__actorMatcher create() throws ViatraQueryException {
    return new ActorCapabilityInvolvement__actorMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ActorCapabilityInvolvement__actorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ActorCapabilityInvolvement__actorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a ActorCapabilityInvolvement__actorMatch object.
   * 
   */
  public Collection<ActorCapabilityInvolvement__actorMatch> getAllMatches(final ActorCapabilityInvolvement pSelf, final Actor pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a ActorCapabilityInvolvement__actorMatch object, or null if no match is found.
   * 
   */
  public ActorCapabilityInvolvement__actorMatch getOneArbitraryMatch(final ActorCapabilityInvolvement pSelf, final Actor pTarget) {
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
  public boolean hasMatch(final ActorCapabilityInvolvement pSelf, final Actor pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final ActorCapabilityInvolvement pSelf, final Actor pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final ActorCapabilityInvolvement pSelf, final Actor pTarget, final IMatchProcessor<? super ActorCapabilityInvolvement__actorMatch> processor) {
    rawForEachMatch(new Object[]{pSelf, pTarget}, processor);
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
  public boolean forOneArbitraryMatch(final ActorCapabilityInvolvement pSelf, final Actor pTarget, final IMatchProcessor<? super ActorCapabilityInvolvement__actorMatch> processor) {
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
  public ActorCapabilityInvolvement__actorMatch newMatch(final ActorCapabilityInvolvement pSelf, final Actor pTarget) {
    return ActorCapabilityInvolvement__actorMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<ActorCapabilityInvolvement> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<ActorCapabilityInvolvement> results = new HashSet<ActorCapabilityInvolvement>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ActorCapabilityInvolvement> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ActorCapabilityInvolvement> getAllValuesOfself(final ActorCapabilityInvolvement__actorMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ActorCapabilityInvolvement> getAllValuesOfself(final Actor pTarget) {
    return rawAccumulateAllValuesOfself(new Object[]{
    null, 
    pTarget
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Actor> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<Actor> results = new HashSet<Actor>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Actor> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Actor> getAllValuesOftarget(final ActorCapabilityInvolvement__actorMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Actor> getAllValuesOftarget(final ActorCapabilityInvolvement pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected ActorCapabilityInvolvement__actorMatch tupleToMatch(final Tuple t) {
    try {
        return ActorCapabilityInvolvement__actorMatch.newMatch((ActorCapabilityInvolvement) t.get(POSITION_SELF), (Actor) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected ActorCapabilityInvolvement__actorMatch arrayToMatch(final Object[] match) {
    try {
        return ActorCapabilityInvolvement__actorMatch.newMatch((ActorCapabilityInvolvement) match[POSITION_SELF], (Actor) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected ActorCapabilityInvolvement__actorMatch arrayToMatchMutable(final Object[] match) {
    try {
        return ActorCapabilityInvolvement__actorMatch.newMutableMatch((ActorCapabilityInvolvement) match[POSITION_SELF], (Actor) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<ActorCapabilityInvolvement__actorMatcher> querySpecification() throws ViatraQueryException {
    return ActorCapabilityInvolvement__actorQuerySpecification.instance();
  }
}

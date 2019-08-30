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
package org.polarsys.capella.viatra.core.data.capellacore.surrogate;

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
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.InvolverElement__involvedInvolvementsMatch;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.InvolverElement__involvedInvolvementsQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.capellacore.surrogate.InvolverElement__involvedInvolvements pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link InvolverElement__involvedInvolvementsMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="involvedInvolvements")
 * pattern InvolverElement__involvedInvolvements(self : InvolverElement, target : Involvement) {
 * 	AbstractCapability.ownedAbstractFunctionAbstractCapabilityInvolvements(self, target);
 * 	} or {	AbstractCapability.ownedFunctionalChainAbstractCapabilityInvolvements(self, target);
 * 	} or {	Capability.ownedActorCapabilityInvolvements(self, target);
 * 	} or {	Capability.ownedSystemCapabilityInvolvement(self, target);
 * 	} or {	CapabilityRealization.ownedActorCapabilityRealizations(self, target);
 * 	} or {	CapabilityRealization.ownedSystemComponentCapabilityRealizations(self, target);
 * 	} or {	OperationalCapability.ownedEntityOperationalCapabilityInvolvements(self, target);
 * 	} or {	FunctionalChain.ownedFunctionalChainInvolvements(self, target);
 * 	} or {	Mission.ownedActorMissionInvolvements(self, target);
 * 	} or {	Mission.ownedSystemMissionInvolvement(self, target);
 * 	} or {	PhysicalPath.ownedPhysicalPathInvolvements(self, target);
 * 
 * }
 * </pre></code>
 * 
 * @see InvolverElement__involvedInvolvementsMatch
 * @see InvolverElement__involvedInvolvementsProcessor
 * @see InvolverElement__involvedInvolvementsQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class InvolverElement__involvedInvolvementsMatcher extends BaseMatcher<InvolverElement__involvedInvolvementsMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static InvolverElement__involvedInvolvementsMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    InvolverElement__involvedInvolvementsMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (InvolverElement__involvedInvolvementsMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static InvolverElement__involvedInvolvementsMatcher create() throws ViatraQueryException {
    return new InvolverElement__involvedInvolvementsMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(InvolverElement__involvedInvolvementsMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private InvolverElement__involvedInvolvementsMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a InvolverElement__involvedInvolvementsMatch object.
   * 
   */
  public Collection<InvolverElement__involvedInvolvementsMatch> getAllMatches(final InvolverElement pSelf, final Involvement pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a InvolverElement__involvedInvolvementsMatch object, or null if no match is found.
   * 
   */
  public InvolverElement__involvedInvolvementsMatch getOneArbitraryMatch(final InvolverElement pSelf, final Involvement pTarget) {
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
  public boolean hasMatch(final InvolverElement pSelf, final Involvement pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final InvolverElement pSelf, final Involvement pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final InvolverElement pSelf, final Involvement pTarget, final IMatchProcessor<? super InvolverElement__involvedInvolvementsMatch> processor) {
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
  public boolean forOneArbitraryMatch(final InvolverElement pSelf, final Involvement pTarget, final IMatchProcessor<? super InvolverElement__involvedInvolvementsMatch> processor) {
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
  public InvolverElement__involvedInvolvementsMatch newMatch(final InvolverElement pSelf, final Involvement pTarget) {
    return InvolverElement__involvedInvolvementsMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<InvolverElement> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<InvolverElement> results = new HashSet<InvolverElement>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<InvolverElement> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<InvolverElement> getAllValuesOfself(final InvolverElement__involvedInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<InvolverElement> getAllValuesOfself(final Involvement pTarget) {
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
  protected Set<Involvement> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<Involvement> results = new HashSet<Involvement>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Involvement> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Involvement> getAllValuesOftarget(final InvolverElement__involvedInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Involvement> getAllValuesOftarget(final InvolverElement pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected InvolverElement__involvedInvolvementsMatch tupleToMatch(final Tuple t) {
    try {
        return InvolverElement__involvedInvolvementsMatch.newMatch((InvolverElement) t.get(POSITION_SELF), (Involvement) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected InvolverElement__involvedInvolvementsMatch arrayToMatch(final Object[] match) {
    try {
        return InvolverElement__involvedInvolvementsMatch.newMatch((InvolverElement) match[POSITION_SELF], (Involvement) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected InvolverElement__involvedInvolvementsMatch arrayToMatchMutable(final Object[] match) {
    try {
        return InvolverElement__involvedInvolvementsMatch.newMutableMatch((InvolverElement) match[POSITION_SELF], (Involvement) match[POSITION_TARGET]);
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
  public static IQuerySpecification<InvolverElement__involvedInvolvementsMatcher> querySpecification() throws ViatraQueryException {
    return InvolverElement__involvedInvolvementsQuerySpecification.instance();
  }
}

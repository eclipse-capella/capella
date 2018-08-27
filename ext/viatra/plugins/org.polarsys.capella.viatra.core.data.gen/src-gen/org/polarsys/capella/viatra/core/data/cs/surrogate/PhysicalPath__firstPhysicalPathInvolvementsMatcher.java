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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

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
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__firstPhysicalPathInvolvementsMatch;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__firstPhysicalPathInvolvements pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link PhysicalPath__firstPhysicalPathInvolvementsMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="firstPhysicalPathInvolvements")
 * pattern PhysicalPath__firstPhysicalPathInvolvements(self : PhysicalPath, target : PhysicalPathInvolvement) {
 * 	PhysicalPath.ownedPhysicalPathInvolvements(self, target);
 * 	PhysicalPathInvolvement.involved(target, _);
 * 	neg find _PreviousInvolvement(target, _);
 * }
 * </pre></code>
 * 
 * @see PhysicalPath__firstPhysicalPathInvolvementsMatch
 * @see PhysicalPath__firstPhysicalPathInvolvementsProcessor
 * @see PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class PhysicalPath__firstPhysicalPathInvolvementsMatcher extends BaseMatcher<PhysicalPath__firstPhysicalPathInvolvementsMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static PhysicalPath__firstPhysicalPathInvolvementsMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    PhysicalPath__firstPhysicalPathInvolvementsMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (PhysicalPath__firstPhysicalPathInvolvementsMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static PhysicalPath__firstPhysicalPathInvolvementsMatcher create() throws ViatraQueryException {
    return new PhysicalPath__firstPhysicalPathInvolvementsMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(PhysicalPath__firstPhysicalPathInvolvementsMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private PhysicalPath__firstPhysicalPathInvolvementsMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a PhysicalPath__firstPhysicalPathInvolvementsMatch object.
   * 
   */
  public Collection<PhysicalPath__firstPhysicalPathInvolvementsMatch> getAllMatches(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a PhysicalPath__firstPhysicalPathInvolvementsMatch object, or null if no match is found.
   * 
   */
  public PhysicalPath__firstPhysicalPathInvolvementsMatch getOneArbitraryMatch(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget) {
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
  public boolean hasMatch(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget, final IMatchProcessor<? super PhysicalPath__firstPhysicalPathInvolvementsMatch> processor) {
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
  public boolean forOneArbitraryMatch(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget, final IMatchProcessor<? super PhysicalPath__firstPhysicalPathInvolvementsMatch> processor) {
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
  public PhysicalPath__firstPhysicalPathInvolvementsMatch newMatch(final PhysicalPath pSelf, final PhysicalPathInvolvement pTarget) {
    return PhysicalPath__firstPhysicalPathInvolvementsMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<PhysicalPath> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<PhysicalPath> results = new HashSet<PhysicalPath>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalPath> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalPath> getAllValuesOfself(final PhysicalPath__firstPhysicalPathInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalPath> getAllValuesOfself(final PhysicalPathInvolvement pTarget) {
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
  protected Set<PhysicalPathInvolvement> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<PhysicalPathInvolvement> results = new HashSet<PhysicalPathInvolvement>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalPathInvolvement> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalPathInvolvement> getAllValuesOftarget(final PhysicalPath__firstPhysicalPathInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalPathInvolvement> getAllValuesOftarget(final PhysicalPath pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected PhysicalPath__firstPhysicalPathInvolvementsMatch tupleToMatch(final Tuple t) {
    try {
        return PhysicalPath__firstPhysicalPathInvolvementsMatch.newMatch((PhysicalPath) t.get(POSITION_SELF), (PhysicalPathInvolvement) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected PhysicalPath__firstPhysicalPathInvolvementsMatch arrayToMatch(final Object[] match) {
    try {
        return PhysicalPath__firstPhysicalPathInvolvementsMatch.newMatch((PhysicalPath) match[POSITION_SELF], (PhysicalPathInvolvement) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected PhysicalPath__firstPhysicalPathInvolvementsMatch arrayToMatchMutable(final Object[] match) {
    try {
        return PhysicalPath__firstPhysicalPathInvolvementsMatch.newMutableMatch((PhysicalPath) match[POSITION_SELF], (PhysicalPathInvolvement) match[POSITION_TARGET]);
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
  public static IQuerySpecification<PhysicalPath__firstPhysicalPathInvolvementsMatcher> querySpecification() throws ViatraQueryException {
    return PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification.instance();
  }
}

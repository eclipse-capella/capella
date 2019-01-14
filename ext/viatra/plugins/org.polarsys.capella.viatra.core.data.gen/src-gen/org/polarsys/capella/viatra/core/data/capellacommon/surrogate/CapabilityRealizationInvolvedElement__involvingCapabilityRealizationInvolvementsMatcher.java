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
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="involvingCapabilityRealizationInvolvements")
 * pattern CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements(self : CapabilityRealizationInvolvedElement, target : CapabilityRealizationInvolvement) {
 * 	CapabilityRealizationInvolvedElement.involvingInvolvements(self, target);
 * }
 * </pre></code>
 * 
 * @see CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch
 * @see CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsProcessor
 * @see CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher extends BaseMatcher<CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher create() throws ViatraQueryException {
    return new CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch object.
   * 
   */
  public Collection<CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch> getAllMatches(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch object, or null if no match is found.
   * 
   */
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch getOneArbitraryMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
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
  public boolean hasMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget, final IMatchProcessor<? super CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch> processor) {
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
  public boolean forOneArbitraryMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget, final IMatchProcessor<? super CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch> processor) {
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
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch newMatch(final CapabilityRealizationInvolvedElement pSelf, final CapabilityRealizationInvolvement pTarget) {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<CapabilityRealizationInvolvedElement> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<CapabilityRealizationInvolvedElement> results = new HashSet<CapabilityRealizationInvolvedElement>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationInvolvedElement> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationInvolvedElement> getAllValuesOfself(final CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationInvolvedElement> getAllValuesOfself(final CapabilityRealizationInvolvement pTarget) {
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
  protected Set<CapabilityRealizationInvolvement> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<CapabilityRealizationInvolvement> results = new HashSet<CapabilityRealizationInvolvement>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationInvolvement> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationInvolvement> getAllValuesOftarget(final CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationInvolvement> getAllValuesOftarget(final CapabilityRealizationInvolvedElement pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch tupleToMatch(final Tuple t) {
    try {
        return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch.newMatch((CapabilityRealizationInvolvedElement) t.get(POSITION_SELF), (CapabilityRealizationInvolvement) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch arrayToMatch(final Object[] match) {
    try {
        return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch.newMatch((CapabilityRealizationInvolvedElement) match[POSITION_SELF], (CapabilityRealizationInvolvement) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch arrayToMatchMutable(final Object[] match) {
    try {
        return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatch.newMutableMatch((CapabilityRealizationInvolvedElement) match[POSITION_SELF], (CapabilityRealizationInvolvement) match[POSITION_TARGET]);
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
  public static IQuerySpecification<CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsMatcher> querySpecification() throws ViatraQueryException {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvementsQuerySpecification.instance();
  }
}

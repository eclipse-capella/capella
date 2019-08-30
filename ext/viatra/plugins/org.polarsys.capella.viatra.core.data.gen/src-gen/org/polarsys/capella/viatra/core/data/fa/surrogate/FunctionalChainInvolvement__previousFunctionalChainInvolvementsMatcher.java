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
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__previousFunctionalChainInvolvements pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="previousFunctionalChainInvolvements")
 * pattern FunctionalChainInvolvement__previousFunctionalChainInvolvements(self : FunctionalChainInvolvement, target : FunctionalChainInvolvement) {
 * 	FunctionalChainInvolvementLink.target(target, self);
 * } or {
 * 	FunctionalChainInvolvementLink.source(self, target);
 * }
 * </pre></code>
 * 
 * @see FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch
 * @see FunctionalChainInvolvement__previousFunctionalChainInvolvementsProcessor
 * @see FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher extends BaseMatcher<FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher create() throws ViatraQueryException {
    return new FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch object.
   * 
   */
  public Collection<FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch> getAllMatches(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch object, or null if no match is found.
   * 
   */
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch getOneArbitraryMatch(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget) {
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
  public boolean hasMatch(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget, final IMatchProcessor<? super FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch> processor) {
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
  public boolean forOneArbitraryMatch(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget, final IMatchProcessor<? super FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch> processor) {
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
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch newMatch(final FunctionalChainInvolvement pSelf, final FunctionalChainInvolvement pTarget) {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<FunctionalChainInvolvement> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<FunctionalChainInvolvement> results = new HashSet<FunctionalChainInvolvement>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalChainInvolvement> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalChainInvolvement> getAllValuesOfself(final FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalChainInvolvement> getAllValuesOfself(final FunctionalChainInvolvement pTarget) {
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
  protected Set<FunctionalChainInvolvement> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<FunctionalChainInvolvement> results = new HashSet<FunctionalChainInvolvement>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalChainInvolvement> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalChainInvolvement> getAllValuesOftarget(final FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalChainInvolvement> getAllValuesOftarget(final FunctionalChainInvolvement pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch tupleToMatch(final Tuple t) {
    try {
        return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch.newMatch((FunctionalChainInvolvement) t.get(POSITION_SELF), (FunctionalChainInvolvement) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch arrayToMatch(final Object[] match) {
    try {
        return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch.newMatch((FunctionalChainInvolvement) match[POSITION_SELF], (FunctionalChainInvolvement) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch arrayToMatchMutable(final Object[] match) {
    try {
        return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch.newMutableMatch((FunctionalChainInvolvement) match[POSITION_SELF], (FunctionalChainInvolvement) match[POSITION_TARGET]);
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
  public static IQuerySpecification<FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher> querySpecification() throws ViatraQueryException {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification.instance();
  }
}

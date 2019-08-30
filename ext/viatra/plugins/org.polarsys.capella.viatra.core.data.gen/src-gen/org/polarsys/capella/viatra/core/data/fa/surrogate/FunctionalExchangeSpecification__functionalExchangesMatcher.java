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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchangeSpecification__functionalExchangesMatch;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchangeSpecification__functionalExchangesQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchangeSpecification__functionalExchanges pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link FunctionalExchangeSpecification__functionalExchangesMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="functionalExchanges")
 * pattern FunctionalExchangeSpecification__functionalExchanges(self : FunctionalExchangeSpecification, target : FunctionalExchange) {
 * 	FunctionalExchangeSpecification.realizations(self, target);
 * }
 * </pre></code>
 * 
 * @see FunctionalExchangeSpecification__functionalExchangesMatch
 * @see FunctionalExchangeSpecification__functionalExchangesProcessor
 * @see FunctionalExchangeSpecification__functionalExchangesQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class FunctionalExchangeSpecification__functionalExchangesMatcher extends BaseMatcher<FunctionalExchangeSpecification__functionalExchangesMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static FunctionalExchangeSpecification__functionalExchangesMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    FunctionalExchangeSpecification__functionalExchangesMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (FunctionalExchangeSpecification__functionalExchangesMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static FunctionalExchangeSpecification__functionalExchangesMatcher create() throws ViatraQueryException {
    return new FunctionalExchangeSpecification__functionalExchangesMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(FunctionalExchangeSpecification__functionalExchangesMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private FunctionalExchangeSpecification__functionalExchangesMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a FunctionalExchangeSpecification__functionalExchangesMatch object.
   * 
   */
  public Collection<FunctionalExchangeSpecification__functionalExchangesMatch> getAllMatches(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a FunctionalExchangeSpecification__functionalExchangesMatch object, or null if no match is found.
   * 
   */
  public FunctionalExchangeSpecification__functionalExchangesMatch getOneArbitraryMatch(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget) {
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
  public boolean hasMatch(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget, final IMatchProcessor<? super FunctionalExchangeSpecification__functionalExchangesMatch> processor) {
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
  public boolean forOneArbitraryMatch(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget, final IMatchProcessor<? super FunctionalExchangeSpecification__functionalExchangesMatch> processor) {
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
  public FunctionalExchangeSpecification__functionalExchangesMatch newMatch(final FunctionalExchangeSpecification pSelf, final FunctionalExchange pTarget) {
    return FunctionalExchangeSpecification__functionalExchangesMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<FunctionalExchangeSpecification> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<FunctionalExchangeSpecification> results = new HashSet<FunctionalExchangeSpecification>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalExchangeSpecification> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalExchangeSpecification> getAllValuesOfself(final FunctionalExchangeSpecification__functionalExchangesMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalExchangeSpecification> getAllValuesOfself(final FunctionalExchange pTarget) {
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
  protected Set<FunctionalExchange> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<FunctionalExchange> results = new HashSet<FunctionalExchange>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalExchange> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalExchange> getAllValuesOftarget(final FunctionalExchangeSpecification__functionalExchangesMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<FunctionalExchange> getAllValuesOftarget(final FunctionalExchangeSpecification pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected FunctionalExchangeSpecification__functionalExchangesMatch tupleToMatch(final Tuple t) {
    try {
        return FunctionalExchangeSpecification__functionalExchangesMatch.newMatch((FunctionalExchangeSpecification) t.get(POSITION_SELF), (FunctionalExchange) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected FunctionalExchangeSpecification__functionalExchangesMatch arrayToMatch(final Object[] match) {
    try {
        return FunctionalExchangeSpecification__functionalExchangesMatch.newMatch((FunctionalExchangeSpecification) match[POSITION_SELF], (FunctionalExchange) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected FunctionalExchangeSpecification__functionalExchangesMatch arrayToMatchMutable(final Object[] match) {
    try {
        return FunctionalExchangeSpecification__functionalExchangesMatch.newMutableMatch((FunctionalExchangeSpecification) match[POSITION_SELF], (FunctionalExchange) match[POSITION_TARGET]);
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
  public static IQuerySpecification<FunctionalExchangeSpecification__functionalExchangesMatcher> querySpecification() throws ViatraQueryException {
    return FunctionalExchangeSpecification__functionalExchangesQuerySpecification.instance();
  }
}

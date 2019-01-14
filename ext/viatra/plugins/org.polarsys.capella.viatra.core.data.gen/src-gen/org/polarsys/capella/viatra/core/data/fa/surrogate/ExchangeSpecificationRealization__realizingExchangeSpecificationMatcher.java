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
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecificationRealization__realizingExchangeSpecificationMatch;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecificationRealization__realizingExchangeSpecification pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ExchangeSpecificationRealization__realizingExchangeSpecificationMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Surrogate(feature="realizingExchangeSpecification")
 * pattern ExchangeSpecificationRealization__realizingExchangeSpecification(self : ExchangeSpecificationRealization, target : ExchangeSpecification) {
 * 	ExchangeSpecificationRealization.sourceElement(self, target);
 * }
 * </pre></code>
 * 
 * @see ExchangeSpecificationRealization__realizingExchangeSpecificationMatch
 * @see ExchangeSpecificationRealization__realizingExchangeSpecificationProcessor
 * @see ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher extends BaseMatcher<ExchangeSpecificationRealization__realizingExchangeSpecificationMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher create() throws ViatraQueryException {
    return new ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a ExchangeSpecificationRealization__realizingExchangeSpecificationMatch object.
   * 
   */
  public Collection<ExchangeSpecificationRealization__realizingExchangeSpecificationMatch> getAllMatches(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a ExchangeSpecificationRealization__realizingExchangeSpecificationMatch object, or null if no match is found.
   * 
   */
  public ExchangeSpecificationRealization__realizingExchangeSpecificationMatch getOneArbitraryMatch(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget) {
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
  public boolean hasMatch(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget, final IMatchProcessor<? super ExchangeSpecificationRealization__realizingExchangeSpecificationMatch> processor) {
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
  public boolean forOneArbitraryMatch(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget, final IMatchProcessor<? super ExchangeSpecificationRealization__realizingExchangeSpecificationMatch> processor) {
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
  public ExchangeSpecificationRealization__realizingExchangeSpecificationMatch newMatch(final ExchangeSpecificationRealization pSelf, final ExchangeSpecification pTarget) {
    return ExchangeSpecificationRealization__realizingExchangeSpecificationMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<ExchangeSpecificationRealization> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<ExchangeSpecificationRealization> results = new HashSet<ExchangeSpecificationRealization>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ExchangeSpecificationRealization> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ExchangeSpecificationRealization> getAllValuesOfself(final ExchangeSpecificationRealization__realizingExchangeSpecificationMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ExchangeSpecificationRealization> getAllValuesOfself(final ExchangeSpecification pTarget) {
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
  protected Set<ExchangeSpecification> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<ExchangeSpecification> results = new HashSet<ExchangeSpecification>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ExchangeSpecification> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ExchangeSpecification> getAllValuesOftarget(final ExchangeSpecificationRealization__realizingExchangeSpecificationMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<ExchangeSpecification> getAllValuesOftarget(final ExchangeSpecificationRealization pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected ExchangeSpecificationRealization__realizingExchangeSpecificationMatch tupleToMatch(final Tuple t) {
    try {
        return ExchangeSpecificationRealization__realizingExchangeSpecificationMatch.newMatch((ExchangeSpecificationRealization) t.get(POSITION_SELF), (ExchangeSpecification) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected ExchangeSpecificationRealization__realizingExchangeSpecificationMatch arrayToMatch(final Object[] match) {
    try {
        return ExchangeSpecificationRealization__realizingExchangeSpecificationMatch.newMatch((ExchangeSpecificationRealization) match[POSITION_SELF], (ExchangeSpecification) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected ExchangeSpecificationRealization__realizingExchangeSpecificationMatch arrayToMatchMutable(final Object[] match) {
    try {
        return ExchangeSpecificationRealization__realizingExchangeSpecificationMatch.newMutableMatch((ExchangeSpecificationRealization) match[POSITION_SELF], (ExchangeSpecification) match[POSITION_TARGET]);
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
  public static IQuerySpecification<ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher> querySpecification() throws ViatraQueryException {
    return ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification.instance();
  }
}

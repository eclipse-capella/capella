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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

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
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityRealization__realizedCapabilityMatch;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapabilityRealization__realizedCapabilityQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityRealization__realizedCapability pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link AbstractCapabilityRealization__realizedCapabilityMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="realizedCapability")
 * pattern AbstractCapabilityRealization__realizedCapability(self : AbstractCapabilityRealization, target : AbstractCapability) {
 * 	AbstractCapabilityRealization.targetElement(self, target);
 * }
 * </pre></code>
 * 
 * @see AbstractCapabilityRealization__realizedCapabilityMatch
 * @see AbstractCapabilityRealization__realizedCapabilityProcessor
 * @see AbstractCapabilityRealization__realizedCapabilityQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class AbstractCapabilityRealization__realizedCapabilityMatcher extends BaseMatcher<AbstractCapabilityRealization__realizedCapabilityMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static AbstractCapabilityRealization__realizedCapabilityMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    AbstractCapabilityRealization__realizedCapabilityMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (AbstractCapabilityRealization__realizedCapabilityMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static AbstractCapabilityRealization__realizedCapabilityMatcher create() throws ViatraQueryException {
    return new AbstractCapabilityRealization__realizedCapabilityMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(AbstractCapabilityRealization__realizedCapabilityMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private AbstractCapabilityRealization__realizedCapabilityMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a AbstractCapabilityRealization__realizedCapabilityMatch object.
   * 
   */
  public Collection<AbstractCapabilityRealization__realizedCapabilityMatch> getAllMatches(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a AbstractCapabilityRealization__realizedCapabilityMatch object, or null if no match is found.
   * 
   */
  public AbstractCapabilityRealization__realizedCapabilityMatch getOneArbitraryMatch(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget) {
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
  public boolean hasMatch(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget, final IMatchProcessor<? super AbstractCapabilityRealization__realizedCapabilityMatch> processor) {
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
  public boolean forOneArbitraryMatch(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget, final IMatchProcessor<? super AbstractCapabilityRealization__realizedCapabilityMatch> processor) {
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
  public AbstractCapabilityRealization__realizedCapabilityMatch newMatch(final AbstractCapabilityRealization pSelf, final AbstractCapability pTarget) {
    return AbstractCapabilityRealization__realizedCapabilityMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<AbstractCapabilityRealization> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<AbstractCapabilityRealization> results = new HashSet<AbstractCapabilityRealization>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractCapabilityRealization> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractCapabilityRealization> getAllValuesOfself(final AbstractCapabilityRealization__realizedCapabilityMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractCapabilityRealization> getAllValuesOfself(final AbstractCapability pTarget) {
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
  protected Set<AbstractCapability> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<AbstractCapability> results = new HashSet<AbstractCapability>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractCapability> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractCapability> getAllValuesOftarget(final AbstractCapabilityRealization__realizedCapabilityMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractCapability> getAllValuesOftarget(final AbstractCapabilityRealization pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected AbstractCapabilityRealization__realizedCapabilityMatch tupleToMatch(final Tuple t) {
    try {
        return AbstractCapabilityRealization__realizedCapabilityMatch.newMatch((AbstractCapabilityRealization) t.get(POSITION_SELF), (AbstractCapability) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected AbstractCapabilityRealization__realizedCapabilityMatch arrayToMatch(final Object[] match) {
    try {
        return AbstractCapabilityRealization__realizedCapabilityMatch.newMatch((AbstractCapabilityRealization) match[POSITION_SELF], (AbstractCapability) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected AbstractCapabilityRealization__realizedCapabilityMatch arrayToMatchMutable(final Object[] match) {
    try {
        return AbstractCapabilityRealization__realizedCapabilityMatch.newMutableMatch((AbstractCapabilityRealization) match[POSITION_SELF], (AbstractCapability) match[POSITION_TARGET]);
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
  public static IQuerySpecification<AbstractCapabilityRealization__realizedCapabilityMatcher> querySpecification() throws ViatraQueryException {
    return AbstractCapabilityRealization__realizedCapabilityQuerySpecification.instance();
  }
}

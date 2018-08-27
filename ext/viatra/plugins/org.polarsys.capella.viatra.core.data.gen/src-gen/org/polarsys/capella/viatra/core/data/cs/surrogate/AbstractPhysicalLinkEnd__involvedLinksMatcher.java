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
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinksMatch;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.AbstractPhysicalLinkEnd__involvedLinksQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinks pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link AbstractPhysicalLinkEnd__involvedLinksMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // Start of user code for imports 
 * 
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="involvedLinks")
 * pattern AbstractPhysicalLinkEnd__involvedLinks(self : AbstractPhysicalLinkEnd, target : PhysicalLink) {
 * 	PhysicalLink.linkEnds(target, self);
 * } or {
 * 	PhysicalLinkEnd.port(ple, self);	
 * 	PhysicalLink.linkEnds(target, ple);
 * }
 * </pre></code>
 * 
 * @see AbstractPhysicalLinkEnd__involvedLinksMatch
 * @see AbstractPhysicalLinkEnd__involvedLinksProcessor
 * @see AbstractPhysicalLinkEnd__involvedLinksQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class AbstractPhysicalLinkEnd__involvedLinksMatcher extends BaseMatcher<AbstractPhysicalLinkEnd__involvedLinksMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static AbstractPhysicalLinkEnd__involvedLinksMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    AbstractPhysicalLinkEnd__involvedLinksMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (AbstractPhysicalLinkEnd__involvedLinksMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static AbstractPhysicalLinkEnd__involvedLinksMatcher create() throws ViatraQueryException {
    return new AbstractPhysicalLinkEnd__involvedLinksMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(AbstractPhysicalLinkEnd__involvedLinksMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private AbstractPhysicalLinkEnd__involvedLinksMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a AbstractPhysicalLinkEnd__involvedLinksMatch object.
   * 
   */
  public Collection<AbstractPhysicalLinkEnd__involvedLinksMatch> getAllMatches(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a AbstractPhysicalLinkEnd__involvedLinksMatch object, or null if no match is found.
   * 
   */
  public AbstractPhysicalLinkEnd__involvedLinksMatch getOneArbitraryMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
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
  public boolean hasMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget, final IMatchProcessor<? super AbstractPhysicalLinkEnd__involvedLinksMatch> processor) {
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
  public boolean forOneArbitraryMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget, final IMatchProcessor<? super AbstractPhysicalLinkEnd__involvedLinksMatch> processor) {
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
  public AbstractPhysicalLinkEnd__involvedLinksMatch newMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
    return AbstractPhysicalLinkEnd__involvedLinksMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<AbstractPhysicalLinkEnd> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<AbstractPhysicalLinkEnd> results = new HashSet<AbstractPhysicalLinkEnd>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractPhysicalLinkEnd> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractPhysicalLinkEnd> getAllValuesOfself(final AbstractPhysicalLinkEnd__involvedLinksMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<AbstractPhysicalLinkEnd> getAllValuesOfself(final PhysicalLink pTarget) {
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
  protected Set<PhysicalLink> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<PhysicalLink> results = new HashSet<PhysicalLink>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalLink> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalLink> getAllValuesOftarget(final AbstractPhysicalLinkEnd__involvedLinksMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<PhysicalLink> getAllValuesOftarget(final AbstractPhysicalLinkEnd pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected AbstractPhysicalLinkEnd__involvedLinksMatch tupleToMatch(final Tuple t) {
    try {
        return AbstractPhysicalLinkEnd__involvedLinksMatch.newMatch((AbstractPhysicalLinkEnd) t.get(POSITION_SELF), (PhysicalLink) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected AbstractPhysicalLinkEnd__involvedLinksMatch arrayToMatch(final Object[] match) {
    try {
        return AbstractPhysicalLinkEnd__involvedLinksMatch.newMatch((AbstractPhysicalLinkEnd) match[POSITION_SELF], (PhysicalLink) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected AbstractPhysicalLinkEnd__involvedLinksMatch arrayToMatchMutable(final Object[] match) {
    try {
        return AbstractPhysicalLinkEnd__involvedLinksMatch.newMutableMatch((AbstractPhysicalLinkEnd) match[POSITION_SELF], (PhysicalLink) match[POSITION_TARGET]);
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
  public static IQuerySpecification<AbstractPhysicalLinkEnd__involvedLinksMatcher> querySpecification() throws ViatraQueryException {
    return AbstractPhysicalLinkEnd__involvedLinksQuerySpecification.instance();
  }
}

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
package org.polarsys.capella.viatra.core.data.information.communication.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__accessMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__acquireMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__callMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__consumeMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__executeMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__produceMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__receiveMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__sendMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__transmitMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__writeMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__accessQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__acquireQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__callQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__consumeQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__executeQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__produceQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__receiveQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__sendQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__transmitQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationLinkExchanger__writeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CommunicationLinkExchanger.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CommunicationLinkExchanger.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.communication.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CommunicationLinkExchanger__produce</li>
 * <li>CommunicationLinkExchanger__consume</li>
 * <li>CommunicationLinkExchanger__send</li>
 * <li>CommunicationLinkExchanger__receive</li>
 * <li>CommunicationLinkExchanger__call</li>
 * <li>CommunicationLinkExchanger__execute</li>
 * <li>CommunicationLinkExchanger__write</li>
 * <li>CommunicationLinkExchanger__access</li>
 * <li>CommunicationLinkExchanger__acquire</li>
 * <li>CommunicationLinkExchanger__transmit</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CommunicationLinkExchanger extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CommunicationLinkExchanger instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CommunicationLinkExchanger();
    }
    return INSTANCE;
  }
  
  private static CommunicationLinkExchanger INSTANCE;
  
  private CommunicationLinkExchanger() throws ViatraQueryException {
    querySpecifications.add(CommunicationLinkExchanger__produceQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__consumeQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__sendQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__receiveQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__callQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__executeQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__writeQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__accessQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__acquireQuerySpecification.instance());
    querySpecifications.add(CommunicationLinkExchanger__transmitQuerySpecification.instance());
  }
  
  public CommunicationLinkExchanger__produceQuerySpecification getCommunicationLinkExchanger__produce() throws ViatraQueryException {
    return CommunicationLinkExchanger__produceQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__produceMatcher getCommunicationLinkExchanger__produce(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__produceMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__consumeQuerySpecification getCommunicationLinkExchanger__consume() throws ViatraQueryException {
    return CommunicationLinkExchanger__consumeQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__consumeMatcher getCommunicationLinkExchanger__consume(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__consumeMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__sendQuerySpecification getCommunicationLinkExchanger__send() throws ViatraQueryException {
    return CommunicationLinkExchanger__sendQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__sendMatcher getCommunicationLinkExchanger__send(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__sendMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__receiveQuerySpecification getCommunicationLinkExchanger__receive() throws ViatraQueryException {
    return CommunicationLinkExchanger__receiveQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__receiveMatcher getCommunicationLinkExchanger__receive(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__receiveMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__callQuerySpecification getCommunicationLinkExchanger__call() throws ViatraQueryException {
    return CommunicationLinkExchanger__callQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__callMatcher getCommunicationLinkExchanger__call(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__callMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__executeQuerySpecification getCommunicationLinkExchanger__execute() throws ViatraQueryException {
    return CommunicationLinkExchanger__executeQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__executeMatcher getCommunicationLinkExchanger__execute(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__executeMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__writeQuerySpecification getCommunicationLinkExchanger__write() throws ViatraQueryException {
    return CommunicationLinkExchanger__writeQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__writeMatcher getCommunicationLinkExchanger__write(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__writeMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__accessQuerySpecification getCommunicationLinkExchanger__access() throws ViatraQueryException {
    return CommunicationLinkExchanger__accessQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__accessMatcher getCommunicationLinkExchanger__access(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__accessMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__acquireQuerySpecification getCommunicationLinkExchanger__acquire() throws ViatraQueryException {
    return CommunicationLinkExchanger__acquireQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__acquireMatcher getCommunicationLinkExchanger__acquire(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__acquireMatcher.on(engine);
  }
  
  public CommunicationLinkExchanger__transmitQuerySpecification getCommunicationLinkExchanger__transmit() throws ViatraQueryException {
    return CommunicationLinkExchanger__transmitQuerySpecification.instance();
  }
  
  public CommunicationLinkExchanger__transmitMatcher getCommunicationLinkExchanger__transmit(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__transmitMatcher.on(engine);
  }
}

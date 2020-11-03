/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.information.communication.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__access;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__acquire;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__call;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__consume;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__execute;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__produce;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__receive;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__send;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__transmit;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__write;

/**
 * A pattern group formed of all public patterns defined in CommunicationLinkExchanger.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CommunicationLinkExchanger extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CommunicationLinkExchanger instance() {
    if (INSTANCE == null) {
        INSTANCE = new CommunicationLinkExchanger();
    }
    return INSTANCE;
  }
  
  private static CommunicationLinkExchanger INSTANCE;
  
  private CommunicationLinkExchanger() {
    querySpecifications.add(CommunicationLinkExchanger__produce.instance());
    querySpecifications.add(CommunicationLinkExchanger__consume.instance());
    querySpecifications.add(CommunicationLinkExchanger__send.instance());
    querySpecifications.add(CommunicationLinkExchanger__receive.instance());
    querySpecifications.add(CommunicationLinkExchanger__call.instance());
    querySpecifications.add(CommunicationLinkExchanger__execute.instance());
    querySpecifications.add(CommunicationLinkExchanger__write.instance());
    querySpecifications.add(CommunicationLinkExchanger__access.instance());
    querySpecifications.add(CommunicationLinkExchanger__acquire.instance());
    querySpecifications.add(CommunicationLinkExchanger__transmit.instance());
  }
  
  public CommunicationLinkExchanger__produce getCommunicationLinkExchanger__produce() {
    return CommunicationLinkExchanger__produce.instance();
  }
  
  public CommunicationLinkExchanger__produce.Matcher getCommunicationLinkExchanger__produce(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__produce.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__consume getCommunicationLinkExchanger__consume() {
    return CommunicationLinkExchanger__consume.instance();
  }
  
  public CommunicationLinkExchanger__consume.Matcher getCommunicationLinkExchanger__consume(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__consume.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__send getCommunicationLinkExchanger__send() {
    return CommunicationLinkExchanger__send.instance();
  }
  
  public CommunicationLinkExchanger__send.Matcher getCommunicationLinkExchanger__send(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__send.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__receive getCommunicationLinkExchanger__receive() {
    return CommunicationLinkExchanger__receive.instance();
  }
  
  public CommunicationLinkExchanger__receive.Matcher getCommunicationLinkExchanger__receive(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__receive.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__call getCommunicationLinkExchanger__call() {
    return CommunicationLinkExchanger__call.instance();
  }
  
  public CommunicationLinkExchanger__call.Matcher getCommunicationLinkExchanger__call(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__call.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__execute getCommunicationLinkExchanger__execute() {
    return CommunicationLinkExchanger__execute.instance();
  }
  
  public CommunicationLinkExchanger__execute.Matcher getCommunicationLinkExchanger__execute(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__execute.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__write getCommunicationLinkExchanger__write() {
    return CommunicationLinkExchanger__write.instance();
  }
  
  public CommunicationLinkExchanger__write.Matcher getCommunicationLinkExchanger__write(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__write.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__access getCommunicationLinkExchanger__access() {
    return CommunicationLinkExchanger__access.instance();
  }
  
  public CommunicationLinkExchanger__access.Matcher getCommunicationLinkExchanger__access(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__access.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__acquire getCommunicationLinkExchanger__acquire() {
    return CommunicationLinkExchanger__acquire.instance();
  }
  
  public CommunicationLinkExchanger__acquire.Matcher getCommunicationLinkExchanger__acquire(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__acquire.Matcher.on(engine);
  }
  
  public CommunicationLinkExchanger__transmit getCommunicationLinkExchanger__transmit() {
    return CommunicationLinkExchanger__transmit.instance();
  }
  
  public CommunicationLinkExchanger__transmit.Matcher getCommunicationLinkExchanger__transmit(final ViatraQueryEngine engine) {
    return CommunicationLinkExchanger__transmit.Matcher.on(engine);
  }
}

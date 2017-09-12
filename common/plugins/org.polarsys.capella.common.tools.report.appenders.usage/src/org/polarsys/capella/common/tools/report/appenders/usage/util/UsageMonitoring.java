/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.usage.util;

public class UsageMonitoring {
  private String applicationName;
  private String applicationVersion;
  private String eventName;
  private String eventContext;
  private String eventStatus;
  private String addendum;
  
  public UsageMonitoring(final String applicationName, final String applicationVersion, final String eventName, final String eventContext, final String eventStatus, final String addendum) {
  this.applicationName = applicationName;
    this.applicationVersion = applicationVersion;
    this.eventName = eventName;
    this.eventContext = eventContext;
    this.eventStatus = eventStatus;
    this.addendum = addendum;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getApplicationVersion() {
    return applicationVersion;
  }

  public void setApplicationVersion(String applicationVersion) {
    this.applicationVersion = applicationVersion;
  }
  
  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }
  
  public String getEventContext() {
    return eventContext;
  }

  public void setEventContext(String eventContext) {
    this.eventContext = eventContext;
  }

  public String getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(String eventStatus) {
    this.eventStatus = eventStatus;
  }
  
  public String getAddendum() {
    return addendum;
  }

  public void setAddendum(String addendum) {
    this.addendum = addendum;
  }
}

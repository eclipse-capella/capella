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
  private String toolName;
  private String toolVersion;
  private String context;
  private String event;
  private String status;

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public UsageMonitoring(final String toolName, final String toolVersion, final String event, final String context) {
    this.toolName = toolName;
    this.toolVersion = toolVersion;
    this.event = event;
    this.context = context;
  }

  public UsageMonitoring(final String toolName, final String toolVersion, final String event, final String context,
      final String status) {
    this.toolName = toolName;
    this.toolVersion = toolVersion;
    this.event = event;
    this.context = context;
    this.status = status;
  }

  public String getToolName() {
    return toolName;
  }

  public void setToolName(String toolName) {
    this.toolName = toolName;
  }

  public String getToolVersion() {
    return toolVersion;
  }

  public void setToolVersion(String toolVersion) {
    this.toolVersion = toolVersion;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

}

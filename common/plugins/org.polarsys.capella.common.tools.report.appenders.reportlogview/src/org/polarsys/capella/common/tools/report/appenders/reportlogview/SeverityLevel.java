/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import org.apache.log4j.Level;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

/**
 * Severity levels for the report log view (marker view)
 */
public enum SeverityLevel {

  DEBUG("Debug", MarkerViewPlugin.getDefault().getImage("debug.gif")), //$NON-NLS-1$ //$NON-NLS-2$
  INFO("Info", MarkerViewPlugin.getDefault().getImage("inform.gif")), //$NON-NLS-1$ //$NON-NLS-2$
  WARNING("Warning", MarkerViewPlugin.getDefault().getImage("warn.gif")), //$NON-NLS-1$ //$NON-NLS-2$
  ERROR("Error", MarkerViewPlugin.getDefault().getImage("error.gif")), //$NON-NLS-1$ //$NON-NLS-2$
  FATAL("Fatal", MarkerViewPlugin.getDefault().getImage("fatal.gif")); //$NON-NLS-1$ //$NON-NLS-2$

  SeverityLevel(String representation, Image image) {
    this.representation = representation;
    this.image = image;
  }

  private Image image;
  private String representation;

  @SuppressWarnings("boxing")
  protected static SeverityLevel getLevel(IMarker marker) {
    SeverityLevel result = null;
    try {
      Object severity = marker.getAttribute(IMarker.SEVERITY);
      if (severity == null) {
        return null;
      }
      if (severity instanceof Integer) {
        switch ((Integer) severity) {
          case IMarker.SEVERITY_ERROR:
            result = ERROR;
          break;
          case IMarker.SEVERITY_WARNING:
            result = WARNING;
          break;
          case IMarker.SEVERITY_INFO:
            result = INFO;
          break;
        }
      } else if (severity instanceof Level) {
        // This is a violation of the API for IMarker.Severity
        // which states the attribute is a _number_. We also want
        // to integrate Log4J levels..
        result = getLevel((Level) severity);
      }
    } catch (CoreException exception) {
      exception.printStackTrace();
    }
    return result;
  }

  protected static SeverityLevel getLevel(Level level) {
    SeverityLevel result = null;
    if (level.equals(Level.DEBUG)) {
      result = DEBUG;
    } else if (level.equals(Level.INFO)) {
      result = INFO;
    } else if (level.equals(Level.WARN)) {
      result = WARNING;
    } else if (level.equals(Level.ERROR)) {
      result = ERROR;
    } else if (level.equals(Level.FATAL)) {
      result = FATAL;
    }
    return result;
  }

  @Override
  public String toString() {
    return representation;
  }

  /**
   * @return
   */
  public Image getImage() {
    return image;
  }

}

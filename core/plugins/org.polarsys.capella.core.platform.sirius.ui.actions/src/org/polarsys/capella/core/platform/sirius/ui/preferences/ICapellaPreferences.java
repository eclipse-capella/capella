/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Overall Capella Preferences constants.
 */
public interface ICapellaPreferences {
  /**
   * Detection version preference related to Capella detection version mechanism.
   */
  public static final String PREFERENCE_DETECTION_VERSION = "DetectionVersion"; //$NON-NLS-1$

  /**
   * Preference constant for Capella Aird fragment file extension.<br>
   * Either {@link CapellaResourceHelper#AIRD_FILE_EXTENSION} or {@link CapellaResourceHelper#AIRD_FRAGMENT_FILE_EXTENSION}.
   */
  public static final String PREFERENCE_CAPELLA_AIRD_FRAGMENT_FILE_EXTENSION = "CapellaAirdFragmentFileExtension"; //$NON-NLS-1$
}

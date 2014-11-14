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
package org.polarsys.capella.core.command.recorder.ui.handler;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;

import org.polarsys.capella.common.command.recorder.core.RecorderCoreActivator;
import org.polarsys.capella.common.command.recorder.core.exception.RecorderException;
import org.polarsys.capella.common.command.recorder.core.manager.IRecorderManager;
import org.polarsys.capella.common.command.recorder.core.preferences.IRecorderCorePreferenceConstants;
import org.polarsys.capella.common.command.recorder.core.preferences.RecorderCorePreferenceServices;
import org.polarsys.capella.core.command.recorder.internal.CapellaProjectRecorderManager;
import org.polarsys.capella.core.command.recorder.internal.utils.CapellaProjectUtils;

public class CapellaRecorderHandler implements IPreferenceChangeListener {

  /** Singleton */
  public static CapellaRecorderHandler INSTANCE = new CapellaRecorderHandler();

  /** the recorder manager */
  IRecorderManager _recorderManager = null;

  /** Preferences */
  protected IEclipsePreferences _node;

  /** for internal use */
  private void initRecorderManager() {
    
    if ( null == _recorderManager) {    
      // Project natures to match
      final Collection<String> natures = Collections.singleton(CapellaProjectUtils.getCapellaProjectNatureId());
      // The manager itself
      _recorderManager = new CapellaProjectRecorderManager(natures);
    }
    
  }
  
  /** start/stop manager in accordance with the recorder state preference. */
  public void startUp() {
    
    initRecorderManager();
    
    try {
      if (RecorderCorePreferenceServices.isRecorderShouldBeRunning()) {
        _recorderManager.startup();
      } else {
        _recorderManager.shutDown();
      }
    } catch (RecorderException exception_p) {
      // do nothing
    }

    return;
  }

  /** handled recorder manager */
  public IRecorderManager getRecorder() {
    return _recorderManager;
  }

  /**
   * Constructor
   */
  protected CapellaRecorderHandler() {

    _node = new InstanceScope().getNode(RecorderCoreActivator.getDefault().getPluginId());

    // Registering to preferences
    _node.addPreferenceChangeListener(this);

  }

  /**
   * {@inheritDoc}
   */
  public void preferenceChange(PreferenceChangeEvent event_p) {

    if (event_p.getKey().equals(IRecorderCorePreferenceConstants.RECORDER_STATE_PREF_ID)) {
      boolean newState = RecorderCorePreferenceServices.isRecorderShouldBeRunning();

      initRecorderManager();
      
      try {
        if (true == newState) {
          _recorderManager.startup();
        } else {
          _recorderManager.shutDown();
        }
      } catch (RecorderException exception_p) {
        // Do nothing
      }

    } else if ( event_p.getKey().equals(IRecorderCorePreferenceConstants.RECORDER_ROOT_PATH_PREF_ID) ) {
      try {
        _recorderManager.shutDown();
        _recorderManager.startup();
      } catch (RecorderException exception_p) {
        // Do nothing
      }
    }

    return;
  }

}

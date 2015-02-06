/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesInitializer;
import org.polarsys.capella.core.model.preferences.IInterModelIntegrityPreferences;
import org.polarsys.capella.test.framework.api.BasicTestCaseWithModelPreLoading;

/**
 * @author Erwan Brottier
 */
public abstract class CheckInterModelInconsistencyDetectionTestCase extends BasicTestCaseWithModelPreLoading {

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}
	
	protected void setDetectorActive(boolean state) {
		CapellaModelPreferencesInitializer preferencesInitializer = new CapellaModelPreferencesInitializer();
    preferencesInitializer.initializeDefaultPreferences();
    preferencesInitializer.putBoolean(IInterModelIntegrityPreferences.PREFS_PREVENT_ON_THE_FLY_DEPENDENCY_VIOLATION, state, ProjectScope.class);
	}
	
	protected SystemEngineering getSystemEngineering(Project project) {
		for (EObject object : project.eContents())
			if (object instanceof SystemEngineering)
				return (SystemEngineering) object;
		return null;
	}

	protected Class getClassNamed(String className, DataPkg pck) {
		for (Class clazz : pck.getOwnedClasses())
			if (clazz.getName().equals(className))
				return clazz;
		return null;
	}

  @Override
  protected void tearDown() throws Exception {
  	for (String modelName : getProjectNamesToLoad())
  		getSessionForLoadedCapellaModel(modelName).save(new NullProgressMonitor());
  	super.tearDown();
  }
	
}

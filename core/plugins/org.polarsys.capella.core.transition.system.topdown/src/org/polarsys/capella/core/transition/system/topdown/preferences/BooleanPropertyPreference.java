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
package org.polarsys.capella.core.transition.system.topdown.preferences;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.property.IDefaultValueProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 */
public class BooleanPropertyPreference extends AbstractProperty implements IEditableProperty, IDefaultValueProperty{
	  
	private static String pluginId ="org.polarsys.capella.core.projection.preferences";
	
	private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
	
	private static ScopedCapellaPreferencesStore store;
	
	private static Boolean newWorkspace = true;

	IProject project;

	public BooleanPropertyPreference() {
		super();
	    this.project =
	            PreferencesHelper.getSelectedEclipseProject() != null ? PreferencesHelper.getSelectedEclipseProject() : PreferencesHelper.getSelectedCapellaProject();
		store = ScopedCapellaPreferencesStore.getInstance(pluginId);
	}
	
  /**
   * {@inheritDoc}
   */
  public Object getType() {
    return Boolean.class;
  }

  public IScopeContext getScope() {
	  //TODO FIX THIS
	    return new ProjectScope(this.project);
	  }

  /**
   * {@inheritDoc}
   */
  public Object getValue(IPropertyContext context_p) {
	  //TODO FIX THIS
	  String scope= getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);
	 
	  
	  String preferenceId = getId();
	    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
	      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
	    }
	    return ScopedCapellaPreferencesStore.getInstance(scope).getBoolean(preferenceId);
//	    IProject project = PreferencesHelper.getSelectedCapellaProject();
//
//	  if (project!=null){
//	  Boolean inProjectScope= this.getScope().getClass().equals(ProjectScope.class);
//	  return ScopedCapellaPreferencesStore.getBoolean(inProjectScope, key);
//	  }
//	  return ScopedCapellaPreferencesStore.getBoolean(true, key);
  }
  
  /**
   * {@inheritDoc}
   */
public void setValue(IPropertyContext context_p) {
	  
	  String scope= getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);

	  Object value = context_p.getCurrentValue(this);
	  String preferenceId = getId();
	    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
	      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
	    }
	     ScopedCapellaPreferencesStore.getInstance(scope).setValue(preferenceId, ((Boolean)toType(value, context_p)).booleanValue());
  }
  
  /**
   * {@inheritDoc}
   */
  public Object toType(Object value_p, IPropertyContext context_p) {
	    Boolean value = Boolean.TRUE;
	    try {
	      if (value_p instanceof Boolean) {
	        value = (Boolean) value_p;
	      } else if (value_p instanceof String) {
	        value = Boolean.valueOf((String) value_p);
	      }
	    } catch (Exception e) {
	      //Nothing here
	    }
	    return value;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
	    if (newValue_p == null) {
	      return Status.CANCEL_STATUS;
	    }
	    return Status.OK_STATUS;
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  public Object getDefaultValue(IPropertyContext context_p) {
	    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
	    return ((Boolean) toType(argument, context_p)).booleanValue();
	  }

//	  /**
//	   * @param value
//	   * @param key
//	   */
//	  private void initializeAllOpenedProjects(String key, Object value) {
//	    IProject[] iProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
//
//	    // initialize default scope
//	    if (value instanceof Boolean) {
//	      ScopedCapellaPreferencesStore.putBoolean(null, key, ((Boolean) value).booleanValue());
//	    } else if (value instanceof String) {
//	      ScopedCapellaPreferencesStore.putString(null, key, ((String) value));
//	    }
//
//	    // initialize project scopes
//	    for (IProject iProject : iProjects) {
//	      if (iProject.isOpen() && iProject.isAccessible() && (CapellaResourceHelper.isCapellaProject(iProject))) {
//	        if (value instanceof Boolean) {
//	          ScopedCapellaPreferencesStore.putBoolean(iProject, key, ((Boolean) value).booleanValue());
//	        } else if (value instanceof String) {
//	          ScopedCapellaPreferencesStore.putString(iProject, key, (String) value);
//	        }
//	      }
//	   	 }
//		}

}

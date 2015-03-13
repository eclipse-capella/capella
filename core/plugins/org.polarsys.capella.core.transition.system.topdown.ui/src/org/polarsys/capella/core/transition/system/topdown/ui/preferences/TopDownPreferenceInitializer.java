package org.polarsys.capella.core.transition.system.topdown.ui.preferences;

import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.IDefaultValueProperty;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;

public class TopDownPreferenceInitializer extends AbstractPreferencesInitializer {
	  /**
	   */
	  public TopDownPreferenceInitializer() {
	    super(org.polarsys.capella.core.transition.system.topdown.ui.Activator.PLUGIN_ID);
	  }

	  /**
	   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	   */
	  @Override
	  public void initializeDefaultPreferences() {
		  
		   IProperties _properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
		   IPropertyContext _context = new PropertyContext(_properties);
		   for (IProperty property : _properties.getAllItems()) {
			   if (property instanceof IDefaultValueProperty) {
				   _context.setCurrentValue(property, ((IDefaultValueProperty)property).getDefaultValue(_context));
			   }
		   }
		    _context.writeAll();
			ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID).save();
	  }
	}

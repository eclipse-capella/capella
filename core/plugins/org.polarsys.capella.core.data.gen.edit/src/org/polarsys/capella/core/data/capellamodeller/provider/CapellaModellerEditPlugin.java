/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.capellamodeller.provider;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.polarsys.capella.common.data.activity.provider.ActivityEditPlugin;
import org.polarsys.capella.common.data.behavior.provider.BehaviorEditPlugin;
import org.polarsys.capella.common.data.modellingcore.provider.ModellingCoreEditPlugin;
import org.polarsys.kitalpha.emde.model.edit.provider.EmdeEditPlugin;

/**
 * This is the central singleton for the CapellaModeller edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class CapellaModellerEditPlugin extends EMFPlugin {
	/**
   * Keep track of the singleton.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final CapellaModellerEditPlugin INSTANCE = new CapellaModellerEditPlugin();

	/**
   * Keep track of the singleton.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static Implementation plugin;

	/**
   * Create the instance.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellaModellerEditPlugin() {
    super
      (new ResourceLocator [] {
         ModellingCoreEditPlugin.INSTANCE,
         EmdeEditPlugin.INSTANCE,
         ActivityEditPlugin.INSTANCE,
         BehaviorEditPlugin.INSTANCE,
       });
  }

	/**
   * Returns the singleton instance of the Eclipse plugin.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the singleton instance.
   * @generated
   */
	@Override
	public ResourceLocator getPluginResourceLocator() {
    return plugin;
  }

	/**
   * Returns the singleton instance of the Eclipse plugin.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the singleton instance.
   * @generated
   */
	public static Implementation getPlugin() {
    return plugin;
  }

	/**
   * The actual implementation of the Eclipse <b>Plugin</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static class Implementation extends EclipsePlugin {
		/**
     * Creates an instance.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		public Implementation() {
      super();

      // Remember the static instance.
      //
      plugin = this;
    }
		
		//begin-capella-code
		/**
		 * Returns the object's graphical representation.
		 * (This method comes from a customization of the standard EMF generator)
		 *
		 * @param key the identifier of the object.
		 * @generated
		 */
		@Override
		public Object getImage(String key) {
      return FileLocator.find(this.getBundle(), new Path("icons/" + key + ".gif"), null);
    }
		//end-capella-code
	}

}

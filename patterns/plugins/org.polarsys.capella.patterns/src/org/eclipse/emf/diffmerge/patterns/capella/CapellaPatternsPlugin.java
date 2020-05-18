/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella;

import java.util.Collections;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.diffmerge.patterns.capella.validation.ValidatorAdapter;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonPatternInstance;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonpatternsupportPackage;
import org.eclipse.emf.ecore.EValidator;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl;
import org.polarsys.capella.core.validation.CapellaValidationActivator;


/**
 * The activator class for this plug-in
 */
public class CapellaPatternsPlugin extends Plugin {
  
	/** The shared instance */
	private static CapellaPatternsPlugin __plugin;
	
	/**
	 * Specific adapter factory, see plug-in initialization
	 */
	private static class CapellaPatternsAdapterFactory implements IAdapterFactory {
	  /**
	   * Constructor
	   */
	  public CapellaPatternsAdapterFactory() {
	    // Nothing specific
	  }
    /**
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getAdapter(Object adaptableObject_p, Class adapterType_p) {
      Object result = null;
      if (adaptableObject_p instanceof CommonPatternInstance &&
          ModelElement.class.equals(adapterType_p))
        result = new DummyModelElement();
      return result;
    }
    /**
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
     */
    public Class<?>[] getAdapterList() {
      return new Class<?>[] { ModelElement.class };
    }
	}
  /**
   * Specific adapter class, see plug-in initialization
   */
	private static class DummyModelElement extends ModelElementImpl {
	  /**
	   * Constructor
	   */
	  public DummyModelElement() {
	    // Nothing specific
	  }
	}
	
	
	/**
	 * Constructor
	 */
	public CapellaPatternsPlugin() {
    Platform.getAdapterManager().registerAdapters(
        new CapellaPatternsAdapterFactory(), CommonPatternInstance.class);
	}
	
  /**
   * Return the shared instance of the activator
   */
  public static CapellaPatternsPlugin getDefault() {
    return __plugin;
  }
  
  /**
   * Return the ID of this plug-in according to MANIFEST.MF
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
  
  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
    EValidator.Registry.INSTANCE.put(
        ModellingcorePackage.eINSTANCE, new ValidatorAdapter());
    CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().registerAdditionalPackages(Collections.singletonList(CommonpatternsupportPackage.eINSTANCE));
		__plugin = this;
	}
  
	/**
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		__plugin = null;
		super.stop(context);
	}
	
}

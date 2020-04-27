/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


/**
 * The activator for this plug-in.
 * This class uses tricks and restriction violations in order to correctly interact
 * with the Eclipse ClearCase plugins if they are present.
 */
@SuppressWarnings("restriction")
public class CapellaComparePlugin extends AbstractUIPlugin {
  
	/** The plug-in ID */
	public static final String PLUGIN_ID = "org.polarsys.capella.core.compare"; //$NON-NLS-1$
  
	/** The shared instance */
	private static CapellaComparePlugin plugin;
	
	/** The constant identifying ClearCase projects */
	protected static final String CC_PROJECT_NATURE = "com.rational.clearcase.ccprovider_nature"; //$NON-NLS-1$
	
	/** The (potentially null) Eclipse projects dedicated to referencing files outside the workspace */
	protected final Map<CapellaScope, IProject> proxyProjects;
  
  
	/**
	 * Constructor
	 */
	public CapellaComparePlugin() {
	  proxyProjects = new HashMap<CapellaScope, IProject>();
	}
  
	/**
	 * Clean up the proxy projects for no side effect between successive executions
	 */
	public void cleanupProxyProjects() {
	  for (IProject proxyProject : proxyProjects.values()) {
	    try {
	      RepositoryProvider.unmap(proxyProject);
	    } catch (CoreException e) {
	      // Ignore
	    }
	    try {
	      proxyProject.delete(true, true, null);
	    } catch (CoreException e) {
	      // Ignore
	    }
	  }
	}
	
  /**
   * Return the shared instance
   * @return a non-null instance of this class
   */
  public static CapellaComparePlugin getDefault() {
    return plugin;
  }
  
  /**
   * Return an Eclipse project dedicated to referencing files outside the workspace
   * @param scope the scope that requires the project
   * @param externalFilePath the path of one of the files outside the workspace
   * @return a potentially null Eclipse project
   */
  public IProject getProxyProject(CapellaScope scope, IPath externalFilePath) {
    IProject result = proxyProjects.get(scope);
    if (result == null) {
      IWorkspace wk = ResourcesPlugin.getWorkspace();
      if (wk != null && wk.getRoot() != null) {
        result = wk.getRoot().getProject(
            "DiffMergeExternalFiles_" + System.identityHashCode(scope)); //$NON-NLS-1$
        try {
          if (!result.exists())
            result.create(null);
          if (!result.isOpen())
            result.open(null);
          forceClearCaseNature(result, externalFilePath);
          proxyProjects.put(scope, result);
        } catch (TeamException e) {
          // Team nature could not be changed: proceed
        } catch (CoreException e) {
          // Failed to properly configure the project
          result = null;
        }
      }
    }
    return result;
  }
  
  /**
   * Make the ClearCase plugins, if present, identify the given project as a
   * ClearCase project associated to the view of the file at the given path.
   * This is really a trick.
   * @param project a non-null project
   * @param referencePath a non-null path
   * @return whether the operation succeeded
   */
  protected boolean forceClearCaseNature(IProject project, IPath referencePath) {
    boolean result = false;
    try {
      RepositoryProvider.map(project, CC_PROJECT_NATURE);
      ProxyProjectWrapper wrapper = new ProxyProjectWrapper(project, referencePath);
      // We use reflexivity since we don't have access to the API of the CC plugin
      Bundle ccBundle = Platform.getBundle("com.rational.clearcase"); //$NON-NLS-1$
      Class<?> ccClass = ccBundle.loadClass("com.rational.clearcase.ClearCasePlugin"); //$NON-NLS-1$
      Class<?> srvClass = ccBundle.loadClass("com.rational.clearcase.RSCMService"); //$NON-NLS-1$
      Object service = ccClass.getMethod("getCMService").invoke(null); //$NON-NLS-1$
      Object eval = srvClass.getMethod("connectProject", IProject.class).invoke( //$NON-NLS-1$
          service, wrapper);
      result = eval instanceof Boolean && ((Boolean)eval).booleanValue();
    } catch (Exception e) {
      // CC not loaded
    }
    return result;
  }
  
	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
  @Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}
  
	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
  @Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		cleanupProxyProjects();
	}
  
  
  /**
   * Fake projects that wrap an actual project and bind it to a user-defined location.
   * This is a trick to make ClearCase believe a project is in a given ClearCase view.
   */
  protected static class ProxyProjectWrapper extends Project {
    /** The non-null wrapped project */
    private final IProject wrappedProject;
    /** The non-null location */
    private final IPath specificLocation;
    /**
     * Constructor
     * @param wrappedProjectParam the non-null project being wrapped
     * @param specificLocationParam the non-null fake location of the project
     */
    public ProxyProjectWrapper(IProject wrappedProjectParam, IPath specificLocationParam) {
      super(wrappedProjectParam.getFullPath(), (Workspace)wrappedProjectParam.getWorkspace());
      this.wrappedProject = wrappedProjectParam;
      this.specificLocation = specificLocationParam;
    }
    /**
     * @see org.eclipse.core.internal.resources.Resource#getLocation()
     */
    @Override
    public IPath getLocation() {
      return specificLocation;
    }
    /**
     * Return the project being wrapped
     * @return a non-null project
     */
    public IProject getWrappedProject() {
      return wrappedProject;
    }
    /**
     * @see org.eclipse.core.internal.resources.Resource#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object peer) {
      boolean result = false;
      if (peer instanceof ProxyProjectWrapper)
        result = ((ProxyProjectWrapper)peer).getWrappedProject().equals(getWrappedProject());
      else if (peer instanceof IProject)
        result = ((IProject)peer).equals(getWrappedProject());
      return result;
    }
    /**
     * @see org.eclipse.core.internal.resources.Resource#hashCode()
     */
    @Override
    public int hashCode() {
      return getWrappedProject().hashCode();
    }
  }
  
}

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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.tig.efprovider.TigEfProvider;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;


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
	protected final Map<CapellaScope, IProject> _proxyProjects;
  
  
	/**
	 * Constructor
	 */
	public CapellaComparePlugin() {
	  _proxyProjects = new HashMap<CapellaScope, IProject>();
	}
  
	/**
	 * Clean up the proxy projects for no side effect between successive executions
	 */
	public void cleanupProxyProjects() {
	  for (IProject proxyProject : _proxyProjects.values()) {
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
   * Return the Capella editing domain
   * @return a potentially (but normally not) null editing domain
   */
  public TransactionalEditingDomain getEditingDomain() {
    TransactionalEditingDomain result = null;
    String editingDomainId = TigEfProvider.getExecutionManagerName();
    ExecutionManager em =
      ExecutionManagerRegistry.getInstance().getExecutionManager(editingDomainId);
    if (em != null)
      result = em.getEditingDomain();
    return result;
  }
  
  /**
   * Return an Eclipse project dedicated to referencing files outside the workspace
   * @param scope_p the scope that requires the project
   * @param externalFilePath_p the path of one of the files outside the workspace
   * @return a potentially null Eclipse project
   */
  public IProject getProxyProject(CapellaScope scope_p, IPath externalFilePath_p) {
    IProject result = _proxyProjects.get(scope_p);
    if (result == null) {
      IWorkspace wk = ResourcesPlugin.getWorkspace();
      if (wk != null && wk.getRoot() != null) {
        result = wk.getRoot().getProject(
            "DiffMergeExternalFiles_" + System.identityHashCode(scope_p)); //$NON-NLS-1$
        try {
          if (!result.exists())
            result.create(null);
          if (!result.isOpen())
            result.open(null);
          forceClearCaseNature(result, externalFilePath_p);
          _proxyProjects.put(scope_p, result);
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
   * @param project_p a non-null project
   * @param referencePath_p a non-null path
   * @return whether the operation succeeded
   */
  protected boolean forceClearCaseNature(IProject project_p, IPath referencePath_p) {
    boolean result = false;
    try {
      RepositoryProvider.map(project_p, CC_PROJECT_NATURE);
      ProxyProjectWrapper wrapper = new ProxyProjectWrapper(project_p, referencePath_p);
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
    private final IProject _wrappedProject;
    /** The non-null location */
    private final IPath _specificLocation;
    /**
     * Constructor
     * @param wrappedProject_p the non-null project being wrapped
     * @param specificLocation_p the non-null fake location of the project
     */
    public ProxyProjectWrapper(IProject wrappedProject_p, IPath specificLocation_p) {
      super(wrappedProject_p.getFullPath(), (Workspace)wrappedProject_p.getWorkspace());
      _wrappedProject = wrappedProject_p;
      _specificLocation = specificLocation_p;
    }
    /**
     * @see org.eclipse.core.internal.resources.Resource#getLocation()
     */
    @Override
    public IPath getLocation() {
      return _specificLocation;
    }
    /**
     * Return the project being wrapped
     * @return a non-null project
     */
    public IProject getWrappedProject() {
      return _wrappedProject;
    }
    /**
     * @see org.eclipse.core.internal.resources.Resource#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object peer_p) {
      boolean result = false;
      if (peer_p instanceof ProxyProjectWrapper)
        result = ((ProxyProjectWrapper)peer_p).getWrappedProject().equals(getWrappedProject());
      else if (peer_p instanceof IProject)
        result = ((IProject)peer_p).equals(getWrappedProject());
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

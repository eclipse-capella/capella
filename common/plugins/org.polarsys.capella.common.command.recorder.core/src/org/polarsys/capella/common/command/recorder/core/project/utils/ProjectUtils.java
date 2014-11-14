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
package org.polarsys.capella.common.command.recorder.core.project.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Utility class for {@link IProject}
 *
 */
public class ProjectUtils {
  
  /**
   * Check whether a {@link IProject} nature is of given type.
   * @param project_p the target {@link IProject}
   * @param natureIdToMatch_p the nature to match id.
   * @return <code>true</code> whether no nature was defined (e.g. empty or <code>null</code> value as argument.)
   */
  public static boolean isProjectOfType(final IProject project_p, final String natureIdToMatch_p) {
    return 
      null == natureIdToMatch_p || natureIdToMatch_p.equals(ICommonConstants.EMPTY_STRING) ?
         true :
         isProjectOfType(project_p, Collections.singleton(natureIdToMatch_p)
    );
  }
  
  /**
   * Check whether a {@link IProject} nature is of given type.
   * @param project_p the target {@link IProject}
   * @param natureIdsToMatch_p the nature to match ids.
   * @return <code>true</code> whether no nature was defined (e.g. empty or <code>null</code> value as argument.)
   */
  public static boolean isProjectOfType(final IProject project_p, final Collection<String> natureIdsToMatch_p) {
    
    boolean result = false;
    
    if ( // No filtering on nature
        null == natureIdsToMatch_p ||
        natureIdsToMatch_p.isEmpty()
    ) {
      return true;
    }
    
    try {
      String[] projectNatures = project_p.getDescription().getNatureIds();
      result = matche(projectNatures, natureIdsToMatch_p);
    } catch (CoreException exception_p) {
      result = false;
    }
    
    return result;
  }
   
  /**
   * Get all accessible project projects on workspace of a given nature. 
   * @param natureIdToMatch_p the type of project id (or <code>null</code>)
   * @return an empty {@link Collection} whether no result was found.
   */
  public static Collection<IProject> getAllProjectsOfType(String natureIdToMatch_p) {
    return getAllProjectsOfType(Collections.singleton(natureIdToMatch_p));
  }
  
  /**
   * Get all accessible project projects on workspace of a given nature. 
   * @param natureIdsToMatch_p the type of project ids (or <code>null</code>)
   * @return an empty {@link Collection} whether no result was found.
   */
  public static Collection<IProject> getAllProjectsOfType(final Collection<String> natureIdsToMatch_p) {
    
    Set<IProject> result = new HashSet<IProject>();
    
    IWorkspace root = ResourcesPlugin.getWorkspace();
    IProject[] projects = root.getRoot().getProjects();

    boolean shouldBeFilteredWithNature = 
      null != natureIdsToMatch_p && 
      !natureIdsToMatch_p.isEmpty()
    ;
    
    IProject project = null;
    boolean keep = false;
    for( int i =0; i < projects.length; i++) {
      keep = false;
      project = projects[i];
      
      keep = project.isAccessible(); // Project open
      
      if ( keep && shouldBeFilteredWithNature ) { // Nature of the project ok.
       keep = isProjectOfType(project, natureIdsToMatch_p);
      }
      
      // To conclude
      if (keep) {
        result.add(project);
      }
      
    }
   
    return result;
  }
  
  /** for internal use */
  static private boolean matche(final String[] a_p, final Collection<String> b_p) {
    boolean result = false;
    
    for (String str: a_p) {
      if ( b_p.contains(str)) {
        result = true;
        break;
      }
    }
    
    return result;
  }

  
}

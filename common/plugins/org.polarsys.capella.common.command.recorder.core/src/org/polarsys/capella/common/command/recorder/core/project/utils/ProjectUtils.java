/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param project the target {@link IProject}
   * @param natureIdToMatch the nature to match id.
   * @return <code>true</code> whether no nature was defined (e.g. empty or <code>null</code> value as argument.)
   */
  public static boolean isProjectOfType(final IProject project, final String natureIdToMatch) {
    return 
      null == natureIdToMatch || natureIdToMatch.equals(ICommonConstants.EMPTY_STRING) ?
         true :
         isProjectOfType(project, Collections.singleton(natureIdToMatch)
    );
  }
  
  /**
   * Check whether a {@link IProject} nature is of given type.
   * @param project the target {@link IProject}
   * @param natureIdsToMatch the nature to match ids.
   * @return <code>true</code> whether no nature was defined (e.g. empty or <code>null</code> value as argument.)
   */
  public static boolean isProjectOfType(final IProject project, final Collection<String> natureIdsToMatch) {
    
    boolean result = false;
    
    if ( // No filtering on nature
        null == natureIdsToMatch ||
        natureIdsToMatch.isEmpty()
    ) {
      return true;
    }
    
    try {
      String[] projectNatures = project.getDescription().getNatureIds();
      result = matche(projectNatures, natureIdsToMatch);
    } catch (CoreException exception) {
      result = false;
    }
    
    return result;
  }
   
  /**
   * Get all accessible project projects on workspace of a given nature. 
   * @param natureIdToMatch the type of project id (or <code>null</code>)
   * @return an empty {@link Collection} whether no result was found.
   */
  public static Collection<IProject> getAllProjectsOfType(String natureIdToMatch) {
    return getAllProjectsOfType(Collections.singleton(natureIdToMatch));
  }
  
  /**
   * Get all accessible project projects on workspace of a given nature. 
   * @param natureIdsToMatch the type of project ids (or <code>null</code>)
   * @return an empty {@link Collection} whether no result was found.
   */
  public static Collection<IProject> getAllProjectsOfType(final Collection<String> natureIdsToMatch) {
    
    Set<IProject> result = new HashSet<IProject>();
    
    IWorkspace root = ResourcesPlugin.getWorkspace();
    IProject[] projects = root.getRoot().getProjects();

    boolean shouldBeFilteredWithNature = 
      null != natureIdsToMatch && 
      !natureIdsToMatch.isEmpty()
    ;
    
    IProject project = null;
    boolean keep = false;
    for( int i =0; i < projects.length; i++) {
      keep = false;
      project = projects[i];
      
      keep = project.isAccessible(); // Project open
      
      if ( keep && shouldBeFilteredWithNature ) { // Nature of the project ok.
       keep = isProjectOfType(project, natureIdsToMatch);
      }
      
      // To conclude
      if (keep) {
        result.add(project);
      }
      
    }
   
    return result;
  }
  
  /** for internal use */
  static private boolean matche(final String[] a, final Collection<String> b) {
    boolean result = false;
    
    for (String str: a) {
      if ( b.contains(str)) {
        result = true;
        break;
      }
    }
    
    return result;
  }

  
}

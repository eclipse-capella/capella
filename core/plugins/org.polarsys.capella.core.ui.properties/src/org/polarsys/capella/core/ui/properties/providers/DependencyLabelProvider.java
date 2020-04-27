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
package org.polarsys.capella.core.ui.properties.providers;

import java.text.MessageFormat;
import java.util.Collection;


/**
 * A label provider for elements involved in a dependency relation with a 
 * given subject. Elements will then be labeled similar to this:
 * 
 *   subject <-- element (element depends on subject)
 *   subject --> element (subject depends on element)
 *   subject <--> element (dependency cycle)
 * 
 * Clients must specify the dependency relation in the constructor. See below.
 * 
 */
public class DependencyLabelProvider extends CapellaTransfertViewerLabelProvider {

  public static final String FMT_DEFAULT = "{1}";                //$NON-NLS-1$
  public static final String FMT_DEPENDS = "{0} --> {1}";             //$NON-NLS-1$
  public static final String FMT_INVERSE_DEPENDS = "{0} <-- {1}";     //$NON-NLS-1$
  public static final String FMT_CYCLE = "{0} <--> {1}";              //$NON-NLS-1$

  protected Collection<?> dependencies;
  protected Collection<?> inverseDependencies;
  protected Object subject;
  protected String subjectText;
  
  /**
   * A label provider to label packages in the context of package dependencies for a given subject package.
   * 
   * Once the dependency relationship has been specified via the parameters below, it is impossible to change it.
   * The only way for a client to refresh is to create a new instance of this class.
   * 
   * @param dependencies The element that subject depends on 
   * @param inverseDependencies The elements that depend on the subject
   * @param subject the subject itself
   */
  public DependencyLabelProvider(Collection<?> dependencies, Collection<?> inverseDependencies, Object subject) {
    super();
    this.dependencies = dependencies;
    this.inverseDependencies = inverseDependencies;
    this.subject = subject;
  }
  
  // cache the text of the subject, it's required for each element
  protected String getSubjectText(){
    if (subjectText == null){
      subjectText = super.getText(subject);
    }
    return subjectText;
  }
   
  @Override
  /**
   * {@inheritDoc}
   */
  public String getText(Object element){
    
    String left = getSubjectText();
    String right = super.getText(element);
    String fmt = FMT_DEFAULT;
    
    if (dependencies.contains(element) && inverseDependencies.contains(element)){
      fmt = FMT_CYCLE;
    } else if (dependencies.contains(element)){
      fmt = FMT_DEPENDS;
    } else if (inverseDependencies.contains(element)){
      fmt = FMT_INVERSE_DEPENDS;
    }
    return MessageFormat.format(fmt, left, right);
  }
}

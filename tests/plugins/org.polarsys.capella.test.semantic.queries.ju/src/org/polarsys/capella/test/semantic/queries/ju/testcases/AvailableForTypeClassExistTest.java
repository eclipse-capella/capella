/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.polarsys.capella.common.mdsofa.common.misc.ExtensionClassDescriptor;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class AvailableForTypeClassExistTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    // Get the contributed class descriptors
    Collection<ExtensionClassDescriptor> classDescriptors = CategoryRegistry.getInstance().getAvailableForTypeClassDescriptors();
    
    // Collect descriptors for which classes are not found
    List<ExtensionClassDescriptor> notFoundClasses = new ArrayList<ExtensionClassDescriptor>();
    
    // Collect descriptors for which contributor are not found
    List<ExtensionClassDescriptor> notFoundContributors = new ArrayList<ExtensionClassDescriptor>();
    
    // Iterate overall the descriptors and try to load the class
    for(ExtensionClassDescriptor classDescriptor : classDescriptors){
      try {
        classDescriptor.loadClass();        
      }catch(ClassNotFoundException ex){
        notFoundClasses.add(classDescriptor);
      }catch(IllegalStateException ex){
        notFoundContributors.add(classDescriptor);
      }
    }
    
    assertTrue(getClassNotFoundExceptionMsg(notFoundClasses), notFoundClasses.isEmpty());
    assertTrue(getIllegalStateException(notFoundContributors), notFoundContributors.isEmpty());
  }
  
  private String getClassNotFoundExceptionMsg(List<ExtensionClassDescriptor> notFoundClasses){
    StringBuilder builder = new StringBuilder();
    if(notFoundClasses.size() == 1){
      builder.append("Unable to find class '"+notFoundClasses.get(0).getClassName()+"'");
    }else{
      builder.append("Unable to find the following classes: ");
      Iterator<ExtensionClassDescriptor> iterator = notFoundClasses.iterator();
      while (iterator.hasNext()) {
        builder.append(iterator.next().getClassName());
        if(iterator.hasNext()){
          builder.append(" / ");
        }
      }
    }
    return builder.toString();
  }
  
  private String getIllegalStateException(List<ExtensionClassDescriptor> notFoundContributors){
    StringBuilder builder = new StringBuilder();
    if(notFoundContributors.size() == 1){
      builder.append("Cannot locate contributor plug-in '" + notFoundContributors.get(0).getContributorPluginId() + "'");
    }else{
      builder.append("Cannot locate the following contributors plug-ins: ");
      Iterator<ExtensionClassDescriptor> iterator = notFoundContributors.iterator();
      while (iterator.hasNext()) {
        builder.append(iterator.next().getContributorPluginId());
        if(iterator.hasNext()){
          builder.append(" / ");
        }
      }
    }
    return builder.toString();
  }
}

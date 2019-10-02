/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.handlers;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.WordUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;

/**
 * This handler allows to compare two diagrams.
 * 
 * - Select two diagrams on project explorer, right click and Compare Layout. - Select a diagram on project explorer and
 * a layout file, right click and Compare Layout. - Select two layout file, right click and Compare Layout.
 */
public class GenerateDocValidation extends AbstractUiHandler {

  private final String rootCategoryId = "capella.category"; //$NON-NLS-1$
  private final String targetHtmlFileName = "ValidationRules.mediawiki"; //$NON-NLS-1$

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ConstraintRegistry reg = ConstraintRegistry.getInstance();

    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("org.polarsys.capella.validation.doc");
    if (!project.exists()) {
      StatusManager.getManager().handle(new Status(IStatus.ERROR, "dd", "Can you import org.polarsys.capella.validation.doc"), StatusManager.BLOCK);
      return false;
    }
    
    List<IConstraintDescriptor> allRules = reg.getAllDescriptors().stream().filter(x -> x.getCategories().iterator().next().getPath().startsWith(rootCategoryId)).collect(Collectors.toList());
    
    List<String> categories = allRules.stream().map(x -> x.getCategories().iterator().next().getPath()).collect(Collectors.toList());
    Collections.sort(categories);
    
    for (String category : categories) {
      
      List<IConstraintDescriptor> rules = allRules.stream().filter(x -> x.getCategories().iterator().next().getPath().equals(category)).collect(Collectors.toList());
      rules.sort(new Comparator<IConstraintDescriptor>() {
        @Override
        public int compare(IConstraintDescriptor o1, IConstraintDescriptor o2) {
          String id1 = o1.getId().substring(o1.getId().lastIndexOf(".")+1);
          String id2 = o2.getId().substring(o2.getId().lastIndexOf(".")+1);
          return id1.compareTo(id2);
        }
      });
      
      String folder = category.replace(rootCategoryId, "html/Validation Rules");
      IFolder outputFolder = project.getFolder(folder);
      IFile outputFile = project.getFile(folder + "/"+targetHtmlFileName);
      
      String result = "";
      result += "      \n";
      result += "= " + WordUtils.capitalizeFully(category.replace(rootCategoryId + "/", "").replaceAll("/", " > ").replaceAll("_", " ")) + " =\n";
      
      IFile images = project.getFile("html/Images");
      IPath path = images.getFullPath().makeRelativeTo(outputFolder.getFullPath());
      System.out.println(path);
      
      for (IConstraintDescriptor rule : rules) {
        String id1 = rule.getId().substring(rule.getId().lastIndexOf(".")+1);
        String severity = rule.getSeverity().getLiteral();
        result += "<br>\n";
        result +="{| class=\"VALIDATION-RULE\"\n";
        
        result +="!|[[Image:"+path+"/"+severity.toLowerCase()+".gif|"+severity+"]]\n"; 
        result +="|"+rule.getName()+" \n";
        result +="|-\n";
        result +="| colspan=\"2\"|"+rule.getDescription()+"\n";
        result +="|}\n";
      }
      System.out.println(result);
      
      try {
        outputFile.setContents(new ByteArrayInputStream(result.getBytes()), 0, new NullProgressMonitor());
      } catch (CoreException e) {
        e.printStackTrace();
      }
    }
    System.out.println();
    return null;
  }
  
  

}

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
package org.polarsys.capella.core.libraries.commands.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;

import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * Specific command do remove semantic resources from the given session.
 */
//Workaround, pending resolution Sirius bug and / or modification the management of ResourceSet capella and / or management of libraries references by capellaModeller.
public class RemoveSemanticResourceForLibraryCommand extends RecordingCommand {

  /**
   * The contextual {@link Session}.
   */
  protected Session session;

  /**
   * The semantic Resource to remove from the contextual {@link Session}.
   */
  protected Resource semanticResource;

  /**
   * Indicates whether cross referenced resources should be removed or not.
   */
  protected boolean removeCrossReferencedResources;

  /**
   * Indicates if the resource should be removed from controlled resources.
   */
  protected boolean removeFromControlledResources;

  /**
   * Constructor.
   * @param session contextual {@link Session}
   * @param semanticResource Resource
   */
  public RemoveSemanticResourceForLibraryCommand(Session session, Resource semanticResource) {
    this(session, semanticResource, false, false);
  }

  /**
   * Constructor.
   * @param session contextual {@link Session}
   * @param semanticResource Resource
   * @param removeCrossReferencedResources true if any cross referenced resource should be removed to, false otherwise.
   * @param removeFromControlledResources true if the given resource will removed from controlled resources
   */
  public RemoveSemanticResourceForLibraryCommand(Session session, Resource semanticResource, boolean removeCrossReferencedResources,
      boolean removeFromControlledResources) {
    super(session.getTransactionalEditingDomain(), "Remove model");
    this.semanticResource = semanticResource;
    this.session = session;
    this.removeCrossReferencedResources = removeCrossReferencedResources;
    this.removeFromControlledResources = removeFromControlledResources;
  }

  /**
   * Execute the command.
   */
  @Override
  protected void doExecute() {

    // Until the bugs will be fixed

    if ((semanticResource != null) && (session != null) && session.getSemanticResources().contains(semanticResource)) { //
      List<Resource> resourcesToNotUnload = new ArrayList<Resource>();
      resourcesToNotUnload.add(semanticResource);
      MDEAdapterFactory.getResourceSet().getLoadOptions().put("workaroundSirius", resourcesToNotUnload);
      session.removeSemanticResource(semanticResource, new NullProgressMonitor());
      MDEAdapterFactory.getResourceSet().getLoadOptions().remove("workaroundSirius");
      MDEAdapterFactory.getResourceSet().getResources().add(semanticResource);
    }


  }

  public Collection<DAnalysis> getAllAnalyses(Collection<DAnalysis> analysises) {
    Collection<DAnalysis> analysisAndReferenced = new LinkedHashSet<DAnalysis>();
    for (final DAnalysis analysis : new ArrayList<DAnalysis>(analysises)) {
      /* analysis could be null */
      if (analysis != null) {
        analysisAndReferenced.add(analysis);
        addAllReferencedAnalyses(analysisAndReferenced, analysis);
      }
    }
    return analysisAndReferenced;
  }

  private void addAllReferencedAnalyses(final Collection<DAnalysis> analysisAndReferenced, final DAnalysis analysis) {
    for (final DAnalysis referenced : analysis.getReferencedAnalysis()) {
      if (!analysisAndReferenced.contains(referenced) && (referenced.eResource() != null)) {
        analysisAndReferenced.add(referenced);
        addAllReferencedAnalyses(analysisAndReferenced, referenced);
      }
    }
  }

}

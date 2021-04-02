/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.platform.sirius.ui.session.GitConflictHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class GitConflictHelperTest extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("gch-library", "gch-project");
  }

  private final Collection<String> projectFiles = Arrays.asList("/gch-project/fragments/SA.capellafragment",
      "/gch-project/fragments/SA.airdfragment", "/gch-project/gch-project.afm", "/gch-project/gch-project.capella",
      "/gch-project/gch-project.aird");

  private final Collection<String> libraryFiles = Arrays.asList("/gch-library/fragments/SA.capellafragment",
      "/gch-library/fragments/SA.airdfragment", "/gch-library/gch-library.afm", "/gch-library/gch-library.capella",
      "/gch-library/gch-library.aird");

  private final Collection<String> projectAndlibraryFiles = Stream.concat(projectFiles.stream(), libraryFiles.stream())
      .collect(Collectors.toList());

  @Override
  public void test() throws Exception {
    // Test on project
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("gch-project");
    Collection<String> values = toPaths(GitConflictHelper.getFiles(project, x -> true));
    assertTrue(
        "Some files are missing in GitConflictHelper.getFiles(project): "
            + projectFiles.stream().filter(x -> !values.contains(x)).collect(Collectors.toList()),
        isEquals(values, projectFiles));
    
    // Test on session
    Session session = getSession("gch-project");
    Collection<String> values3 = toPaths(GitConflictHelper.getFiles(session, x -> true));
    assertTrue(
        "Some files are missing in GitConflictHelper.getFiles(session): "
            + projectAndlibraryFiles.stream().filter(x -> !values3.contains(x)).collect(Collectors.toList()),
        isEquals(projectAndlibraryFiles, values3));

    // Test on invalid files
    assertFalse(GitConflictHelper.isInGitRepository((IFile) null));
  }

  private boolean isEquals(Collection<String> e, Collection<String> r) {
    return e.containsAll(r) && e.size() == r.size();
  }

  private Collection<String> toPaths(Collection<IFile> files) {
    return files.stream().map(x -> x.getFullPath().toPortableString()).collect(Collectors.toList());
  }
}

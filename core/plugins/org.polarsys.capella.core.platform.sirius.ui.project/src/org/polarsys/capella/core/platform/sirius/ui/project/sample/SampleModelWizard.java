/*******************************************************************************
 * Copyright (c) 2018, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.project.sample;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.internal.resources.ProjectDescriptionReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.xml.sax.InputSource;

/**
 * The wizard allowing to initialize sample Capella model. Unlike the extended class, it import only the selected one,
 * not all available projects from the wizard.
 * 
 * By default, it read the Samples folder located aside the Capella installation location, looking for Zip model files
 */
public class SampleModelWizard extends AbstractExampleInstallerWizard {

  List<ProjectDescriptor> descriptors = null;

  ProjectDescriptor selected = null;

  boolean install = false;

  public class ExampleProjectDescriptor extends ProjectDescriptor {

    public ExampleProjectDescriptor(URI uri) throws AssertionFailedException {
      setContentURI(uri);

      ProjectDescription project = createProjectDescription(contentURI);
      Assert.isNotNull(project);

      setName(project.getName());
      setDescription(project.getComment());
    }
  }

  /**
   * For a zip uri, look for a .project file
   */
  protected ProjectDescription createProjectDescription(URI contentURI) {
    try {
      String location = contentURI.toFileString();
      if (location != null) {
        File file = new File(location);
        if (file.isFile() && file.canRead()) {
          ZipFile zipFile = createZipFile(file);
          if (zipFile != null) {
            try {
              ZipFileStructureProvider structureProvider = new ZipFileStructureProvider(zipFile);
              for (Object folder : structureProvider.getChildren(structureProvider.getRoot())) {
                for (Object subfile : structureProvider.getChildren(folder)) {
                  if (subfile instanceof ZipEntry
                      && ((ZipEntry) subfile).getName().endsWith(IProjectDescription.DESCRIPTION_FILE_NAME)) {
                    BufferedInputStream in = new BufferedInputStream(structureProvider.getContents((ZipEntry) subfile));
                    ProjectDescription description = new ProjectDescriptionReader(ResourcesPlugin.getWorkspace()).read(new InputSource(in));
                    return description;
                  }
                }
              }
            } finally {
              try {
                zipFile.close();
              } catch (IOException e) {
                // Ignore.
              }
            }
          }
        }
      }

    } catch (Exception e) {
      // Something wrong, the zip has not the wanted structure
    }
    return null;
  }

  protected Collection<URI> getSampleLocations() {
    Collection<URI> result = new ArrayList<URI>();
    Location location = Platform.getInstallLocation();
    URL url = location.getURL();
    URI uri = URI.createURI(url.toString());
    URI sampleLocation = uri.trimSegments(2).appendSegment("samples");
    result.add(sampleLocation);
    return result;
  }

  @Override
  protected List<ProjectDescriptor> getProjectDescriptors() {
    if (install == true) {
      return Collections.singletonList(selected);
    }

    if (descriptors == null) {
      descriptors = new ArrayList<AbstractExampleInstallerWizard.ProjectDescriptor>();

      try {
        for (URI location : getSampleLocations()) {
          for (File zip : getOwnedZips(location)) {
            try {
              URI fileUri = URI.createFileURI(zip.getAbsolutePath());
              descriptors.add(new ExampleProjectDescriptor(fileUri));
            } catch (Exception e) {
              // Can't read the zip file
            }
          }
        }

      } catch (Exception e) {
        // Samples folder has not the wanted structure
      }

    }

    return descriptors;
  }

  protected Collection<File> getOwnedZips(URI sampleLocation) {
    Collection<File> zips = new ArrayList<File>();
    FilenameFilter zipFilter = new ZipFilenameFilter();
    File folder = new File(sampleLocation.toFileString());
    if (folder.exists() && folder.isDirectory()) {
      for (File file : folder.listFiles()) {
        if (file.isDirectory()) {
          for (File zip : file.listFiles(zipFilter)) {
            zips.add(zip);
          }
        } else if (zipFilter.accept(file.getParentFile(), file.getName())) {
          zips.add(file);
        }
      }
    }
    return zips;
  }

  @Override
  public void addPages() {
    projectPage = new ProjectPage("projectPage", CommonUIPlugin.INSTANCE.getString("_UI_ProjectPage_title"), null) {

      @Override
      protected void itemSelected() {
        ProjectDescriptor projectDescriptor = getSelectedProjectDescriptor();
        if (projectDescriptor != null) {
          selected = projectDescriptor;
        }
        super.itemSelected();
      }
    };
    projectPage.setDescription("Select a sample project to import");
    addPage(projectPage);
  }

  @Override
  public boolean performFinish() {
    // By default, the wizard loads all available projects, we restrict it to the selected one
    try {
      install = true;
      return super.performFinish();

    } finally {
      install = false;
      
    }
  }

  @Override
  protected void openFiles(IProgressMonitor progressMonitor) {
    List<FileToOpen> filesToOpen = getFilesToOpen();
    List<IFile> airds = new ArrayList<IFile>();
    if (!filesToOpen.isEmpty()) {
      for (FileToOpen open : filesToOpen) {
        airds.add(open.getWorkspaceFile());
      }
      OpenSessionAction action = new OpenSessionAction();
      action.selectionChanged(new StructuredSelection(airds));
      action.run();
    }
  }

  /**
   * Highly inspired by the super method, but Capella sample models have a root folder which is not the expected case.
   */
  @Override
  protected void installProjectFromFile(ProjectDescriptor projectDescriptor, IProgressMonitor progressMonitor)
      throws Exception {
    URI contentURI = projectDescriptor.getContentURI();
    if (contentURI.isPlatform()) {
      contentURI = CommonPlugin.asLocalURI(contentURI);
    }

    ImportOperation importOperation = null;
    ZipFile zipFile = null;
    try {
      String location = contentURI.toFileString();
      if (location != null) {
        File file = new File(location);
        if (file.isFile() && file.canRead()) {
          zipFile = createZipFile(file);
          if (zipFile != null) {
            ZipFileStructureProvider structureProvider = new ZipFileStructureProvider(zipFile);
            Object root = structureProvider.getRoot();
            if (root != null) {
              List child = structureProvider.getChildren(root);
              if (child != null && child.size() > 0) {
                Object mainProject = child.get(0);
                importOperation = new ImportOperation(projectDescriptor.getProject().getFullPath(), mainProject,
                    structureProvider, OVERWRITE_ALL_QUERY);
              }
            }
          }
        }
      }

      if (importOperation != null) {
        installProject(projectDescriptor, importOperation, progressMonitor);
      } else {
        throw new Exception(
            CommonUIPlugin.INSTANCE.getString("_UI_FileError_message", new String[] { contentURI.toString() }));
      }

    } finally {
      if (zipFile != null) {
        try {
          zipFile.close();
        } catch (IOException e) {
          // Ignore.
        }
      }
    }
  }

  @Override
  protected void installProject(ProjectDescriptor projectDescriptor, ImportOperation importOperation,
      IProgressMonitor progressMonitor) throws Exception {
    importOperation.setCreateContainerStructure(false);
    super.installProject(projectDescriptor, importOperation, progressMonitor);
  }

  @Override
  protected List<FileToOpen> getFilesToOpen() {
    if (selected != null) {
      Collection<IFile> airds = new ArrayList<IFile>();
      List<FileToOpen> locations = new ArrayList<FileToOpen>();
      try {
        selected.getProject().accept(new IResourceVisitor() {

          @Override
          public boolean visit(IResource resource) throws CoreException {
            if (CapellaResourceHelper.isAirdResource(resource, true)) {
              airds.add((IFile) resource);
            }
            return true;
          }
        });
      } catch (CoreException e) {
        // Can't browse the project
      }

      for (IFile file : airds) {
        locations.add(new FileToOpen() {
          @Override
          public IFile getWorkspaceFile() {
            return file;
          }
        });
      }
      return locations;
    }
    return Collections.emptyList();
  }

}

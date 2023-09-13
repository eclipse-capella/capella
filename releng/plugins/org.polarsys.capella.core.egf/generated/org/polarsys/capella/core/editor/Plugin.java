//Generated with EGF 1.6.4.202309111303
package org.polarsys.capella.core.editor;

import org.eclipse.egf.emf.pattern.base.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.*;
import org.eclipse.emf.codegen.ecore.genmodel.generator.*;
import org.eclipse.emf.codegen.util.*;
import org.eclipse.emf.ecore.util.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class Plugin extends org.eclipse.egf.emf.pattern.editor.Plugin {
  protected static String nl;

  public static synchronized Plugin create(String lineSeparator) {
    nl = lineSeparator;
    Plugin result = new Plugin();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";" + NL;
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * This is the central singleton for the ";
  protected final String TEXT_5 = " editor plugin." + NL + " * <!-- begin-user-doc -->" + NL
      + " * <!-- end-user-doc -->";
  protected final String TEXT_6 = NL + " * ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */";
  protected final String TEXT_8 = NL + "@Deprecated";
  protected final String TEXT_9 = NL + "public final class ";
  protected final String TEXT_10 = " extends EMFPlugin" + NL + "{";
  protected final String TEXT_11 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_12 = " copyright = ";
  protected final String TEXT_13 = ";";
  protected final String TEXT_14 = NL + "\t";
  protected final String TEXT_15 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic static final ";
  protected final String TEXT_16 = " INSTANCE = new ";
  protected final String TEXT_17 = "();" + NL + "\t";
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tprivate static Implementation plugin;" + NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * Create the instance." + NL + "\t * <!-- begin-user-doc -->"
      + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_20 = "()" + NL + "\t{" + NL + "\t\tsuper" + NL + "\t\t\t(new ResourceLocator [] " + NL
      + "\t\t\t{";
  protected final String TEXT_21 = NL + "\t\t\t\t";
  protected final String TEXT_22 = ".INSTANCE,";
  protected final String TEXT_23 = NL + "\t\t\t});" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
      + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL
      + "\t */";
  protected final String TEXT_24 = NL + "\t@Override";
  protected final String TEXT_25 = NL + "\tpublic ResourceLocator getPluginResourceLocator()" + NL + "\t{";
  protected final String TEXT_26 = NL + "\t\treturn null;";
  protected final String TEXT_27 = NL + "\t\treturn plugin;";
  protected final String TEXT_28 = NL + "\t}" + NL + "\t";
  protected final String TEXT_29 = NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance."
      + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static Implementation getPlugin()" + NL + "\t{" + NL
      + "\t\treturn plugin;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL
      + "\t * The actual implementation of the Eclipse <b>Plugin</b>." + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic static class Implementation extends ";
  protected final String TEXT_30 = NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Creates an instance." + NL
      + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_31 = NL + "\t\t * @param descriptor the description of the plugin.";
  protected final String TEXT_32 = NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Implementation(";
  protected final String TEXT_33 = " descriptor";
  protected final String TEXT_34 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(";
  protected final String TEXT_35 = "descriptor";
  protected final String TEXT_36 = ");" + NL + "\t" + NL + "\t\t\t// Remember the static instance." + NL + "\t\t\t//"
      + NL + "\t\t\tplugin = this;" + NL + "\t\t}" + NL + "" + NL + "\t\t//begin-capella-code" + NL + "\t\t/**" + NL
      + "\t\t * Returns the object's graphical representation." + NL
      + "\t\t * (This method comes from a customization of the standard EMF generator)" + NL + "\t\t *" + NL
      + "\t\t * @param key the identifier of the object." + NL + "\t\t * @generated" + NL + "\t\t */" + NL
      + "\t\t@Override" + NL + "\t\tpublic Object getImage(String key) {" + NL + "\t\t\treturn ";
  protected final String TEXT_37 = ".find(this.getBundle(), new ";
  protected final String TEXT_38 = "(\"icons/\" + key + \".gif\"), null);" + NL + "\t\t}" + NL
      + "\t\t//end-capella-code" + NL + "\t}" + NL;
  protected final String TEXT_39 = NL + "}";
  protected final String TEXT_40 = NL;

  public Plugin() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    List<Object> parameterList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object parameterParameter : parameterList) {

      this.parameter = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) parameterParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_40);
    stringBuffer.append(TEXT_40);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("parameter", this.parameter);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("parameter", this.parameter);
    return parameters;
  }

  protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    /**
     * Copyright (c) 2002-2004 IBM Corporation and others.
     * All rights reserved.   This program and the accompanying materials
     * are made available under the terms of the Eclipse Public License v2.0
     * which accompanies this distribution, and is available at
     * http://www.eclipse.org/legal/epl-v20.html
     *
     * Contributors:
     *   IBM - Initial API and implementation
     */

    GenModel genModel = (GenModel) argument;
    /* Trick to import java.util.* without warnings */Iterator.class.getName();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    stringBuffer.append(TEXT_1);
    {
      //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#LogicalName=org.eclipse.egf.emf.pattern.base.HeaderJava" args="parameter:argument"%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      callParameters.put("argument", parameter);
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#_XHLrsCwtEd-jc5T-XaRJlg",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    stringBuffer.append(TEXT_2);
    stringBuffer.append(genModel.getEditorPluginPackageName());
    stringBuffer.append(TEXT_3);
    genModel.addImport("org.eclipse.emf.common.EMFPlugin");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_5);
    if (genModel.hasAPITags()) {
      stringBuffer.append(TEXT_6);
      stringBuffer.append(genModel.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_7);
    if (isJDK50 && genModel.hasAPIDeprecatedTag()) {
      stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_10);
    if (genModel.hasCopyrightField()) {
      stringBuffer.append(TEXT_11);
      stringBuffer.append(genModel.getImportedName("java.lang.String"));
      stringBuffer.append(TEXT_12);
      stringBuffer.append(genModel.getCopyrightFieldLiteral());
      stringBuffer.append(TEXT_13);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_17);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
      stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_20);
    for (String pluginClassName : genModel.getEditResourceDelegateImportedPluginClassNames()) {
      stringBuffer.append(TEXT_21);
      stringBuffer.append(pluginClassName);
      stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (genModel.useClassOverrideAnnotation()) {
      stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT) {
      stringBuffer.append(TEXT_26);
    } else {
      stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
      stringBuffer.append(TEXT_29);
      stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.ui.EclipseUIPlugin"));
      stringBuffer.append(TEXT_30);
      if (genModel.needsRuntimeCompatibility()) {
        stringBuffer.append(TEXT_31);
      }
      stringBuffer.append(TEXT_32);
      if (genModel.needsRuntimeCompatibility()) {
        stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IPluginDescriptor"));
        stringBuffer.append(TEXT_33);
      }
      stringBuffer.append(TEXT_34);
      if (genModel.needsRuntimeCompatibility()) {
        stringBuffer.append(TEXT_35);
      }
      stringBuffer.append(TEXT_36);
      stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.FileLocator"));
      stringBuffer.append(TEXT_37);
      stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.Path"));
      stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    genModel.emitSortedImports();
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    GenModel genModel = parameter;
    genModel = parameter.getGenModel();
    boolean canGenerate = new CodegenGeneratorAdapter(parameter)
        .canGenerate("org.eclipse.emf.codegen.ecore.genmodel.generator.EditorProject");
    canGenerate = canGenerate && true;
    return canGenerate;
  }
}
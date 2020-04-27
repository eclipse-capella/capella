//Generated with EGF 1.5.0.v20170706-0846
package org.polarsys.capella.core.model;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.ecore.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.*;
import org.polarsys.capella.core.egf.*;
import org.polarsys.kitalpha.emde.egf.helper.*;

public class ResourceClass extends org.polarsys.kitalpha.emde.egf.model.ResourceClass {
	protected static String nl;

	public static synchronized ResourceClass create(String lineSeparator) {
		nl = lineSeparator;
		ResourceClass result = new ResourceClass();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "";
	protected final String TEXT_2 = NL + "package ";
	protected final String TEXT_3 = ";" + NL;
	protected final String TEXT_4 = NL + NL + "import org.eclipse.emf.ecore.xmi.XMLResource;" + NL
			+ "import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;" + NL + "" + NL + "/**" + NL
			+ " * <!-- begin-user-doc -->" + NL + " * The <b>Resource </b> associated with the package." + NL
			+ " * <!-- end-user-doc -->" + NL + " * @see ";
	protected final String TEXT_5 = NL + " * @generated" + NL + " */" + NL + "public class ";
	protected final String TEXT_6 = " extends ";
	protected final String TEXT_7 = NL + "{";
	protected final String TEXT_8 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
	protected final String TEXT_9 = " copyright = ";
	protected final String TEXT_10 = ";";
	protected final String TEXT_11 = NL;
	protected final String TEXT_12 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */  " + NL + "\tprivate ";
	protected final String TEXT_13 = "<Object> lookupTable = new ";
	protected final String TEXT_14 = "<Object>();" + NL + "  " + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */  " + NL + "\tprivate ";
	protected final String TEXT_15 = " parserPool = new ";
	protected final String TEXT_16 = "();  " + NL + "  " + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */  " + NL + "\tprivate ";
	protected final String TEXT_17 = "<Object, Object> nameToFeatureMap = new ";
	protected final String TEXT_18 = "<Object, Object>();" + NL + "\t";
	protected final String TEXT_19 = NL + "\t/**" + NL + "\t * Creates an instance of the resource." + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL
			+ "\t * @param uri the URI of the new resource." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
	protected final String TEXT_20 = "(URI uri)" + NL + "\t{" + NL + "\t\tsuper(uri);" + NL + "\t}\t";
	protected final String TEXT_21 = NL + NL + "      //begin-capella-code" + NL + "\t  /**" + NL
			+ "\t   * {@inheritDoc}" + NL + "\t   * @generated" + NL + "\t   */" + NL + "\t  @Override" + NL
			+ "\t  protected void attachedHelper(";
	protected final String TEXT_22 = " eObject) {" + NL + "\t    // Make sure specified object has its id generated."
			+ NL + "\t    if (eObject instanceof ";
	protected final String TEXT_23 = ") {" + NL + "\t      setID(eObject, ((ModelElement) eObject).getId());" + NL
			+ "\t    } else if (null != eObject && null != eObject.eClass()) {" + NL + "\t      ";
	protected final String TEXT_24 = " attribute = eObject.eClass().getEIDAttribute();" + NL
			+ "\t      if (attribute != null) {" + NL + "\t        Object id = eObject.eGet(attribute);" + NL
			+ "\t        if (id instanceof ";
	protected final String TEXT_25 = ") {" + NL + "\t          setID(eObject, (";
	protected final String TEXT_26 = ")id);" + NL + "\t        }" + NL + "\t      }" + NL + "\t    }" + NL
			+ "\t    super.attachedHelper(eObject);" + NL + "\t  }" + NL + "      //end-capella-code" + NL + "      "
			+ NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL
			+ "\t * @generated" + NL + "\t */";
	protected final String TEXT_27 = NL + "\t@Override";
	protected final String TEXT_28 = NL + "\tprotected ";
	protected final String TEXT_29 = " createXMLSave() {";
	protected final String TEXT_30 = NL + "\t\treturn new ";
	protected final String TEXT_31 = "(createXMLHelper());";
	protected final String TEXT_32 = NL + "\t\treturn new ";
	protected final String TEXT_33 = "(createXMLHelper());";
	protected final String TEXT_34 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_35 = NL + "\t@Override";
	protected final String TEXT_36 = NL + "\tprotected ";
	protected final String TEXT_37 = " createXMLHelper() {";
	protected final String TEXT_38 = NL + "\t\treturn new ";
	protected final String TEXT_39 = "(this);";
	protected final String TEXT_40 = NL + "\t\treturn new ";
	protected final String TEXT_41 = "(this);";
	protected final String TEXT_42 = "\t" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->"
			+ NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_43 = NL + "\t@Override";
	protected final String TEXT_44 = NL + "\tprotected ";
	protected final String TEXT_45 = " createXMLLoad() {";
	protected final String TEXT_46 = NL + "\t\treturn new ";
	protected final String TEXT_47 = "((";
	protected final String TEXT_48 = ") createXMLHelper());";
	protected final String TEXT_49 = NL + "\t\treturn new ";
	protected final String TEXT_50 = "((";
	protected final String TEXT_51 = ") createXMLHelper());";
	protected final String TEXT_52 = "\t" + NL + "\t}\t";
	protected final String TEXT_53 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_54 = NL + "\t@Override";
	protected final String TEXT_55 = NL + "\tprotected boolean useIDAttributes() {" + NL + "\t\treturn false;" + NL
			+ "\t}";
	protected final String TEXT_56 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_57 = NL + "\t@Override";
	protected final String TEXT_58 = NL + "\tprotected boolean useUUIDs() {" + NL + "\t\treturn true;" + NL + "\t}";
	protected final String TEXT_59 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_60 = NL + "\t@Override";
	protected final String TEXT_61 = NL + "\tprotected void init() {" + NL + "\t\tsuper.init();" + NL;
	protected final String TEXT_62 = NL + "\t\tsetTrackingModification(true);";
	protected final String TEXT_63 = NL + "\t\t// Save Options" + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, \"UTF-8\");  //$NON-NLS-1$" + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);" + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);    " + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);    " + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE);    " + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));" + NL
			+ "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());\t\t        "
			+ NL + "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_FLUSH_THRESHOLD, Integer.valueOf(0x01000000));"
			+ NL + "\t\tgetDefaultSaveOptions().put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);" + NL + "" + NL
			+ "\t\t// Load Options" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_USE_PARSER_POOL, parserPool);" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);" + NL
			+ "\t\tgetDefaultLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);" + NL + "\t}"
			+ NL + "" + NL + "\t//begin-capella-code" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * " + NL
			+ "\t * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#getEObjectByID(java.lang.String)" + NL
			+ "\t * @generated" + NL + "\t */";
	protected final String TEXT_64 = NL + "\t@Override";
	protected final String TEXT_65 = NL + "\tprotected ";
	protected final String TEXT_66 = " getEObjectByID(String id) {" + NL + "\t\tif (idToEObjectMap == null)" + NL
			+ "\t\t\treturn super.getEObjectByID(id);" + NL + "\t\treturn getIDToEObjectMap().get(id);" + NL + "\t}"
			+ NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL
			+ "\t * " + NL + "\t * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#getIntrinsicIDToEObjectMap()"
			+ NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_67 = NL + "\t@Override";
	protected final String TEXT_68 = NL + "\t@SuppressWarnings(\"unchecked\")" + NL + "\tpublic ";
	protected final String TEXT_69 = " getIntrinsicIDToEObjectMap() {" + NL + "\t\treturn getIDToEObjectMap();" + NL
			+ "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->"
			+ NL + "\t * " + NL + "\t * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#doUnload()" + NL
			+ "\t * @generated" + NL + "\t */";
	protected final String TEXT_70 = NL + "\t@Override";
	protected final String TEXT_71 = NL + "\tprotected void doUnload() {" + NL + "\t\tsuper.doUnload();" + NL
			+ "\t\tthis.idToEObjectMap = null;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * " + NL
			+ "\t * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#setLoaded()" + NL + "\t * @generated" + NL
			+ "\t */";
	protected final String TEXT_72 = NL + "\t@Override";
	protected final String TEXT_73 = NL + "\tprotected ";
	protected final String TEXT_74 = " setLoaded(boolean isLoaded) {" + NL + "\t\treturn super.setLoaded(isLoaded);"
			+ NL + "\t}" + NL + "\t//end-capella-code" + NL + "\t";
	protected final String TEXT_75 = NL + "\t/**" + NL
			+ "\t * A load option that turns of the use of the generate data converters." + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */"
			+ NL + "\tpublic static final String OPTION_USE_DATA_CONVERTER = \"USE_DATA_CONVERTER\"; ";
	protected final String TEXT_76 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_77 = NL + "\t@Override";
	protected final String TEXT_78 = NL + "\tpublic void doLoad(InputStream inputStream, ";
	protected final String TEXT_79 = " options) throws IOException" + NL + "\t{" + NL
			+ "\t\tif (options != null && Boolean.TRUE.equals(options.get(OPTION_USE_DATA_CONVERTER)))" + NL + "\t\t{"
			+ NL + "\t\t  getContents().add" + NL + "\t\t\t (load" + NL + "\t\t\t\t (new InputSource(inputStream), "
			+ NL + "\t\t\t\t  (";
	protected final String TEXT_80 = ")options.get(XMLResource.OPTION_PARSER_FEATURES), " + NL + "\t\t\t\t  (";
	protected final String TEXT_81 = ")options.get(XMLResource.OPTION_PARSER_PROPERTIES), " + NL
			+ "\t\t\t\t  Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER))).eContainer());" + NL
			+ "\t\t}" + NL + "\t\telse" + NL + "\t\t{  " + NL + "\t\t\tsuper.doLoad(inputStream, options);" + NL
			+ "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_82 = NL + "\t@Override";
	protected final String TEXT_83 = NL + "\tpublic void doLoad(InputSource inputSource, ";
	protected final String TEXT_84 = " options) throws IOException" + NL + "\t{" + NL
			+ "\t\tif (options != null && Boolean.TRUE.equals(options.get(OPTION_USE_DATA_CONVERTER)))" + NL + "\t\t{"
			+ NL + "\t\t  getContents().add" + NL + "\t\t\t (load" + NL + "\t\t\t\t (inputSource," + NL + "\t\t\t\t  (";
	protected final String TEXT_85 = ")options.get(XMLResource.OPTION_PARSER_FEATURES), " + NL + "\t\t\t\t  (";
	protected final String TEXT_86 = ")options.get(XMLResource.OPTION_PARSER_PROPERTIES), " + NL
			+ "\t\t\t\t  Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER))).eContainer());" + NL
			+ "\t\t}" + NL + "\t\telse" + NL + "\t\t{  " + NL + "\t\t\tsuper.doLoad(inputSource, options);" + NL
			+ "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
			+ "\tprotected static final XMLParserPool parserPool = new XMLParserPoolImpl();" + NL + "" + NL + "\t/**"
			+ NL + "\t * Loads an instance from the input." + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @param inputSource the input from which to load." + NL
			+ "\t * @param features a map of the parser features and their values." + NL
			+ "\t * @param properties a map of a parser properties and their values." + NL
			+ "\t * @param useLexicalHandler whether a lexical handler should be used during loading." + NL
			+ "\t * @return the root object; for the case of a document root, the child of that document root is return."
			+ NL + "\t * @throws ParserConfigurationException" + NL + "\t * @throws SAXException" + NL
			+ "\t * @generated" + NL + "\t */" + NL + "\tpublic static EObject load(InputSource inputSource, ";
	protected final String TEXT_87 = " features, ";
	protected final String TEXT_88 = " properties, boolean useLexicalHandler) throws IOException" + NL + "\t{" + NL
			+ "\t\t";
	protected final String TEXT_89 = " requiredFeatures = new ";
	protected final String TEXT_90 = "();" + NL
			+ "\t\trequiredFeatures.put(\"http://xml.org/sax/features/namespaces\", Boolean.TRUE); ";
	protected final String TEXT_91 = " " + NL + "\t\tif (features != null)" + NL + "\t\t{" + NL
			+ "\t\t\trequiredFeatures.putAll(features);" + NL + "\t\t}" + NL + "\t\t" + NL
			+ "\t\tif (properties == null)" + NL + "\t\t{" + NL + "\t\t\tproperties = Collections.";
	protected final String TEXT_92 = "emptyMap()";
	protected final String TEXT_93 = "EMPTY_MAP";
	protected final String TEXT_94 = ";" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tSAXParser saxParser = null;" + NL
			+ "\t\ttry" + NL + "\t\t{" + NL
			+ "\t\t\tsaxParser = parserPool.get(requiredFeatures, properties, useLexicalHandler);" + NL
			+ "\t\t\tfinal FrameFactory.DocumentRootStackFrame documentRoot = FrameFactory.INSTANCE.pushDocumentRoot(null, null);"
			+ NL + "\t\t\tXMLTypeResourceImpl.Handler handler = new XMLTypeResourceImpl.Handler(documentRoot);" + NL
			+ "\t\t\tsaxParser.parse(inputSource, handler);";
	protected final String TEXT_95 = NL
			+ "\t\t\treturn (EObject)((EObject)FrameFactory.INSTANCE.popDocumentRoot(documentRoot)).eContents().get(0);";
	protected final String TEXT_96 = NL
			+ "\t\t\treturn FrameFactory.INSTANCE.popDocumentRoot(documentRoot).eContents().get(0);";
	protected final String TEXT_97 = NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL
			+ "\t\t\tthrow new IOWrappedException(exception);" + NL + "\t\t}" + NL + "\t\tfinally" + NL + "\t\t{" + NL
			+ "\t\t\tparserPool.release(saxParser, requiredFeatures, properties, useLexicalHandler);" + NL + "\t\t}"
			+ NL + "\t}" + NL;
	protected final String TEXT_98 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
			+ "\tpublic final static class FrameFactory" + NL + "\t{" + NL + "\t\t/**" + NL
			+ "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL
			+ "\t\t */" + NL + "\t\tpublic static final FrameFactory INSTANCE = new FrameFactory();" + NL + "\t";
	protected final String TEXT_99 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
	protected final String TEXT_100 = "StackFrame ";
	protected final String TEXT_101 = ";" + NL;
	protected final String TEXT_102 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
	protected final String TEXT_103 = " ";
	protected final String TEXT_104 = ";" + NL;
	protected final String TEXT_105 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic ";
	protected final String TEXT_106 = "StackFrame push";
	protected final String TEXT_107 = "(";
	protected final String TEXT_108 = " previous, Attributes attributes)" + NL + "\t\t{" + NL + "\t\t\t ";
	protected final String TEXT_109 = "StackFrame result";
	protected final String TEXT_110 = " = ";
	protected final String TEXT_111 = " == null ? new ";
	protected final String TEXT_112 = "StackFrame() : ";
	protected final String TEXT_113 = ";" + NL + "\t\t\t ";
	protected final String TEXT_114 = " = null;" + NL + "\t\t\t result";
	protected final String TEXT_115 = ".pushOnto(previous);" + NL + "\t\t\t result";
	protected final String TEXT_116 = ".handleAttributes(attributes);" + NL + "\t\t\t return result";
	protected final String TEXT_117 = ";" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL
			+ "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL
			+ "\t\t */" + NL + "\t\tpublic ";
	protected final String TEXT_118 = " pop";
	protected final String TEXT_119 = "(";
	protected final String TEXT_120 = "StackFrame ";
	protected final String TEXT_121 = ")" + NL + "\t\t{" + NL + "\t\t\t";
	protected final String TEXT_122 = " result";
	protected final String TEXT_123 = "Value = ";
	protected final String TEXT_124 = ".pop";
	protected final String TEXT_125 = "();" + NL + "\t\t\tthis.";
	protected final String TEXT_126 = " = ";
	protected final String TEXT_127 = ";" + NL + "\t\t\treturn result";
	protected final String TEXT_128 = "Value;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL
			+ "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL
			+ "\t\t */" + NL + "\t\tpublic static class ";
	protected final String TEXT_129 = "StackFrame extends ";
	protected final String TEXT_130 = NL + "\t\t{" + NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL
			+ "\t\t\tprotected ";
	protected final String TEXT_131 = " the";
	protected final String TEXT_132 = ";" + NL + "\t\t";
	protected final String TEXT_133 = NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL
			+ "\t\t\tprotected ";
	protected final String TEXT_134 = ".FrameFactory.";
	protected final String TEXT_135 = "StackFrame ";
	protected final String TEXT_136 = ";" + NL;
	protected final String TEXT_137 = NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL
			+ "\t\t\tprotected ";
	protected final String TEXT_138 = " ";
	protected final String TEXT_139 = ";" + NL + "\t\t";
	protected final String TEXT_140 = NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */";
	protected final String TEXT_141 = NL + "\t\t\t@Override";
	protected final String TEXT_142 = NL + "\t\t\tpublic void handleAttributes(Attributes attributes)" + NL + "\t\t\t{";
	protected final String TEXT_143 = NL + "\t\t\t\tString theValue = attributes.getValue(";
	protected final String TEXT_144 = ", \"";
	protected final String TEXT_145 = "\");";
	protected final String TEXT_146 = NL + "\t\t\t\ttheValue = attributes.getValue(";
	protected final String TEXT_147 = ", \"";
	protected final String TEXT_148 = "\");";
	protected final String TEXT_149 = NL + "\t\t\t\tif (theValue != null)" + NL + "\t\t\t\t{";
	protected final String TEXT_150 = NL + "\t\t\t\t\tthe";
	protected final String TEXT_151 = ".set";
	protected final String TEXT_152 = "(";
	protected final String TEXT_153 = ".create";
	protected final String TEXT_154 = "(theValue));";
	protected final String TEXT_155 = NL + "\t\t\t\t\tthe";
	protected final String TEXT_156 = ".set";
	protected final String TEXT_157 = "((";
	protected final String TEXT_158 = ")";
	protected final String TEXT_159 = ".createFromString(";
	protected final String TEXT_160 = ", theValue));";
	protected final String TEXT_161 = NL + "\t\t\t\t}";
	protected final String TEXT_162 = NL + "\t\t\t\t// There are attributes to handle.";
	protected final String TEXT_163 = NL + "\t\t\t}" + NL + "\t\t" + NL + "\t\t\t/**" + NL
			+ "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated"
			+ NL + "\t\t\t */";
	protected final String TEXT_164 = NL + "\t\t\t@Override";
	protected final String TEXT_165 = NL + "\t\t\tpublic ";
	protected final String TEXT_166 = " startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException"
			+ NL + "\t\t\t{";
	protected final String TEXT_167 = NL + "\t\t\t\t";
	protected final String TEXT_168 = "else ";
	protected final String TEXT_169 = "if (\"";
	protected final String TEXT_170 = "\".equals(localName) && ";
	protected final String TEXT_171 = ".equals(namespace))" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\treturn ";
	protected final String TEXT_172 = " = ";
	protected final String TEXT_173 = ".FrameFactory.INSTANCE.push";
	protected final String TEXT_174 = "(this, attributes);" + NL + "\t\t\t\t}";
	protected final String TEXT_175 = NL
			+ "\t\t\t\treturn super.startElement(namespace, localName, qName, attributes);";
	protected final String TEXT_176 = NL + "\t\t\t\telse" + NL + "\t\t\t\t{" + NL
			+ "\t\t\t\t\treturn super.startElement(namespace, localName, qName, attributes);" + NL + "\t\t\t\t}";
	protected final String TEXT_177 = NL + "\t\t\t}" + NL + "" + NL + "\t\t\t/**" + NL
			+ "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated"
			+ NL + "\t\t\t */";
	protected final String TEXT_178 = NL + "\t\t\t@Override";
	protected final String TEXT_179 = NL + "\t\t\tpublic void endElement(";
	protected final String TEXT_180 = " child) throws SAXException" + NL + "\t\t\t{";
	protected final String TEXT_181 = NL + "\t\t\t\t";
	protected final String TEXT_182 = "else ";
	protected final String TEXT_183 = "if (child == ";
	protected final String TEXT_184 = ")" + NL + "\t\t\t\t{";
	protected final String TEXT_185 = NL + "\t\t\t\t\tthe";
	protected final String TEXT_186 = ".";
	protected final String TEXT_187 = "().add(";
	protected final String TEXT_188 = ".FrameFactory.INSTANCE.pop";
	protected final String TEXT_189 = "(";
	protected final String TEXT_190 = "));";
	protected final String TEXT_191 = NL + "\t\t\t\t\tthe";
	protected final String TEXT_192 = ".set";
	protected final String TEXT_193 = "(";
	protected final String TEXT_194 = ".FrameFactory.INSTANCE.pop";
	protected final String TEXT_195 = "(";
	protected final String TEXT_196 = "));";
	protected final String TEXT_197 = NL + "\t\t\t\t\t";
	protected final String TEXT_198 = " = null;" + NL + "\t\t\t\t}";
	protected final String TEXT_199 = NL + "\t\t\t\tsuper.endElement(child);";
	protected final String TEXT_200 = NL + "\t\t\t\telse" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tsuper.endElement(child);"
			+ NL + "\t\t\t\t}";
	protected final String TEXT_201 = NL + "\t\t\t}" + NL + "" + NL + "\t\t\t/**" + NL
			+ "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated"
			+ NL + "\t\t\t */";
	protected final String TEXT_202 = NL + "\t\t\t@Override";
	protected final String TEXT_203 = NL + "\t\t\tpublic void create()" + NL + "\t\t\t{" + NL + "\t\t\t\tthe";
	protected final String TEXT_204 = " = ";
	protected final String TEXT_205 = ".create";
	protected final String TEXT_206 = "();" + NL + "\t\t\t}" + NL + "\t\t" + NL + "\t\t\t/**" + NL
			+ "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated"
			+ NL + "\t\t\t */" + NL + "\t\t\tprotected ";
	protected final String TEXT_207 = " pop";
	protected final String TEXT_208 = "()" + NL + "\t\t\t{" + NL + "\t\t\t\tpop();" + NL + "\t\t\t\t";
	protected final String TEXT_209 = " result";
	protected final String TEXT_210 = "Value = the";
	protected final String TEXT_211 = ";" + NL + "\t\t\t\tthe";
	protected final String TEXT_212 = " = null;" + NL + "\t\t\t\treturn result";
	protected final String TEXT_213 = "Value;" + NL + "\t\t\t}" + NL + "\t\t" + NL + "\t\t}" + NL;
	protected final String TEXT_214 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL
			+ "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic ";
	protected final String TEXT_215 = " push";
	protected final String TEXT_216 = "(";
	protected final String TEXT_217 = " previous, Attributes attributes)" + NL + "\t\t{" + NL + "\t\t\t ";
	protected final String TEXT_218 = " result";
	protected final String TEXT_219 = " = ";
	protected final String TEXT_220 = " == null ? new ";
	protected final String TEXT_221 = "() : ";
	protected final String TEXT_222 = ";" + NL + "\t\t\t ";
	protected final String TEXT_223 = " = null;" + NL + "\t\t\t result";
	protected final String TEXT_224 = ".pushOnto(previous);" + NL + "\t\t\t result";
	protected final String TEXT_225 = ".handleAttributes(attributes);" + NL + "\t\t\t return result";
	protected final String TEXT_226 = ";" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL
			+ "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL
			+ "\t\t */" + NL + "\t\tpublic ";
	protected final String TEXT_227 = " pop";
	protected final String TEXT_228 = "(";
	protected final String TEXT_229 = " ";
	protected final String TEXT_230 = ")" + NL + "\t\t{";
	protected final String TEXT_231 = NL + "\t\t\t";
	protected final String TEXT_232 = " result";
	protected final String TEXT_233 = "Value = ";
	protected final String TEXT_234 = ".create";
	protected final String TEXT_235 = "(";
	protected final String TEXT_236 = ".popValue());";
	protected final String TEXT_237 = NL + "\t\t\t";
	protected final String TEXT_238 = " result";
	protected final String TEXT_239 = "Value = ((";
	protected final String TEXT_240 = ")";
	protected final String TEXT_241 = ".createFromString(";
	protected final String TEXT_242 = ", ";
	protected final String TEXT_243 = ".popValue())).";
	protected final String TEXT_244 = "();";
	protected final String TEXT_245 = NL + "\t\t\t";
	protected final String TEXT_246 = " result";
	protected final String TEXT_247 = "Value = (";
	protected final String TEXT_248 = ")";
	protected final String TEXT_249 = ".createFromString(";
	protected final String TEXT_250 = ", ";
	protected final String TEXT_251 = ".popValue());";
	protected final String TEXT_252 = NL + "\t\t\tthis.";
	protected final String TEXT_253 = " = ";
	protected final String TEXT_254 = ";" + NL + "\t\t\treturn result";
	protected final String TEXT_255 = "Value;" + NL + "\t\t}" + NL;
	protected final String TEXT_256 = NL + "\t}" + NL;
	protected final String TEXT_257 = NL + "} //";
	protected final String TEXT_258 = NL;
	protected final String TEXT_259 = NL;

	public ResourceClass() {
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

			this.parameter = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) parameterParameter;

			if (preCondition(ctx)) {
				ctx.setNode(new Node.Container(currentNode, getClass()));
				orchestration(ctx);
			}

		}
		ctx.setNode(currentNode);
		if (ctx.useReporter()) {
			ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
		}

		stringBuffer.append(TEXT_258);
		stringBuffer.append(TEXT_259);
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
		 * Copyright (c) 2002-2006 IBM Corporation and others.
		 * All rights reserved.   This program and the accompanying materials
		 * terms of the Eclipse Public License 2.0 which is available at
		 * http://www.eclipse.org/legal/epl-2.0
		 * 
		 * SPDX-License-Identifier: EPL-2.0
		 *
		 * Contributors:
		 *   IBM - Initial API and implementation
		 */

		GenPackage genPackage = (GenPackage) argument;
		GenModel genModel = genPackage.getGenModel();
		ExtendedMetaData extendedMetaData = genModel.getExtendedMetaData();
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
		stringBuffer.append(genPackage.getUtilitiesPackageName());
		stringBuffer.append(TEXT_3);
		genModel.getImportedName("org.eclipse.emf.common.util.URI");
		genModel.markImportLocation(stringBuffer);
		stringBuffer.append(TEXT_4);
		stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
		stringBuffer.append(TEXT_5);
		stringBuffer.append(genPackage.getResourceClassName());
		stringBuffer.append(TEXT_6);
		stringBuffer.append(genPackage.getImportedResourceBaseClassName());
		stringBuffer.append(TEXT_7);
		if (genModel.hasCopyrightField()) {
			stringBuffer.append(TEXT_8);
			stringBuffer.append(genModel.getImportedName("java.lang.String"));
			stringBuffer.append(TEXT_9);
			stringBuffer.append(genModel.getCopyrightFieldLiteral());
			stringBuffer.append(TEXT_10);
			stringBuffer.append(genModel.getNonNLS());
			stringBuffer.append(TEXT_11);
		}
		if (genPackage.getResource() == GenResourceKind.XML_LITERAL
				|| genPackage.getResource() == GenResourceKind.XMI_LITERAL) {
			stringBuffer.append(TEXT_12);
			stringBuffer.append(genModel.getImportedName("java.util.List"));
			stringBuffer.append(TEXT_13);
			stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
			stringBuffer.append(TEXT_14);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLParserPool"));
			stringBuffer.append(TEXT_15);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl"));
			stringBuffer.append(TEXT_16);
			stringBuffer.append(genModel.getImportedName("java.util.Map"));
			stringBuffer.append(TEXT_17);
			stringBuffer.append(genModel.getImportedName("java.util.HashMap"));
			stringBuffer.append(TEXT_18);
		}
		stringBuffer.append(TEXT_19);
		stringBuffer.append(genPackage.getResourceClassName());
		stringBuffer.append(TEXT_20);
		if (genPackage.getResource() == GenResourceKind.XML_LITERAL
				|| genPackage.getResource() == GenResourceKind.XMI_LITERAL) {
			if (genPackage.isExtensibleProviderFactory() || genPackage.isChildCreationExtenders()) {
				stringBuffer.append(TEXT_21);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
				stringBuffer.append(TEXT_22);
				stringBuffer.append(
						genModel.getImportedName("org.polarsys.capella.common.data.modellingcore.ModelElement"));
				stringBuffer.append(TEXT_23);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EAttribute"));
				stringBuffer.append(TEXT_24);
				stringBuffer.append(genModel.getImportedName("java.lang.String"));
				stringBuffer.append(TEXT_25);
				stringBuffer.append(genModel.getImportedName("java.lang.String"));
				stringBuffer.append(TEXT_26);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_27);
				}
				stringBuffer.append(TEXT_28);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLSave"));
				stringBuffer.append(TEXT_29);
				if (genPackage.getResource() == GenResourceKind.XML_LITERAL) {
					stringBuffer.append(TEXT_30);
					stringBuffer.append(genModel
							.getImportedName("org.polarsys.capella.common.data.core.gen.xmi.impl.CapellaXMLSaveImpl"));
					stringBuffer.append(TEXT_31);
				} else {
					stringBuffer.append(TEXT_32);
					stringBuffer.append(genModel
							.getImportedName("org.polarsys.capella.common.data.core.gen.xmi.impl.CapellaXMLSaveImpl"));
					stringBuffer.append(TEXT_33);
				}
				stringBuffer.append(TEXT_34);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_35);
				}
				stringBuffer.append(TEXT_36);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLHelper"));
				stringBuffer.append(TEXT_37);
				if (genPackage.getResource() == GenResourceKind.XML_LITERAL) {
					stringBuffer.append(TEXT_38);
					stringBuffer
							.append(genModel.getImportedName("org.polarsys.kitalpha.emde.xmi.XMLExtensionHelperImpl"));
					stringBuffer.append(TEXT_39);
				} else {
					stringBuffer.append(TEXT_40);
					stringBuffer
							.append(genModel.getImportedName("org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl"));
					stringBuffer.append(TEXT_41);
				}
				stringBuffer.append(TEXT_42);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_43);
				}
				stringBuffer.append(TEXT_44);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLLoad"));
				stringBuffer.append(TEXT_45);
				if (genPackage.getResource() == GenResourceKind.XML_LITERAL) {
					stringBuffer.append(TEXT_46);
					stringBuffer
							.append(genModel.getImportedName("org.polarsys.kitalpha.emde.xmi.XMLExtensionLoadImpl"));
					stringBuffer.append(TEXT_47);
					stringBuffer
							.append(genModel.getImportedName("org.polarsys.kitalpha.emde.xmi.XMLExtensionHelperImpl"));
					stringBuffer.append(TEXT_48);
				} else {
					stringBuffer.append(TEXT_49);
					stringBuffer
							.append(genModel.getImportedName("org.polarsys.kitalpha.emde.xmi.XMIExtensionLoadImpl"));
					stringBuffer.append(TEXT_50);
					stringBuffer
							.append(genModel.getImportedName("org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl"));
					stringBuffer.append(TEXT_51);
				}
				stringBuffer.append(TEXT_52);
			}
			if (ExtensionHelper.isUseIDAttributes(genPackage) == false) {
				stringBuffer.append(TEXT_53);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_54);
				}
				stringBuffer.append(TEXT_55);
			}
			if (ExtensionHelper.isUseUUIDS(genPackage)) {
				stringBuffer.append(TEXT_56);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_57);
				}
				stringBuffer.append(TEXT_58);
			}
			stringBuffer.append(TEXT_59);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_60);
			}
			stringBuffer.append(TEXT_61);
			if (ExtensionHelper.isTrackResourceModification(genPackage)) {
				stringBuffer.append(TEXT_62);
			}
			stringBuffer.append(TEXT_63);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_64);
			}
			stringBuffer.append(TEXT_65);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
			stringBuffer.append(TEXT_66);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_67);
			}
			stringBuffer.append(TEXT_68);
			stringBuffer.append(genModel.getImportedName("java.util.Map"));
			stringBuffer.append(TEXT_69);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_70);
			}
			stringBuffer.append(TEXT_71);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_72);
			}
			stringBuffer.append(TEXT_73);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
			stringBuffer.append(TEXT_74);
		}
		if (genPackage.isDataTypeConverters() && (genPackage.hasDocumentRoot()
				|| org.eclipse.emf.ecore.xml.type.XMLTypePackage.eNS_URI.equals(genPackage.getNSURI()))) {
			boolean isXMLTypePackage = org.eclipse.emf.ecore.xml.type.XMLTypePackage.eNS_URI
					.equals(genPackage.getNSURI());
			final String _Map = genModel.useGenerics() ? "Map<?, ?>" : "Map";
			final String _MapStringBoolean = genModel.useGenerics() ? "Map<String, Boolean>" : "Map";
			final String _MapStringWildcard = genModel.useGenerics() ? "Map<String, ?>" : "Map";
			if (!isXMLTypePackage) {
				genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource");
				genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLParserPool");
				genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl");
				genModel.getImportedName("java.io.InputStream");
				genModel.getImportedName("java.io.IOException");
				genModel.getImportedName("org.eclipse.emf.ecore.EObject");
				genModel.getImportedName("java.util.Collections");
				genModel.getImportedName("java.util.HashMap");
				genModel.getImportedName("java.util.Map");
				genModel.getImportedName("org.xml.sax.InputSource");
				genModel.getImportedName("javax.xml.parsers.SAXParser");
			}
			genModel.getImportedName("org.xml.sax.Attributes");
			genModel.getImportedName("org.xml.sax.SAXException");
			String _StackFrame = genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeResourceImpl")
					+ ".StackFrame";
			String _DataFrame = genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeResourceImpl")
					+ ".DataFrame";
			if (!isXMLTypePackage) {
				stringBuffer.append(TEXT_75);
				stringBuffer.append(genModel.getNonNLS());
				stringBuffer.append(TEXT_76);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_77);
				}
				stringBuffer.append(TEXT_78);
				stringBuffer.append(_Map);
				stringBuffer.append(TEXT_79);
				stringBuffer.append(_MapStringBoolean);
				stringBuffer.append(TEXT_80);
				stringBuffer.append(_MapStringWildcard);
				stringBuffer.append(TEXT_81);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_82);
				}
				stringBuffer.append(TEXT_83);
				stringBuffer.append(_Map);
				stringBuffer.append(TEXT_84);
				stringBuffer.append(_MapStringBoolean);
				stringBuffer.append(TEXT_85);
				stringBuffer.append(_MapStringWildcard);
				stringBuffer.append(TEXT_86);
				stringBuffer.append(_MapStringBoolean);
				stringBuffer.append(TEXT_87);
				stringBuffer.append(_MapStringWildcard);
				stringBuffer.append(TEXT_88);
				stringBuffer.append(_MapStringBoolean);
				stringBuffer.append(TEXT_89);
				stringBuffer.append(_MapStringBoolean.replaceAll("Map", "HashMap"));
				stringBuffer.append(TEXT_90);
				stringBuffer.append(genModel.getNonNLS());
				stringBuffer.append(TEXT_91);
				if (genModel.useGenerics()) {
					stringBuffer.append(TEXT_92);
				} else {
					stringBuffer.append(TEXT_93);
				}
				stringBuffer.append(TEXT_94);
				if (genModel.isSuppressEMFTypes()) {
					stringBuffer.append(TEXT_95);
				} else {
					stringBuffer.append(TEXT_96);
				}
				stringBuffer.append(TEXT_97);
			}
			stringBuffer.append(TEXT_98);
			for (GenClass genClass : genPackage.getGenClasses()) {
				stringBuffer.append(TEXT_99);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_100);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_101);
			}
			for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
				stringBuffer.append(TEXT_102);
				stringBuffer.append(_DataFrame);
				stringBuffer.append(TEXT_103);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_104);
			}
			for (GenClass genClass : genPackage.getGenClasses()) {
				List<EStructuralFeature> attributes = extendedMetaData.getAllAttributes(genClass.getEcoreClass());
				List<EStructuralFeature> elements = extendedMetaData.getAllElements(genClass.getEcoreClass());
				stringBuffer.append(TEXT_105);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_106);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_107);
				stringBuffer.append(_StackFrame);
				stringBuffer.append(TEXT_108);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_109);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_110);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_111);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_112);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_113);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_114);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_115);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_116);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_117);
				stringBuffer.append(genClass.getImportedInterfaceName());
				stringBuffer.append(TEXT_118);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_119);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_120);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_121);
				stringBuffer.append(genClass.getImportedInterfaceName());
				stringBuffer.append(TEXT_122);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_123);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_124);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_125);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_126);
				stringBuffer.append(genClass.getSafeUncapName());
				stringBuffer.append(TEXT_127);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_128);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_129);
				stringBuffer.append(_StackFrame);
				stringBuffer.append(TEXT_130);
				stringBuffer.append(genClass.getImportedInterfaceName());
				stringBuffer.append(TEXT_131);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_132);
				for (GenFeature genFeature : genClass.getAllGenFeatures()) {
					String name = extendedMetaData.getName(genFeature.getEcoreFeature());
					if ((elements.contains(genFeature.getEcoreFeature())
							|| attributes.contains(genFeature.getEcoreFeature())) && name.indexOf(":") == -1) {
						if (genFeature.isReferenceType()) {
							stringBuffer.append(TEXT_133);
							stringBuffer.append(
									genFeature.getTypeGenClass().getGenPackage().getImportedResourceClassName());
							stringBuffer.append(TEXT_134);
							stringBuffer.append(genFeature.getTypeGenClass().getName());
							stringBuffer.append(TEXT_135);
							stringBuffer.append(genFeature.getSafeName());
							stringBuffer.append(TEXT_136);
						} else {
							stringBuffer.append(TEXT_137);
							stringBuffer.append(_DataFrame);
							stringBuffer.append(TEXT_138);
							stringBuffer.append(genFeature.getSafeName());
							stringBuffer.append(TEXT_139);
						}
					}
				}
				stringBuffer.append(TEXT_140);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_141);
				}
				stringBuffer.append(TEXT_142);
				int count = 0;
				for (GenFeature genFeature : genClass.getAllGenFeatures()) {
					String name = extendedMetaData.getName(genFeature.getEcoreFeature());
					if (attributes.contains(genFeature.getEcoreFeature()) && !genFeature.isDerived()
							&& name.indexOf(":") == -1) {
						String namespace = Literals
								.toStringLiteral(extendedMetaData.getNamespace(genFeature.getEcoreFeature()), genModel);
						if ("null".equals(namespace))
							namespace = "\"\"";
						if (!genFeature.isReferenceType()) {
							GenClassifier genClassifier = genFeature.getTypeGenClassifier();
							if (count++ == 0) {
								stringBuffer.append(TEXT_143);
								stringBuffer.append(namespace);
								stringBuffer.append(TEXT_144);
								stringBuffer.append(name);
								stringBuffer.append(TEXT_145);
							} else {
								stringBuffer.append(TEXT_146);
								stringBuffer.append(namespace);
								stringBuffer.append(TEXT_147);
								stringBuffer.append(name);
								stringBuffer.append(TEXT_148);
							}
							stringBuffer.append(TEXT_149);
							if (genClassifier.getGenPackage().isDataTypeConverters()) {
								stringBuffer.append(TEXT_150);
								stringBuffer.append(genClass.getName());
								stringBuffer.append(TEXT_151);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(TEXT_152);
								stringBuffer
										.append(genClassifier.getGenPackage().getQualifiedFactoryInstanceAccessor());
								stringBuffer.append(TEXT_153);
								stringBuffer.append(genClassifier.getName());
								stringBuffer.append(TEXT_154);
							} else {
								stringBuffer.append(TEXT_155);
								stringBuffer.append(genClass.getName());
								stringBuffer.append(TEXT_156);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(TEXT_157);
								stringBuffer.append(genFeature.getImportedType(null));
								stringBuffer.append(TEXT_158);
								stringBuffer
										.append(genClassifier.getGenPackage().getQualifiedEFactoryInstanceAccessor());
								stringBuffer.append(TEXT_159);
								stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
								stringBuffer.append(TEXT_160);
							}
							stringBuffer.append(TEXT_161);
						}
					}
				}
				if (count == 0) {
					stringBuffer.append(TEXT_162);
				}
				stringBuffer.append(TEXT_163);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_164);
				}
				stringBuffer.append(TEXT_165);
				stringBuffer.append(_StackFrame);
				stringBuffer.append(TEXT_166);
				count = 0;
				for (GenFeature genFeature : genClass.getAllGenFeatures()) {
					String name = extendedMetaData.getName(genFeature.getEcoreFeature());
					if (elements.contains(genFeature.getEcoreFeature()) && name.indexOf(":") == -1) {
						String namespace = Literals
								.toStringLiteral(extendedMetaData.getNamespace(genFeature.getEcoreFeature()), genModel);
						if ("null".equals(namespace))
							namespace = "\"\"";
						stringBuffer.append(TEXT_167);
						if (count++ != 0) {
							stringBuffer.append(TEXT_168);
						}
						stringBuffer.append(TEXT_169);
						stringBuffer.append(name);
						stringBuffer.append(TEXT_170);
						stringBuffer.append(namespace);
						stringBuffer.append(TEXT_171);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_172);
						stringBuffer.append(
								genFeature.getTypeGenClassifier().getGenPackage().getImportedResourceClassName());
						stringBuffer.append(TEXT_173);
						stringBuffer.append(genFeature.getTypeGenClassifier().getName());
						stringBuffer.append(TEXT_174);
					}
				}
				if (count == 0) {
					stringBuffer.append(TEXT_175);
				} else {
					stringBuffer.append(TEXT_176);
				}
				stringBuffer.append(TEXT_177);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_178);
				}
				stringBuffer.append(TEXT_179);
				stringBuffer.append(_StackFrame);
				stringBuffer.append(TEXT_180);
				count = 0;
				for (GenFeature genFeature : genClass.getAllGenFeatures()) {
					String name = extendedMetaData.getName(genFeature.getEcoreFeature());
					if (elements.contains(genFeature.getEcoreFeature()) && name.indexOf(":") == -1) {
						stringBuffer.append(TEXT_181);
						if (count++ != 0) {
							stringBuffer.append(TEXT_182);
						}
						stringBuffer.append(TEXT_183);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_184);
						if (genFeature.isListType()) {
							stringBuffer.append(TEXT_185);
							stringBuffer.append(genClass.getName());
							stringBuffer.append(TEXT_186);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_187);
							stringBuffer.append(
									genFeature.getTypeGenClassifier().getGenPackage().getImportedResourceClassName());
							stringBuffer.append(TEXT_188);
							stringBuffer.append(genFeature.getTypeGenClassifier().getName());
							stringBuffer.append(TEXT_189);
							stringBuffer.append(genFeature.getSafeName());
							stringBuffer.append(TEXT_190);
						} else {
							stringBuffer.append(TEXT_191);
							stringBuffer.append(genClass.getName());
							stringBuffer.append(TEXT_192);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(TEXT_193);
							stringBuffer.append(
									genFeature.getTypeGenClassifier().getGenPackage().getImportedResourceClassName());
							stringBuffer.append(TEXT_194);
							stringBuffer.append(genFeature.getTypeGenClassifier().getName());
							stringBuffer.append(TEXT_195);
							stringBuffer.append(genFeature.getSafeName());
							stringBuffer.append(TEXT_196);
						}
						stringBuffer.append(TEXT_197);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_198);
					}
				}
				if (count == 0) {
					stringBuffer.append(TEXT_199);
				} else {
					stringBuffer.append(TEXT_200);
				}
				stringBuffer.append(TEXT_201);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_202);
				}
				stringBuffer.append(TEXT_203);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_204);
				stringBuffer.append(genPackage.getQualifiedFactoryInstanceAccessor());
				stringBuffer.append(TEXT_205);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_206);
				stringBuffer.append(genClass.getImportedInterfaceName());
				stringBuffer.append(TEXT_207);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_208);
				stringBuffer.append(genClass.getImportedInterfaceName());
				stringBuffer.append(TEXT_209);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_210);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_211);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_212);
				stringBuffer.append(genClass.getName());
				stringBuffer.append(TEXT_213);
			}
			for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
				stringBuffer.append(TEXT_214);
				stringBuffer.append(_DataFrame);
				stringBuffer.append(TEXT_215);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_216);
				stringBuffer.append(_StackFrame);
				stringBuffer.append(TEXT_217);
				stringBuffer.append(_DataFrame);
				stringBuffer.append(TEXT_218);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_219);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_220);
				stringBuffer.append(_DataFrame);
				stringBuffer.append(TEXT_221);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_222);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_223);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_224);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_225);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_226);
				stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
				stringBuffer.append(TEXT_227);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_228);
				stringBuffer.append(_DataFrame);
				stringBuffer.append(TEXT_229);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_230);
				if (genDataType.getGenPackage().isDataTypeConverters()) {
					stringBuffer.append(TEXT_231);
					stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
					stringBuffer.append(TEXT_232);
					stringBuffer.append(genDataType.getName());
					stringBuffer.append(TEXT_233);
					stringBuffer.append(genDataType.getGenPackage().getQualifiedFactoryInstanceAccessor());
					stringBuffer.append(TEXT_234);
					stringBuffer.append(genDataType.getName());
					stringBuffer.append(TEXT_235);
					stringBuffer.append(genDataType.getSafeUncapName());
					stringBuffer.append(TEXT_236);
				} else if (genDataType.isPrimitiveType()
						&& genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
					stringBuffer.append(TEXT_237);
					stringBuffer.append(genDataType.getImportedInstanceClassName());
					stringBuffer.append(TEXT_238);
					stringBuffer.append(genDataType.getName());
					stringBuffer.append(TEXT_239);
					stringBuffer.append(genDataType.getObjectInstanceClassName());
					stringBuffer.append(TEXT_240);
					stringBuffer.append(genDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
					stringBuffer.append(TEXT_241);
					stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
					stringBuffer.append(TEXT_242);
					stringBuffer.append(genDataType.getSafeUncapName());
					stringBuffer.append(TEXT_243);
					stringBuffer.append(genDataType.getPrimitiveValueFunction());
					stringBuffer.append(TEXT_244);
				} else {
					stringBuffer.append(TEXT_245);
					stringBuffer.append(genDataType.getImportedInstanceClassName());
					stringBuffer.append(TEXT_246);
					stringBuffer.append(genDataType.getName());
					stringBuffer.append(TEXT_247);
					stringBuffer.append(genDataType.getObjectInstanceClassName());
					stringBuffer.append(TEXT_248);
					stringBuffer.append(genDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
					stringBuffer.append(TEXT_249);
					stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
					stringBuffer.append(TEXT_250);
					stringBuffer.append(genDataType.getSafeUncapName());
					stringBuffer.append(TEXT_251);
				}
				stringBuffer.append(TEXT_252);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_253);
				stringBuffer.append(genDataType.getSafeUncapName());
				stringBuffer.append(TEXT_254);
				stringBuffer.append(genDataType.getName());
				stringBuffer.append(TEXT_255);
			}
			stringBuffer.append(TEXT_256);
		}
		stringBuffer.append(TEXT_257);
		stringBuffer.append(genPackage.getResourceClassName());
		genModel.emitSortedImports();
		InternalPatternContext ictx = (InternalPatternContext) ctx;
		new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
	}
}
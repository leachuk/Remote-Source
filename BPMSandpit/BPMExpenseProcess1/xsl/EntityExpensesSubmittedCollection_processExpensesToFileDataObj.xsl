<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../xsd/readExpensesFromDb_table.xsd"/>
      <rootElement name="EntityExpensesSubmittedCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/readExpensesFromDb"/>
      <param name="entityExpensesSubmittedCollection" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../businessCatalog/Expenses/ExpensesToFileObject.xsd"/>
      <rootElement name="ExpensesToFileObject" namespace="http://xmlns.oracle.com/bpm/bpmobject/Expenses/ExpensesToFileObject"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.5.0(build 110418.1550.0174) AT [THU MAY 30 19:46:41 EST 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/readExpensesFromDb"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/Expenses/ExpensesToFileObject"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl ns0 xsd bpmo ns1 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <ns1:ExpensesToFileObject>
      <ns1:amount>
        <xsl:value-of select="/ns0:EntityExpensesSubmittedCollection/ns0:EntityExpensesSubmitted/ns0:amount"/>
      </ns1:amount>
      <ns1:user_id>
        <xsl:value-of select="/ns0:EntityExpensesSubmittedCollection/ns0:EntityExpensesSubmitted/ns0:userId"/>
      </ns1:user_id>
      <ns1:incured_date>
        <xsl:value-of select="/ns0:EntityExpensesSubmittedCollection/ns0:EntityExpensesSubmitted/ns0:incuredDate"/>
      </ns1:incured_date>
      <ns1:entry_id>
        <xsl:value-of select="/ns0:EntityExpensesSubmittedCollection/ns0:EntityExpensesSubmitted/ns0:entryId"/>
      </ns1:entry_id>
      <ns1:description>
        <xsl:value-of select="/ns0:EntityExpensesSubmittedCollection/ns0:EntityExpensesSubmitted/ns0:description"/>
      </ns1:description>
    </ns1:ExpensesToFileObject>
  </xsl:template>
</xsl:stylesheet>
